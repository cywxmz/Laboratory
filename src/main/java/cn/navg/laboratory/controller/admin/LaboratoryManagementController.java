package cn.navg.laboratory.controller.admin;

import cn.navg.laboratory.pojo.Laboratory;
import cn.navg.laboratory.pojo.Result;
import cn.navg.laboratory.service.admin.LaboratoryManagementService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@Slf4j
@RestController
public class LaboratoryManagementController {
    @Autowired
    private LaboratoryManagementService laboratoryManagementService;
    //查询所有的实验室信息
    @PostMapping("/admin/queryAllLaboratory")
    public Result getAllLaboratory(@RequestParam("currentPage") int currentPage,
                                   @RequestParam("pageSize") int pageSize){
        return laboratoryManagementService.getAllLaboratory(currentPage, pageSize);
    }
    
    //按条件查询实验室信息
    @PostMapping("/admin/getLaboratoryListByCondition")
    public Result queryLaboratoryListByCondition(@RequestBody HashMap<String, Object> data){
        log.info("按条件查询实验室信息:{}", data);
        return laboratoryManagementService.queryLaboratoryListByCondition(data);
    }
    
    //查询实验室的设备
    @PostMapping("/admin/getEquipmentListByLaboratoryId")
    public Result getEquipmentListByLaboratoryId(@RequestParam("laboratoryId") int laboratoryId){
        return laboratoryManagementService.getEquipmentListByLaboratoryId(laboratoryId);
    }
    
    //根据实验室ID查询设备总数
    @PostMapping("/admin/getEquipmentTotalCountByLaboratoryId")
    public Result getEquipmentTotalCountByLaboratoryId(@RequestParam("laboratoryId") int laboratoryId){
        return laboratoryManagementService.getEquipmentTotalCountByLaboratoryId(laboratoryId);
    }
    
    //添加实验室
    @PostMapping("/admin/addLaboratory")
    public Result addLaboratory(@RequestBody Laboratory laboratory){
        log.info("添加实验室:{}", laboratory);
        return laboratoryManagementService.addLaboratory(laboratory);
    }
    
    //根据实验室ID更新实验室信息
    @PostMapping("/admin/updateLaboratory")
    public Result updateLaboratory(@RequestBody Laboratory laboratory){
        log.info("根据实验室ID更新实验室信息:{}", laboratory);
        return laboratoryManagementService.updateLaboratory(laboratory);
    }
    
    //根据实验室ID删除实验室
    @PostMapping("/admin/deleteLaboratory")
    public Result deleteLaboratory(@RequestParam("laboratoryId") int laboratoryId){
        log.info("根据实验室ID删除实验室:{}", laboratoryId);
        return laboratoryManagementService.deleteLaboratory(laboratoryId);
    }
}
