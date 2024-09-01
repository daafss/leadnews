package com.heima.model.user.dtos;

import lombok.Data;

/**
 * Author: violet
 * Date: 2024/9/1 14:29
 */
@Data
public class LoginDto {

    /**
     * 手机号
     */
    // @ApiModelProperty(value = "手机号",required = true)
    private String phone;

    /**
     * 密码
     */
    // @ApiModelProperty(value = "密码",required = true)
    private String password;
}
