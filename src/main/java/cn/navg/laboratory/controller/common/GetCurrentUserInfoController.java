package cn.navg.laboratory.controller.common;

import cn.navg.laboratory.pojo.Result;
import cn.navg.laboratory.service.common.GetCurrentUserInfoService;
import cn.navg.laboratory.utils.JWTutils;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
public class GetCurrentUserInfoController {
    @Autowired
    private GetCurrentUserInfoService getCurrentUserInfoService;
    
    //获取当前登录用户信息
    @GetMapping("/common/getCurrentUserInfo")
    public Result getCurrentUserInfo(HttpServletRequest request){
        return getCurrentUserInfoService.getCurrentUserInfo(request.getHeader("Authorization"));
    }
}
