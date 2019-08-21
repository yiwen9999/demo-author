package com.hex.demoauthor.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * User: hexuan
 * Date: 2019/8/19
 * Time: 10:56 AM
 */
@Controller
@RequestMapping("/page")
public class PageController {

    @GetMapping("/token")
    public String token() {
        return "token";
    }

}
