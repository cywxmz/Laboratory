package cn.navg.laboratory.controller.admin;

import cn.navg.laboratory.pojo.BorrowRequest;
import cn.navg.laboratory.pojo.Result;
import cn.navg.laboratory.service.admin.DeviceBorrowManagementService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@Slf4j
@RestController
public class DeviceBorrowManagementController {
    @Autowired
    private DeviceBorrowManagementService deviceBorrowManagementService;
    
    // 查询所有设备借用记录
    @PostMapping("/admin/queryAllBorrowRecord")
    public Result queryAllBorrowRecord(int currentPage, int pageSize) {
        return deviceBorrowManagementService.getDeviceBorrowRecordList(currentPage, pageSize);
    }
    
    // 按条件查询设备借用记录
    @PostMapping("/admin/queryBorrowRecordByCondition")
    public Result queryBorrowRecordByCondition(@RequestBody HashMap<String, Object> condition) {
        log.info("按条件查询设备借用记录：{}", condition);
        return deviceBorrowManagementService.getDeviceBorrowRecordByCondition(condition);
    }
    
    // 审批设备借用请求
    @PostMapping("/admin/approveBorrow")
    public Result approveBorrow(@RequestBody BorrowRequest borrowRequest) {
        log.info("审批设备借用请求：{}", borrowRequest);
        return deviceBorrowManagementService.approveBorrow(borrowRequest);
    }
}
