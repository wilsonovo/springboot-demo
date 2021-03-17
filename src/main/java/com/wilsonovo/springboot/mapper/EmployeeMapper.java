package com.wilsonovo.springboot.mapper;

import com.wilsonovo.springboot.pojo.Employee;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface EmployeeMapper {

    void add(Map employee);

    void update(Map employee);

    List<Employee> getAll();

    Employee getEmployeeById(Integer id);

    void delete(Integer id);
}
