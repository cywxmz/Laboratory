package cn.navg.laboratory.service.admin;

import cn.navg.laboratory.mapper.admin.equipmentManagementMapper;
import cn.navg.laboratory.pojo.Equipment;
import cn.navg.laboratory.pojo.Result;

import java.util.HashMap;
import java.util.List;

public interface EquipmentManagementService {
    //查询所有的实验室 只需要实验室名称
    public Result getALLLaboratoryName();
    
    //查询所有的设备类型
    public Result getAllEquipmentType();
    
    //查询所有的设备
    public Result queryAllEquipment(int currentPage, int pageSize);
    
    //根据多个条件模糊查询设备列表
    public Result searchEquipment(HashMap<String, Object> params);
    
    //更新设备信息
    public Result updateEquipment(Equipment equipment);
    
    //添加设备
    public Result addEquipment(Equipment equipment);
    
    //根据设备ID删除设备
    public Result deleteEquipment(int equipmentId);
    
}
