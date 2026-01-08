package cn.navg.laboratory.controller.admin;

import cn.navg.laboratory.pojo.LaboratoryUsage;
import cn.navg.laboratory.pojo.Page;
import cn.navg.laboratory.pojo.Result;
import cn.navg.laboratory.service.admin.LaboratoryReservationManagementService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@Slf4j
@RestController
public class LaboratoryReservationManagementController {
    @Autowired
    private LaboratoryReservationManagementService reservationService;
    
    //查询所有预约记录
    @PostMapping("/admin/queryAllLaboratoryReservation")
    public Result getAllLaboratoryReservationList(@RequestParam("currentPage") Integer currentPage,
                                                  @RequestParam("pageSize") Integer pageSize) {
        log.info("查询所有预约记录，分页信息：{}{}", currentPage, pageSize);
        return reservationService.getAllReservations(currentPage, pageSize);
    }
    
    //审批实验室预约记录
    @PostMapping("/admin/approveLaboratoryReservation")
    public Result approveLaboratoryReservation(@RequestBody LaboratoryUsage laboratoryUsage){
        log.info("审批实验室预约记录：{}", laboratoryUsage);
        return reservationService.approveReservation(laboratoryUsage);
    }
    
    //取消实验室预约记录
    @PostMapping("/admin/cancelLaboratoryReservation")
    public Result cancelLaboratoryReservation(@RequestParam("usageId") Integer usageId){
        log.info("取消实验室预约记录：{}", usageId);
        return reservationService.cancelReservation(usageId);
    }
    
    //根据实验室名称或者状态查询预约记录
    @PostMapping("/admin/queryLaboratoryReservationByCondition")
    public Result getLaboratoryReservationListByStatusOrName(@RequestBody HashMap<String, Object> params){
        log.info("根据实验室名称或者状态查询预约记录：{}", params);
        return reservationService.getLaboratoryReservationListByStatusOrName(params);
    }
}
