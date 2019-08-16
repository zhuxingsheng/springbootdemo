package com.jack.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 类上面没有使用@Componment,因为在入口类使用了@EnableConfigurationProperties
 * @description: defalut config
 * @author: Jack
 * @create: 2019-08-03 17:33
 */
@ConfigurationProperties("jjk.default")
@Data
public class DefaultConfig {

    private String version;
}
