package com.hex.demoauthor.service;

import com.hex.demoauthor.domain.Operator;
import com.hex.demoauthor.enums.ResultEnum;
import com.hex.demoauthor.exception.HexException;
import com.hex.demoauthor.repository.OperatorRepository;
import com.hex.demoauthor.util.Md5SaltTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;

/**
 * User: hexuan
 * Date: 2019/2/28
 * Time: 3:31 PM
 */
@Service
public class OperatorServiceImpl implements OperatorService {

    @Autowired
    private OperatorRepository operatorRepository;

    @Override
    public Operator save(Operator operator) {
        return operatorRepository.save(operator);
    }

    @Override
    public Optional<Operator> findById(String id) {
        return operatorRepository.findById(id);
    }

    @Override
    public void delete(String id) {
        operatorRepository.deleteById(id);
    }

    @Override
    public Operator login(String name, String password) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        Operator operator = operatorRepository.findByName(name);

        boolean result = false;
        if (null != operator && 2 == operator.getState()) {
            result = Md5SaltTool.validPassword(password, operator.getPassword());
        }

        if (!result) {
            throw new HexException(ResultEnum.ERROR_LOGIN);
        }

        return operator;
    }
}
