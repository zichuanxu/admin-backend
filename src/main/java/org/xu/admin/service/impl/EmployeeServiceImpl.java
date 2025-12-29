package org.xu.admin.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.xu.admin.dto.EmployeeDTO;
import org.xu.admin.dto.EmployeeExcelDTO;
import org.xu.admin.entity.Department;
import org.xu.admin.entity.Employee;
import org.xu.admin.mapper.DepartmentMapper;
import org.xu.admin.mapper.EmployeeMapper;
import org.xu.admin.service.IEmployeeService;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements IEmployeeService {

    @Autowired
    private EmployeeMapper employeeMapper;

    @Autowired
    private DepartmentMapper departmentMapper;

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


    /**
     * 优化后的批量导出逻辑
     */
    @Override
    public void export(HttpServletResponse response, List<Long> ids) throws IOException {
        // 1. 设置响应头 (修复编码问题，使用 StandardCharsets)
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setCharacterEncoding("utf-8");
        // 防止文件名中文乱码
        String fileName = URLEncoder.encode("员工信息表", StandardCharsets.UTF_8).replaceAll("\\+", "%20");
        response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");

        // 2. 获取数据源 (DTO中已经包含了 departmentName)
        List<EmployeeDTO> allData = this.getAllEmployeesWithDept();

        // 3. 筛选数据
        List<EmployeeExcelDTO> exportList;
        if (ids != null && !ids.isEmpty()) {
            Set<Long> idSet = new HashSet<>(ids);
            exportList = allData.stream()
                    .filter(item -> idSet.contains(item.getId()))
                    .map(this::convertToExcelDTO) // 抽离转换逻辑
                    .collect(Collectors.toList());
        } else {
            exportList = allData.stream()
                    .map(this::convertToExcelDTO)
                    .collect(Collectors.toList());
        }

        // 4. 写出数据
        EasyExcel.write(response.getOutputStream(), EmployeeExcelDTO.class)
                .sheet("员工列表")
                .doWrite(exportList);
    }

    /**
     * 辅助方法：DTO 转 ExcelDTO
     */
    private EmployeeExcelDTO convertToExcelDTO(EmployeeDTO dto) {
        EmployeeExcelDTO excelDTO = new EmployeeExcelDTO();
        BeanUtils.copyProperties(dto, excelDTO);
        // 确保部门名称被正确设置（虽然 copyProperties 会做，但显式写出更安全）
        excelDTO.setDepartmentName(dto.getDepartmentName());
        return excelDTO;
    }

    /**
     * 修复后的导入逻辑：DTO -> Entity 转换 + 部门名称映射
     */
    @Override
    @Transactional(rollbackFor = Exception.class) // 开启事务，保证原子性
    public void importExcel(MultipartFile file) throws IOException {
        // 1. 读取 Excel 数据到 EmployeeExcelDTO (注意：这里读的是 ExcelDTO，不是 Employee)
        List<EmployeeExcelDTO> excelList = EasyExcel.read(file.getInputStream())
                .head(EmployeeExcelDTO.class)
                .sheet()
                .doReadSync();

        if (excelList.isEmpty()) return;

        // 2. 预加载所有部门数据，构建 Map<部门名称, 部门ID>
        // 避免在循环中查库 (N+1问题)
        List<Department> departments = departmentMapper.selectList(null);
        Map<String, Integer> deptMap = departments.stream()
                .collect(Collectors.toMap(Department::getName, Department::getId, (k1, k2) -> k1)); // 避免重名报错

        // 3. 获取现有工号，用于去重
        Set<String> existNoSet = this.list().stream()
                .map(Employee::getNo)
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());

        // 4. 数据转换与校验 (ExcelDTO -> Entity)
        List<Employee> saveList = new ArrayList<>();

        for (EmployeeExcelDTO dto : excelList) {
            // 4.1 校验工号是否已存在
            if (dto.getNo() == null || existNoSet.contains(dto.getNo())) {
                continue; // 跳过重复或无效工号
            }

            Employee employee = new Employee();
            BeanUtils.copyProperties(dto, employee); // 复制基础字段(name, gender, etc.)

            // 4.2 【关键】处理部门转换：名称 -> ID
            String importDeptName = dto.getDepartmentName();
            if (importDeptName != null && deptMap.containsKey(importDeptName)) {
                employee.setDepartmentId(deptMap.get(importDeptName));
            } else {
                // 策略选择：
                // A. 如果部门不存在，设置为 null (当前策略)
                // B. 或者抛出异常提示用户
                // C. 或者自动创建新部门 (逻辑会变复杂)
                employee.setDepartmentId(null);
            }

            // 4.3 补充默认字段
            employee.setCreatedAt(java.time.LocalDateTime.now());

            saveList.add(employee);
            existNoSet.add(dto.getNo()); // 避免Excel内部重复
        }

        // 5. 批量保存
        if (!saveList.isEmpty()) {
            this.saveBatch(saveList);
        }
    }
}
