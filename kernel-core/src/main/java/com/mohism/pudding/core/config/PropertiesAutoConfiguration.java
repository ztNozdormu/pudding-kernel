/**
 * Copyright 2018-2020 stylefeng & fengshuonan (sn93@qq.com)
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.mohism.pudding.core.config;

import com.mohism.pudding.core.config.properties.AppNameProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * 默认的配置
 *
 * @author fengshuonan
 * @date 2018-01-07 12:33
 */
@Configuration
@PropertySource("classpath:/default-config.properties")
public class PropertiesAutoConfiguration {

    @Bean
    @ConfigurationProperties(prefix = "spring.application.name")
    public AppNameProperties appNameProperties() {
        return new AppNameProperties();
    }
}
