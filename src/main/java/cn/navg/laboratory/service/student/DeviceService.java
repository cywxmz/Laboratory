package cn.navg.laboratory.service.student;

import cn.navg.laboratory.pojo.Equipment;
import cn.navg.laboratory.pojo.Result;

import java.util.HashMap;
import java.util.List;

public interface DeviceService {
    //学生按条件查询设备
    public Result queryDevice(HashMap<String, Object> params);
    
}
