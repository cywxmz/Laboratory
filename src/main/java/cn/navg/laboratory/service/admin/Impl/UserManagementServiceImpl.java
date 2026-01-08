package cn.navg.laboratory.service.admin.Impl;

import cn.navg.laboratory.mapper.admin.UserManagementMapper;
import cn.navg.laboratory.pojo.Page;
import cn.navg.laboratory.pojo.Result;
import cn.navg.laboratory.pojo.User;
import cn.navg.laboratory.service.admin.UserManagementService;
import cn.navg.laboratory.utils.GetNowDataTime;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Slf4j
@Service
//用户管理服务实现类
public class UserManagementServiceImpl implements UserManagementService {
    
    @Autowired
    private UserManagementMapper userManagementMapper;
    
    //查询所有用户
    @Override
    public List<User> queryAllUsers(Integer currentPage, Integer pageSize) {
        System.out.println("currentPage = " + currentPage);
        System.out.println("pageSize = " + pageSize);
        //offset = (current_page - 1) * page_size  （当前页-1）* 每页条数
//        System.out.println("offset = " + ((currentPage - 1) * pageSize));
        return userManagementMapper.queryAllUsers((currentPage - 1) * pageSize, pageSize);
    }
    
    //查询所有用户的条数
    @Override
    public Integer queryAllUsersCount() {
        return userManagementMapper.queryAllUsersCount();
    }
    
    //按条件查询用户
    @Override
    public Result queryUsersByConditions(HashMap<String, Object> map) {
        Integer currentPage = (Integer) map.get("currentPage");
        Integer PageSize = (Integer) map.get("PageSize");
        
        // 添加空值检查
        if (currentPage == null) currentPage = 1;
        if (PageSize == null) PageSize = 10;
        
        HashMap<String, Object> countMap = new HashMap<>();
        countMap.put("status", map.get("status"));
        countMap.put("roleType", map.get("roleType"));
        countMap.put("username", map.get("username"));
        
        map.put("currentPage", (currentPage - 1) * PageSize);
        map.put("PageSize", PageSize);
        
        //查询用户的总条数
        Integer total = userManagementMapper.queryUsersByConditionsCount(countMap);
        //分页信息
        Page page = new Page(currentPage, PageSize, total);
        
        log.info("查询用户条件：==============map====={}", map);
        
        //查询用户
        List<User> users = userManagementMapper.queryUsersByConditions(map);
        
        System.out.println("total = " + total);
        
        
        return Result.success(users, page);
    }
    
    @Override
    public Result addUser(User user) {
        //查询用户是否存在,根据用户账号查询
        User existUser = userManagementMapper.queryUserExistByUserAccount(user.getUserAccount());
        if (existUser != null) {
            return Result.error("用户账号已存在");
        }
        
        
        user.setCreateTime(GetNowDataTime.formattedTime);
        user.setUpdateTime(GetNowDataTime.formattedTime);
        
        //根据角色类型设置角色Id
        int roleId = switch (user.getRoleType()) {
            case "admin" -> 1;
            case "teacher" -> 2;
            case "student" -> 3;
            default -> 0;
        };
        //添加用户
        int result = userManagementMapper.addUser(user);
        //查询刚才最新添加的用户的UserId 根据用户账号查询
        Integer userId = userManagementMapper.queryUserIdByUserAccount(user.getUserAccount());
        //添加用户角色关系
        int roleResult = userManagementMapper.addUserRole(userId, roleId, GetNowDataTime.formattedTime);
        if (result > 0 && roleResult > 0) {
            return Result.success("添加用户成功");
        }
        
        return Result.error("添加用户失败");
    }
    
    //更新用户
    @Override
    public Result updateUser(User user) {
        //校验用户名,账号,密码,状态,邮箱,手机号是否为空
        if (user.getUserName().isEmpty() || user.getUserAccount().isEmpty() || user.getUserPassword().isEmpty() || user.getUserStatus() == null || user.getUserEmail().isEmpty() || user.getUserPhone().isEmpty()) {
            return Result.error("信息不完整,请填写完整信息");
        }
        
        //判断传递的角色类型 再根据角色类型设置角色Id
        int roleId = switch (user.getRoleType()) {
            case "admin" -> 1;
            case "teacher" -> 2;
            case "student" -> 3;
            default -> 0;
        };
        
        //1.先更新用户的角色关系
        int roleResult = userManagementMapper.updateUserRole(user.getUserId(), roleId);
        if (roleResult > 0) {
            //2.再更新用户的其他信息
            int result = userManagementMapper.updateUser(user);
            if (result > 0) {
                return Result.success(user.getUserName() + "的信息更新成功");
            }
        }
        return Result.error("更新用户失败");
    }
    
    //数据回显 根据用户Id查询用户信息
    @Override
    public Result queryUserById(Integer userId) {
        User user = userManagementMapper.queryUserById(userId);
        if (user != null) {
            String roleType = userManagementMapper.queryRoleTypeById(userId);
            user.setRoleType(roleType);
            return Result.success(user);
        }
        return Result.error("用户信息查询失败");
    }
    
    //删除用户 根据用户Id删除
    @Override
    public Result deleteUser(Integer userId) {
        int result = userManagementMapper.deleteUser(userId);
        if (result > 0) {
            return Result.success("删除用户成功");
        }
        return Result.error("删除用户失败");
    }
}
