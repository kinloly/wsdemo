package com.example.wsdemo.service.impl;

import javax.jws.WebService;

import org.springframework.stereotype.Service;

import com.example.wsdemo.domain.Student;
import com.example.wsdemo.service.StudentService;

@Service

@WebService(targetNamespace="http://service.wsdemo.example.com/",endpointInterface="com.example.wsdemo.service.StudentService")
public class StudentServiceImpl implements StudentService{

	@Override
	public Student getStudent() {
		return new Student(1, "liuguang", 25);
	}
	
}
