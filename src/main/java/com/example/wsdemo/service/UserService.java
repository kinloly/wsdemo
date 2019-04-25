package com.example.wsdemo.service;

import javax.jws.WebMethod;
import javax.jws.WebService;

import com.example.wsdemo.domain.User;


@WebService
public interface UserService {

	@WebMethod
	public User getUser();
}
