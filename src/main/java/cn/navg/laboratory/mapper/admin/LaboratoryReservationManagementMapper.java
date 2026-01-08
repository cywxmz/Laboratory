package cn.navg.laboratory.mapper.admin;

import cn.navg.laboratory.pojo.LaboratoryUsage;
import cn.navg.laboratory.pojo.Page;
import cn.navg.laboratory.pojo.Result;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

@Mapper
public interface LaboratoryReservationManagementMapper {
    //查询所有预约记录
    public List<HashMap<String, Object>> getAllLaboratoryReservationList(
            @Param("currentPage") Integer currentPage, @Param("pageSize") Integer pageSize);
    
    //查询所有预约记录总数
    public int getAllLaboratoryReservationListCount();
    
    //审批预约记录 更新使用开始时间、使用状态、预约状态、更新时间、备注
    public int approveReservation(LaboratoryUsage laboratoryUsage);
    
    //取消预约记录 更新使用状态、预约状态、更新时间、备注
    public int cancelReservation(LaboratoryUsage laboratoryUsage);
    
    //根据实验室名称或者状态查询预约记录
    public List<HashMap<String, Object>> getLaboratoryReservationListByStatusOrName(@Param("query") HashMap<String, Object> query);
    
    //查询总条数 根据特殊查询条件
    public int getLaboratoryReservationListByStatusOrNameCount(@Param("query") HashMap<String, Object> query);
}
