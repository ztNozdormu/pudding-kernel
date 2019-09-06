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
import com.mohism.pudding.kernel.generator.config.GenerateConfig;
import com.mohism.pudding.kernel.generator.config.GenerateModularFileConfig;
import com.mohism.pudding.kernel.generator.engine.BeetlTemplateEngine;
import com.mohism.pudding.kernel.generator.enums.ModularEnum;
import com.mohism.pudding.kernel.model.util.CreateFileUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 代码生成执行器
 *
 * @author nozdormu
 * @date 2019-07-24-晚上22:53
 */
public class PuddingGenerator {
    // 创建业务模块基本结构文件
    public static void doCreateBaseBmFiles(){
        /**
         * 创建业务接口服务模块结构
         * 例子: com.mohism.pudding.base.route
         *                 --api
         *                 --dto
         *                 --entity
         *                 --vo
         */
        /**
         * 创建业务层实现服务模块结构
         * 例子:com.mohism.pudding.base.route.server
         *                 --conifg
         *                 --coure
         *                   --db
         *                 --modular
         *                   --controller
         *                   --mapper
         *                   --mapping
         *                   --provider
         *                   --server
         *                     --
         *
         */

    }
    public static void doGeneration(GenerateConfig generateParams) {

        AutoGenerator mpg = new AutoGenerator();

        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        gc.setOutputDir(generateParams.getOutputDirectory());
        gc.setFileOverride(true);
        gc.setActiveRecord(false);// 不需要ActiveRecord特性的请改为false
        gc.setBaseResultMap(true);// XML ResultMap
        gc.setBaseColumnList(true);// XML columList
        gc.setEnableCache(false);
//        gc.setSwagger2(true);//实体生成swagger注解
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

//        // 公共字段填充
//        ArrayList<TableFill> tableFills = new ArrayList<>();
//        tableFills.add(new TableFill("CREATE_TIME\\", FieldFill.INSERT));
//        tableFills.add(new TableFill("UPDATE_TIME", FieldFill.UPDATE));
//        tableFills.add(new TableFill("CREATE_USER", FieldFill.INSERT));
//        tableFills.add(new TableFill("UPDATE_USER", FieldFill.UPDATE));
//        strategy.setTableFillList(tableFills);
        strategy.setSuperEntityClass("com.mohism.pudding.kernel.base.entity.BaseEntity");
        strategy.setEntityLombokModel(true);
        strategy.setRestControllerStyle(true);
//        strategy.setEntityTableFieldAnnotationEnable(true);
        strategy.setSuperEntityColumns("id");
        strategy.setControllerMappingHyphenStyle(true);
        mpg.setStrategy(strategy);

        // 自定义模板配置，可以 copy 源码 mybatis-plus/src/main/resources/templates 下面内容修改，
        // 放置自己项目的 src/main/resources/templates 目录下, 默认名称一下可以不配置，也可以自定义模板名称
        TemplateConfig tc = new TemplateConfig();
//        tc.setController(null);
//        if (!generateParams.getGeneratorInterface()) {
//            tc.setService(null);
//            tc.setServiceImpl("/templates/NoneInterfaceServiceImpl.java");
//        }
        // 自定义模板配置，模板可以参考源码 /mybatis-plus/src/main/resources/template 使用 copy
        // 至您项目 src/main/resources/template 目录下，模板名称也可自定义如下配置：
        tc.setController("/templates/controller.java");
        // 关闭默认 xml 生成，调整生成 至 根目录
        tc.setXml("/templates/mapper.xml");
        tc.setEntity("/templates/entity.java");
        tc.setMapper("/templates/mapper.java");
        tc.setService("/templates/service.java");
        tc.setServiceImpl("/templates/serviceImpl.java");
        // 如上任何一个模块如果设置 空 OR Null 将不生成该模块。
        mpg.setTemplate(tc);
        // 设置自定义的模板引擎
        mpg.setTemplateEngine(new BeetlTemplateEngine());
        // 包配置
        PackageConfig pc = new PackageConfig();
        // 包基本路径
        pc.setParent(generateParams.getParentPackage());
        // 功能模块名称
        pc.setModuleName("");
        // xml配置文件保存地址
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

//        PuddingGenerator.doGeneration(new GenerateConfig());
        // 项目功能模块基本文件夹目录生成
        GenerateModularFileConfig gmfc = new GenerateModularFileConfig();
            gmfc.setBaseModularFile("com.mohism.pudding");
            gmfc.setBusinessModularFile("cms");
            gmfc.setFunctionModularFile("pblog");
            gmfc.setModularType("interface");
            StringBuilder sbBase = new StringBuilder();
            sbBase.append(gmfc.getBaseModularFile());
            sbBase.append(".");
            sbBase.append(gmfc.getBusinessModularFile());
            sbBase.append(".");
            sbBase.append(gmfc.getFunctionModularFile());
             List<String> sbInterfaces = new ArrayList<String>();
             List<String> sbServices = new ArrayList<String>();
             // 接口层OR业务服务层
        String ss = ModularEnum.getDirsBymodularType(gmfc.getModularType());
             String[] childrenDirs = ss.split(",");
             for(String childrenDir:childrenDirs){
                 if("interface".equals(gmfc.getModularType())){
                     String sbInterface=sbBase.toString();
                     sbInterface+="."+childrenDir;
                     sbInterfaces.add(sbInterface);
                 }
//                 if("server".equals(gmfc.getModularType())){
//                     StringBuilder sbService = new StringBuilder();
//                     sbService=sbBase;
//                     sbService.append("server");
//                     sbService.append(".");
//                     sbService.append(childrenDir);
//                     sbServices.add(sbService);
//                 }
             }
         System.out.println(sbInterfaces);
         System.out.println(sbServices);
            sbInterfaces.stream().forEach(
                    sbInterface->{
                        sbInterface = sbInterface.replace(".","/");
                        CreateFileUtil.createDir(sbInterface);
                    }
            );

    }

}