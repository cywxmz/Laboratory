package cn.navg.laboratory.pojo;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

// 报废申请实体类
@Data
@Getter
@Setter
public class ScrapApplication {
    Integer ScrapId;           // 报废申请ID，主键
    Integer EquipmentId;       // 设备ID，外键关联Equipment表
    Integer ApplicantId;       // 申请人ID，外键关联User表
    String ApplicantName;      // 申请人姓名
    String ApplyDate;            // 申请日期
    String ScrapReason;        // 报废原因
    Integer ScrapStatus;       // 报废状态（0-待审批，1-已批准，2-已拒绝）
    Integer ApproverId;        // 审批人ID，外键关联User表
    String ApproverName;       // 审批人姓名
    String ApprovalTime;         // 审批时间
    String ApprovalComment;    // 审批意见
    String ScrapDate;            // 实际报废日期
    String CreateTime;           // 创建时间
    String UpdateTime;           // 更新时间
}