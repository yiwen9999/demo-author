package com.hex.demoauthor.converter;

import com.hex.demoauthor.domain.Operator;
import com.hex.demoauthor.vo.OperatorVO;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;


public class Operator2OperatorVOConverter {
    public static OperatorVO converter(Operator operator) {
        OperatorVO operatorVO = new OperatorVO();
        BeanUtils.copyProperties(operator, operatorVO);
        return operatorVO;
    }

    public static List<OperatorVO> converter(List<Operator> operatorList) {
        List<OperatorVO> operatorVOList = new ArrayList<>();
        OperatorVO operatorVO;
        for (Operator operator : operatorList) {
            operatorVO = converter(operator);
            operatorVOList.add(operatorVO);
        }
        return operatorVOList;
    }
}
