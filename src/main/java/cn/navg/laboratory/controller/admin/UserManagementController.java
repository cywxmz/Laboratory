package cn.navg.laboratory.controller.admin;

import cn.navg.laboratory.pojo.Page;
import cn.navg.laboratory.pojo.Result;
import cn.navg.laboratory.pojo.User;
import cn.navg.laboratory.service.admin.UserManagementService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@Slf4j
@RestController
public class UserManagementController {
    @Autowired
    private UserManagementService userManagementService;

    //查询所有用户
    @PostMapping("/queryAllUsers")
    public Result<List<User>> queryAllUsers(@RequestBody HashMap<String,Object> map) {
        //获取当前页和每页条数
        Integer currentPage = (Integer) map.get("currentPage");
        Integer pageSize = (Integer) map.get("pageSize");

        //查询所有用户的总条数
        Integer total = userManagementService.queryAllUsersCount();
        //分页信息
        Page page = new Page(currentPage, pageSize, total);
        //查询所有用户
        List<User> users = userManagementService.queryAllUsers(currentPage, pageSize);
        return Result.success(users,page);
    }

    //按条件查询用户
    @PostMapping("/queryUsersByCondition")
    public Result queryUsersByCondition(@RequestBody HashMap<String,Object> map) {
        log.info("查询用户条件：=============={}",map);
        
        return userManagementService.queryUsersByConditions(map);
    }
    
    //添加用户
    @PostMapping("/user/addUser")
    public Result addUser(@RequestBody User user) {
        log.info("添加用户：=============={}",user);
        return userManagementService.addUser(user);
    }
    
    //更新用户
    @PostMapping("/user/updateUser")
    public Result updateUser(@RequestBody User user) {
        log.info("更新用户：=============={}",user);
        return userManagementService.updateUser(user);
    }
    
    //数据回显 根据用户Id查询用户信息
    @PostMapping("/user/queryUserById")
    public Result queryUserById(@RequestParam("userId") Integer userId) {
        log.info("根据用户Id查询用户信息：=============={}",userId);
        return userManagementService.queryUserById(userId);
    }
    
    //删除用户 根据用户Id删除
    @PostMapping("/user/deleteUserById")
    public Result deleteUser(@RequestParam("userId") Integer userId) {
        log.info("根据用户Id删除用户：=============={}",userId);
        return userManagementService.deleteUser(userId);
    }
}
