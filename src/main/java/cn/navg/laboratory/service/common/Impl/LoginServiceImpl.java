package cn.navg.laboratory.service.common.Impl;

import cn.navg.laboratory.mapper.common.LoginMapper;
import cn.navg.laboratory.pojo.Result;
import cn.navg.laboratory.service.common.LoginService;
import cn.navg.laboratory.utils.JWTutils;
import org.springframework.beans.factory.annotation.Autowired;

import cn.navg.laboratory.pojo.User;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private LoginMapper loginMapper;
    
    @Override
    public Result login(String username, String password,String roleType) {
        //校验用户名和密码是否为空
        if (username == null || password == null || roleType == null) {
            return Result.error("用户名、密码不能为空");
        }
        //校验用户名和密码是否正确
        User user = loginMapper.login(username, password, roleType);
        if (user == null) {
            return Result.error("用户名或密码错误");
        }
        
        //登录成功以后会返回一个token 调用生成token的方法
        HashMap<String, Object> claims = new HashMap<>();
        claims.put("userId", user.getUserId());
        claims.put("username", user.getUserName());
        claims.put("roleType", user.getRoleType());
        String token = JWTutils.createToken(claims);
        claims.put("token", token);
        return Result.success(claims);
    }
}
