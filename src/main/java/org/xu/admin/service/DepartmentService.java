package org.xu.admin.service;

import org.xu.admin.entity.Department;

import java.util.List;

public interface DepartmentService {

    List<Department> getDepartments();

    Department getDepartmentById(Long id);
}
