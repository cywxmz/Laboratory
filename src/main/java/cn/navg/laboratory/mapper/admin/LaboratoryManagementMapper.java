package cn.navg.laboratory.mapper.admin;

import cn.navg.laboratory.pojo.Equipment;
import cn.navg.laboratory.pojo.Laboratory;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

@Mapper
public interface LaboratoryManagementMapper {
    //查询所有的实验室信息
    public List<Laboratory> getAllLaboratory(@Param("currentPage") int currentPage, @Param("pageSize") int pageSize);
    
    //根据条件查询实验室信息
    public List<Laboratory> queryLaboratoryListByCondition(HashMap<String, Object> data);
    
    //查询所有的实验室的总条数
    public Integer getTotalCount();
    
    //按条件查询实验室的总条数
    public Integer getTotalCountByCondition(HashMap<String, Object> data);
    
    //根据实验室ID查询实验室的设备
    public List<Equipment> getEquipmentListByLaboratoryId(int laboratoryId);
    
    //根据实验室ID查询设备总数
    public Integer getEquipmentTotalCountByLaboratoryId(int laboratoryId);
    
    //根据实验室名称、所在楼宇、房间号、编号查询实验室是否已存在
    public Laboratory getLaboratoryByCondition(Laboratory laboratory);
    
    //添加实验室
    public int addLaboratory(Laboratory laboratory);
    
    //根据实验室ID更新实验室信息
    public int updateLaboratory(Laboratory laboratory);
    
    //根据实验室ID删除实验室
    public int deleteLaboratory(@Param("laboratoryId") int laboratoryId);
    
    //编辑时查询是否已存在该实验室 除了自己
    public Integer checkLaboratoryIsExist(Laboratory laboratory);
}
