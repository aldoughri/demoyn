package com.example.demo.Service;

import com.example.demo.Model.Department;
import com.example.demo.Repository.DepartmentDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DepartmentService {
    @Autowired
    DepartmentDAO departmentDAO;

    public List<Department> getDepartments() {
        List<Department> departments = new ArrayList<>();
        departmentDAO.findAll().forEach(departmet -> departments.add(departmet));
        return departments;
    }

    public Department getDepid(Long id) {
        return departmentDAO.findById(id).get();
    }

    public void add(Department department) {
        departmentDAO.save(department);
    }

    public void remove(Long id) {
        departmentDAO.deleteById(id);
    }
}
