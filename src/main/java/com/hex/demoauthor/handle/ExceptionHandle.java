package com.hex.demoauthor.handle;

import com.hex.demoauthor.domain.Result;
import com.hex.demoauthor.enums.ResultEnum;
import com.hex.demoauthor.exception.HexException;
import com.hex.demoauthor.util.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 异常捕获
 * User: hexuan
 * Date: 2017/8/14
 * Time: 下午3:04
 */
@ControllerAdvice
@Slf4j
public class ExceptionHandle {
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result handle(Exception e) {
        if (e instanceof HexException) {
            HexException hexException = (HexException) e;
            return ResultUtil.error(hexException.getCode(), hexException.getMessage());
        } else {
            log.error("【系统异常】{}", e);
            return ResultUtil.error(ResultEnum.UN_KNOW_ERROR);
        }
    }
}
