package cn.navg.laboratory.pojo;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

// 设备类型表
@Data
@Getter
@Setter
public class EquipmentType {
    Integer TypeId;           // 设备类型ID，主键
    String TypeName;          // 设备类型名称
    String TypeDescription;   // 设备类型描述
    Date CreateTime;          // 创建时间
    Date UpdateTime;          // 更新时间
}