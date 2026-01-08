package cn.navg.laboratory.service.student.Impl;

import cn.navg.laboratory.mapper.student.DeviceMapper;
import cn.navg.laboratory.pojo.Equipment;
import cn.navg.laboratory.pojo.Page;
import cn.navg.laboratory.pojo.Result;
import cn.navg.laboratory.service.student.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class DeviceServiceImpl implements DeviceService {
    @Autowired
    private DeviceMapper deviceMapper;

    //学生按条件查询设备
    @Override
    public Result queryDevice(HashMap<String, Object> params) {
        //当前页
        int currentPage = (Integer) params.get("currentPage");
        //每页条数
        int pageSize = (Integer) params.get("pageSize");
        
        //重新赋值
        params.put("currentPage", (currentPage - 1) * pageSize);
        params.put("pageSize", pageSize);
        
        
        //查询设备总条数
        Integer total = deviceMapper.getTotalDevice(params);
        //分页信息
        Page page = new Page(currentPage, pageSize, total);
        return Result.success(deviceMapper.queryDevice(params), page);
    }
}
