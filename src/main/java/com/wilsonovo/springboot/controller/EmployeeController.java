package com.wilsonovo.springboot.controller;

import com.wilsonovo.springboot.dao.DepartmentDao;
import com.wilsonovo.springboot.dao.EmployeeDao;
import com.wilsonovo.springboot.pojo.Department;
import com.wilsonovo.springboot.pojo.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collection;

@Controller
public class EmployeeController {

    @Autowired
    EmployeeDao employeeDao;

    @Autowired
    DepartmentDao departmentDao;

    @RequestMapping("/employees")
    public String list(Model model){
        Collection<Employee> employees = employeeDao.getAll();
        model.addAttribute("employees",employees);
        return "employees/list";
    }

    @GetMapping("/addEmployee")
    public String toAddPage(Model model){
        Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("departments",departments);
        return "employees/add";
    }

    @PostMapping("/addEmployee")
    public String addEmployee(Employee employee){
        employeeDao.add(employee);
        return "redirect:/employees";
    }

    @GetMapping("/editEmployee/{id}")
    public String toEditPage(@PathVariable("id")Integer id, Model model){
        Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("departments",departments);
        Employee employee = employeeDao.getEmployeeById(id);
        model.addAttribute("employee",employee);
        return "employees/edit";
    }

    @PostMapping("/editEmployee")
    public String editEmployee(Employee employee){
        employeeDao.add(employee);
        return "redirect:/employees";
    }
}
