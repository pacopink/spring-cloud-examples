package com.neo.controller;

import com.neo.dao.SysUserRepository;
import com.neo.entity.Result;
import com.neo.entity.SysUser;
import com.neo.exception.http.MethodNotAllowedException405;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.server.PathParam;

@RestController
public class SysUserController {
    private static final Logger logger = LoggerFactory.getLogger(SysUserController.class);
    @Autowired
    SysUserRepository sysUserRepository;

    @RequestMapping(value="/user/{name}", method=RequestMethod.GET)
    public SysUser getUserByName(@PathVariable("name")String name, HttpServletResponse response) {
        logger.info("try to findByName {}", name);
        SysUser user = sysUserRepository.findByName(name);
        logger.info("find user {}", user);
        if (user==null) {
            response.setStatus(HttpStatus.NOT_FOUND.value());
        }
        return user;
    }

    @RequestMapping(value="/user/{name}", method=RequestMethod.DELETE)
    public Result deleteUserByName(@PathVariable("name")String name,
        HttpServletResponse response) {
        logger.info("try to findByName {}, before delete", name);
        if (sysUserRepository.deleteByName(name)>0) {
            Result result = Result.makeSuccessResult(name);
            return result;
        } else {
            Result result = new Result(Result.FAILURE, "name not found", name);
            response.setStatus(HttpStatus.NOT_FOUND.value());
            return result;
        }
    }

    @RequestMapping(value="/user", method = {RequestMethod.POST, RequestMethod.PUT})
    public Result addOrUpdateUser(@RequestBody()SysUser user,
                          HttpServletRequest request,
                          HttpServletResponse response) {
        String method = request.getMethod().toUpperCase();
        String name = user.getName();
        Result res = null;

        boolean isExists = sysUserRepository.existsByName(name);
        if (isExists && method.equals("POST")) { //POST 对于存在的要报错
            res = new Result(Result.FAILURE, "user already exists", name);
            response.setStatus(HttpStatus.CONFLICT.value());
        } else {
            logger.info("addOrUpdateUser: {}", name, user);
            SysUser u = sysUserRepository.saveAndFlush(user);
            if (u != null) {
                res = new Result(Result.SUCCESS, Result.MESSAGE_OK, u);
                //根据原先存在与否，返回200或者201
                response.setStatus(isExists ? HttpStatus.OK.value() : HttpStatus.CREATED.value());
            } else {
                res = new Result(Result.FAILURE, "internal error, contact your admin", null);
                response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            }
        }
        return res;
    }
}
