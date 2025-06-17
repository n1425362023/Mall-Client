package org.ysu.mall.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.ysu.mall.entity.User;
import org.ysu.mall.mapper.UserMapper;
import org.ysu.mall.service.UserService;

/**
* @author DELL
* @description 针对表【user】的数据库操作Service实现
* @createDate 2025-06-17 09:52:36
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService {

}




