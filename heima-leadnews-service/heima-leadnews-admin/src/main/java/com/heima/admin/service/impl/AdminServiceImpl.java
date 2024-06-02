package com.heima.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.heima.admin.mapper.AdminMapper;
import com.heima.admin.service.AdminService;
import com.heima.model.admin.dtos.AdLoginDto;
import com.heima.model.admin.pojos.AdUser;
import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.common.enums.AppHttpCodeEnum;
import com.heima.utils.common.AppJwtUtil;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
@Transactional
public class AdminServiceImpl extends ServiceImpl<AdminMapper, AdUser> implements AdminService {
    public ResponseResult login(AdLoginDto dto) {
        if (!StringUtils.isNotBlank(dto.getName()) || !StringUtils.isNotBlank(dto.getPassword())) {
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);
        }
        LambdaQueryWrapper<AdUser> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(AdUser::getName, dto.getName());
        AdUser adUser = getOne(wrapper);
        if (adUser == null) {
            return ResponseResult.errorResult(AppHttpCodeEnum.DATA_NOT_EXIST,"用户信息不存在");
        }
        adUser.setLoginTime(new Date());
        updateById(adUser);
        String salt = adUser.getSalt();
        String password = dto.getPassword();
        String pswd = DigestUtils.md5Hex((password + salt).getBytes());
        if (!StringUtils.equals(pswd, adUser.getPassword())) {
            return ResponseResult.errorResult(AppHttpCodeEnum.LOGIN_PASSWORD_ERROR);
        }
        Map<String,Object> map = new HashMap<>();
        map.put("token",AppJwtUtil.getToken(adUser.getId().longValue()));
        map.put("user",adUser);
        return ResponseResult.okResult(map);
    }
}
