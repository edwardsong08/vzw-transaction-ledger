package com.example.vzw_transaction_ledger.repository;

import com.example.vzw_transaction_ledger.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import java.util.List;

@Mapper
public interface UserMapper {
    @Select("SELECT * FROM user")
    List<User> findAllUsers();
}
