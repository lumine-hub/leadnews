package com.heima.admin.controller;

import com.heima.admin.service.AdminService;
import com.heima.model.admin.dtos.AdChannel;
import com.heima.model.admin.dtos.AdLoginDto;
import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.wemedia.dtos.ChannelDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;

    @PostMapping("/login/in")
    public ResponseResult login(@RequestBody AdLoginDto dto) {
        return adminService.login(dto);
    }

}
