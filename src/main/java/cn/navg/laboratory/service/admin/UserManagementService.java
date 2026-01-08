package cn.navg.laboratory.service.admin;

import cn.navg.laboratory.pojo.Page;
import cn.navg.laboratory.pojo.Result;
import cn.navg.laboratory.pojo.User;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public interface UserManagementService {
    //查询所有用户
    public List<User> queryAllUsers(Integer currentPage, Integer pageSize);

    //查询所有用户的条数
    public Integer queryAllUsersCount();

    //按条件查询用户
    public Result queryUsersByConditions(HashMap<String,Object> map);
    
    //添加用户
   public Result addUser(User user);
   
   public Result updateUser(User user);
    
    //数据回显 根据用户Id查询用户信息
   public Result queryUserById(Integer userId);
   
   //删除用户 根据用户Id删除
   public Result deleteUser(Integer userId);
}
