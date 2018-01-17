package com.mx.common.dao;


import com.mx.generator.pojo.SysUser;

import java.util.HashMap;

public interface IFrameDao {
    SysUser logining(HashMap<String, Object> map);
}
