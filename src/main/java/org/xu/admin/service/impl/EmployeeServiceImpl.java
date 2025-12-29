package org.xu.admin.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.xu.admin.dto.EmployeeDTO;
import org.xu.admin.entity.Employee;
import org.xu.admin.mapper.EmployeeMapper;
import org.xu.admin.service.IEmployeeService;

import java.util.List;

@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements IEmployeeService {

    @Autowired
    private EmployeeMapper employeeMapper;

    @Override
    public IPage<EmployeeDTO> getEmployeesWithDeptPage(Integer pageNum, Integer pageSize) {
        Page<EmployeeDTO> page = new Page<>(pageNum, pageSize);
        return employeeMapper.selectEmployeePage(page);
    }

    @Override
    public List<EmployeeDTO> getAllEmployeesWithDept() {
        return employeeMapper.selectEmployees();
    }

    @Override
    @Transactional // 涉及写操作，建议开启事务
    public boolean saveEmployee(Employee employee) {
        return this.save(employee);
    }

    @Override
    @Transactional
    public boolean updateEmployee(Employee employee) {
        return this.updateById(employee);
    }

    @Override
    @Transactional
    public boolean deleteEmployee(Long id) {
        return this.removeById(id);
    }

    @Override
    @Transactional
    public boolean deleteBatch(List<Long> ids) {
        return this.removeByIds(ids);
    }

    @Override
    public Employee selectById(Long id) {
        return this.getById(id);
    }

}
