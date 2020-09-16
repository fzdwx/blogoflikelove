package com.lk.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lk.blog.entity.User;


/**
 * <p>
 * 服务类
 * </p>
 *
 * @author likelove
 * @since 2020-09-04
 */
public interface UserService extends IService<User> {
    /**
     * 传递用户生成token
     * @param user
     * @return
     */
    String login(User user);

    User getByUid(User user);
}
