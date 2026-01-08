package cn.navg.laboratory.pojo;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;

/**
 * 用户角色关联实体类
 */
@Data
@Getter
@Setter
public class UserRole {
    /**
     * 用户角色关联ID，主键
     */
    private Integer UserRoleId;

    /**
     * 用户ID，外键关联User表
     */
    private Integer UserId;

    /**
     * 角色ID，外键关联Role表
     */
    private Integer RoleId;

    /**
     * 创建时间
     */
    private Date CreateTime;
}