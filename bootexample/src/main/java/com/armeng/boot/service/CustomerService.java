package com.armeng.boot.service;

import com.armeng.boot.dao.CustomerDao;
import com.armeng.boot.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private CustomerDao customerDao;

    public List<Customer> queryAll(String name) throws Exception{

        return customerDao.getAll(name);
    }

}
