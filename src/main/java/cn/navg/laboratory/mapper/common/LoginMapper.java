package cn.navg.laboratory.mapper.common;

import cn.navg.laboratory.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface LoginMapper {
    public User login(@Param("UserAccount") String username, @Param("UserPassword") String password, @Param("roleType") String roleType);
}
