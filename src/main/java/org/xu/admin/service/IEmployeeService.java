package org.xu.admin.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.multipart.MultipartFile;
import org.xu.admin.dto.EmployeeDTO;
import org.xu.admin.entity.Employee;

import java.io.IOException;
import java.util.List;

public interface IEmployeeService extends IService<Employee> {

    IPage<EmployeeDTO> getEmployeesWithDeptPage(Integer pageNum, Integer pageSize);

    List<EmployeeDTO> getAllEmployeesWithDept();

    boolean saveEmployee(Employee employee);

    boolean updateEmployee(Employee employee);

    boolean deleteEmployee(Long id);

    boolean deleteBatch(List<Long> ids);

    Employee selectById(Long id);

    void export(HttpServletResponse response, List<Long> ids) throws IOException;

    void importExcel(MultipartFile file) throws IOException;
}
