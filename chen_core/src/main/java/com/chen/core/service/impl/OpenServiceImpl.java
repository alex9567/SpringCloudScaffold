package com.chen.core.service.impl;

import com.chen.common.exception.BaseException;
import com.chen.core.sign.CheckSign;
import com.chen.service.OpenService;
import com.chen.service.requestDTO.TestOpenRequestDTO;
import com.chen.common.result.Result;
import com.chen.common.exception.ErrorEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class OpenServiceImpl implements OpenService {

    @Override
    public Result<String> test(TestOpenRequestDTO requestDTO) {
        if(!CheckSign.check(requestDTO)){
            throw new BaseException("OpenServiceImpl#test", ErrorEnum.SIGN_CHECK_ERROR.getCode(),ErrorEnum.SIGN_CHECK_ERROR.getMessage());
        }
        return Result.success("success");
    }
}

