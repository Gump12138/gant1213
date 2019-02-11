package com.gm.gant1213.service;


import com.gm.gant1213.domain.User;

import java.util.List;
import java.util.Map;

public interface UserService {

    Map <String,Object> regist(String username, String password, String email);
    List<User> login(String username, String password);

    List<User> findAll();

}
