package cc.chisato.ssm.service;

import cc.chisato.ssm.dao.EmployeeMapper;
import cc.chisato.ssm.entity.Employee;
import cc.chisato.ssm.entity.EmployeeExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    EmployeeMapper mapper;

    public List<Employee> getAll() {
        return mapper.selectByExampleWithDept(null);
    }

    public void insertEmp(Employee employee) {
        mapper.insertSelective(employee);
    }

    public boolean checkEmail(String email) {
        EmployeeExample employeeExample = new EmployeeExample();
        EmployeeExample.Criteria criteria = employeeExample.createCriteria();
        criteria.andEmailEqualTo(email);
        long l = mapper.countByExample(employeeExample);
        return l == 0;
    }

    public Employee getEmpById(Integer id) {
        return mapper.selectByPrimaryKey(id);
    }

    public void updateEmp(Employee employee) {
        mapper.updateByPrimaryKeySelective(employee);
    }

    public void deleteEmp(Integer id) {
        mapper.deleteByPrimaryKey(id);
    }

    public void deleteBatch(List<Integer> ids) {
        EmployeeExample employeeExample = new EmployeeExample();
        EmployeeExample.Criteria criteria = employeeExample.createCriteria();
        criteria.andEmpIdIn(ids);
        mapper.deleteByExample(employeeExample);
    }
}
