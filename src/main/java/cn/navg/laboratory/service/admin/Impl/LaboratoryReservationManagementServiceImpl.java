package cn.navg.laboratory.service.admin.Impl;

import cn.navg.laboratory.mapper.admin.LaboratoryReservationManagementMapper;
import cn.navg.laboratory.pojo.LaboratoryUsage;
import cn.navg.laboratory.pojo.Page;
import cn.navg.laboratory.pojo.Result;
import cn.navg.laboratory.service.admin.LaboratoryReservationManagementService;
import cn.navg.laboratory.utils.GetNowDataTime;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Slf4j
@Service
public class LaboratoryReservationManagementServiceImpl implements LaboratoryReservationManagementService {
    @Autowired
    private LaboratoryReservationManagementMapper reservationMapper;
    
    @Override
    public Result getAllReservations(Integer currentPage, Integer pageSize) {
        int totalCount = reservationMapper.getAllLaboratoryReservationListCount();
        Page page = new Page(currentPage, pageSize, totalCount);
        currentPage = (currentPage - 1) * pageSize;
        
        List<HashMap<String, Object>> laboratoryUsages =
                reservationMapper.getAllLaboratoryReservationList(currentPage, pageSize);
        
        
        return Result.success(laboratoryUsages, page);
    }
    
    //审批预约记录 更新使用开始时间、使用状态、预约状态、更新时间、备注
    @Override
    public Result approveReservation(LaboratoryUsage laboratoryUsage) {
        //手动设置更新时间
        laboratoryUsage.setUpdateTime(GetNowDataTime.formattedTime);
        laboratoryUsage.setUsageStartTime(GetNowDataTime.formattedTime);
        int res = reservationMapper.approveReservation(laboratoryUsage);
        if (res > 0) {
            return Result.success("审批成功");
        }
        return Result.error("审批失败");
    }
    
    @Override
    public Result cancelReservation(Integer usageId) {
        //手动设置更新时间
        LaboratoryUsage laboratoryUsage = new LaboratoryUsage();
        laboratoryUsage.setUsageId(usageId);
        laboratoryUsage.setReservationStatus(2);//预约状态（1-待审批，2-已取消，3-已拒绝，4-已通过）
        laboratoryUsage.setUsageStatus(2);//使用状态（0-未开始，1-使用中，2-已结束）
        laboratoryUsage.setRemark("-管理员取消");
        laboratoryUsage.setUpdateTime(GetNowDataTime.formattedTime);
        
        int res = reservationMapper.cancelReservation(laboratoryUsage);
        if (res > 0) {
            return Result.success("取消使用或预约成功");
        }
        return Result.error("取消使用或预约失败");
    }
    
    //根据实验室名称或者状态查询预约记录
    @Override
    public Result getLaboratoryReservationListByStatusOrName(HashMap<String, Object> params) {
        //分页
        Integer currentPage = (Integer) params.get("currentPage");
        Integer pageSize = (Integer) params.get("pageSize");
        currentPage = (currentPage - 1) * pageSize;
        params.put("currentPage", currentPage);
        params.put("pageSize", pageSize);
        
        //查询总条数 根据特殊查询条件
        int totalCount = reservationMapper.getLaboratoryReservationListByStatusOrNameCount(params);
        Page page = new Page(currentPage, pageSize, totalCount);
        
        List<HashMap<String, Object>> laboratoryUsages =
                reservationMapper.getLaboratoryReservationListByStatusOrName(params);
        return Result.success(laboratoryUsages, page);
    }
}
