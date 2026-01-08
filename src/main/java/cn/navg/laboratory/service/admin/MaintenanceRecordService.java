package cn.navg.laboratory.service.admin;

import cn.navg.laboratory.pojo.MaintenanceRecord;
import cn.navg.laboratory.pojo.Result;

import java.util.HashMap;

public interface MaintenanceRecordService {
    //查询维修记录 查询所有
    public Result queryRepairRecord(int currentPage, int pageSize);
    
    //按条件查询维修记录
    public Result queryMaintenanceRecordByCondition(HashMap<String, Object> condition);
    
    //更新维修状态/记录
    public Result updateMaintenanceStatus(HashMap<String, Object> condition);
    
    //审批维修记录
    public Result approveMaintenanceRecord(MaintenanceRecord maintenanceRecord);
}
