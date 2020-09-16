package com.lk.blog.service;

import com.lk.blog.entity.User;
import com.lk.blog.entity.UserInfo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author likelove
 * @since 2020-09-04
 */
public interface UserInfoService extends IService<UserInfo> {
    /**
     * 根据user的uid返回响应的userinfo
     * @return userinfo
     */
    UserInfo getByUid(User user);
}
