package com.nowcoder.community.controller;

import com.nowcoder.community.service.AlphaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/alpha")
public class AlphaController {
    @Autowired
    private AlphaService alphaService;

    @RequestMapping("/data")
    @ResponseBody
    public String getData() {
        return alphaService.find();
    }


    @RequestMapping("/hello")
    @ResponseBody
    public String sayHello() {
        return "Hello Spring Boot";
    }

    @RequestMapping("/http")
//    @ResponseBody
    public void http(HttpServletRequest request, HttpServletResponse response) {
        //get request data
        System.out.println(request.getMethod());
        System.out.println(request.getServletPath());

        Enumeration<String> enumeration = request.getHeaderNames();

        while (enumeration.hasMoreElements()) {
            String name = enumeration.nextElement();
            String value = request.getHeader(name);
            System.out.println(name+": "+value);
        }

        System.out.println(request.getParameter("code"));

        //make response
        response.setContentType("text/html;charset=utf-8");
        try (PrintWriter writer = response.getWriter()){
            writer.write("<h1>牛客网</h1>");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //get request
    //  /students?current=1&limit=20
    @RequestMapping(path = "/students", method = RequestMethod.GET)
    @ResponseBody
    public String getStudents(
            @RequestParam(name = "current", required = false, defaultValue = "1") int current,
            @RequestParam(name = "limit", required = false, defaultValue = "10") int limit){
        System.out.println(current);
        System.out.println(limit);
        return "some students";
    }

    @RequestMapping(path = "/student/{id}", method = RequestMethod.GET)
    @ResponseBody
    public String getStudent(@PathVariable("id") int id) {
        System.out.println(id);
        return "a student";
    }

    // POST request
    @RequestMapping(path = "/student", method = RequestMethod.POST)
    @ResponseBody
    public String saveStudent(String name, int age) {
        System.out.println(name);
        System.out.println(age);
        return "success";
    }

    //response html data
    @RequestMapping(path = "/teacher", method = RequestMethod.GET)
    public ModelAndView getTeacher() {
        ModelAndView mav = new ModelAndView();
        mav.addObject("name", "zhangsan");
        mav.addObject("age", "30");

        mav.setViewName("/demo/view");
        return mav;
    }

    @RequestMapping(path = "/school", method = RequestMethod.GET)
    public String getSchool(Model model) {
        model.addAttribute("name", "pku");
        model.addAttribute("age", 120);
        return "demo/view";
    }

    // respond json
    //java object->json string->js object
    @RequestMapping(path="/emp", method=RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getEmp () {
        Map<String, Object> emp = new HashMap<>();
        emp.put("name", "zhangsan");
        emp.put("age", 30);
        emp.put("salary", 8000);
        return emp;
    }
}
