package com.mohism.pudding.kernel.generator.config;

import lombok.Data;

/**
 * @ 创建人 zt
 * @ 创建时间 2019/9/2
 * @ 描述 自动生成业务模块文件配置类
 */
@Data
public class GenerateModularFileConfig {
    /**
     * 创建业务接口服务模块结构
     * com.mohism.pudding.业务模块名称.功能模块名称
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
     *                   --service
     *                     --impl
     *
     */
    /**
     * 基础模块文件 默认:com.mohism.pudding
     */
    private String baseModularFile;
    /**
     * 业务模块文件
     * 比如:基础业务:base CMS业务:cms 股票业务:stock
     */
    private String businessModularFile;
    /**
     *  业务功能模块
     *  比如:博客功能模块:pblog  文件功能模块:file 日志功能模块:log 字典功能模块:dict
     */
    private String functionModularFile;
    /**
     * 模块类型
     * 比如: 接口定义层:interface 业务服务实现层:server
     */
    private String modularType;
    /**
     * 目标父模块名称
     */
    private String firstParentModularName;
    private String secondParentModularName;
    private String funModularName;
    private String interfaceModularName;
    private String serverModularName;

}
