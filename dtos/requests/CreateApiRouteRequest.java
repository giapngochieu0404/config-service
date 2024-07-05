package com.hieuubuntu.configservice.dtos.requests;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonNaming(SnakeCaseStrategy.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateApiRouteRequest {

  private String path;
  @NotBlank
  private String uri;
  private String pattern;
  private String replacement;
  private String method;
  private Integer retries;
  private Integer responseTimeout;
  private Integer connectTimeout;

}
