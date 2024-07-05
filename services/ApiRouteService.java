package com.hieuubuntu.configservice.services;

import com.hieuubuntu.configservice.dtos.requests.CreateApiRouteRequest;

public interface ApiRouteService {
    void create(CreateApiRouteRequest request, Long userId);
    void update();
    void deleteById(Integer id);
}
