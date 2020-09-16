package com.lk.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lk.blog.entity.User;
import com.lk.blog.entity.UserInfo;
import com.lk.blog.mapper.UserInfoMapper;
import com.lk.blog.service.UserInfoService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author likelove
 * @since 2020-09-04
 */
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements UserInfoService {

    /**
     * 根据user的uid返回响应的userinfo
     *
     * @param user
     *
     * @return userinfo
     */
    @Override
    public UserInfo getByUid(User user) {
        QueryWrapper<UserInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("uid", user.getUid());
        return this.getOne(queryWrapper);
    }
}
