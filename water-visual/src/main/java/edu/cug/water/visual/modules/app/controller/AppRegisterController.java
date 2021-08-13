/**
 * Copyright (c) 2016-2019 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package edu.cug.water.visual.modules.app.controller;


import edu.cug.water.visual.common.utils.R;
import edu.cug.water.visual.common.validator.ValidatorUtils;
import edu.cug.water.visual.modules.app.entity.UserEntity;
import edu.cug.water.visual.modules.app.form.RegisterForm;
import edu.cug.water.visual.modules.app.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * 注册
 *
 * @author Mark sunlightcs@gmail.com
 */
@RestController
@RequestMapping("/app")
@Api("APP注册接口")
public class AppRegisterController {
    @Autowired
    private UserService userService;

    @PostMapping("register")
    @ApiOperation("注册")
    public R register(@RequestBody RegisterForm form){
        //表单校验
        ValidatorUtils.validateEntity(form);

        UserEntity user = new UserEntity();
        user.setMobile(form.getMobile());
        user.setUsername(form.getMobile());
        user.setPassword(DigestUtils.sha256Hex(form.getPassword()));
        user.setCreateTime(new Date());
        userService.save(user);

        return R.ok();
    }
}
