package cn.navg.laboratory.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * 用户实体类
 */
@Data
@Getter
@Setter
public class User {
    private Integer UserId;
    private String UserName;
    private String UserAccount;
    private String UserPassword;
    private String UserEmail;
    private String UserPhone;
    private Integer UserStatus;
    private String Department;
    private String Position;
    private String CreateTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonProperty("updateTime")
    private String UpdateTime;
    private String RoleType;

}