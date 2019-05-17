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
package com.mohism.pudding.kernel.scanner.config;


import com.mohism.pudding.kernel.scanner.config.properties.ScannerProperties;
import com.mohism.pudding.kernel.scanner.modular.ApiResourceScaner;
import com.mohism.pudding.kernel.scanner.modular.factory.ApiResourceFactory;
import com.mohism.pudding.kernel.scanner.modular.factory.DefaultApiResourceFactory;
import com.mohism.pudding.kernel.scanner.modular.listener.ResourceReportListener;
import com.mohism.pudding.kernel.scanner.modular.service.ResourceCollectService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.mohism.pudding.kernel.model.constants.ConfigPrefixConstants.SCANNER_PREFIX;

/**
 * 扫描器默认配置
 * <p>
 * 注意：资源扫描的使用需要配置ResourceService这个Bean到Spring容器
 *
 * @author fengshuonan
 * @date 2018-02-06 17:25
 */
@Configuration
public class ScannerAutoConfiguration {

    @Value("${spring.application.name}")
    private String applicationName;

    @Bean
    @ConfigurationProperties(prefix = SCANNER_PREFIX)
    public ScannerProperties scannerProperties() {
        return new ScannerProperties();
    }

    /**
     * 资源工厂
     */
    @Bean
    public ApiResourceFactory apiResourceFactory() {
        return new DefaultApiResourceFactory();
    }

    /**
     * 资源收集服务
     */
    @Bean
    public ResourceCollectService resourceCollectService(ApiResourceFactory apiResourceFactory, ScannerProperties scannerProperties) {
        return new ResourceCollectService(apiResourceFactory, scannerProperties);
    }

    /**
     * 资源扫描器
     */
    @Bean
    public ApiResourceScaner apiResourceScaner(ApiResourceFactory apiResourceFactory, ScannerProperties scannerProperties) {
        return new ApiResourceScaner(apiResourceFactory, scannerProperties, applicationName);
    }

    /**
     * 资源扫描之后的资源汇报操作（向roses-auth服务）
     */
    @Bean
    @ConditionalOnProperty(prefix = SCANNER_PREFIX, name = "open", havingValue = "true")
    public ResourceReportListener resourceReportListener() {
        return new ResourceReportListener();
    }
}
