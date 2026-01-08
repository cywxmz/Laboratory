package cn.navg.laboratory.mapper.common;

import cn.navg.laboratory.pojo.Result;
import cn.navg.laboratory.pojo.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface GetCurrentUserInfoMapper {
    //根据用户id查询用户信息
    public User getCurrentUserInfo(int userId);
}
