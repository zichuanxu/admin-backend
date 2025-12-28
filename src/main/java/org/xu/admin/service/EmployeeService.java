package org.xu.admin.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.xu.admin.dto.EmployeeDTO;
import org.xu.admin.entity.Employee;

import java.util.List;

public interface EmployeeService {

    IPage<EmployeeDTO> getEmployeesWithDeptPage(Integer pageNum, Integer pageSize);

    List<EmployeeDTO> getAllEmployeesWithDept();

    boolean saveEmployee(Employee employee);
    boolean updateEmployee(Employee employee);
    boolean deleteEmployee(Long id);
    boolean deleteBatch(List<Long> ids);

    Employee selectById(Long id);
}
