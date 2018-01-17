package com.mx.system.dao;

import com.mx.system.model.MasterUserEntity;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

public interface MasterUserEntityMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MasterUserEntity record);

    int insertSelective(MasterUserEntity record);

    MasterUserEntity selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MasterUserEntity record);

    int updateByPrimaryKey(MasterUserEntity record);

    /**
     * 登录
     * @param id
     * @return
     */
    @Select(value="SELECT * from master_user a where a.order_customer_id=#{id}")
    Map getUserByCustomerId(@Param("id") Integer id);

    /**
     * 登录
     * @param username
     * @param password
     * @return
     */
    @Select(value="SELECT * from master_user a where a.user_name=#{username} and a.password=#{password} and user_type=#{usertype} and enablef=0 and deletef=0")
    Map login(@Param("username") String username, @Param("password") String password, @Param("usertype") Integer usertype);

    /**
     * ERP登录
     * @param username
     * @param password
     * @return
     */
    @Select(value="SELECT * from master_user a where a.user_name=#{username} and a.password=#{password} and user_type != 3 and enablef=0 and deletef=0")
    Map erpLogin(@Param("username") String username, @Param("password") String password);

    /**
     * 按类别获取用户
     * @param type
     * @return
     */
    @Select(value="SELECT * from master_user a where a.user_type=#{type} and a.enablef=0 and a.deletef=0")
    List<Map> getUserByType(@Param("type") Integer type);
}