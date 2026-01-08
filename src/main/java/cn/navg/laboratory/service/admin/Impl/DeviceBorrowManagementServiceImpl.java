package cn.navg.laboratory.service.admin.Impl;

import cn.navg.laboratory.mapper.admin.DeviceBorrowManagementMapper;
import cn.navg.laboratory.pojo.BorrowRequest;
import cn.navg.laboratory.pojo.Page;
import cn.navg.laboratory.pojo.Result;
import cn.navg.laboratory.service.admin.DeviceBorrowManagementService;
import cn.navg.laboratory.utils.GetNowDataTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class DeviceBorrowManagementServiceImpl implements DeviceBorrowManagementService {
    @Autowired
    private DeviceBorrowManagementMapper deviceBorrowManagementMapper;
    
    @Override
    public Result getDeviceBorrowRecordList(int currentPage, int pageSize) {
        // 计算偏移量
        currentPage = (currentPage - 1) * pageSize;
        
        // 查询设备借用记录 总数
        int total = deviceBorrowManagementMapper.getTotalDeviceBorrowRecord();
        Page page = new Page(currentPage, pageSize, total);
        return Result.success(deviceBorrowManagementMapper.getDeviceBorrowRecordList(currentPage, pageSize), page);
    }
    
    // 按条件查询设备借用记录
    @Override
    public Result getDeviceBorrowRecordByCondition(HashMap<String, Object> condition) {
        // 计算偏移量
        int currentPage = (int) condition.get("currentPage");
        int pageSize = (int) condition.get("pageSize");
        currentPage = (currentPage - 1) * pageSize;
        condition.put("currentPage", currentPage);
        condition.put("pageSize", pageSize);
        
        // 查询设备借用记录 总数
        int total = deviceBorrowManagementMapper.getTotalDeviceBorrowRecordByCondition(condition);
        Page page = new Page(currentPage, pageSize, total);
        return Result.success(deviceBorrowManagementMapper.getDeviceBorrowRecordByCondition(condition), page);
    }
    
    // 审批设备借用请求
    @Override
    public Result approveBorrow(BorrowRequest borrowRequest) {
        
        // 检查设备数量是否充足
        String equipmentCount = deviceBorrowManagementMapper.getEquipmentCount(borrowRequest.getEquipmentId());
        if ((equipmentCount.isEmpty() || Integer.parseInt(equipmentCount) <= 0) && borrowRequest.getApprovalStatus() == 1) {
            return Result.error("设备库存数量不足！");
        }
        
        if (borrowRequest.getApprovalStatus() == 2) {
            int i = deviceBorrowManagementMapper.approveBorrow(borrowRequest);
            if (i > 0) {
                return Result.success("审批已拒绝!");
            }
        }
        
        // 更新设备数量,使其数量-1
        int result = deviceBorrowManagementMapper.updateDeviceCountMinusOne(borrowRequest.getEquipmentId());
        if (result <= 0) {
            return Result.error("审批失败，实验室设备数量更新失败");
        }
        borrowRequest.setApprovalTime(GetNowDataTime.formattedTime);
        borrowRequest.setUpdateTime(GetNowDataTime.formattedTime);
        
        // 审批设备借用请求
        result = deviceBorrowManagementMapper.approveBorrow(borrowRequest);
        if (result > 0) {
            return Result.success("审批成功");
        }
        return Result.error("审批失败");
    }
}
