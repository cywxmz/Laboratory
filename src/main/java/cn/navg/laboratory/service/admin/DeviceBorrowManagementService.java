package cn.navg.laboratory.service.admin;

import cn.navg.laboratory.pojo.BorrowRequest;
import cn.navg.laboratory.pojo.Result;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

public interface DeviceBorrowManagementService {
    //查询设备借用记录 查询所有
    public Result getDeviceBorrowRecordList(@Param("currentPage") int currentPage, @Param("pageSize") int pageSize);
    
    // 按条件查询设备借用记录
    public Result getDeviceBorrowRecordByCondition(HashMap<String, Object> condition);
    
    // 审批设备借用请求
    public Result approveBorrow(BorrowRequest borrowRequest);
}
