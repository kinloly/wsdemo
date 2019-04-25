package com.example.wsdemo.service.impl;

import javax.jws.WebService;

import org.springframework.stereotype.Service;

import com.example.wsdemo.domain.User;
import com.example.wsdemo.service.UserService;

@Service
@WebService(targetNamespace="http://service.wsdemo.example.com/",endpointInterface="com.example.wsdemo.service.UserService")
public class UserServiceImpl implements UserService{

	@Override
	public User getUser() {
		return new User(1, "liuguang", 25);
	}

}
