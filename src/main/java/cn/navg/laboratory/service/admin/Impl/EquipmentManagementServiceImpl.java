package cn.navg.laboratory.service.admin.Impl;

import cn.navg.laboratory.mapper.admin.equipmentManagementMapper;
import cn.navg.laboratory.pojo.Equipment;
import cn.navg.laboratory.pojo.Page;
import cn.navg.laboratory.pojo.Result;
import cn.navg.laboratory.service.admin.EquipmentManagementService;
import cn.navg.laboratory.utils.GetNowDataTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class EquipmentManagementServiceImpl implements EquipmentManagementService {
    @Autowired
    private equipmentManagementMapper equipmentManagementMapper;
    
    //查询所有的实验室 只需要实验室名称
    @Override
    public Result getALLLaboratoryName() {
        return Result.success(equipmentManagementMapper.getALLLaboratoryName());
    }
    
    //查询所有的设备类型
    @Override
    public Result getAllEquipmentType() {
        return Result.success(equipmentManagementMapper.getAllEquipmentType());
    }
    
    //查询所有的设备
    @Override
    public Result queryAllEquipment(int currentPage, int pageSize) {

        //查询总条数
        int total = equipmentManagementMapper.getTotalEquipment();
        Page page = new Page(currentPage, pageSize, total);
        //查询分页数据
        List<Equipment> equipmentList = equipmentManagementMapper.getAllEquipment(currentPage, pageSize);
        return Result.success(equipmentList, page);
    }
    
    //根据多个条件模糊查询设备列表
    @Override
    public Result searchEquipment(HashMap<String, Object> params) {
        //重新设置分页参数
        int currentPage = (int) params.get("currentPage");
        int pageSize = (int) params.get("pageSize");
        //查询总条数
        int total = equipmentManagementMapper.getTotalConditionsEquipment(params);
        
        Page page = new Page(currentPage, pageSize, total);
        currentPage = (currentPage - 1) * pageSize;
        params.put("currentPage", currentPage);
        params.put("pageSize", pageSize);
        
        //查询分页数据
        List<Equipment> equipmentList = equipmentManagementMapper.searchEquipment(params);
        return Result.success(equipmentList,page);
    }
    
    @Override
    public Result updateEquipment(Equipment equipment) {
        //对时间进行重新赋值
        equipment.setUpdateTime(GetNowDataTime.formattedTime);
        
        //更新设备信息
        int rows = equipmentManagementMapper.updateEquipment(equipment);
        if(rows > 0){
            return Result.success("设备信息更新成功");
        }
        return Result.error("设备信息更新失败");
    }
    
    @Override
    public Result addEquipment(Equipment equipment) {
        //对时间进行重新赋值
        equipment.setCreateTime(GetNowDataTime.formattedTime);
        equipment.setUpdateTime(GetNowDataTime.formattedTime);
        
        //添加设备
        int rows = equipmentManagementMapper.addEquipment(equipment);
        if(rows > 0){
            return Result.success("设备信息添加成功");
        }
        return Result.error("设备信息添加失败");
    }
    
    //根据设备ID删除设备
    @Override
    public Result deleteEquipment(int equipmentId) {
        //删除设备
        int rows = equipmentManagementMapper.deleteEquipment(equipmentId);
        if(rows > 0){
            return Result.success("设备信息删除成功");
        }
        return Result.error("设备信息删除失败");
    }
}
