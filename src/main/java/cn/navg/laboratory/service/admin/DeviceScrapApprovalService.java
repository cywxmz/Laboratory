package cn.navg.laboratory.service.admin;

import cn.navg.laboratory.pojo.Result;
import cn.navg.laboratory.pojo.ScrapApplication;

import java.util.HashMap;

// 设备报废审批服务接口
public interface DeviceScrapApprovalService {
    //查询所有 设备报废审批记录
    public Result getAllScrapApplications(int currentPage, int pageSize);
    
    //根据条件查询 设备报废审批记录
    public Result getScrapApplicationsByCondition(HashMap<String, Object> conditions);
    
    // 审批设备报废请求
    public Result approveScrap(ScrapApplication scrapApplication);
}
