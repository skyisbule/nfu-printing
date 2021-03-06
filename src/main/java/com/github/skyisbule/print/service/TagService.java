package com.github.skyisbule.print.service;

import com.github.skyisbule.print.common.ErrorConstant;
import com.github.skyisbule.print.dao.TagDao;
import com.github.skyisbule.print.domain.Tag;
import com.github.skyisbule.print.domain.TagExample;
import com.github.skyisbule.print.domain.User;
import com.github.skyisbule.print.exception.GlobalException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
public class TagService {

    @Autowired
    TagDao tagDao;
    @Autowired
    UserService userService;
    @Autowired
    HttpServletRequest request;

    private void checkTagData(Tag tag) throws GlobalException {
        if (tag.getTagName()==null || tag.getTagName().length()>20)
            throw new GlobalException(ErrorConstant.PARAM_ERROR);
        TagExample e = new TagExample();
        e.createCriteria()
                .andTagNameEqualTo(tag.getTagName());
        boolean saved = tagDao.countByExample(e) > 0;
        if (saved)
            throw new GlobalException(ErrorConstant.TAG_HAD_SAVED);
    }

    public String doInsert(Tag tag) throws GlobalException {
        checkTagData(tag);
        User user;
        try {
            user = userService.getUser(request);
        }catch (Exception e){
            throw new GlobalException(e.getMessage());
        }
        tag.setTid(null);
        tag.setCreateDate(new Date());
        tag.setUsedCount(0);
        tag.setUid(user.getUid());
        tag.setUserName(user.getNickName());
        tagDao.insert(tag);
        return "新建标签成功。";
    }

    public List<Tag> getTagsByPage(Integer page,Integer pageSize,String sort){
        TagExample e = new TagExample();
        if (sort==null || sort.equals("0"))
            e.setOrderByClause(" used_count desc ");
        else
            e.setOrderByClause(" tid desc ");
        if (page == null) page = 0;
        if (pageSize == null) pageSize = 10;
        e.setOffset((long)page*pageSize);
        e.setLimit(pageSize);
        return tagDao.selectByExample(e);
    }

    public String doUpdate(int tid,String newName) throws GlobalException {
        User user;
        try {
            user = userService.getUser(request);
        }catch (Exception e){
            throw new GlobalException(e.getMessage());
        }
        Tag old = tagDao.selectByPrimaryKey(tid);
        if (!user.isAdmin())
            if (!Objects.equals(old.getUid(), user.getUid()))
                throw new GlobalException(ErrorConstant.NO_PERMISSION);
        old.setTagName(newName);
        tagDao.updateByPrimaryKey(old);
        return "修改成功。";
    }

    public String doDelete(int tid)throws GlobalException {
        User user;
        try {
            user = userService.getUser(request);
        }catch (Exception e){
            throw new GlobalException(e.getMessage());
        }
        Tag old = tagDao.selectByPrimaryKey(tid);
        if (!user.isAdmin())
            if (!Objects.equals(old.getUid(), user.getUid()))
                throw new GlobalException(ErrorConstant.NO_PERMISSION);
        tagDao.deleteByPrimaryKey(tid);
        return "删除成功。";
    }

    @Transactional(rollbackFor = Exception.class)
    public String tagCountIns(ArrayList<Integer> ids) throws GlobalException {
        User user;
        try {
            user = userService.getUser(request);
        }catch (Exception e){
            throw new GlobalException(e.getMessage());
        }
        ids.forEach(id->{tagDao.countIns1(id);System.out.println(id);});
        return "success";
    }

}
