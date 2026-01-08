package cn.navg.laboratory.mapper.student;

import cn.navg.laboratory.pojo.Equipment;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;

@Mapper
public interface DeviceMapper {
    //学生按条件查询设备
    public List<Equipment> queryDevice(HashMap<String, Object> params);
    
    //统计总条数
    public Integer getTotalDevice(HashMap<String, Object> params);
}
