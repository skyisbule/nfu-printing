package com.github.skyisbule.print.controller;

import com.github.skyisbule.print.common.BaseHttpResponse;
import com.github.skyisbule.print.domain.Tag;
import com.github.skyisbule.print.exception.GlobalException;
import com.github.skyisbule.print.service.TagService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Api(description = "标签相关的接口")
@RestController
@RequestMapping(value = "/api/tag",method = {RequestMethod.GET,RequestMethod.POST})
public class TagController {

    @Autowired
    TagService tagService;

    @ApiOperation("新建一个tag，有鉴权，需要处在登陆状态才能调用此接口。")
    @RequestMapping("/create")
    public BaseHttpResponse<String> create(Tag tag) throws GlobalException {
        try{
            return new BaseHttpResponse<>(tagService.doInsert(tag));
        }catch (Exception e){
            throw new GlobalException(e.getMessage());
        }
    }

    @ApiOperation("分页获取tag信息，默认按使用次数降序排序。")
    @RequestMapping("/get")
    public BaseHttpResponse<List<Tag>> select(@Param("从0开始") Integer page,
                                         @Param("每页的大小，建议传10") Integer pageSize,
                                         @Param("排序方式，传0按使用次数，传1按时间，不传默认次数") String sort) throws GlobalException {
        try{
            return new BaseHttpResponse<>(tagService.getTagsByPage(page,pageSize,sort));
        }catch (Exception e){
            throw new GlobalException(e.getMessage());
        }
    }

    @ApiOperation("修改tag的名字，只能修改自己的创建的，除非你是管理员。")
    @RequestMapping("/update")
    public BaseHttpResponse<String> update(int tid,String name) throws GlobalException {
        try{
            return new BaseHttpResponse<>(tagService.doUpdate(tid,name));
        }catch (Exception e){
            throw new GlobalException(e.getMessage());
        }
    }

    @ApiOperation("删除一个tag，只能删除自己的创建的，除非你是管理员。")
    @RequestMapping("/delete")
    public BaseHttpResponse<String> delete(int tid) throws GlobalException {
        try{
            return new BaseHttpResponse<>(tagService.doDelete(tid));
        }catch (Exception e){
            throw new GlobalException(e.getMessage());
        }
    }

    @ApiOperation("批量为标签的使用次数+1,请用post请求，如[1,2,3]")
    @RequestMapping(value = "/tag-count-ins",method = RequestMethod.POST)
    public BaseHttpResponse<String> tagCountIns(@RequestBody ArrayList<Integer> ids) throws GlobalException {
        try{
            return new BaseHttpResponse<>(tagService.tagCountIns(ids));
        }catch (Exception e){
            throw new GlobalException(e.getMessage());
        }
    }

}
