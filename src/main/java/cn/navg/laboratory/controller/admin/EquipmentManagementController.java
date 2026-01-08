package cn.navg.laboratory.controller.admin;

import cn.navg.laboratory.pojo.Equipment;
import cn.navg.laboratory.pojo.Result;
import cn.navg.laboratory.service.admin.EquipmentManagementService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@Slf4j
@RestController
public class EquipmentManagementController {
    @Autowired
    private EquipmentManagementService equipmentManagementService;
    
    //查询所有的实验室名称
    @PostMapping("/admin/queryAllLaboratoryName")
    public Result queryAllLaboratoryName() {
        return equipmentManagementService.getALLLaboratoryName();
    }
    
    //查询所有的设备类型
    @PostMapping("/admin/queryAllEquipmentType")
    public Result queryAllEquipmentType(){
        return equipmentManagementService.getAllEquipmentType();
    }
    
    //查询所有的设备
    @PostMapping("/admin/queryAllEquipment")
    public Result queryAllEquipment(@RequestParam("currentPage") int currentPage, @RequestParam("pageSize") int pageSize){
        return equipmentManagementService.queryAllEquipment(currentPage, pageSize);
    }
    
    //根据多个条件模糊查询设备列表
    @PostMapping("/admin/queryEquipmentByCondition")
    public Result searchEquipment(@RequestBody HashMap<String, Object> params){
        log.info("根据多个条件模糊查询设备列表: {}", params);
        return equipmentManagementService.searchEquipment(params);
    }
    
    //更新设备信息
    @PostMapping("/admin/updateDevice")
    public Result updateEquipment(@RequestBody Equipment equipment){
        log.info("更新设备信息: {}", equipment);
        return equipmentManagementService.updateEquipment(equipment);
    }
    
    //添加设备
    @PostMapping("/admin/addDevice")
    public Result addEquipment(@RequestBody Equipment equipment){
        return equipmentManagementService.addEquipment(equipment);
    }
    
    //根据设备ID删除设备
    @PostMapping("/admin/deleteDevice")
    public Result deleteEquipment(@RequestBody Equipment equipment){
        int equipmentId = equipment.getEquipmentId();
        return equipmentManagementService.deleteEquipment(equipmentId);
    }
    
    
}
