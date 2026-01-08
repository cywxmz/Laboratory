package cn.navg.laboratory.controller.common;

import cn.navg.laboratory.pojo.Result;
import cn.navg.laboratory.service.common.CheckTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
public class CheckTokenController {
    @Autowired
    private CheckTokenService checkTokenService;
    
    //user/verifyToken 验证token是否过期
    @PostMapping("/user/verifyToken")
    public Result verifyToken(@RequestHeader("Authorization") String token) {
        return checkTokenService.checkTokenExpiration(token);
    }
}
