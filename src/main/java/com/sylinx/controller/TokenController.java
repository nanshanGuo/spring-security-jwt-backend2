package com.sylinx.controller;

import com.sylinx.model.LoginUser;
import com.sylinx.model.ResponseInfo;
import com.sylinx.service.TokenService;
import com.sylinx.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TokenController {

    @Autowired
    TokenService tokenService;

    @Autowired
    UserService userService;

    @PostMapping({"/tokencheck"})
    public ResponseInfo token(String token) {
//        String token = user.getToken();
        LoginUser loginUser = tokenService.getLoginUser(token);

        ResponseInfo responseInfo = new ResponseInfo();
        if (loginUser == null) {
            responseInfo.setCode(HttpStatus.BAD_REQUEST.name());
            responseInfo.setMessage("トークンError");
        } else {
            responseInfo.setCode(HttpStatus.OK.name());
            responseInfo.setMessage("トークンOK");
        }
        return responseInfo;
    }

    @PostMapping({"/getUser"})
    public LoginUser user(String userName) {
        LoginUser user = userService.getUser(userName);
        return user;
    }
}
