package com.mx.system.service;

import com.mx.generator.pojo.SysCode;

import java.util.List;

/**
 * code管理service
 *
 * @author mx
 */
public interface ICodeService {

    /**
     * 添加代码
     *
     * @param code 代码信息
     */
    Integer addCode(SysCode code);

    /**
     * 获得已存代码
     *
     * @param type 类型
     * @return list
     */
    List<SysCode> getCode(Integer type);
}
