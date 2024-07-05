package com.hieuubuntu.configservice.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = ApiRoute.TABLE_NAME)
public class ApiRoute {
    public static final String TABLE_NAME = "api_routes";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Size(max = 255)
    @Column(name = "path")
    private String path;

    @Size(max = 255)
    @Column(name = "uri")
    private String uri;

    @Size(max = 255)
    @Column(name = "pattern")
    private String pattern;

    @Size(max = 255)
    @Column(name = "replacement")
    private String replacement;

    @Column(name = "retries", columnDefinition = "tinyint UNSIGNED")
    private Integer retries;

    @Column(name = "response_timeout")
    private Integer responseTimeout;

    @Column(name = "connect_timeout")
    private Integer connectTimeout;

    @Size(max = 50)
    @Column(name = "method", length = 50)
    private String method;

    @Column(name = "created_at")
    @CreationTimestamp
    private LocalDateTime createdAt;

    @ColumnDefault("'0'")
    @Column(name = "created_by")
    private Long createdBy;

    @Column(name = "modified_at")
    @UpdateTimestamp
    private LocalDateTime modifiedAt;

    @ColumnDefault("'0'")
    @Column(name = "modified_by")
    private Long modifiedBy;

}