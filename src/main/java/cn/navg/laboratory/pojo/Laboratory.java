package cn.navg.laboratory.pojo;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

// 实验室实体类
@Data
@Getter
@Setter
public class Laboratory {
    private Integer laboratoryId;      // 实验室ID，主键
    private String laboratoryName;     // 实验室名称
    private String building;           // 所在楼宇
    private String roomNumber;         // 房间号
    private Integer capacity;          // 可容纳设备数量
    private String managerName;        // 负责人姓名
    private String managerPhone;       // 负责人电话
    private Integer laboratoryStatus;  // 实验室状态（1-正常，0-关闭）
    private String description;        // 实验室描述
    private String createTime;           // 创建时间
    private String updateTime;           // 更新时间
}
