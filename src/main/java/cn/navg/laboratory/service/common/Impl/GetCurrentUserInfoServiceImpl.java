package cn.navg.laboratory.service.common.Impl;

import cn.navg.laboratory.mapper.common.GetCurrentUserInfoMapper;
import cn.navg.laboratory.pojo.Result;
import cn.navg.laboratory.service.common.GetCurrentUserInfoService;
import cn.navg.laboratory.utils.JWTutils;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class GetCurrentUserInfoServiceImpl implements GetCurrentUserInfoService {
    @Autowired
    private GetCurrentUserInfoMapper getCurrentUserInfoMapper;
    
    @Override
    public Result getCurrentUserInfo(String token) {
        //截取token
        token = token.substring(7);
        //解析token
        Claims claims;
        try {
            claims = JWTutils.ParseToken(token);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        //从token中获取用户id
        int userId = (int) claims.get("userId");
        //拿用户id查询对应的其他信息
        return Result.success(getCurrentUserInfoMapper.getCurrentUserInfo(userId));
    }
}
