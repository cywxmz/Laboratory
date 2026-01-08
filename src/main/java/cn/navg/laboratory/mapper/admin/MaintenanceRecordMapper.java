package cn.navg.laboratory.mapper.admin;

import cn.navg.laboratory.pojo.MaintenanceRecord;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

@Mapper
public interface MaintenanceRecordMapper {
    //查询维修记录 查询所有
    public List<MaintenanceRecord> getRepairRecordList(@Param("currentPage") int currentPage, @Param("pageSize") int pageSize);
    
    //查询维修记录 总数
    public int getTotalRepairRecord();
 
    //按条件查询维修记录
    public List<MaintenanceRecord> getMaintenanceRecordByCondition(HashMap<String, Object> condition);
    
    //按条件查询维修记录 总数
    public int getTotalMaintenanceRecordByCondition(HashMap<String, Object> condition);
    
    //更新维修状态/记录
    public int updateMaintenanceStatus(HashMap<String, Object> condition);
    
    // 审批维修记录
    public int approveMaintenanceRecord(MaintenanceRecord maintenanceRecord);
}
