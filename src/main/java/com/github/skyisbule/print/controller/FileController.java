package com.github.skyisbule.print.controller;

import com.github.skyisbule.print.common.BaseHttpResponse;
import com.github.skyisbule.print.domain.DbFile;
import com.github.skyisbule.print.exception.GlobalException;
import com.github.skyisbule.print.service.FileService;
import com.github.skyisbule.print.vo.FileWithUserVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/file",method = {RequestMethod.POST})
@Api(description = "用户上传文件相关的接口")
public class FileController {

    @Autowired
    FileService fileService;

    @ApiOperation("添加一条文件记录,返回这条文件的信息，包括主键。fid、date、uid不用传。")
    @RequestMapping("/add")
    public BaseHttpResponse<DbFile> add(DbFile file) throws GlobalException {
        try{
            return new BaseHttpResponse<>(fileService.add(file));
        }catch (Exception e){
            throw new GlobalException(e.getMessage());
        }
    }

    @ApiOperation("删除一条文件记录")
    @RequestMapping("/delete")
    public BaseHttpResponse<String> delete(@ApiParam("主键id")Integer fid) throws GlobalException {
        try{
            return new BaseHttpResponse<>(fileService.doDelete(fid));
        }catch (Exception e){
            throw new GlobalException(e.getMessage());
        }
    }

    @ApiOperation("根据条件获取文件对象，传什么什么就是查询条件，会触发鉴权。")
    @RequestMapping("/get")
    public BaseHttpResponse<List<FileWithUserVo>> getByParam(@ApiParam("页码数从0开始")    Integer  page,
                                                             @ApiParam("每页返回多少数据")  Integer  pageSize,
                                                             @ApiParam("文件名，模糊搜索")  String   fileName,
                                                             @ApiParam("用户id")          Integer  uid,
                                                             @ApiParam("开始时间，如：2018-1-1 00:00:00") String uploadTimeStart,
                                                             @ApiParam("结束时间，如：2018-1-1 00:00:00") String uploadTimeEnd,
                                                             @ApiParam("是否公开：0否1是") Integer isPublic) throws GlobalException {
        try{
            return new BaseHttpResponse<>(fileService.get(page,pageSize,fileName,uid,uploadTimeStart,uploadTimeEnd,isPublic));
        }catch (Exception e){
            throw new GlobalException(e.getMessage());
        }
    }

    @ApiOperation("修改文件记录，非管理员用户只能修改自己的。")
    @RequestMapping("/update")
    public BaseHttpResponse<String> doUpdate(@ApiParam("主键id")Integer fid,
                                             @ApiParam("文件名")String fileName,
                                             @ApiParam("是否公开")Integer isPublic) throws GlobalException {
        try{
            return new BaseHttpResponse<>(fileService.doUpdate(fid,fileName,isPublic));
        }catch (Exception e){
            throw new GlobalException(e.getMessage());
        }
    }

}
