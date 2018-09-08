package com.armeng.boot.dao;

import com.armeng.boot.entity.Customer;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public interface CustomerDao {

    List<Customer> getAll(@Param("name") String name);

    Customer getOne(Long id);

}
