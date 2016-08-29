package com.nowcoderExample.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Administrator on 2016/7/22 0022.
 */
@Controller
public class SettingController {
    @RequestMapping("/setting")
    @ResponseBody
    public String setting() {
        return "Setting; ok";
    }
}
