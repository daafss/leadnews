package com.heima.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.user.dtos.LoginDto;
import com.heima.model.user.pojos.ApUser;

/**
 * Author: violet
 * Date: 2024/9/1 14:25
 */
public interface ApUserService extends IService<ApUser> {

    /**
     * app端登陆
     * @param dto
     * @return
     */
    ResponseResult login(LoginDto dto);
}
