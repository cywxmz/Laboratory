package cn.navg.laboratory.mapper.teacher;

import cn.navg.laboratory.pojo.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

@Mapper
public interface DeviceManagementMapper {
    //查询正在使用的设备 正在被借出的设备
    public int getUsingDeviceList();
    
    //查询正在维修的设备总数
    public int getRepairingDeviceCount();
    
    //查询报废设备总数
    public int getScrapedDeviceCount();
    
    //查询所有设备的总数
    public int getAllDeviceCount();
    
    //根据设备特殊查询条件查询设备
    public List<HashMap<String, Object>> getDeviceListByCondition(HashMap<String, Object> condition);
    
    //根据特殊查询条件查询设备总数
    public int getDeviceListByConditionCount(HashMap<String, Object> condition);
    
    //教师申请借用设备
    public int borrowDevice(BorrowRequest borrowRequest);
    
    //以下2方法未实现
    
    //判断设备是否库存是否充足
    public int checkDeviceStockSufficiency(Integer equipmentId, Integer borrowCount);
    
    //查询教师是否有未处理的借用申请 最大可借用设备数为5
    public int getUnprocessedBorrowRequestCount(Integer teacherId);
    
    //根据特殊查询条件查询实验室
    public List<Laboratory> getLaboratoryListByCondition(HashMap<String, Object> condition);
    
    //根据特殊查询条件查询实验室总数
    public int getLaboratoryListByConditionCount(HashMap<String, Object> condition);
    
    
    //教师预约实验室
    public int reserveLaboratory(LaboratoryUsage laboratoryUsage);
    
    //根据实验室ID查询当前实验室状态 关闭还是正常
    public Laboratory getLaboratoryStatus(Integer laboratoryId);
    
    //查询对应实验室的预约记录
    public List<LaboratoryUsage> getReservedLaboratoryListByTimePeriod
    (@Param("laboratoryId") Integer laboratoryId,
     @Param("usageStartTime") String usageStartTime,
     @Param("usageEndTime") String usageEndTime);
    
    //根据实验室ID查询预约记录
    public List<LaboratoryUsage> getReservedLaboratoryListByLaboratoryId(Integer laboratoryId);
    
    
    //查询教师的实验室预约记录 /teacher/getLaboratoryReservationList
    public List<HashMap<String, Object>> getTeacherReservedLaboratoryList(@Param("userId") Integer userId,
                                                                          @Param("currentPage") Integer currentPage,
                                                                          @Param("pageSize") Integer pageSize);
    
    //根据特殊条件查询教师的实验室预约记录  实验室名称、预约状态、使用状态
    public List<HashMap<String, Object>> getTeacherReservedLaboratoryListBySpecialCondition(HashMap<String, Object> condition);
    
    // 根据特殊条件查询教师的实验室预约记录总数
    public Integer getTeacherReservedLaboratoryListBySpecialConditionCount(HashMap<String, Object> condition);
    
    //查询教师的设备借用记录 所有
    public List<HashMap<String, Object>> getDeviceBorrowRecordByTeacher(@Param("userId") Integer userId,
                                                                        @Param("currentPage") Integer currentPage,
                                                                        @Param("pageSize") Integer pageSize);
    
    //查询教师的设备借用记录 所有总数
    public int getDeviceBorrowRecordByTeacherCount(Integer userId);
    
    //根据特殊条件查询教师的设备借用记录总数 设备名称、审批状态、归还状态
    public int getTeacherBorrowRecordByConditionCount(HashMap<String, Object> condition);
    
    //根据特殊条件查询教师的设备借用记录 设备名称、审批状态、归还状态
    public List<HashMap<String, Object>> getTeacherBorrowRecordByCondition(HashMap<String, Object> condition);
    
    //教师取消实验室预约
    public int cancelReserveLaboratory(@Param("UsageId") Integer UsageId,
                                       @Param("UpdateTime") String UpdateTime);
    
    //1教师归还设备更新借用记录状态
    public int returnDevice(@Param("requestId") Integer requestId,
                            @Param("actualReturnTime") String actualReturnTime,
                            @Param("updateTime") String updateTime);
    //2更新设备数量
    public int updateDeviceCount(@Param("equipmentId") Integer equipmentId);
    //3根据借用记录id查询设备id 然后更新设备数量
    public int queryDeviceIdByBorrowRecordId(@Param("requestId") Integer requestId, @Param("borrowCount") Integer borrowCount);
    
    //教师申请维修设备
    public int maintainDevice(MaintenanceRecord maintainRequest);
    
    //教师查询报修记录 所有
    public List<HashMap<String, Object>> getMaintenanceRecordByTeacher(Integer userId, Integer currentPage, Integer pageSize);
    
    //教师查询报修记录 所有总数
    public int getMaintenanceRecordByTeacherCount(Integer userId);
    
    //教师根据设备名称或者维修状态查询包修记录
    public List<HashMap<String, Object>> getMaintenanceRecordByDeviceNameOrStatus(HashMap<String, Object> condition);
    
    //统计总条数
    //教师根据设备名称或者维修状态查询包修记录总数
    public int getMaintenanceRecordByDeviceNameOrStatusCount(HashMap<String, Object> condition);
    
    //教师对设备报废申请
    public int scrapDevice(ScrapApplication scrapApplication);
}
