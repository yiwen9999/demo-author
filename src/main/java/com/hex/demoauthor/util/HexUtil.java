package com.hex.demoauthor.util;

import com.hex.demoauthor.enums.ResultEnum;
import com.hex.demoauthor.exception.HexException;
import com.hex.demoauthor.vo.OperatorVO;
import org.springframework.validation.BindingResult;

import javax.servlet.http.HttpServletRequest;

/**
 * User: hexuan
 * Date: 2017/9/19
 * Time: 上午10:57
 */
public class HexUtil {

    public static void validateBindResult(BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new HexException(ResultEnum.ERROR_PARAM.getCode(), bindingResult.getFieldError().getDefaultMessage());
        }
    }

    public static OperatorVO getOperatorVO(HttpServletRequest request) {
        return JwtUtil.parseToken(request.getHeader("Authorization"));
    }

}
