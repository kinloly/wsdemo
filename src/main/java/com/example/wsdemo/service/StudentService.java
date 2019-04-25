package com.example.wsdemo.service;

import javax.jws.WebMethod;
import javax.jws.WebService;

import com.example.wsdemo.domain.Student;

@WebService
public interface StudentService {

	@WebMethod
	public Student getStudent();
}
