package org.xu.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.xu.admin.dto.EmployeeDTO;
import org.xu.admin.entity.Employee;

import java.util.List;

@Mapper
public interface EmployeeMapper extends BaseMapper<Employee> {
    // 自定义分页关联查询
    IPage<EmployeeDTO> selectEmployeePage(Page<EmployeeDTO> page);

    List<EmployeeDTO> selectEmployees();
}
