package org.example.fraud_project.Web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class homeControler {

    @GetMapping({"", "/"})
    public String home() {
        return "index";
    }
}
