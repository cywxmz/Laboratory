package cn.navg.laboratory.controller.admin;

import cn.navg.laboratory.pojo.Result;
import cn.navg.laboratory.pojo.ScrapApplication;
import cn.navg.laboratory.service.admin.DeviceScrapApprovalService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@Slf4j
@RestController
public class DeviceScrapApprovalController {
    @Autowired
    private DeviceScrapApprovalService deviceScrapApprovalService;
    
    
    //查询所有 设备报废审批记录
    @PostMapping("/admin/queryAllScrapRecord")
    public Result getAllScrapApplications(@RequestParam("currentPage") int currentPage,
                                          @RequestParam("pageSize") int pageSize) {
        return deviceScrapApprovalService.getAllScrapApplications(currentPage, pageSize);
    }
    
    //根据条件查询 设备报废审批记录
    @PostMapping("/admin/queryScrapRecordByCondition")
    public Result getScrapApplicationsByCondition(@RequestBody HashMap<String, Object> conditions) {
        log.info("根据条件查询 设备报废审批记录: {}", conditions);
        return deviceScrapApprovalService.getScrapApplicationsByCondition(conditions);
    }
    
    // 审批设备报废请求
    @PostMapping("/admin/approveScrap")
    public Result approveScrap(@RequestBody ScrapApplication scrapApplication) {
        log.info("审批设备报废请求: {}", scrapApplication);
        return deviceScrapApprovalService.approveScrap(scrapApplication);
    }
}
