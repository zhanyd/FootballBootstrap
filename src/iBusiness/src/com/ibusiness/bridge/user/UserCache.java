package com.ibusiness.bridge.user;
/**
 * 帐号/用户缓存接口
 * 
 * @author JiangBo
 *
 */
public interface UserCache {
    UserDTO findById(String id);

    UserDTO findByUsername(String username, String userRepoRef);

    UserDTO findByRef(String ref, String userRepoRef);

    void updateUser(UserDTO userDto);

    void removeUser(UserDTO userDto);
}
