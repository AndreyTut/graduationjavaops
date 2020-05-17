package my.study.graduation.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/")
public class RootController {

    @GetMapping
    @ResponseBody
    public String rootAnswer() {
        return "<h1>Hello from my graduation project! ;)</h1>";
    }


}
