package cn.navg.laboratory.service.common;

import cn.navg.laboratory.pojo.Result;
import org.springframework.stereotype.Service;

public interface LoginService {
    public Result login(String username, String password,String roleType);
}
