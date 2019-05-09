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
package com.mohism.pudding.kernel.model.api.base;

import com.mohism.pudding.kernel.model.validator.BaseValidatingParam;
import lombok.Getter;
import lombok.Setter;

/**
 * 远程服务的参数的基类
 *
 * @author fengshuonan
 * @date 2018-08-06-下午4:22
 */
@Getter
@Setter
public abstract class AbstractBaseRequest implements BaseValidatingParam {

    /**
     * 唯一请求号
     */
    private String requestNo;

    /**
     * 业务节点id
     */
    private String spanId;

}
