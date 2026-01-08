package cn.navg.laboratory.service.admin.Impl;

import cn.navg.laboratory.mapper.admin.LaboratoryManagementMapper;
import cn.navg.laboratory.pojo.Equipment;
import cn.navg.laboratory.pojo.Laboratory;
import cn.navg.laboratory.pojo.Page;
import cn.navg.laboratory.pojo.Result;
import cn.navg.laboratory.service.admin.LaboratoryManagementService;
import cn.navg.laboratory.utils.GetNowDataTime;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Slf4j
@Service
public class LaboratoryManagementServiceImpl implements LaboratoryManagementService {
    @Autowired
    private LaboratoryManagementMapper laboratoryManagementMapper;
    
    @Override
    public Result getAllLaboratory(int currentPage, int pageSize) {
        Page page = new Page(currentPage, pageSize, laboratoryManagementMapper.getTotalCount());
        currentPage = (currentPage - 1) * pageSize;
        List<Laboratory> laboratories = laboratoryManagementMapper.getAllLaboratory(currentPage, pageSize);
        return Result.success(laboratories, page);
    }
    
    @Override
    public Result queryLaboratoryListByCondition(HashMap<String, Object> data) {
        Integer currentPage = (Integer) data.get("currentPage");
        Integer pageSize = (Integer) data.get("pageSize");
        Page page = new Page(currentPage, pageSize, laboratoryManagementMapper.getTotalCountByCondition(data));
        currentPage = (currentPage - 1) * pageSize;
        data.put("currentPage", currentPage);
        data.put("pageSize", pageSize);
        
        //根据条件查询实验室信息
        List<Laboratory> laboratories = laboratoryManagementMapper.queryLaboratoryListByCondition(data);
        log.info("按条件查询实验室信息:{}", laboratories);
        return Result.success(laboratories, page);
    }
    
    //根据实验室ID查询实验室的设备
    @Override
    public Result getEquipmentListByLaboratoryId(int laboratoryId) {
        List<Equipment> equipments = laboratoryManagementMapper.getEquipmentListByLaboratoryId(laboratoryId);
        return Result.success(equipments);
    }
    
    //根据实验室ID查询设备总数
    @Override
    public Result getEquipmentTotalCountByLaboratoryId(int laboratoryId) {
        Integer totalCount = laboratoryManagementMapper.getEquipmentTotalCountByLaboratoryId(laboratoryId);
        return Result.success(totalCount);
    }
    
    //添加实验室
    @Override
    public Result addLaboratory(Laboratory laboratory) {
       //现根据实验室名称、所在楼宇、房间号、编号查询是否已存在该实验室
       Integer checkLaboratoryIsExist = laboratoryManagementMapper.checkLaboratoryIsExist(laboratory);
       if(checkLaboratoryIsExist > 0){
           return Result.error("对应楼栋、房间号、编号或已存在实验室");
       }
       //添加实验室
        //创建时间和更新时间默认当前时间
       laboratory.setCreateTime(GetNowDataTime.formattedTime);
       laboratory.setUpdateTime(GetNowDataTime.formattedTime);
       int result = laboratoryManagementMapper.addLaboratory(laboratory);
       return result > 0 ? Result.success("添加实验室成功") : Result.error("添加实验室失败");
    }
    
    //根据实验室ID更新实验室信息
    @Override
    public Result updateLaboratory(Laboratory laboratory) {

        //现根据实验室名称、所在楼宇、房间号、编号查询是否已存在该实验室 除了自己
        Integer checkLaboratoryIsExist = laboratoryManagementMapper.checkLaboratoryIsExist(laboratory);
        if(checkLaboratoryIsExist > 0){
            return Result.error("对应楼栋、房间号、编号或已存在实验室");
        }
        
        
        //更新时间默认当前时间
        laboratory.setUpdateTime(GetNowDataTime.formattedTime);
        int result = laboratoryManagementMapper.updateLaboratory(laboratory);
        return result > 0 ? Result.success("更新实验室成功") : Result.error("更新实验室失败");
    }
    
    //根据实验室ID删除实验室
    @Override
    public Result deleteLaboratory(int laboratoryId) {
        int result = laboratoryManagementMapper.deleteLaboratory(laboratoryId);
        return result > 0 ? Result.success("删除实验室成功") : Result.error("删除实验室失败");
    }
}
