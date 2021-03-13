package com.wilsonovo.springboot.dao;

import com.wilsonovo.springboot.pojo.Department;
import com.wilsonovo.springboot.pojo.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Repository
public class EmployeeDao {

    private  static Map<Integer, Employee> employees = null;

    @Autowired
    private DepartmentDao departmentDao;
    static{
        employees = new HashMap<Integer, Employee>();
        employees.put(1001,new Employee(1001,"Staff A","employee@gmail.com",1,new Department(101,"Department A")));
        employees.put(1002,new Employee(1002,"Staff B","employee@gmail.com",0,new Department(102,"Department B")));
        employees.put(1003,new Employee(1003,"Staff C","employee@gmail.com",1,new Department(103,"Department C")));
        employees.put(1004,new Employee(1004,"Staff D","employee@gmail.com",0,new Department(104,"Department D")));
        employees.put(1005,new Employee(1005,"Staff E","employee@gmail.com",1,new Department(105,"Department E")));
    }

    private static Integer initId = 1006;

    public void add(Employee employee){
        if(employee.getId()==null){
            employee.setId(initId++);
        }
        employee.setDepartment(departmentDao.getDepartmentById(employee.getDepartment().getId()));
        employees.put(employee.getId(),employee);
    }

    public Collection<Employee> getAll(){
        return employees.values();
    }

    public Employee getEmployeeById(Integer id){
        return employees.get(id);
    }

    public void delete(Integer id){
        employees.remove(id);
    }

}

