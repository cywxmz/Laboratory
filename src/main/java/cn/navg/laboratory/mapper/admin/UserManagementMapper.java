package cn.navg.laboratory.mapper.admin;

import cn.navg.laboratory.pojo.Result;
import cn.navg.laboratory.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

@Mapper
public interface UserManagementMapper {
    //查询所有用户
    public List<User> queryAllUsers(@Param("currentPage") Integer currentPage, @Param("pageSize") Integer pageSize);

    //查询所有用户的总条数
    public Integer queryAllUsersCount();

    //按条件查询用户
    public List<User> queryUsersByConditions(HashMap<String,Object> map);
    
    //按条件查询用户的总条数
    public Integer queryUsersByConditionsCount(HashMap<String,Object> map);
    
    //添加用户
    public Integer addUser(User user);
    
    //添加用户的同时添加用户角色关系
    public Integer addUserRole(@Param("UserId") Integer UserId, @Param("RoleId") Integer RoleId, @Param("CreateTime") String CreateTime);
    
    //查询刚才最新添加的用户的UserId 根据用户账号查询
    public Integer queryUserIdByUserAccount(String userAccount);
    
    //查询单个用户是否存在,根据用户账号查询
    public User queryUserExistByUserAccount(String userAccount);
    
    //更新用户 根据用户Id更新
    public Integer updateUser(User user);
    
    //更新用户角色关系 根据用户Id更新
    public Integer updateUserRole(@Param("UserId") Integer UserId, @Param("RoleId") Integer RoleId);

    //数据回显 根据用户Id查询用户信息
    public User queryUserById(Integer UserId);
    
    //数据回显 查询角色类型 根据角色Id查询
    public String queryRoleTypeById(Integer RoleId);
    
    //删除用户 根据用户Id删除
    public Integer deleteUser(Integer UserId);
}
