package com.hex.demoauthor.controller;

import com.hex.demoauthor.domain.Operator;
import com.hex.demoauthor.domain.Result;
import com.hex.demoauthor.enums.ResultEnum;
import com.hex.demoauthor.form.OperatorForm;
import com.hex.demoauthor.service.OperatorService;
import com.hex.demoauthor.util.HexUtil;
import com.hex.demoauthor.util.JwtUtil;
import com.hex.demoauthor.util.Md5SaltTool;
import com.hex.demoauthor.util.ResultUtil;
import com.hex.demoauthor.vo.OperatorVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * User: hexuan
 * Date: 2019/3/11
 * Time: 2:42 PM
 */
@RestController
public class OperatorController {

    @Autowired
    private OperatorService operatorService;

    @PostMapping("/login")
    public Result login(String name, String password) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        Operator operator = operatorService.login(name, password);
        if (null != operator) {
            Map<String, String> map = new HashMap<>();
            map.put("token", JwtUtil.generateToken(operator));
            return ResultUtil.success(map);
        } else {
            return ResultUtil.error(ResultEnum.ERROR_LOGIN.getCode(), ResultEnum.ERROR_LOGIN.getMsg());
        }
    }

    @GetMapping("/operatorInfo")
    public Result operatorInfo(HttpServletRequest request) {
        OperatorVO operatorVO = HexUtil.getOperatorVO(request);
        return ResultUtil.success(operatorVO);
    }

    @PostMapping("/register")
    public Result register(@Valid OperatorForm operatorForm, BindingResult bindingResult) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        HexUtil.validateBindResult(bindingResult);

        Operator operator = new Operator();
        BeanUtils.copyProperties(operatorForm, operator);

        operator.setPassword(Md5SaltTool.getEncryptedPwd(operator.getPassword()));

        Map<String, String> map = new HashMap<>();
        map.put("token", JwtUtil.generateToken(operatorService.save(operator)));
        return ResultUtil.success(map);
    }

    @PostMapping("/updatePasswordAdmin")
    public Result updatePasswordAdmin(String operatorId, String password) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        Optional<Operator> operatorOptional = operatorService.findById(operatorId);
        Operator operator = null;
        if (operatorOptional.isPresent()) {
            operator = operatorOptional.get();
            operator.setPassword(Md5SaltTool.getEncryptedPwd(password));
            operatorService.save(operator);
        }
        return ResultUtil.success(operator);
    }
}
