package com.heima.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.common.enums.AppHttpCodeEnum;
import com.heima.model.user.dtos.LoginDto;
import com.heima.model.user.pojos.ApUser;
import com.heima.user.mapper.ApUserMapper;
import com.heima.user.service.ApUserService;
import com.heima.utils.common.AppJwtUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;

import java.util.HashMap;

/**
 * Author: violet
 * Date: 2024/9/1 14:26
 */
@Service
public class ApUserServiceImpl extends ServiceImpl<ApUserMapper, ApUser> implements ApUserService {
    /**
     * app端登陆
     *
     * @param dto
     * @return
     */
    @Override
    public ResponseResult login(LoginDto dto) {
        //1.正常登录（手机号+密码登录）
        if (!StringUtils.isEmpty(dto.getPhone()) && !StringUtils.isEmpty(dto.getPassword())){
            //1.1查询用户
            LambdaQueryWrapper<ApUser> wrapper = new LambdaQueryWrapper<>();
            // 另外一种创建wrapper的方法
            // LambdaQueryWrapper<ApUser> wrapper = Wrappers.<ApUser>lambdaQuery();
            wrapper.eq(ApUser::getPhone, dto.getPhone());
            ApUser apUser = getOne(wrapper);
            if (apUser == null){
                return ResponseResult.errorResult(AppHttpCodeEnum.DATA_NOT_EXIST,"用户不存在");
            }

            //1.2 比对密码
            String salt = apUser.getSalt();
            String password = dto.getPassword();
            password = DigestUtils.md5DigestAsHex((password + salt).getBytes());
            if (!password.equals(apUser.getPassword())){
                return ResponseResult.errorResult(AppHttpCodeEnum.LOGIN_PASSWORD_ERROR,"密码错误");
            }
            //1.3 返回数据 jwt
            HashMap<String, Object> map = new HashMap<>();
            map.put("token", AppJwtUtil.getToken(apUser.getId().longValue()));
            apUser.setSalt("");
            apUser.setPassword("");
            map.put("user", apUser);
            return ResponseResult.okResult(map);
        }else {
            //2.游客 同样返回token id = 0
            HashMap<String, Object> map = new HashMap<>();
            map.put("token", AppJwtUtil.getToken(0L));
            return ResponseResult.okResult(map);
        }
    }
}
