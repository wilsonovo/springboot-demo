package com.wilsonovo.springboot.controller;

import com.wilsonovo.springboot.mapper.DepartmentMapper;
import com.wilsonovo.springboot.mapper.EmployeeMapper;
import com.wilsonovo.springboot.pojo.Department;
import com.wilsonovo.springboot.pojo.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.relational.core.sql.In;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.List;

@Controller
public class EmployeeController {

    @Autowired
    EmployeeMapper employeeMapper;

    @Autowired
    DepartmentMapper departmentMapper;

    @RequestMapping("/employees")
    public String list(Model model){
        List<Employee> employees = employeeMapper.getAll();
        model.addAttribute("employees",employees);
        return "employees/list";
    }

    @GetMapping("/addEmployee")
    public String toAddPage(Model model){
        List<Department> departments = departmentMapper.getDepartments();
        model.addAttribute("departments",departments);
        return "employees/add";
    }

    @PostMapping("/addEmployee")
    public String addEmployee(Employee employee){
        HashMap<String, Object> map = new HashMap<>();
        map.put("name",employee.getName());
        map.put("email",employee.getEmail());
        map.put("gender",employee.getGender());
        map.put("birthday",employee.getBirthday());
        map.put("department",employee.getDepartment().getId());
        employeeMapper.add(map);
        return "redirect:/employees";
    }

    @GetMapping("/editEmployee/{id}")
    public String toEditPage(@PathVariable("id")Integer id, Model model){
        List<Department> departments = departmentMapper.getDepartments();
        model.addAttribute("departments",departments);
        Employee employee = employeeMapper.getEmployeeById(id);
        model.addAttribute("employee",employee);
        return "employees/edit";
    }

    @PostMapping("/editEmployee")
    public String editEmployee(Employee employee){
        HashMap<String, Object> map = new HashMap<>();
        map.put("id",employee.getId());
        map.put("name",employee.getName());
        map.put("email",employee.getEmail());
        map.put("gender",employee.getGender());
        map.put("birthday",employee.getBirthday());
        map.put("department",employee.getDepartment().getId());
        employeeMapper.update(map);
        return "redirect:/employees";
    }

    @GetMapping("/deleteEmployee/{id}")
    public String deleteEmployee(@PathVariable("id")Integer id){
        employeeMapper.delete(id);
        return "redirect:/employees";
    }
}
