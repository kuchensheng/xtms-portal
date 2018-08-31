package com.xtms.application.controller.user;

import com.mermaid.application.api.UserCloudService;
import com.mermaid.application.constant.EnumLoginResult;
import com.mermaid.application.constant.EnumSex;
import com.mermaid.application.constant.EnumUserStatus;
import com.mermaid.application.dto.LoginLogDTO;
import com.mermaid.application.dto.UserInfoDTO;
import com.mermaid.framework.mvc.APIResponse;
import com.mermaid.framework.mvc.QueryResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

/**
 * Desription:
 *
 * @author:Hui CreateDate:2018/8/31 23:39
 * version 1.0
 */
@RestController
@Api(value = "用户信息管理中心",tags = "西塘民宿门户接口")
public class UserController {

    @Autowired
    private UserCloudService userCloudService;

    @ApiOperation(value = "添加用户")
    @RequestMapping(value = "/app/user",method = RequestMethod.POST)
    APIResponse<Integer> createUser(
            @ApiParam(required = true,name = "name",value = "用户名") @RequestParam(value = "name") String name,
            @ApiParam(required = true,name = "password",value = "密码") @RequestParam(value = "password") String password,
            @ApiParam(name = "age",value = "年龄") @RequestParam(required = false,value = "age") String age,
            @ApiParam(required = true,name = "status",value = "角色") @RequestParam(value = "status")EnumUserStatus status,
            @ApiParam(required = true,name = "sex",value = "性别") @RequestParam(value = "sex")EnumSex sex,
            @ApiParam(name = "phone",value = "电话") @RequestParam(required = false,value = "phone") String phone,
            @ApiParam(name = "email",value = "邮箱") @RequestParam(required = false,value = "email") String email,
            @ApiParam(name = "qq",value = "qq") @RequestParam(required = false,value = "qq") String qq,
            @ApiParam(name = "avatarId",value = "用户头像") @RequestParam(required = false,value = "avatarId") Integer avatarId,
            @ApiParam(name = "appId",value = "应用Id") @RequestParam(required = false,value = "appId") String appId
    ){
        return userCloudService.createUser(name,password,age,status,sex,phone,email,qq,avatarId,appId,new Date());
    }

    @ApiOperation(value = "更新用户信息")
    @RequestMapping(value = "/app/user/{userId}",method = RequestMethod.PUT)
    APIResponse<Boolean> updateUserInfo(
            @ApiParam(required = true,name = "userId",value = "用户Id") @PathVariable(value = "userId") Integer userId,
            @ApiParam(required = true,name = "name",value = "用户名") @RequestParam(value = "name") String name,
            @ApiParam(required = true,name = "password",value = "密码") @RequestParam(value = "password") String password,
            @ApiParam(name = "age",value = "年龄") @RequestParam(required = false,value = "age") String age,
            @ApiParam(required = true,name = "status",value = "角色") @RequestParam(value = "status")EnumUserStatus status,
            @ApiParam(required = true,name = "sex",value = "性别") @RequestParam(value = "sex")EnumSex sex,
            @ApiParam(name = "phone",value = "电话") @RequestParam(required = false,value = "phone") String phone,
            @ApiParam(name = "email",value = "邮箱") @RequestParam(required = false,value = "email") String email,
            @ApiParam(name = "qq",value = "qq") @RequestParam(required = false,value = "qq") String qq,
            @ApiParam(name = "avatarId",value = "用户头像") @RequestParam(required = false,value = "avatarId") Integer avatarId,
            @ApiParam(name = "appId",value = "应用Id") @RequestParam(required = false,value = "appId") String appId,
            @ApiParam(name = "updateTime",value = "更新时间") @RequestParam(required = false,value = "updateTime") Date updateTime
    ) {
                return userCloudService.updateUserInfo(userId,name,password,age,status,sex,phone,email,qq,avatarId,appId,new Date());
    }

    @ApiOperation(value = "删除用户信息")
    @RequestMapping(value = "/app/user/{userId}",method = RequestMethod.DELETE)
    APIResponse<Boolean> deleteUserInfo(
            @ApiParam(required = true,name = "userId",value = "用户id") @PathVariable(value = "userId") Integer userId
    ) {
                return userCloudService.deleteUserInfo(userId);
    }

    @ApiOperation(value = "批量删除用户信息")
    @RequestMapping(value = "/app/user/batch",method = RequestMethod.DELETE)
    APIResponse<Boolean> deleteUsers(
            @ApiParam(required = true,name = "userIds",value = "用户Id数组") @RequestParam(value = "userIds") Integer[] userIds
    ) {
                return userCloudService.deleteUsers(userIds);
    }

    @ApiOperation(value = "获取Id用户详细信息")
    @RequestMapping(value = "/app/user/{userId}",method = RequestMethod.GET)
    APIResponse<UserInfoDTO> getUserInfoDTO(
            @ApiParam(required = true,name = "userId",value = "用户Id") @PathVariable(value = "userId") Integer userId
    ) {
                return userCloudService.getUserInfoDTO(userId);
    }

    @ApiOperation(value = "批量获取用户详情")
    @RequestMapping(value = "/app/user/list",method = RequestMethod.GET)
    APIResponse<List<UserInfoDTO>> getUserInfoList(
            @ApiParam(required = true,name = "userIds",value = "用户Id数组") @RequestParam(value = "userIds") Integer[] userIds
    ) {
                return userCloudService.getUserInfoList(userIds);
    }

    @ApiOperation(value = "获取应用下的用户列表")
    @RequestMapping(value = "/app/user/list/{appId}",method = RequestMethod.GET)
    APIResponse<List<UserInfoDTO>> getUserInfoListByAppId(
            @ApiParam(required = true,name = "appId",value = "应用Id") @PathVariable(value = "appId") String appId
    ) {
                return userCloudService.getUserInfoListByAppId(appId);
    }

    @ApiOperation(value = "检查session是否还存在")
    @RequestMapping(value = "/app/user/session/exist",method = RequestMethod.GET)
    APIResponse<Boolean> checkSession() {
                return userCloudService.checkSession();
    }

    @ApiOperation(value = "用户登录")
    @RequestMapping(value = "/app/user/login",method = RequestMethod.POST)
    APIResponse<HttpSession> login(
            @ApiParam(required = true,name = "name",value = "用户名") @RequestParam(value = "name") String name,
            @ApiParam(required = true,name = "password",value = "密码") @RequestParam(value = "password") String password,
            @ApiParam(name = "loinTime",value = "登录时间") @RequestParam(required = false,value = "loginTime") Date loginTime,
            @ApiParam(name = "appId",value = "应用Id") @RequestParam(required = false,value = "appId") String appId
    ) {
                return userCloudService.login(name,password,loginTime,appId);
    }

    @ApiOperation(value = "登出")
    @RequestMapping(value = "/app/user/login",method = RequestMethod.DELETE)
    APIResponse loginOut() {
                return userCloudService.loginOut();
    }

    @ApiOperation(value = "上一次登录信息")
    @RequestMapping(value = "/app/login/last/{userId}",method = RequestMethod.GET)
    APIResponse<LoginLogDTO> getLastLoginInfo(
            @ApiParam(required = true,name = "userId",value = "用户id") @PathVariable(value = "userId") Integer userId,
            @ApiParam(name = "result",value = "状态") @RequestParam(value = "result",required = false,defaultValue = "SUCCESS")EnumLoginResult result
    ) {
        return userCloudService.getLastLoginInfo(userId,result);
    }

    @ApiOperation(value = "获取登陆信息列表")
    @RequestMapping(value = "/app/login/list",method = RequestMethod.GET)
    APIResponse<QueryResult<LoginLogDTO>> getLoginInfoList(
            @ApiParam(name = "userId",value = "用户ID") @RequestParam(required = false,value = "userId") Integer userId,
            @ApiParam(name = "appId",value = "应用Id") @RequestParam(required = false,value = "appId") String appId,
            @ApiParam(name = "startTime",value = "开始时间") @RequestParam(required = false,value = "startTime") Date startTime,
            @ApiParam(name = "endTime",value = "结束时间") @RequestParam(required = false,value = "endTime") Date endTime,
            @ApiParam(name = "pageNum",value = "页码") @RequestParam(required = false,value = "pageNum",defaultValue = "1") Integer pageNum,
            @ApiParam(name = "pageSize",value = "页面大小") @RequestParam(required = false,value = "pageSize",defaultValue = "10") Integer pageSize
    ) {
                return userCloudService.getLoginInfoList(userId,appId,startTime,endTime,pageNum,pageSize);
    }
}
