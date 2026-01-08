package cn.navg.laboratory.mapper.admin;

import cn.navg.laboratory.pojo.ScrapApplication;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

// 设备报废审批映射接口
@Mapper
public interface DeviceScrapApprovalMapper {
    //查询所有 设备报废审批记录
    public List<ScrapApplication> getAllScrapApplications(@Param("currentPage") int currentPage, @Param("pageSize") int pageSize);
    
    //查询所有 设备报废审批记录 总数
    public int getTotalScrapApplications();
    
    //根据条件查询 设备报废审批记录
    public List<ScrapApplication> getScrapApplicationsByCondition(HashMap<String, Object> conditions);
    
    //根据条件查询 设备报废审批记录 总数
    public Integer getTotalScrapApplicationsByCondition(HashMap<String, Object> conditions);
    
    // 审批设备报废请求
    public int approveScrap(ScrapApplication scrapApplication);
    
    //使设备数量减1
    public int decreaseDeviceCount(@Param("equipmentId") Integer equipmentId);
}
