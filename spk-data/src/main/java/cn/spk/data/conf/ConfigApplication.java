package cn.spk.data.conf;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(ClientUserConfigProperties.class)
public class ConfigApplication {

}
