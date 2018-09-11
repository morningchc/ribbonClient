package com.example.demo.controller;

import com.example.demo.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;


/**
 * Created by Administrator on 2018/9/10.
 */

@RestController
@RequestMapping("/test")
public class Test {
    public static Logger logger= LoggerFactory.getLogger(Test.class);

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping(value = "/init")
    public String init(){
        return "I'm ribbonClient";
    }
    @GetMapping("/test")
    public String test(){
        logger.info("调用client服务welcome");
        String msg = restTemplate.getForObject("http://client/client/welcome/welcome/chc",String.class);
        return msg;
    }
    @GetMapping("/getTest")
    public String getTest(){
        logger.info("调用client服务getTset,并传递参数");
        Map<String,String> param = new HashMap<>();
        param.put("name","chc");
        param.put("age","18");
        String msg =  restTemplate.getForObject("http://client/client/welcome/getTest?name={name}&age={age}",String.class, param);
        return msg;
    }
    @GetMapping("/postTest")
    public String postTest(){
        User user = new User();
        user.setName("chc");
        user.setAge(18);
        user = restTemplate.postForObject("http://client/client/welcome/postTest",user,User.class);
        return user.getName()+" "+user.getAge();
    }
}
