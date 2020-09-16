package com.lk.blog.mapper;

import com.lk.blog.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author likelove
 * @since 2020-09-04
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
