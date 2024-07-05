package com.hieuubuntu.configservice.services;

import com.hieuubuntu.configservice.dtos.requests.CreateApiRouteRequest;
import com.hieuubuntu.configservice.entities.ApiRoute;
import com.hieuubuntu.configservice.publishers.RedisMessagePublisher;
import com.hieuubuntu.configservice.repositories.ApiRouteRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ApiRouteServiceImpl implements ApiRouteService {
    public static final String REFRESH_ROUTE_MSG = "Update";

    private final ApiRouteRepository apiRouteRepository;

    private final RedisMessagePublisher redisMessagePublisher;

    @Value("${redis.channel.refresh-route}")
    private String channel;

    @Override
    public void create(CreateApiRouteRequest request, Long userId) {
        var apiRoute = ApiRoute.builder()
                .path(request.getPath())
                .uri(request.getUri())
                .pattern(request.getPattern())
                .path(request.getPath())
                .replacement(request.getReplacement())
                .method(request.getMethod())
                .connectTimeout(request.getConnectTimeout())
                .responseTimeout(request.getResponseTimeout())
                .retries(request.getRetries())
                .createdBy(userId)
                .modifiedBy(userId)
                .build();

        apiRouteRepository.save(apiRoute);

        redisMessagePublisher.publish(channel, REFRESH_ROUTE_MSG);
    }

    @Override
    public void update() {

    }

    @Override
    public void deleteById(Integer id) {

    }
}
