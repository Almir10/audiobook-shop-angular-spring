package com.abd.spring_boot_audiobooks.controller;

import com.abd.spring_boot_audiobooks.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/internal")
public class InternalApiController {

    @Autowired
    private IUserService userService;

    @PutMapping("make-admin/{username}")
    public ResponseEntity<?> makeAdmin(@PathVariable String username){

        userService.makeAdmin(username);

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
