package cn.navg.laboratory.pojo;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

// 设备表
@Data
@Getter
@Setter
public class Equipment {
    Integer EquipmentId;       // 设备ID，主键
    String EquipmentName;      // 设备名称
    String EquipmentModel;     // 设备型号规格
    Integer TypeId;            // 设备类型ID，外键关联EquipmentType表
    String Manufacturer;       // 生产厂商
    String PurchaseDate;         // 购买日期
    BigDecimal PurchasePrice;  // 购买价格
    Integer LaboratoryId;      // 所属实验室ID，外键关联Laboratory表
    Integer EquipmentStatus;   // 设备状态（1-正常，2-借用中，3-维修中，4-报废）
    Integer UsageLife;         // 使用寿命（年）
    String SerialNumber;       // 设备序列号
    Integer Quantity;          // 设备数量
    String Description;        // 设备描述
    String CreateTime;           // 创建时间
    String UpdateTime;           // 更新时间
}