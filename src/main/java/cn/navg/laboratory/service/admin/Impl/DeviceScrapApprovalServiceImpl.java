package cn.navg.laboratory.service.admin.Impl;

import cn.navg.laboratory.mapper.admin.DeviceScrapApprovalMapper;
import cn.navg.laboratory.pojo.Page;
import cn.navg.laboratory.pojo.Result;
import cn.navg.laboratory.pojo.ScrapApplication;
import cn.navg.laboratory.service.admin.DeviceScrapApprovalService;
import cn.navg.laboratory.utils.GetNowDataTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

// 设备报废审批服务实现类
@Service
public class DeviceScrapApprovalServiceImpl implements DeviceScrapApprovalService {
    @Autowired
    private DeviceScrapApprovalMapper deviceScrapApprovalMapper;
    
    //查询所有 设备报废审批记录
    @Override
    public Result getAllScrapApplications(int currentPage, int pageSize) {
        //查询所有 设备报废审批记录 总数
        int totalScrapApplications = deviceScrapApprovalMapper.getTotalScrapApplications();
        //分页信息
        Page page = new Page(currentPage, pageSize, totalScrapApplications);
        currentPage = (currentPage - 1) * pageSize;
        //查询所有 设备报废审批记录
        List<ScrapApplication> scrapApplications = deviceScrapApprovalMapper.getAllScrapApplications(currentPage, pageSize);
        return Result.success(scrapApplications, page);
    }
    
    //根据条件查询 设备报废审批记录
    @Override
    public Result getScrapApplicationsByCondition(HashMap<String, Object> conditions) {
        int currentPage = (Integer) conditions.get("currentPage");
        int pageSize = (Integer) conditions.get("pageSize");
        conditions.put("currentPage", (currentPage - 1) * pageSize);
        
        
        //根据条件查询 设备报废审批记录 总数
        int totalScrapApplications = deviceScrapApprovalMapper.getTotalScrapApplicationsByCondition(conditions);
        //根据条件查询 设备报废审批记录
        List<ScrapApplication> scrapApplications = deviceScrapApprovalMapper.getScrapApplicationsByCondition(conditions);
        
        //分页信息
        Page page = new Page(currentPage, pageSize, totalScrapApplications);
        return Result.success(scrapApplications, page);
    }
    
    // 审批设备报废请求 同时设备数量要减1
    @Override
    public Result approveScrap(ScrapApplication scrapApplication) {
        scrapApplication.setScrapDate(GetNowDataTime.formattedTime);
        scrapApplication.setApprovalTime(GetNowDataTime.formattedTime);
        scrapApplication.setUpdateTime(GetNowDataTime.formattedTime);
        
        //使设备数量减1
        int decreaseResult = deviceScrapApprovalMapper.decreaseDeviceCount(scrapApplication.getEquipmentId());
        if (decreaseResult <= 0) {
            return Result.error("审批失败，设备数量减1失败");
        }
        
        //审批设备报废请求
        int result = deviceScrapApprovalMapper.approveScrap(scrapApplication);
        if (result > 0) {
            return Result.success("审批成功");
        } else {
            return Result.error("审批失败");
        }
    }
}
