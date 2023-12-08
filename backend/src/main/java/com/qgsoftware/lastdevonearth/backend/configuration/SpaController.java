package com.qgsoftware.lastdevonearth.backend.configuration;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SpaController {
    @RequestMapping("/**/{path:[^\\.]*}")
    public String forward() {
        return "forward:/app.component.html";
    }
}
