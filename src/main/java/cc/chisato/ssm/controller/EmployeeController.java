package cc.chisato.ssm.controller;

import cc.chisato.ssm.entity.Employee;
import cc.chisato.ssm.entity.Message;
import cc.chisato.ssm.service.EmployeeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class EmployeeController {
    @Autowired
    EmployeeService service;

    @GetMapping("/emps")
    public String getEmps(@RequestParam(value = "pn", defaultValue = "1") Integer pn, Model model) {
        PageHelper.startPage(pn, 5);
        List<Employee> emps = service.getAll();
        PageInfo page = new PageInfo(emps, 5);
        model.addAttribute("page", page);
        return "list";
    }

    @GetMapping("/jemps")
    @ResponseBody
    public Message getEmpsWithJson(@RequestParam(value = "pn", defaultValue = "1") Integer pn) {
        PageHelper.startPage(pn, 5);
        List<Employee> emps = service.getAll();
        PageInfo page = new PageInfo(emps, 5);
        return Message.success().add("page", page);
    }

    @PostMapping("/emp")
    @ResponseBody
    public Message saveEmp(@Valid Employee employee, BindingResult result) {
        if (result.hasErrors()) {
            List<FieldError> fieldErrors = result.getFieldErrors();
            Map map = new HashMap();
            for (FieldError error : fieldErrors) {
                map.put(error.getField(), error.getDefaultMessage());
            }
            return Message.fail().add("errors", map);
        }
        service.insertEmp(employee);
        return Message.success();
    }

    @GetMapping("/checkEmail")
    @ResponseBody
    public Message checkEmail(@RequestParam("email") String email) {
        if (service.checkEmail(email)) {
            return Message.success();
        } else {
            return Message.fail();
        }
    }

    @GetMapping("/emp/{id}")
    @ResponseBody
    public Message getEmpById(@PathVariable Integer id) {
        Employee emp = service.getEmpById(id);
        if (emp != null) {
            return Message.success().add("emp", emp);
        } else {
            return Message.fail().add("error", "在数据库中没有检索到");
        }
    }

    @PutMapping("/emp/{empId}")
    @ResponseBody
    public Message updateEmp(Employee employee) {
        service.updateEmp(employee);
        return Message.success();
    }

    @DeleteMapping("/emp/{ids}")
    @ResponseBody
    public Message deleteEmpById(@PathVariable String ids) {
        if (!ids.contains("-")) {
            service.deleteEmp(Integer.parseInt(ids));
        } else {
            List<Integer> array_id = new ArrayList<>();
            String[] str_ids = ids.split("-");
            for (String id : str_ids) {
                array_id.add(Integer.parseInt(id));
            }
            service.deleteBatch(array_id);
        }
        return Message.success();
    }
}
