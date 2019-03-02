package com.gm.gant1213.mapper;

import com.gm.gant1213.domain.User;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

@org.apache.ibatis.annotations.Mapper
@Repository
public interface UserMapper extends Mapper <User> {



}
