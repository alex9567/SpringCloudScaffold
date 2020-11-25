package com.chen.core.service.impl;

import com.chen.common.exception.BaseException;
import com.chen.core.sign.CheckSign;
import com.chen.service.OpenService;
import com.chen.service.requestDTO.TestOpenRequestDTO;
import com.chen.service.result.Result;
import com.chen.service.result.ResultEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class OpenServiceImpl implements OpenService {

    @Override
    public Result<String> test(TestOpenRequestDTO requestDTO) {
        if(!CheckSign.check(requestDTO)){
            throw new BaseException("OpenServiceImpl#test", ResultEnum.SIGN_CHECK_ERROR.getCode(),ResultEnum.SIGN_CHECK_ERROR.getMessage());
        }
        return Result.success("success");
    }
}

