package com.ricka.princy.stationprojet1.repository.conf;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("fjpa.database")
@Getter
@Setter
@NoArgsConstructor
public class ConnectionProperties {
    private String url;
    private String username;
    private String password;
}
