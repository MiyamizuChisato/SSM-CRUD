package cc.chisato.ssm.controller;

import cc.chisato.ssm.entity.Department;
import cc.chisato.ssm.entity.Message;
import cc.chisato.ssm.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;

    @GetMapping("/depts")
    @ResponseBody
    public Message getDepts() {
        List<Department> depts = departmentService.getAll();
        return Message.success().add("depts", depts);
    }
}
