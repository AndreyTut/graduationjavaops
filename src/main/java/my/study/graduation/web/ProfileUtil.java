package my.study.graduation.web;

import my.study.graduation.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProfileUtil {

    private UserService service;

    @Autowired
    public ProfileUtil(UserService service) {
        this.service = service;
    }


}
