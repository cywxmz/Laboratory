package cn.navg.laboratory.service.common;

import cn.navg.laboratory.pojo.Result;

import java.util.HashMap;

public interface CheckTokenService {
    //检查token是否过期
    public Result checkTokenExpiration(String token);
}
