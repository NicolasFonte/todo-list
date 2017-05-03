package com.yordex.test.dl.rest;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
class LoginResource {

    @RequestMapping("/user")
    public Principal user(Principal user) {
        return user;
    }
}
