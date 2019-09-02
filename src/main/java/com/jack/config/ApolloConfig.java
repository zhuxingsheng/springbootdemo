package com.jack.config;


import com.ctrip.framework.apollo.Config;
import com.ctrip.framework.apollo.model.ConfigChange;
import com.ctrip.framework.apollo.model.ConfigChangeEvent;
import com.ctrip.framework.apollo.spring.annotation.ApolloConfigChangeListener;
import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

@EnableApolloConfig
@Configuration
@Slf4j
public class ApolloConfig {

    /**
     * 从apollo获取配置值
     */
    @com.ctrip.framework.apollo.spring.annotation.ApolloConfig
    private Config config;

    /**
     * 当配置修改发布后，这儿调用时，会获取到新值
     * @return
     */
    public String getVersion(){
        return config.getProperty("jjk.default.version","default-version");
    }


    /**
     * 主动发现配置发生变更
     * @param changeEvent
     */
    @ApolloConfigChangeListener
    public void configChanged(ConfigChangeEvent changeEvent) {
        log.info("changedKeys:{}",changeEvent.changedKeys());
        changeEvent.changedKeys().forEach(key -> {
            ConfigChange change = changeEvent.getChange(key);
            log.info(String.format("Found JavaConfigSample change - key: %s, oldValue: %s, newValue: %s, changeType: %s", change.getPropertyName(), change.getOldValue(), change.getNewValue(), change.getChangeType()));
        });
    }
}
