package cn.navg.laboratory.pojo;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

// 实验室使用记录实体类
@Data
@Getter
@Setter
public class LaboratoryUsage {
    Integer UsageId;           // 预约ID
    Integer LaboratoryId;      // 实验室ID，
    Integer UserId;            // 使用人ID，
    String UserName;           // 使用人姓名
    String UsageStartTime;       // 使用开始时间
    String UsageEndTime;         // 使用结束时间
    String UsagePurpose;       // 使用用途
    Integer UsageStatus;       // 使用状态
    Integer ReservationStatus; // 预约状态
    String ReservationTime;      // 预约时间
    String CreateTime;           // 创建时间
    String UpdateTime;           // 更新时间
    String Remark;              // 备注 管理员审批意见
}