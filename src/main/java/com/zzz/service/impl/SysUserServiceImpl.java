package com.zzz.service.impl;

import com.zzz.dao.SysUserDao;
import com.zzz.model.SysUser;
import com.zzz.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    SysUserDao userDao;

    @Override
    public List<SysUser> getUsers(int page, int pageSize) {
        int offset = (page - 1) * pageSize;
        return userDao.getUserWithPagination(offset, pageSize);
    }

    @Override
    public Integer getTotalNum() {
        return userDao.getTotalNum();
    }
}
