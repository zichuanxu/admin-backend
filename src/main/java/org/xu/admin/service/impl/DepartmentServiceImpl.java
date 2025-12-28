package org.xu.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.xu.admin.entity.Department;
import org.xu.admin.mapper.DepartmentMapper;
import org.xu.admin.service.DepartmentService;

import java.util.List;

@Service
public class DepartmentServiceImpl extends ServiceImpl<DepartmentMapper, Department> implements DepartmentService {
    @Override
    public List<Department> getDepartments() {
        return this.list();
    }

    @Override
    public Department getDepartmentById(Long id) {
        return this.getById(id);
    }
}
