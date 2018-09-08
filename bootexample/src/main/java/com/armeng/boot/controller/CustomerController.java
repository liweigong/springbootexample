package com.armeng.boot.controller;

import com.armeng.boot.entity.Customer;
import com.armeng.boot.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @RequestMapping("/all")
    public ResponseEntity queryAll(String name){
        try{
            log.info("name={}",name);
            List<Customer> customerList = customerService.queryAll(name);
            return ResponseEntity.ok(customerList);
        }catch (Exception e){
            log.error("查询所有用户失败.",e);
            return ResponseEntity.badRequest().body("查询失败");
        }
    }

    @RequestMapping("/alllike")
    public ResponseEntity queryAllLike(String name){
        try{
            log.info("name={}",name);
            List<Customer> customerList = customerService.queryAll("%"+name+"%");
            return ResponseEntity.ok(customerList);
        }catch (Exception e){
            log.error("查询所有用户失败.",e);
            return ResponseEntity.badRequest().body("查询失败");
        }
    }
}
