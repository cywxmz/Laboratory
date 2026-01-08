package cn.navg.laboratory.mapper.admin;

import cn.navg.laboratory.pojo.BorrowRequest;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

@Mapper
public interface DeviceBorrowManagementMapper {
    //查询设备借用记录 查询所有
    public List<HashMap<String, Object>> getDeviceBorrowRecordList(@Param("currentPage") int currentPage, @Param("pageSize") int pageSize);
    
    // 查询设备借用记录 总数
    public int getTotalDeviceBorrowRecord();
    
    //按条件查询 设备借用记录
    public List<HashMap<String, Object>> getDeviceBorrowRecordByCondition(HashMap<String, Object> condition);
    
    //按条件查询 设备借用记录 总数
    public int getTotalDeviceBorrowRecordByCondition(HashMap<String, Object> condition);
    
    // 审批设备借用请求
    public int approveBorrow(BorrowRequest borrowRequest);
    
    //更新设备数量,使其数量-1
    public int updateDeviceCountMinusOne(int equipmentId);
    
    // 查询设备数量
    public String getEquipmentCount(Integer equipmentId);
}
