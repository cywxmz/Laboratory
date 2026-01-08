package cn.navg.laboratory.service.admin.Impl;

import cn.navg.laboratory.mapper.admin.MaintenanceRecordMapper;
import cn.navg.laboratory.pojo.MaintenanceRecord;
import cn.navg.laboratory.pojo.Page;
import cn.navg.laboratory.pojo.Result;
import cn.navg.laboratory.service.admin.MaintenanceRecordService;
import cn.navg.laboratory.utils.GetNowDataTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class MaintenanceRecordServiceImpl implements MaintenanceRecordService {
    @Autowired
    private MaintenanceRecordMapper maintenanceRecordMapper;
    
    //查询维修记录 查询所有
    @Override
    public Result queryRepairRecord(int currentPage, int pageSize) {
        currentPage = (currentPage - 1) * pageSize;
        //查询维修记录 查询所有
        List<MaintenanceRecord> maintenanceRecords =
                maintenanceRecordMapper.getRepairRecordList(currentPage, pageSize);
        
        //查询维修记录 总数
        int totalRepairRecord = maintenanceRecordMapper.getTotalRepairRecord();
        Page page = new Page(currentPage, pageSize, totalRepairRecord);
        return Result.success(maintenanceRecords, page);
    }
    
    //按条件查询维修记录
    @Override
    public Result queryMaintenanceRecordByCondition(HashMap<String, Object> condition) {
        //重新设置分页参数
        int currentPage = (int) condition.get("currentPage");
        int pageSize = (int) condition.get("pageSize");
        currentPage = (currentPage - 1) * pageSize;
        condition.put("currentPage", currentPage);
        condition.put("pageSize", pageSize);
        
        //按条件查询维修记录
        List<MaintenanceRecord> maintenanceRecords =
                maintenanceRecordMapper.getMaintenanceRecordByCondition(condition);
        
        //按条件查询维修记录 总数
        int totalMaintenanceRecord = maintenanceRecordMapper.getTotalMaintenanceRecordByCondition(condition);
        Page page = new Page(currentPage, pageSize, totalMaintenanceRecord);
        return Result.success(maintenanceRecords, page);
    }
    
    //更新维修状态/记录
    @Override
    public Result updateMaintenanceStatus(HashMap<String, Object> condition) {
        condition.put("updateTime", GetNowDataTime.formattedTime);
        int rows = maintenanceRecordMapper.updateMaintenanceStatus(condition);
        if (rows > 0) {
            return Result.success("更新成功");
        } else {
            return Result.error("更新失败");
        }
    }
    
    //审批维修记录
    @Override
    public Result approveMaintenanceRecord(MaintenanceRecord maintenanceRecord) {
        maintenanceRecord.setUpdateTime(GetNowDataTime.formattedTime);
        int rows = maintenanceRecordMapper.approveMaintenanceRecord(maintenanceRecord);
        if (rows > 0) {
            return Result.success("审批成功,维修记录已更新");
        } else {
            return Result.error("审批失败");
        }
    }
}
