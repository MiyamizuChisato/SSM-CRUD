package cc.chisato.ssm.service;

import cc.chisato.ssm.dao.DepartmentMapper;
import cc.chisato.ssm.entity.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {
    @Autowired
    private DepartmentMapper mapper;

    public List<Department> getAll() {
        return mapper.selectByExample(null);
    }
}
