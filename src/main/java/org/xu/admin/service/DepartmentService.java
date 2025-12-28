package org.xu.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.xu.admin.entity.Department;

import java.util.List;

public interface DepartmentService extends IService<Department> {

    List<Department> getDepartments();

    Department getDepartmentById(Long id);
}
