package cn.navg.laboratory.service.admin;

import cn.navg.laboratory.pojo.Laboratory;
import cn.navg.laboratory.pojo.Result;

import java.util.HashMap;

public interface LaboratoryManagementService {
    //查询所有的实验室信息
    public Result getAllLaboratory(int currentPage, int pageSize);
    
    //按条件查询实验室信息
    public Result queryLaboratoryListByCondition(HashMap<String, Object> data);
    
    //根据实验室ID查询实验室的设备
    public Result getEquipmentListByLaboratoryId(int laboratoryId);
    
    //根据实验室ID查询设备总数
    public Result getEquipmentTotalCountByLaboratoryId(int laboratoryId);
    
    //添加实验室
    public Result addLaboratory(Laboratory laboratory);
    
    //根据实验室ID更新实验室信息
    public Result updateLaboratory(Laboratory laboratory);
    
    //根据实验室ID删除实验室
    public Result deleteLaboratory(int laboratoryId);
}
