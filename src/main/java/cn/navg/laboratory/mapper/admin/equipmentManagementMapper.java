package cn.navg.laboratory.mapper.admin;

import cn.navg.laboratory.pojo.Equipment;
import cn.navg.laboratory.pojo.EquipmentType;
import cn.navg.laboratory.pojo.Laboratory;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

@Mapper
public interface equipmentManagementMapper {
    //查询所有的实验室 只需要实验室名称
    public List<String> getALLLaboratoryName();
    
    //查询所有的设备类型
    public List<EquipmentType> getAllEquipmentType();
    
    public List<Equipment> getAllEquipment(@Param("currentPage") int currentPage, @Param("pageSize") int pageSize);
    
    //查询所有的设备 总条数
    public Integer getTotalEquipment();
    
    //根据多个条件模糊查询设备列表 当前页,每页条数,设备名称,类型名称,设备状态,实验室名称
    public List<Equipment> searchEquipment(HashMap<String, Object> params);
    
    //根据多个条件模糊查询设备总条数 当前页,每页条数,设备名称,类型名称,设备状态,实验室名称
    public Integer getTotalConditionsEquipment(HashMap<String, Object> params);
    
    //根据设备ID更新设备信息
    public Integer updateEquipment(Equipment equipment);
    
    //根据设备类型名称查询设备类型ID 用于更新设备信息外键关联
//    public Integer getEquipmentTypeIdByName(String typeName);
//
//    //根据实验室名称查询实验室ID 用于更新设备信息外键关联
//    public Integer getLaboratoryIdByName(String laboratoryName);
    
    public int addEquipment(Equipment equipment);
    
    public int deleteEquipment(int equipmentId);
}
