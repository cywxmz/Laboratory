package cn.navg.laboratory.mapper.teacher;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.HashMap;
import java.util.List;

@Mapper
public interface HomeInfoMapper {
    //查询当前时间
    public List<HashMap<String, Object>> getHomeInfo(@Param("nowtime") String nowtime, @Param("userId") String userID);
}
