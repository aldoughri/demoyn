package com.example.demo.Service;

import com.example.demo.Model.Department;
import com.example.demo.Repository.DepartmentDOA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DepartmentService {
@Autowired
    DepartmentDOA departmentDOA;
    public List<Department> getDepartments() {
        List<Department> departments =new ArrayList<>();
        departmentDOA.findAll().forEach(departmet-> departments.add(departmet));
        return departments;
    }

    public Department getDepid(Long id) {
    return departmentDOA.findById(id).get();
    }

    public void add(Department department) {
    departmentDOA.save(department);
    }

    public void remove(Long id) {
    departmentDOA.deleteById(id);
    }
}
