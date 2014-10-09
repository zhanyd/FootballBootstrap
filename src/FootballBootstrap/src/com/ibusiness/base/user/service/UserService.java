package com.ibusiness.base.user.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ibusiness.base.user.dao.UserBaseDao;
import com.ibusiness.base.user.dao.UserRepoDao;
import com.ibusiness.base.user.entity.UserBase;
import com.ibusiness.security.api.scope.ScopeHolder;
/**
 * 用户业务类
 * 
 * @author JiangBo
 *
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class UserService {
    private UserBaseDao userBaseDao;
    private UserRepoDao userRepoDao;

    public void insertUser(UserBase userBase, Long userRepoId) {
        // user repo
        userBase.setUserRepo(userRepoDao.get(userRepoId));

        userBase.setScopeId(ScopeHolder.getScopeId());
        userBaseDao.save(userBase);

    }

    public void updateUser(UserBase userBase, Long userRepoId) {
        // user repo
        userBase.setUserRepo(userRepoDao.get(userRepoId));
        userBaseDao.save(userBase);
    }

    public void removeUser(UserBase userBase) {
        userBaseDao.remove(userBase);
    }

    @Resource
    public void setUserBaseDao(UserBaseDao userBaseDao) {
        this.userBaseDao = userBaseDao;
    }

    @Resource
    public void setUserRepoDao(UserRepoDao userRepoDao) {
        this.userRepoDao = userRepoDao;
    }
}
