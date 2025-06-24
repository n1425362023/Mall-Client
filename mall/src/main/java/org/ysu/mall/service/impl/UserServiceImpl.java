package org.ysu.mall.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.ysu.mall.domain.dto.UserDto;
import org.ysu.mall.domain.entity.User;
import org.ysu.mall.enums.ResultEnum;
import org.ysu.mall.exception.BusinessException;
import org.ysu.mall.mapper.UserMapper;
import org.ysu.mall.service.UserService;
import org.ysu.mall.util.Sha256Util;

import java.util.List;

/**
* @author DELL
* @description 针对表【user】的数据库操作Service实现
* @createDate 2025-06-17 09:52:36
*/
@Service
@Transactional(rollbackFor = Exception.class)
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    private final UserMapper userMapper;


    public Boolean register(UserDto userDto){
        //检查用户是否存在
        if(lambdaQuery().eq(User::getUsername, userDto.getUsername()).exists()){
            throw new BusinessException(ResultEnum.USERNAME_CONFLICT);
        }
        try{
            User user = new User()
                    .setUsername(userDto.getUsername())
                    .setPassword(Sha256Util.encrypt(userDto.getPassword()))
                    .setPhone(userDto.getPhone());
            if(!save(user)){
                throw new BusinessException(ResultEnum.USER_ADD_ERROR);
            }
            return true;

        }catch (BusinessException e){
            throw e;
        }catch (Exception e){
            throw new BusinessException(ResultEnum.SYSTEM_ERROR,"用户添加失败");
        }
    }

    public User login(String username, String password){
        if(!lambdaQuery().eq(null != username, User::getUsername, username).exists()){
            throw new BusinessException(ResultEnum.USER_NOT_FOUND);
        }
        try{
           User user = lambdaQuery().eq(User::getUsername, username).one();
            if(!Sha256Util.encrypt(password).equals(user.getPassword())){
                throw new BusinessException(ResultEnum.USER_PASSWORD_ERROR);
            }
            return user;
        }catch (BusinessException e){
            throw e;
        }catch (Exception e){
            throw new BusinessException(ResultEnum.SYSTEM_ERROR,"用户登录失败");
        }
    }

    public User update(UserDto userDto){
        User user = lambdaQuery().eq(User::getUsername, userDto.getUsername()).one();
        if(null == user){
            throw new BusinessException(ResultEnum.USERNAME_CONFLICT);
        }
        try{
            user.setUsername(userDto.getUsername())
                    .setPhone(userDto.getPhone())
                    .setAvatar(userDto.getAvatar());
            if(!updateById(user)){
                throw new BusinessException(ResultEnum.USER_UPDATE_ERROR);
            }
            return user;

        }catch (BusinessException e){
            throw e;
        }catch (Exception e){
            throw new BusinessException(ResultEnum.SYSTEM_ERROR,"用户更新失败");
        }
    }

    public Boolean resetPassword(Integer userId, String password) {
        try{
            User user = userMapper.selectById(userId);
            if (user != null) {
                user.setPassword(Sha256Util.encrypt(password));
                if(!updateById(user)) throw new BusinessException(ResultEnum.USER_PASSWORD_RESET_FAILED);
                return true;
            }else {
                throw new BusinessException(ResultEnum.USER_NOT_FOUND);
            }
        }catch(Exception e){
            throw new BusinessException(ResultEnum.SYSTEM_ERROR, "用户密码重置失败");
        }
    }

    public Boolean delete(Integer userId) {
        try{
            if(!removeById(userId)) throw new BusinessException(ResultEnum.USER_DELETE_ERROR);
            return true;
        }catch(Exception e){
            throw new BusinessException(ResultEnum.SYSTEM_ERROR, "用户删除失败");
        }
    }

    public User getUserById(Integer userId) {
        try{
            return userMapper.selectById(userId);
        }catch(Exception e){
            throw new BusinessException(ResultEnum.SYSTEM_ERROR, "用户查询失败");
        }
    }

    @Override
    public List<User> listAll(String name) {
        try {
            return lambdaQuery()
                    .eq(name != null && !name.isEmpty(), User::getUsername, name)
                    .list();
        } catch (Exception e) {
            log.error("Error fetching users by name: {}", e.getMessage());
            throw new BusinessException(ResultEnum.SYSTEM_ERROR, "根据用户名查询用户失败");
        }
    }

    @Override
    public List<String> listDistinctRoles() {
        try {
            return lambdaQuery()
                    .select(User::getRole)
                    .groupBy(User::getRole)
                    .list()
                    .stream()
                    .map(User::getRole)
                    .toList();
        } catch (Exception e) {
            log.error("Error fetching distinct roles: {}", e.getMessage());
            throw new BusinessException(ResultEnum.SYSTEM_ERROR, "查询角色种类失败");
        }
    }
}
