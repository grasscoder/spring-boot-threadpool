package com.hql.java.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.hql.java.entity.UserEntity;
import com.hql.java.mapper.UserMapper;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserMapper mapper;

    public List<UserEntity> select(int page, int pageSize) {
        return mapper.select(page, pageSize);
    }

    public List<UserEntity> select2(int page, int pageSize) {
        return mapper.select2(page, pageSize);
    }

    public int size() {
        return mapper.size();
    }

    @Transactional
    public void insert(String name, String password) {
        mapper.insert(name, password);
    }


}
