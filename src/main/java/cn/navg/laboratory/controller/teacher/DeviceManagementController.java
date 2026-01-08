package cn.navg.laboratory.controller.teacher;

import cn.navg.laboratory.pojo.*;
import cn.navg.laboratory.service.teacher.DeviceManagementService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@Slf4j
@RestController
public class DeviceManagementController {
    @Autowired
    private DeviceManagementService deviceManagementService;
    
    //可视化查询设备使用情况
    @PostMapping("/teacher/getVisualData")
    public Result getVisualData() {
        return deviceManagementService.getVisualData();
    }
    
    //根据设备特殊查询条件查询设备
    @PostMapping("/teacher/getDeviceBorrowManagementListBySpecialCondition")
    public Result getDeviceListByCondition(@RequestBody HashMap<String, Object> condition) {
        log.info("根据设备特殊查询条件查询设备:{}", condition);
        return deviceManagementService.getDeviceListByCondition(condition);
    }
    
    //教师申请借用设备
    @PostMapping("/teacher/borrowDevice")
    public Result borrowDevice(@RequestBody BorrowRequest borrowRequest) {
        log.info("教师申请借用设备:{}", borrowRequest);
        return deviceManagementService.borrowDevice(borrowRequest);
    }
    
    //按条件查询实验室列表
    @PostMapping("/teacher/getLaboratoryReservationListBySpecialCondition")
    public Result getLaboratoryListByCondition(@RequestBody HashMap<String, Object> condition) {
        log.info("按条件查询实验室列表1111:{}", condition);
        return deviceManagementService.getLaboratoryListByCondition(condition);
    }
    
    //教师预约实验室 /teacher/reserveLaboratory
    @PostMapping("/teacher/reserveLaboratory")
    public Result reserveLaboratory(@RequestBody LaboratoryUsage laboratoryUsage) {
        log.info("教师预约实验室:{}", laboratoryUsage);
        return deviceManagementService.reserveLaboratory(laboratoryUsage);
    }
    
    //将预约时段的记录返回给教师
    @PostMapping("/teacher/getLaboratoryReservationsByDate")
    public Result getLaboratoryReservationsByDate(@RequestParam("laboratoryId") Integer laboratoryId) {
        log.info("将预约时段的记录返回给教师:{}", laboratoryId);
        return deviceManagementService.getLaboratoryReservationsByDate(laboratoryId);
    }
    
    //查询教师的实验室预约记录 /teacher/getLaboratoryReservationList
    @PostMapping("/teacher/getLaboratoryReservationList")
    public Result getTeacherReservedLaboratoryList(@RequestParam("userId") Integer userId,
                                                   @RequestParam("currentPage") Integer currentPage,
                                                   @RequestParam("pageSize") Integer pageSize) {
        log.info("查询教师的实验室预约记录:{}", userId);
        return deviceManagementService.getTeacherReservedLaboratoryList(userId, currentPage, pageSize);
    }
    
    //根据特殊条件查询教师的实验室预约记录
    @PostMapping("/teacher/getMaintenanceRecordByTeacher")
    public Result getTeacherReservedLaboratoryListBySpecialCondition(@RequestBody HashMap<String, Object> condition) {
        return deviceManagementService.getTeacherReservedLaboratoryListBySpecialCondition(condition);
    }
    
    //查询教师的设备借用记录 所有
    @PostMapping("/teacher/getDeviceBorrowRecordByTeacher")
    public Result getDeviceBorrowRecordByTeacher(@RequestParam("userId") Integer userId,
                                                 @RequestParam("currentPage") Integer currentPage,
                                                 @RequestParam("pageSize") Integer pageSize) {
        log.info("查询教师的设备借用记录:{}", userId);
        return deviceManagementService.getDeviceBorrowRecordByTeacher(userId, currentPage, pageSize);
    }
    
    //根据特殊条件查询教师的设备借用记录
    @PostMapping("/teacher/getTeacherBorrowRecordByCondition")
    public Result getTeacherBorrowRecordByCondition(@RequestBody HashMap<String, Object> condition) {
        log.info("根据特殊条件查询教师的设备借用记录:{}", condition);
        return deviceManagementService.getTeacherBorrowRecordByCondition(condition);
    }
    
    //教师取消实验室预约
    @PostMapping("/teacher/cancelReserveLaboratory")
    public Result cancelReserveLaboratory(@RequestParam("laboratoryUsageId") Integer laboratoryUsageId) {
        log.info("教师取消实验室预约:{}", laboratoryUsageId);
        return deviceManagementService.cancelReserveLaboratory(laboratoryUsageId);
    }
    
    //教师归还设备
    @PostMapping("/teacher/returnDeviceByRequestId")
    public Result returnDevice(Integer requestId) {
        log.info("教师归还设备:{}", requestId);
        return deviceManagementService.returnDevice(requestId);
    }
    
    //教师申请维修设备
    @PostMapping("/teacher/maintainDevice")
    public Result maintainDevice(@RequestBody MaintenanceRecord maintainRequest) {
        log.info("教师申请维修设备:{}", maintainRequest);
        return deviceManagementService.maintainDevice(maintainRequest);
    }
    
    //教师查询报修记录
    @PostMapping("/teacher/getMaintenanceRecordTeacher")
    public Result getMaintenanceRecordByTeacher(Integer userId,
                                                Integer currentPage,
                                                Integer pageSize) {
        log.info("教师查询报修记录:{}", userId);
        return deviceManagementService.getMaintenanceRecordByTeacher(userId, currentPage, pageSize);
    }
    
    //教师根据设备名称或者维修状态查询报修记录
    @PostMapping("/teacher/getMaintenanceRecordByDeviceNameOrStatus")
    public Result getMaintenanceRecordByDeviceNameOrStatus(@RequestBody HashMap<String, Object> condition) {
        log.info("教师根据设备名称或者维修状态查询包修记录:{}", condition);
        return deviceManagementService.getMaintenanceRecordByDeviceNameOrStatus(condition);
    }
    
    //教师申请设备报废
    @PostMapping("/teacher/scrapDevice")
    public Result scrapDevice(@RequestBody ScrapApplication scrapApplication) {
        log.info("教师对设备报废申请:{}", scrapApplication);
        return deviceManagementService.scrapDevice(scrapApplication);
    }
}
