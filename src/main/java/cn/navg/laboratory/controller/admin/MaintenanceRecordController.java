package cn.navg.laboratory.controller.admin;

import cn.navg.laboratory.pojo.MaintenanceRecord;
import cn.navg.laboratory.pojo.Result;
import cn.navg.laboratory.service.admin.MaintenanceRecordService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@Slf4j
@RestController
public class MaintenanceRecordController {
    @Autowired
    private MaintenanceRecordService maintenanceRecordService;
    
    //查询维修记录 查询所有
    @PostMapping("/admin/queryAllMaintenanceRecord")
    public Result queryRepairRecord(@RequestParam("currentPage") int currentPage, @RequestParam("pageSize") int pageSize) {
        return maintenanceRecordService.queryRepairRecord(currentPage, pageSize);
    }
    
    //按条件查询维修记录
    @PostMapping("/admin/queryMaintenanceRecordByCondition")
    public Result queryMaintenanceRecordByCondition(@RequestBody HashMap<String, Object> condition) {
        log.info("按条件查询维修记录:{}", condition);
        return maintenanceRecordService.queryMaintenanceRecordByCondition(condition);
    }
    
    //更新维修状态/记录
    @PostMapping("/admin/updateMaintenanceStatus")
    public Result updateMaintenanceStatus(@RequestBody HashMap<String, Object> condition) {
        log.info("更新维修状态/记录:{}", condition);
        return maintenanceRecordService.updateMaintenanceStatus(condition);
    }
    
    //审批维修记录
    @PostMapping("/admin/approveMaintenance")
    public Result approveMaintenanceRecord(@RequestBody MaintenanceRecord maintenanceRecord) {
        log.info("审批维修记录:{}", maintenanceRecord);
        return maintenanceRecordService.approveMaintenanceRecord(maintenanceRecord);
    }
}
