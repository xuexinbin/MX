package com.mx.system.dao;

import com.mx.generator.pojo.SysCode;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * code管理 Dao
 *
 * @author mx
 */
@Repository
public interface CodeMapper {

    /**
     * 添加代码
     *
     * @param code 代码信息
     */
    Integer addCode(SysCode code);

    /**
     * 编辑代码
     *
     * @param code 代码信息
     */
    void editCode(SysCode code);

    /**
     * 获得已存代码
     *
     * @param type 类型
     * @return list
     */
    List<SysCode> getCode(Integer type);
}