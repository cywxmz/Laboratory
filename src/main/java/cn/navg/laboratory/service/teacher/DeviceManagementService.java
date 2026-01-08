package cn.navg.laboratory.service.teacher;

import cn.navg.laboratory.pojo.*;

import java.util.HashMap;
import java.util.List;

public interface DeviceManagementService {
    public Result getVisualData();
    
    //根据设备特殊查询条件查询设备
    public Result getDeviceListByCondition(HashMap<String, Object> condition);
    
    //教师申请借用设备
    public Result borrowDevice(BorrowRequest borrowRequest);
    
    //按条件查询实验室列表
    public Result getLaboratoryListByCondition(HashMap<String, Object> condition);
    
    //教师预约实验室
    public Result reserveLaboratory(LaboratoryUsage laboratory);
    
    //将预约时段的记录返回给教师
    public Result getLaboratoryReservationsByDate(Integer laboratoryId);
    
    //查询教师的实验室预约记录
    public Result getTeacherReservedLaboratoryList(Integer userId, Integer currentPage, Integer pageSize);
    
    //根据特殊条件查询教师的实验室预约记录
    public Result getTeacherReservedLaboratoryListBySpecialCondition(HashMap<String, Object> condition);
    
    //查询教师的设备借用记录 所有
    public Result getDeviceBorrowRecordByTeacher(Integer userId, Integer currentPage, Integer pageSize);
    
    //根据特殊条件查询教师的设备借用记录
    public Result getTeacherBorrowRecordByCondition(HashMap<String, Object> condition);
    
    //教师取消实验室预约
    public Result cancelReserveLaboratory(Integer laboratoryUsageId);
    
    //教师归还设备
    public Result returnDevice(Integer requestId);
    
    //教师申请维修设备
    public Result maintainDevice(MaintenanceRecord maintainRequest);
    
    //教师查询报修记录 所有
    public Result getMaintenanceRecordByTeacher(Integer userId, Integer currentPage, Integer pageSize);
    
    //教师根据设备名称或者维修状态查询包修记录
    public Result getMaintenanceRecordByDeviceNameOrStatus(HashMap<String, Object> condition);
    
    //教师对设备报废申请
    public Result scrapDevice(ScrapApplication scrapApplication);
}
