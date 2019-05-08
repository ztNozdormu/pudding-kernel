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
package com.mohism.pudding.kernel.generator;

import lombok.Data;

/**
 * 代码生成所需要传递的参数---修改为配置文件方式
 *
 * @author nozdormu
 * @date 2019-05-08-晚上22:53
 */
@Data
public class GenerateParams {

    //生成代码里，注释的作者
    private String author = "nozdormu";

    //代码生成输出的目录，可为项目路径的相对路径
    private String outputDirectory = "temp";

    //jdbc驱动
    private String jdbcDriver = "com.mysql.jdbc.Driver";

    //数据库连接地址
    private String jdbcUrl = "jdbc:mysql://127.0.0.1:3306/mohism?autoReconnect=true&useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=false&serverTimezone=UTC";

    //数据库账号
    private String jdbcUserName = "root";

    //数据库密码
    private String jdbcPassword = "root";

    //去掉表的前缀
    private String[] removeTablePrefix = {"xx_"};

    //代码生成包含的表，可为空，为空默认生成所有
    private String[] includeTables;

    //代码生成的类的父包名称
    private String parentPackage = "com.mohism.pudding.xxx.modular";

    //service是否生成接口，这个根据自己项目情况决定
    private Boolean generatorInterface = false;

}
