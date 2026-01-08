package cn.navg.laboratory.service.teacher.Impl;

import cn.navg.laboratory.mapper.teacher.DeviceManagementMapper;
import cn.navg.laboratory.pojo.*;
import cn.navg.laboratory.service.teacher.DeviceManagementService;
import cn.navg.laboratory.utils.GetNowDataTime;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Slf4j
@Service
public class DeviceManagementServiceImpl implements DeviceManagementService {
    @Autowired
    DeviceManagementMapper deviceManagementMapper;
    
    
    @Override
    public Result getVisualData() {
        HashMap<String, Integer> usingDeviceList = new HashMap<>();
        //分别为正在被借出的设备总数、正在维修的设备总数、报废设备总数、所有设备的总数
        usingDeviceList.put("borrowTotal", deviceManagementMapper.getUsingDeviceList());
        usingDeviceList.put("repairingTotal", deviceManagementMapper.getRepairingDeviceCount());
        usingDeviceList.put("scrapedTotal", deviceManagementMapper.getScrapedDeviceCount());
        usingDeviceList.put("deviceTotal", deviceManagementMapper.getAllDeviceCount());
        return Result.success(usingDeviceList);
    }
    
    //根据设备特殊查询条件查询设备
    @Override
    public Result getDeviceListByCondition(HashMap<String, Object> condition) {
        //根据条件查询设备总数 用于分页
        int total = deviceManagementMapper.getDeviceListByConditionCount(condition);
        
        //分页
        int currentPage = (int) condition.get("currentPage");
        int pageSize = (int) condition.get("pageSize");
        condition.put("pageSize", pageSize);
        
        //Page
        Page page = new Page(currentPage, pageSize, total);
        
        
        //根据条件查询设备
        List<HashMap<String, Object>> deviceList = deviceManagementMapper.getDeviceListByCondition(condition);
        return Result.success(deviceList, page);
    }
    
    //教师申请借用设备
    @Override
    public Result borrowDevice(BorrowRequest borrowRequest) {
        
        borrowRequest.setUpdateTime(GetNowDataTime.formattedTime);
        
        //教师申请借用设备
        int i = deviceManagementMapper.borrowDevice(borrowRequest);
        if (i > 0) {
            return Result.success("申请借用设备成功");
        }
        return Result.error("申请借用设备失败");
    }
    
    //按条件查询实验室列表
    @Override
    public Result getLaboratoryListByCondition(HashMap<String, Object> condition) {
        int currentPage = (int) condition.get("currentPage");
        int pageSize = (int) condition.get("pageSize");
        //根据条件查询实验室总数 用于分页
        int total = deviceManagementMapper.getLaboratoryListByConditionCount(condition);
        //分页
        condition.put("currentPage", (currentPage - 1) * pageSize);
        condition.put("pageSize", pageSize);
        Page page = new Page(currentPage, pageSize, total);
        return Result.success(deviceManagementMapper.getLaboratoryListByCondition(condition), page);
    }
    
    //教师预约实验室
    @Override
    public Result reserveLaboratory(LaboratoryUsage laboratoryUsage) {
        laboratoryUsage.setUpdateTime(GetNowDataTime.formattedTime);
        laboratoryUsage.setCreateTime(GetNowDataTime.formattedTime);
        log.info("教师预约实验室：{}", laboratoryUsage);
        
        //查询实验室状态
        Laboratory laboratory = deviceManagementMapper.getLaboratoryStatus(laboratoryUsage.getLaboratoryId());
        //如果实验室关闭
        if (laboratory.getLaboratoryStatus() == 0) {
            return Result.error("实验室已关闭，无法预约");
        }
        
        //根据前端选择的预约时段 查询该时段是否空闲
        //查询该时段是否空闲
        List<LaboratoryUsage> count = deviceManagementMapper.
                getReservedLaboratoryListByTimePeriod
                        (laboratoryUsage.getLaboratoryId(),
                                laboratoryUsage.getUsageStartTime(),
                                laboratoryUsage.getUsageEndTime());
        //如果该时段已被预约
        if (!count.isEmpty()) {
            return Result.error("该时段已被预约，无法预约");
        }
        
        //如果该时段未被预约 则教师预约实验室
        //教师预约实验室
        int i = deviceManagementMapper.reserveLaboratory(laboratoryUsage);
        if (i > 0) {
            return Result.success("预约实验室成功");
        }
        return Result.error("预约实验室失败");
    }
    
    //将预约时段的记录返回给教师
    @Override
    public Result getLaboratoryReservationsByDate(Integer laboratoryId) {
        log.info("将预约时段的记录返回给教师:{}", laboratoryId);
        return Result.success(deviceManagementMapper.getReservedLaboratoryListByLaboratoryId(laboratoryId));
    }
    
    //查询教师的实验室预约记录
    @Override
    public Result getTeacherReservedLaboratoryList(Integer userId, Integer currentPage, Integer pageSize) {
        log.info("查询教师的实验室预约记录:{}", userId);
        currentPage = (currentPage - 1) * pageSize;
        return Result.success(deviceManagementMapper.getTeacherReservedLaboratoryList(userId, currentPage, pageSize));
    }
    
    //根据特殊条件查询教师的实验室预约记录
    @Override
    public Result getTeacherReservedLaboratoryListBySpecialCondition(HashMap<String, Object> condition) {
        int currentPage = (int) condition.get("currentPage");
        int pageSize = (int) condition.get("pageSize");
        //根据条件查询实验室总数 用于分页
        int total = deviceManagementMapper.getTeacherReservedLaboratoryListBySpecialConditionCount(condition);
        //分页
        condition.put("currentPage", (currentPage - 1) * pageSize);
        condition.put("pageSize", pageSize);
        Page page = new Page(currentPage, pageSize, total);
        return Result.success(deviceManagementMapper.getTeacherReservedLaboratoryListBySpecialCondition(condition), page);
    }
    
    //查询教师的设备借用记录 所有
    @Override
    public Result getDeviceBorrowRecordByTeacher(Integer userId, Integer currentPage, Integer pageSize) {
        log.info("查询教师的设备借用记录 所有:{}", userId);
        currentPage = (currentPage - 1) * pageSize;
        //根据特殊条件查询教师的设备借用记录总数 用于分页
        int total = deviceManagementMapper.getDeviceBorrowRecordByTeacherCount(userId);
        Page page = new Page(currentPage, pageSize, total);
        return Result.success(deviceManagementMapper.getDeviceBorrowRecordByTeacher(userId, currentPage, pageSize), page);
    }
    
    //根据特殊条件查询教师的设备借用记录
    @Override
    public Result getTeacherBorrowRecordByCondition(HashMap<String, Object> condition) {
        int currentPage = (int) condition.get("currentPage");
        int pageSize = (int) condition.get("pageSize");
        //根据条件查询设备总数 用于分页
        int total = deviceManagementMapper.getTeacherBorrowRecordByConditionCount(condition);
        //分页
        condition.put("currentPage", (currentPage - 1) * pageSize);
        condition.put("pageSize", pageSize);
        Page page = new Page(currentPage, pageSize, total);
        return Result.success(deviceManagementMapper.getTeacherBorrowRecordByCondition(condition), page);
    }
    
    //教师取消实验室预约
    @Override
    public Result cancelReserveLaboratory(Integer laboratoryUsageId) {
        log.info("教师取消实验室预约:{}", laboratoryUsageId);
        //教师取消实验室预约
        int i = deviceManagementMapper.cancelReserveLaboratory(laboratoryUsageId, GetNowDataTime.formattedTime);
        if (i > 0) {
            return Result.success("取消实验室预约成功");
        }
        return Result.error("取消实验室预约失败");
    }
    
    //教师归还设备
    @Override
    public Result returnDevice(Integer requestId) {
        log.info("教师归还设备:{}", requestId);
        //教师归还设备
        int i = deviceManagementMapper.returnDevice(requestId, GetNowDataTime.formattedTime, GetNowDataTime.formattedTime);
        //根据借用记录id查询设备id
        Integer equipmentId = deviceManagementMapper.queryDeviceIdByBorrowRecordId(requestId, 1);
        //更新设备数量
        int j = deviceManagementMapper.updateDeviceCount(equipmentId);
        if (i > 0 && j > 0) {
            return Result.success("归还设备成功");
        }
        return Result.error("归还设备失败");
    }
    
    //教师申请维修设备
    @Override
    public Result maintainDevice(MaintenanceRecord maintainRequest) {
        log.info("教师申请维修设备:{}", maintainRequest);
        //教师申请维修设备
        maintainRequest.setCreateTime(GetNowDataTime.formattedTime);
        maintainRequest.setUpdateTime(GetNowDataTime.formattedTime);
        int i = deviceManagementMapper.maintainDevice(maintainRequest);
        
        log.info("教师申请维修设备:{}", maintainRequest);
        if (i > 0) {
            return Result.success("申请维修设备成功");
        }
        return Result.error("申请维修设备失败");
    }
    
    //教师查询报修记录 所有
    @Override
    public Result getMaintenanceRecordByTeacher(Integer userId, Integer currentPage, Integer pageSize) {
        log.info("教师查询报修记录 所有:{}", userId);
        
        
        //根据特殊条件查询教师的设备报修记录总数 用于分页
        int total = deviceManagementMapper.getMaintenanceRecordByTeacherCount(userId);
        Page page = new Page(currentPage-1, pageSize, total);
        return Result.success(deviceManagementMapper.getMaintenanceRecordByTeacher(userId, (currentPage - 1) * pageSize, pageSize), page);
    }
    
    //教师根据设备名称或者维修状态查询报修记录
    @Override
    public Result getMaintenanceRecordByDeviceNameOrStatus(HashMap<String, Object> condition) {
        log.info("教师根据设备名称或者维修状态查询包修记录:{}", condition);
        //根据特殊条件查询教师的设备报修记录总数 用于分页
        int total = deviceManagementMapper.getMaintenanceRecordByDeviceNameOrStatusCount(condition);
        
        //分页
        int currentPage = (int) condition.get("currentPage");
        int pageSize = (int) condition.get("pageSize");
        Page page = new Page(currentPage-1, pageSize, total);
        currentPage = (currentPage - 1) * pageSize;
        condition.put("currentPage", currentPage);
        return Result.success(deviceManagementMapper.getMaintenanceRecordByDeviceNameOrStatus(condition), page);
    }
    
    //教师对设备报废申请
    @Override
    public Result scrapDevice(ScrapApplication scrapApplication) {
        log.info("教师对设备报废申请:{}", scrapApplication);
        //教师对设备报废申请
        //设置申请日期
        scrapApplication.setApplyDate(GetNowDataTime.formattedDate);
        scrapApplication.setCreateTime(GetNowDataTime.formattedTime);
        scrapApplication.setUpdateTime(GetNowDataTime.formattedTime);
        int i = deviceManagementMapper.scrapDevice(scrapApplication);
        if (i > 0) {
            return Result.success("申请报废设备成功");
        }
        return Result.error("申请报废设备失败");
    }
}
