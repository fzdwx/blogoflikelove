package com.lk.blog.controller;


import com.lk.blog.entity.Blog;
import com.lk.blog.entity.User;
import com.lk.blog.entity.UserInfo;
import com.lk.blog.entity.back.Result;
import com.lk.blog.service.BlogService;
import com.lk.blog.service.UserInfoService;
import com.lk.blog.service.UserService;
import com.lk.blog.utils.JWTUtils;
import com.lk.blog.utils.ResultUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author likelove
 * @since 2020-09-04
 */
@RestController
@RequestMapping ("/blog/user")
@CrossOrigin
@Slf4j
@Api (tags = "用户管理")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserInfoService userInfoService;
    @Autowired
    private BlogService blogService;

    /**
     * 修改用户资料
     *
     * @param userInfo 需要修改资料的用户
     *
     * @return result
     */
    @ApiOperation (value = "修改资料")
    @PutMapping ("/edit")
    public Result updateUserInfo(@RequestBody @ApiParam ("需要修改资料的用户") UserInfo userInfo) {
        log.info("==========UserController====updateUserInfo:userInfo:" + userInfo);
        boolean b = userInfoService.updateById(userInfo);
        return ResultUtils.returnBooleanDataIsNull(b, "修改成功", "修改失败");
    }


    //根据请求头中的token，获取对应的用户信息
    @ApiOperation (value = "修改资料")
    @GetMapping ("/myBlog")
    public Result getMyBlog(@ApiParam ("http请求") HttpServletRequest request) {
        log.info("==========UserController====getMyBlog");
        //获取从token中保存的用户信息
        User clientUser = JWTUtils.getTokenInfo(request);
        //根据用户的uid获取对应的博客信息
        List<Blog> blogList = blogService.getByAuthorId(clientUser);
        HashMap<String, Object> data = ResultUtils.objPutInMap("blogList", blogList);
        //总数量
        int count = blogList.size();
        data.put("blogCount",count);
        return Result.ok().setData(data).setMessage("获取成功");
    }

    /**
     * 数据回显
     *
     * @param request http请求
     * @param status  1，只请求user，2就请求user和userinfo
     *
     * @return Result
     */
    @ApiOperation (value = "数据回显")
    @GetMapping ("/getUserInfo/{status}")
    public Result getUserInfo(@ApiParam ("http请求") HttpServletRequest request,
                              @ApiParam ("1，只请求user，2就请求user和userinfo") @PathVariable Integer status) {
        log.info("==========UserController====getUserInfo:token:" + request.getHeader("token"));
        HashMap<String, Object> data = null;
        try {
            data = new HashMap<>(2);
            //获取从token中保存的用户信息
            User clientUser = JWTUtils.getTokenInfo(request);
            //保存在数据库中对应的用户
            User user = userService.getByUid(clientUser);
            data.put("user", user);
            if (status == 2) {//2就请求user和userinfo
                UserInfo userInfo = userInfoService.getByUid(clientUser);
                data.put("userInfo", userInfo);
            }
            return Result.ok().setData(data).setMessage("获取成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Result.error().setCode(22222).setMessage("获取失败");
    }

    @PostMapping ("/register")
    @ApiOperation (value = "注册用户")
    public Result registerUser(@RequestBody @ApiParam ("需要注册的用户") User user) {
        log.info("==========UserController====registerUser:user:" + user);
        user.setUsername(user.getUid());
        //保存这个用户的信息
        UserInfo userInfo = new UserInfo();
        userInfo.setUid(user.getUid());//当前用户的uid作为用户信息的uid
        userInfo.setNickname(user.getUid());//当前用户的账号作为昵称
        userInfoService.save(userInfo);//保存这个用户的用户信息
        //保存这个用户
        boolean save = userService.save(user);
        return ResultUtils.returnBooleanDataIsNull(save, "注册成功", "注册失败,用户名已经存在");
    }

    /**
     * 用户登录
     *
     * @param user
     *
     * @return
     */
    @PostMapping ("/login")
    @ApiOperation (value = "登录")
    public Result loginUser(@RequestBody @ApiParam ("需要登录的用户") User user) {
        log.info("==========UserController====loginUser:user:" + user);
        String token = null;
        try {
            token = userService.login(user);
        } catch (Exception e) {

        }
        HashMap<String, Object> data = ResultUtils.objPutInMap("token", token);
        return ResultUtils.returnObjectDataIsNull("登录成功", "登录失败，用户名或者密码错误", data);
    }

    @PostMapping ("/check")
    @ApiOperation (value = "解析token")
    public Result checkToken(@RequestBody @ApiParam ("需要解析的token") String token) {
        System.out.println(token);
        boolean b = JWTUtils.checkJwt(token);
        return ResultUtils.returnBooleanDataIsNull(b, "查询成功", "查询失败");
    }
}

