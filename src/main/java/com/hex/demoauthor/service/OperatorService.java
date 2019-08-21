package com.hex.demoauthor.service;


import com.hex.demoauthor.domain.Operator;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;

/**
 * User: hexuan
 * Date: 2019/2/28
 * Time: 3:22 PM
 */
public interface OperatorService {

    Operator save(Operator operator);

    Optional<Operator> findById(String id);

    void delete(String id);

    Operator login(String name, String password) throws UnsupportedEncodingException, NoSuchAlgorithmException;
}
