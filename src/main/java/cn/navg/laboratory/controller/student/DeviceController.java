package cn.navg.laboratory.controller.student;

import cn.navg.laboratory.mapper.student.DeviceMapper;
import cn.navg.laboratory.pojo.Result;
import cn.navg.laboratory.service.student.DeviceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@Slf4j
@RestController
public class DeviceController {
    @Autowired
    private DeviceService deviceService;
    
    //学生按条件查询设备
    @PostMapping("/student/device")
    public Result queryDevice(@RequestBody HashMap<String, Object> params) {
        log.info("学生按条件查询设备:{}", params);
        return deviceService.queryDevice(params);
    }
}
