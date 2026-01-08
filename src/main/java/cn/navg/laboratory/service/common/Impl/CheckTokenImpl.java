package cn.navg.laboratory.service.common.Impl;

import cn.navg.laboratory.pojo.Result;
import cn.navg.laboratory.service.common.CheckTokenService;
import cn.navg.laboratory.utils.JWTutils;
import io.jsonwebtoken.Claims;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class CheckTokenImpl implements CheckTokenService {
    @Override
    public Result checkTokenExpiration(String token) {
        try {
            //截取token中的字符串
            token = token.substring(7);
            Claims claims = JWTutils.ParseToken(token);
            //将token重新添加到claims中
            claims.put("token", token);
            return Result.success(claims);
        } catch (Exception e) {
            return Result.error("token过期");
        }
    }
}
