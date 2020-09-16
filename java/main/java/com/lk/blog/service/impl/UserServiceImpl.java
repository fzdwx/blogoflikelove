package com.lk.blog.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lk.blog.entity.User;
import com.lk.blog.mapper.UserMapper;
import com.lk.blog.service.UserService;
import com.lk.blog.utils.JWTUtils;
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
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    /**
     * 传递用户生成token
     *
     * @param user
     *
     * @return
     */
    @Override
    public String login(User user) {
        //校验：参数合法
        String uid = user.getUid();
        String password = user.getPassword();
        QueryWrapper<User> query = new QueryWrapper<>();
        query.eq("uid", uid);
        User inUser = baseMapper.selectOne(query);
        if (inUser == null) {
            throw new RuntimeException("用户不存在");
        }
        if (!inUser.getPassword().equals(password)) {
            throw new RuntimeException("密码错误");
        }
        return JWTUtils.genJwtToken(user);
    }

    @Override
    public User getByUid(User user) {
        if (user != null) {
            QueryWrapper<User> query = new QueryWrapper<>();
            query.eq("uid", user.getUid());
            return baseMapper.selectOne(query);
        }
        return null;
    }
}
