package com.heima.user.controller.v1;

import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.user.dtos.LoginDto;
import com.heima.user.service.ApUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Author: violet
 * Date: 2024/9/1 14:21
 */
@RestController
@RequestMapping("/api/v1/login")
@Api(value = "app端用户登陆", tags = "ap_user", description = "app端用户登陆API")
public class ApUserLoginController {

    @Autowired
    private ApUserService apUserService;

    /**
     * app端登陆
     * @param dto
     * @return
     */
    @PostMapping("/login_auth")
    @ApiOperation("用户登陆")
    public ResponseResult login(@RequestBody LoginDto dto){
        return apUserService.login(dto);
    }
}
