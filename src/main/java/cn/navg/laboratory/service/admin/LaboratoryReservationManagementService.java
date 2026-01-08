package cn.navg.laboratory.service.admin;

import cn.navg.laboratory.pojo.LaboratoryUsage;
import cn.navg.laboratory.pojo.Page;
import cn.navg.laboratory.pojo.Result;

import java.util.HashMap;
import java.util.List;

public interface LaboratoryReservationManagementService {
    //查询所有预约记录
    public Result getAllReservations(Integer currentPage, Integer pageSize);
    
    //审批实验室预约记录
    public Result approveReservation(LaboratoryUsage laboratoryUsage);
    
    //取消实验室预约记录
    public Result cancelReservation(Integer usageId);
    
    //根据实验室名称或者状态查询预约记录
    public Result getLaboratoryReservationListByStatusOrName(HashMap<String, Object> params);
}
