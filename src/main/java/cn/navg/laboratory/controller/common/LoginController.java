package cn.navg.laboratory.controller.common;

import cn.navg.laboratory.pojo.Result;
import cn.navg.laboratory.service.common.LoginService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController()
public class LoginController {
    @Autowired
    private LoginService loginService;
    
    @PostMapping("/login")
    //登录接口
    public Result login(@RequestParam("username") String username, @RequestParam("password") String password, @RequestHeader("roleType") String roleType) {
        log.info("登录接口被调用，参数为：username={}, password={}, roleType={}", username, password, roleType);
        return loginService.login(username, password, roleType);
    }
}
