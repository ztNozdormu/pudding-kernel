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

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableFill;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

import java.util.ArrayList;

/**
 * 简单代码生成器，service不生成接口
 *
 * @author fengshuonan
 * @Date 2018/7/20 下午1:17
 */
public class SimpleGenerator {

    public static void doGeneration(GenerateParams generateParams) {

        AutoGenerator mpg = new AutoGenerator();

        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        gc.setOutputDir(generateParams.getOutputDirectory());
        gc.setFileOverride(true);
        gc.setActiveRecord(false);// 不需要ActiveRecord特性的请改为false
        gc.setBaseResultMap(true);// XML ResultMap
        gc.setBaseColumnList(true);// XML columList
        gc.setEnableCache(false);
        gc.setOpen(false);
        gc.setAuthor(generateParams.getAuthor());

        // 自定义文件命名，注意 %s 会自动填充表实体属性！
        if (generateParams.getGeneratorInterface()) {
            gc.setServiceName("%sService");
            gc.setServiceImplName("%sServiceImpl");
        } else {
            gc.setServiceName("%sService");
            gc.setServiceImplName("%sService");
        }
        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setDbType(DbType.MYSQL);
        dsc.setDriverName(generateParams.getJdbcDriver());
        dsc.setUrl(generateParams.getJdbcUrl());
        dsc.setUsername(generateParams.getJdbcUserName());
        dsc.setPassword(generateParams.getJdbcPassword());
        mpg.setDataSource(dsc);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setCapitalMode(false);

        // 此处可以移除表前缀表前缀
        strategy.setTablePrefix(generateParams.getRemoveTablePrefix());

        // 表名生成策略
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);

        // 需要生成的表
        strategy.setInclude(generateParams.getIncludeTables());

        // 公共字段填充
        ArrayList<TableFill> tableFills = new ArrayList<>();
        tableFills.add(new TableFill("CREATE_TIME", FieldFill.INSERT));
        tableFills.add(new TableFill("UPDATE_TIME", FieldFill.UPDATE));
        tableFills.add(new TableFill("CREATE_USER", FieldFill.INSERT));
        tableFills.add(new TableFill("UPDATE_USER", FieldFill.UPDATE));
        strategy.setTableFillList(tableFills);

        mpg.setStrategy(strategy);

        // 自定义模板配置，可以 copy 源码 mybatis-plus/src/main/resources/templates 下面内容修改，
        // 放置自己项目的 src/main/resources/templates 目录下, 默认名称一下可以不配置，也可以自定义模板名称
        TemplateConfig tc = new TemplateConfig();
        tc.setController(null);

        if (!generateParams.getGeneratorInterface()) {
            tc.setService(null);
            tc.setServiceImpl("/templates/NoneInterfaceServiceImpl.java");
        }

        //如上任何一个模块如果设置 空 OR Null 将不生成该模块。
        mpg.setTemplate(tc);

        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setParent(generateParams.getParentPackage());
        pc.setModuleName("");
        pc.setXml("mapper.mapping");

        if (generateParams.getGeneratorInterface()) {
            pc.setServiceImpl("service.impl");
            pc.setService("service");
        } else {
            pc.setServiceImpl("service");
            pc.setService("service");
        }

        mpg.setPackageInfo(pc);

        // 执行生成
        mpg.execute();
    }

    public static void main(String[] args) {
        SimpleGenerator.doGeneration(new GenerateParams());
    }

}