package com.hieuubuntu.configservice.controller;

import com.hieuubuntu.configservice.configs.AppConfig;
import com.hieuubuntu.configservice.constants.HTTPHeaderConstant;
import com.hieuubuntu.configservice.dtos.DefaultResponse;
import com.hieuubuntu.configservice.dtos.requests.CreateApiRouteRequest;
import com.hieuubuntu.configservice.services.ApiRouteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping(AppConfig.API_PATH + "/api-routes")
@RestController
@RequiredArgsConstructor
public class ApiRouteController {
    private final ApiRouteService apiRouteService;

    @PostMapping
    public ResponseEntity<DefaultResponse<Object>> create(
            @RequestBody CreateApiRouteRequest request,
            @RequestHeader(HTTPHeaderConstant.USER_ID) Long userId) {

        apiRouteService.create(request, userId);
        return ResponseEntity.ok(DefaultResponse.success(null));
    }

}
