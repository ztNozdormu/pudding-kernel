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
package com.mohism.pudding.kernel.scanner.modular.listener;

import com.mohism.pudding.kernel.model.api.ResourceService;
import com.mohism.pudding.kernel.model.resource.ResourceDefinition;
import com.mohism.pudding.kernel.scanner.config.properties.ScannerProperties;
import com.mohism.pudding.kernel.scanner.modular.factory.ApiResourceFactory;
import org.springframework.beans.BeansException;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationListener;

import java.util.Map;

/**
 * 监听项目初始化完毕,报告到服务器资源
 *
 * @author fengshuonan
 * @date 2018-02-06 13:05
 */
public class ResourceReportListener implements ApplicationListener<ApplicationReadyEvent>, ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {

        System.out.println("发送本系统的所有资源到roses-auth服务开始！");

        //获取当前系统的所有资源
        ApiResourceFactory resourceFactory = applicationContext.getBean(ApiResourceFactory.class);
        Map<String, Map<String, ResourceDefinition>> modularResources = resourceFactory.getModularResources();

        //发送资源到资源服务器
        ScannerProperties scannerProperties = applicationContext.getBean(ScannerProperties.class);
        ResourceService resourceService = applicationContext.getBean(ResourceService.class);
        resourceService.reportResources(scannerProperties.getAppCode(), modularResources);

        System.out.println("发送本系统的所有资源到roses-auth服务完毕！");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
