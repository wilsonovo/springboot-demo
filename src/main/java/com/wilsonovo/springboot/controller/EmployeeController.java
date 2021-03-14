package com.wilsonovo.springboot.controller;

import com.wilsonovo.springboot.dao.EmployeeDao;
import com.wilsonovo.springboot.pojo.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collection;

@Controller
public class EmployeeController {

    @Autowired
    EmployeeDao employeeDao;

    @RequestMapping("/employees")
    public String list(Model model){
        Collection<Employee> employees = employeeDao.getAll();
        model.addAttribute("employees",employees);
        return "employees/list";
    }

}
