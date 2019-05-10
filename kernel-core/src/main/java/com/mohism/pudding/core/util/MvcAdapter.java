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
package com.mohism.pudding.core.util;

import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.mohism.pudding.core.converter.RequestDataMessageConvert;
import com.mohism.pudding.core.converter.RequestDataTypeMethodProcessor;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 统一请求转化器默认配置
 *
 * @author fengshuonan
 * @Date 2018/2/24 14:39
 */
public class MvcAdapter {

    public static RequestMappingHandlerAdapter requestMappingHandlerAdapter(
            RequestMappingHandlerAdapter original,
            FastJsonHttpMessageConverter fastJsonHttpMessageConverter,
            RequestDataMessageConvert requestDataMessageConvert) {

        List<HttpMessageConverter<?>> converters = new ArrayList<>();
        converters.add(requestDataMessageConvert);

        List<HandlerMethodArgumentResolver> argumentResolvers = new ArrayList<>();
        argumentResolvers.add(new RequestDataTypeMethodProcessor(converters));
        original.setCustomArgumentResolvers(argumentResolvers);

        List<HttpMessageConverter<?>> list = new LinkedList<>();

        StringHttpMessageConverter stringHttpMessageConverter = new StringHttpMessageConverter();
        stringHttpMessageConverter.setWriteAcceptCharset(false);  // see SPR-7316
        list.add(stringHttpMessageConverter);

        list.add(fastJsonHttpMessageConverter);
        original.setMessageConverters(list);
        return original;
    }
}