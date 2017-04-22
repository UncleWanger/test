package org.smart4j.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.smart4j.chapter2.domain.Customer;
import org.smart4j.util.DatabaseHelper;
import org.smart4j.util.PropUtil;

import java.sql.Connection;
import java.sql.Driver;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * Created by JERRY ZHANG on 2017-4-22.
 */
public class CustomerService {
    Logger logger = LoggerFactory.getLogger(CustomerService.class);



    public List<Customer> findCustomerList(String keyWord){
        String sql = " select * from customer";
        List<Customer> customerList = DatabaseHelper.queryEntityList(Customer.class, sql, null);
        return customerList;

    }

    public Customer findCustomer(long id ){
        return null;
    }

    public boolean createCustomer(Map<String,Object> fieldMap){
        return false;
    }

    public boolean deleteCustomer(long id){
        return false;
    }

    public boolean updateCustomer(Map<String,Object> fieldMap){
        return false;
    }


}
