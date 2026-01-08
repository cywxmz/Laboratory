package cn.navg.laboratory.controller.teacher;

import cn.navg.laboratory.pojo.Result;
import cn.navg.laboratory.service.teacher.HomeInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    @Autowired
    HomeInfoService homeInfoService;
    
    //统计当前周的借用数量信息
    @PostMapping("/teacher/HomeInfo")
    public Result HomeInfo(@RequestParam("userId") String UserID) {
        return homeInfoService.HomeInfo(UserID);
    }
}
