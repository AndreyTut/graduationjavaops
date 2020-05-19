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
        return "<body>\n" +
                "<div align=\"center\" style=\"text-align: center; background-color: yellow\">\n" +
                "    <h1>Welcome to my graduation project :)</h1>\n" +
                "    <p>Look at readme.md for API description</p>\n" +
                "</div>\n" +
                "</body>\n";
    }


}
