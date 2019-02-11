package com.gm.gant1213.service.impl;

import com.gm.gant1213.domain.User;
import com.gm.gant1213.mapper.UserMapper;
import com.gm.gant1213.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@EnableTransactionManagement
public class UserServiceimpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public Map <String,Object> regist(String username, String password, String email) {
        User u=new User();
        u.setUsername( username );
        List<User> list=userMapper.select( u );
        Map<String,Object> map=new HashMap <>();
        if (list.size()==0){
            u.setEmail( email );
            u.setPassword( password );
            userMapper.insert( u );
            map.put( "flag",true );
            map.put( "user",u );
        }else {
            map.put( "flag",false );
            map.put( "errorMsg","用户名重复");
        }
        return map;
    }

    @Override
    public List<User> login(String username, String password) {
        User user=new User();
        user.setUsername( username );
        user.setPassword( password );
        List<User>list=userMapper.select( user );
        return list;
    }

    @Override
    public List <User> findAll() {
        return userMapper.selectAll();
    }

}
