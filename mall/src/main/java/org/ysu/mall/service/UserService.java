package org.ysu.mall.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.ysu.mall.domain.dto.UserDto;
import org.ysu.mall.domain.entity.User;

/**
* @author DELL
* @description 针对表【user】的数据库操作Service
* @createDate 2025-06-17 09:52:36
*/
public interface UserService extends IService<User> {
    Boolean register(UserDto userDto);

    User login(String Username, String password);

    User update(UserDto userDto);

    Boolean resetPassword(Integer userId, String password);

    Boolean delete(Integer userId);
}
