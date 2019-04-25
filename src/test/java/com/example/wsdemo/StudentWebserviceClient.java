package com.example.wsdemo;

import com.example.wsdemo.domain.Student;
import com.example.wsdemo.service.StudentService;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.frontend.ClientProxy;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;
import org.apache.cxf.transport.http.HTTPConduit;
import org.apache.cxf.transports.http.configuration.HTTPClientPolicy;

public class StudentWebserviceClient {
    private static final String USER_NAME = "admin";
    private static final String PASS_WORD = "pass";

    //动态调用
    public static void main(String[] args) throws Exception {
        main1(args);
//        main2(args);
//        main3(args);
    }

    //动态调用
    public static void main1(String[] args) throws Exception {
        JaxWsDynamicClientFactory dcflient = JaxWsDynamicClientFactory.newInstance();
        Client client = dcflient.createClient("http://localhost:8080/service/student?wsdl");
        client.getOutInterceptors().add(new ClientLoginInterceptor(USER_NAME, PASS_WORD));
        Object[] objects = client.invoke("getStudent");
        System.out.println("*******" + objects[0].toString());
    }

    //调用方式二，通过接口协议获取数据类型
    public static void main2(String[] args) throws Exception {
        JaxWsProxyFactoryBean jaxWsProxyFactoryBean = new JaxWsProxyFactoryBean();
        jaxWsProxyFactoryBean.setAddress("http://localhost:8080/service/student?wsdl");
        jaxWsProxyFactoryBean.setServiceClass(StudentService.class);
        jaxWsProxyFactoryBean.getOutInterceptors().add(new ClientLoginInterceptor(USER_NAME, PASS_WORD));
        StudentService studentService = (StudentService) jaxWsProxyFactoryBean.create();
        Student student = studentService.getStudent();
        System.out.println("UserName:" + student.getName());
    }


    //调用方式三，通过接口协议获取数据类型,设置链接超时和响应时间
    public static void main3(String[] args) throws Exception {
        JaxWsProxyFactoryBean jaxWsProxyFactoryBean = new JaxWsProxyFactoryBean();
        jaxWsProxyFactoryBean.setAddress("http://localhost:8080/service/student?wsdl");
        jaxWsProxyFactoryBean.setServiceClass(StudentService.class);
        jaxWsProxyFactoryBean.getOutInterceptors().add(new ClientLoginInterceptor(USER_NAME, PASS_WORD));
        StudentService studentService = (StudentService) jaxWsProxyFactoryBean.create(); // 创建客户端对象
        Client proxy = ClientProxy.getClient(studentService);
        HTTPConduit conduit = (HTTPConduit) proxy.getConduit();
        HTTPClientPolicy policy = new HTTPClientPolicy();
        policy.setConnectionTimeout(1000);
        policy.setReceiveTimeout(1000);
        conduit.setClient(policy);
        Student student = studentService.getStudent();
        System.out.println("UserName:" + student.getName());
    }

}