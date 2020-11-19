package top.desq.blog.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AjaxController {

    @PostMapping("/ajax/main")
    public String main() {
        return "OK";
    }

}