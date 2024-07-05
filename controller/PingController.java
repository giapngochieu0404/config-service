package com.hieuubuntu.configservice.controller;

import com.hieuubuntu.configservice.dtos.DefaultResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/ping")
public class PingController {
    @GetMapping()
    public ResponseEntity<DefaultResponse<Boolean>> ping() {
        return ResponseEntity.ok(DefaultResponse.success(Boolean.TRUE));
    }
}
