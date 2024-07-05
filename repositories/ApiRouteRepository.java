package com.hieuubuntu.configservice.repositories;

import com.hieuubuntu.configservice.entities.ApiRoute;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApiRouteRepository extends JpaRepository<ApiRoute, Integer> {
}
