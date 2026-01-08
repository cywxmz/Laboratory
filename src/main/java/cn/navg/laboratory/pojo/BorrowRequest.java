package cn.navg.laboratory.pojo;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Data
@Getter
@Setter
public class BorrowRequest {
    Integer RequestId;         // 申请ID，主键
    Integer EquipmentId;       // 设备ID，外键关联Equipment表
    Integer ApplicantId;       // 申请人ID，外键关联User表
    String ApplicantName;      // 申请人姓名
    String ApplyDate;            // 申请日期
    String BorrowStartTime;      // 借用开始时间
    String BorrowEndTime;        // 借用结束时间
    String BorrowReason;       // 借用用途
    Integer ApprovalStatus;    // 审批状态（0-待审批，1-已批准，2-已拒绝）
    Integer ApproverId;        // 审批人ID，外键关联User表
    String ApproverName;       // 审批人姓名
    String ApprovalTime;         // 审批时间
    String ApprovalComment;    // 审批意见
    String ActualReturnTime;     // 实际归还时间
    Integer ReturnStatus;      // 归还状态（0-未归还，1-已归还）
    String CreateTime;           // 创建时间
    String UpdateTime;           // 更新时间
}