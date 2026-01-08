package cn.navg.laboratory.service.common;

import cn.navg.laboratory.pojo.Result;

import java.util.HashMap;

public interface GetCurrentUserInfoService {
    //获取当前登录用户信息
    public Result getCurrentUserInfo(String token);
}
