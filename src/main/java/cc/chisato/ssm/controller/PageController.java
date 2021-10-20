package cc.chisato.ssm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {
    @GetMapping("/list")
    public String index() {
        return "forward:/emps";
    }

    @GetMapping({"/json/list", "/", "index"})
    public String list() {
        return "list_json";
    }
}
