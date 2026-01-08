package cn.navg.laboratory.pojo;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

// 维修记录实体类
@Data
@Getter
@Setter
public class MaintenanceRecord {
    Integer MaintenanceId;      // 维修记录ID，主键
    Integer EquipmentId;        // 设备ID，外键关联Equipment表
    String FaultDescription;    // 故障描述
    String ReportDate;            // 报修日期
    Integer ReporterId;         // 报修人ID，外键关联User表
    String ReporterName;        // 报修人姓名
    String MaintenanceCompany;  // 维修单位
    BigDecimal MaintenanceCost; // 维修费用
    Integer MaintenanceStatus;  // 维修状态（0-待维修，1-维修中，2-已完成，3-无法修复）
    String MaintenanceStartTime;  // 维修开始时间
    String MaintenanceEndTime;    // 维修结束时间
    String MaintenanceContent;  // 维修内容
    String MaintenancePerson;   // 维修人员
    String CreateTime;            // 创建时间
    String UpdateTime;            // 更新时间
    String EquipmentName;       // 设备名称
    String Remark;              // 备注
}