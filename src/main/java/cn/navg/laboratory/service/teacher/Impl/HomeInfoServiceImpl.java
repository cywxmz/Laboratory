package cn.navg.laboratory.service.teacher.Impl;

import cn.navg.laboratory.mapper.teacher.HomeInfoMapper;
import cn.navg.laboratory.pojo.Result;
import cn.navg.laboratory.service.teacher.HomeInfoService;
import cn.navg.laboratory.utils.GetNowDataTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HomeInfoServiceImpl implements HomeInfoService {
    @Autowired
    HomeInfoMapper homeInfoMapper;
    @Override
    public Result HomeInfo(String UserID) {
        //获取当前时间
        String nowTime = GetNowDataTime.formattedDate;
        //返回当前时间
        return Result.success(homeInfoMapper.getHomeInfo(nowTime, UserID));
    }
}
