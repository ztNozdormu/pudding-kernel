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
package com.mohism.pudding.kernel.model.api;


import com.mohism.pudding.kernel.model.auth.AbstractLoginUser;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 鉴权服务,提供颁发,校验,注销等方法
 *
 * @author fengshuonan
 * @date 2018-02-07 9:57
 */
@RequestMapping("/api/authService")
public interface AuthService {

    /**
     * <p>登录(验证账号密码)</p>
     * <p>若登录成功则返回token,若登录不成功则返回null</p>
     *
     * @author fengshuonan
     * @Date 2018/1/12 13:56
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    String login(@RequestParam("account") String account, @RequestParam("password") String password);

    /**
     * 校验token(true-校验成功,false-校验失败)
     *
     * @author fengshuonan
     * @Date 2018/2/7 10:02
     */
    @RequestMapping(value = "/checkToken", method = RequestMethod.POST)
    boolean checkToken(@RequestParam("token") String token);

    /**
     * 注销token
     *
     * @author fengshuonan
     * @Date 2018/2/7 10:02
     */
    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    void logout(@RequestParam("token") String token);

    /**
     * 通过token获取用户信息
     *
     * @author fengshuonan
     * @Date 2018/1/12 16:32
     */
    @RequestMapping(value = "/getLoginUserByToken", method = RequestMethod.POST)
    AbstractLoginUser getLoginUserByToken(@RequestParam("token") String token);

}
