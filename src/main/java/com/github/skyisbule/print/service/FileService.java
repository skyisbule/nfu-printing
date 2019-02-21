package com.github.skyisbule.print.service;

import com.github.skyisbule.print.common.ErrorConstant;
import com.github.skyisbule.print.dao.FileDao;
import com.github.skyisbule.print.dao.UserDao;
import com.github.skyisbule.print.domain.DbFile;
import com.github.skyisbule.print.domain.DbFileExample;
import com.github.skyisbule.print.domain.User;
import com.github.skyisbule.print.domain.UserExample;
import com.github.skyisbule.print.exception.GlobalException;
import com.github.skyisbule.print.vo.FileWithUserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
public class FileService {

    @Autowired
    FileDao fileDao;
    @Autowired
    UserService userService;
    @Autowired
    HttpServletRequest request;
    @Autowired
    UserDao userDao;

    private Integer max = 0;

    public synchronized DbFile add(DbFile file) throws GlobalException {
        User user;
        try {
            user = userService.getUser(request);
        }catch (Exception e){
            throw new GlobalException(e.getMessage());
        }
        file.setUid(user.getUid());
        if (max == 0) max = fileDao.getMaxFid();
        if (max == null) max = 1;
        file.setUploadTime(new Date());
        file.setFid(max);
        max++;
        if (file.getIsPublic() == null)
            file.setIsPublic(0);
        fileDao.insert(file);
        return file;
    }

    public String doDelete(Integer fid) throws GlobalException {
        if (fid == null)
            throw new GlobalException(ErrorConstant.PARAM_ERROR);
        User user;
        try {
            user = userService.getUser(request);
        }catch (Exception e){
            throw new GlobalException(e.getMessage());
        }
        DbFile file = fileDao.selectByPrimaryKey(fid);
        if (!user.isAdmin())
            if (!Objects.equals(file.getUid(), user.getUid())){
                throw new GlobalException(ErrorConstant.DELETE_OTHERS_FILE);
            }
        fileDao.deleteByPrimaryKey(fid);
        return "删除成功";
    }

    /**
     *  如果public是1就不鉴权
     */
    public List<FileWithUserVo> get( Integer page,
                                     Integer pageSize,
                                     String  fileName,
                                     Integer uid,
                                     String  uploadTimeStart,
                                     String  uploadTimeEnd,
                                     Integer isPublic) throws GlobalException, ParseException {
        User user;
        //代表查询的是个人账户下的上传记录
        if (uid != null){
            try {
                user = userService.getUser(request);
                if (!Objects.equals(user.getUid(), uid)){
                    throw new GlobalException(ErrorConstant.NO_PERMISSION);
                }
            }catch (Exception e){
                throw new GlobalException(e.getMessage());
            }
        }
        //走到这里代表查询所有人的公共记录
        if (uid == null){
            isPublic = 1;
        }
        //处理一下时间问题
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date begin = sdf.parse("2018-1-1 00:00:00");
        Date end   = new Date();

        if (uploadTimeStart!=null){
            if (uploadTimeStart.length()>6) {
                begin = sdf.parse(uploadTimeStart + " 00:00:00");
            }
        }

        if (uploadTimeEnd!=null){
            if (uploadTimeEnd.length()>6) {
                end = sdf.parse(uploadTimeEnd + " 00:00:00");
            }
        }

        if (page == null) page=0;
        if (pageSize == 10)pageSize=10;

        DbFileExample e = new DbFileExample();
        e.setOffset((long)page*pageSize);
        e.setLimit(pageSize);
        e.createCriteria()
                .andUidEqualTo(uid)
                .andFileNameLike("%"+fileName+"%")
                .andIsPublicEqualTo(isPublic)
                .andUploadTimeBetween(begin,end);

        ArrayList<FileWithUserVo> result = new ArrayList<>();

        List<DbFile> dbFileList = fileDao.selectByExample(e);
        ArrayList<Integer> uids = new ArrayList<>();
        for (DbFile dbFile : dbFileList) {
            uids.add(dbFile.getUid());
        }

        UserExample ue = new UserExample();
        ue.createCriteria()
                .andUidIn(uids);
        List<User> users = userDao.selectByExample(ue);

        for (int i = 0; i<dbFileList.size();i++) {
            FileWithUserVo vo = new FileWithUserVo();
            vo.buildFile(dbFileList.get(i));
            vo.buildUser(users.get(i));
            result.add(vo);
        }

        return result;

    }

    public String doUpdate(Integer fid,String fileName,Integer isPublic) throws GlobalException {
        User user;
        try {
            user = userService.getUser(request);
        }catch (Exception e){
            throw new GlobalException(e.getMessage());
        }
        if (fid == null) return "文件id不能为空";
        DbFile file = fileDao.selectByPrimaryKey(fid);
        if (!Objects.equals(fid, user.getUid())){
            if (!user.isAdmin())//非管理员只能修改自己的文件记录
                throw new GlobalException(ErrorConstant.NO_PERMISSION);
        }
        if (file == null) return "文件id不存在";
        if (fileName!=null){
            if (fileName.trim().length()!=0)
                file.setFileName(fileName);
        }
        if (isPublic != null)
            file.setIsPublic(isPublic);
        fileDao.updateByPrimaryKey(file);
        return "修改成功";
    }

}
