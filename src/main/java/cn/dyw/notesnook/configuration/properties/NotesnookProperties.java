package cn.dyw.notesnook.configuration.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Notesnook properties
 *
 * @author dyw770
 * @date 2024-11-26
 */
@Data
@Component
@ConfigurationProperties(prefix = "notesnook")
public class NotesnookProperties {

    /**
     * Notesnook 默认salt前缀
     */
    private String appSalt;
}
