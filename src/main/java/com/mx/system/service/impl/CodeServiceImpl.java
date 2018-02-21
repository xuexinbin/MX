package com.mx.system.service.impl;

import com.mx.generator.pojo.SysCode;
import com.mx.system.dao.CodeMapper;
import com.mx.system.model.User;
import com.mx.system.service.ICodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * 代码管理service
 *
 * @author mx
 */
@Service
@Transactional
public class CodeServiceImpl implements ICodeService {

    @Autowired
    CodeMapper codeMapper;

    @Override
    public Integer addCode(SysCode code) {
        if (code.getId() == null) {
            codeMapper.addCode(code);
        } else {
            codeMapper.editCode(code);
        }
        return code.getId();
    }

    @Override
    public List<SysCode> getCode(Integer type) {
        return codeMapper.getCode(type);
    }

}
