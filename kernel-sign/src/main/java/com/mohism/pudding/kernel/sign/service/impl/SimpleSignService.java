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
package com.mohism.pudding.kernel.sign.service.impl;

import cn.hutool.core.date.DateUtil;
import com.mohism.pudding.core.util.SpringContextHolder;
import com.mohism.pudding.core.util.ToolUtil;
import com.mohism.pudding.kernel.sign.config.properties.SignProperties;
import com.mohism.pudding.kernel.sign.service.SignService;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;

/**
 * 签名方法默认实现，通过md5加密方式
 *
 * @author fengshuonan
 * @date 2018-07-24-下午4:35
 */
@Slf4j
public class SimpleSignService implements SignService {

    private static final long DEAULT_SIGN_EXPIRED_SECONDS = 60;

    /**
     * 获取配置文件中的签名失效时间
     *
     * @author fengshuonan
     * @date 2018-05-08 14:02
     */
    private static long getExpiredTime() {
        SignProperties signProperties = null;
        try {
            signProperties = SpringContextHolder.getBean(SignProperties.class);
        } catch (RuntimeException e) {
            return DEAULT_SIGN_EXPIRED_SECONDS;
        }
        if (signProperties == null || signProperties.getTime() == 0L) {
            return DEAULT_SIGN_EXPIRED_SECONDS;
        } else {
            return signProperties.getTime();
        }
    }

    /**
     * 生成签名
     *
     * @author fengshuonan
     * @date 2018-05-08 14:02
     */
    public static String generateSign(String appId, String secret, String timestamp, String data) {
        if (ToolUtil.isOneEmpty(appId, secret, timestamp, data)) {
            throw new IllegalArgumentException("签名参数为空!appId=" + appId + ",secret=" + secret + ",timestamp=" + timestamp + ",data=" + data);
        } else {
            StringBuffer buf = new StringBuffer();
            buf.append(appId);
            buf.append(secret);
            buf.append(timestamp);
            buf.append(data);
            return ToolUtil.md5Hex(buf.toString());
        }
    }

    /**
     * 校验签名是否有效
     *
     * @author fengshuonan
     * @date 2018-05-08 14:02
     */
    public static boolean validateSign(String appId, String secret, String timestamp, String data, String sign) {
        return validateSign(appId, secret, timestamp, data, sign, getExpiredTime());
    }

    /**
     * 校验签名是否有效
     *
     * @author fengshuonan
     * @date 2018-05-08 14:02
     */
    public static boolean validateSign(String appId, String secret, String timestamp, String data, String sign, long expiredSeconds) {
        if (ToolUtil.isOneEmpty(appId, secret, timestamp, data, sign)) {
            throw new IllegalArgumentException("签名参数为空!appId=" + appId + ",secret=" + secret + ",timestamp=" + timestamp + ",data=" + data + ",sign=" + sign);
        }

        Date timestampDate = null;
        try {
            timestampDate = DateUtil.parse(timestamp, "yyyy-MM-dd HH:mm:ss");
        } catch (Exception e) {
            log.error("校验签名是否有效异常！时间转化异常！", e);
            return false;
        }

        long now = System.currentTimeMillis();
        long signTime = timestampDate.getTime();
        if ((now - signTime) / 1000 > expiredSeconds) {
            return false;
        }
        String nowSign = generateSign(appId, secret, timestamp, data);
        if (nowSign.equals(sign)) {
            return true;
        } else {
            return false;
        }
    }

}
