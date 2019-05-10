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
package com.mohism.pudding.core.config;


import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.baomidou.mybatisplus.extension.plugins.OptimisticLockerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.mohism.pudding.core.config.properties.DruidProperties;
import com.mohism.pudding.core.datascope.DataScopeInterceptor;
import com.mohism.pudding.core.dbid.GunsDatabaseIdProvider;
import com.mohism.pudding.core.metadata.CustomMetaObjectHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * MybatisPlus配置
 *
 * @author stylefeng
 * @Date 2017/5/20 21:58
 */
@Configuration
@ConditionalOnProperty(prefix = "spring.datasource", name = "url")
public class MybatisPluginAutoConfiguration {

    @Autowired
    private DruidProperties druidProperties;

    /**
     * mybatis-plus分页插件
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        if (druidProperties.getUrl().contains("oracle")) {
            paginationInterceptor.setDialectType(DbType.ORACLE.getDb());
        } else if (druidProperties.getUrl().contains("postgresql")) {
            paginationInterceptor.setDialectType(DbType.POSTGRE_SQL.getDb());
        } else if (druidProperties.getUrl().contains("sqlserver")) {
            paginationInterceptor.setDialectType(DbType.SQL_SERVER2005.getDb());
        } else {
            paginationInterceptor.setDialectType(DbType.MYSQL.getDb());
        }
        return paginationInterceptor;
    }

    /**
     * 自定义公共字段自动注入
     */
    @ConditionalOnMissingBean
    @Bean
    public MetaObjectHandler metaObjectHandler() {
        return new CustomMetaObjectHandler();
    }

    /**
     * 数据范围mybatis插件
     */
    @Bean
    public DataScopeInterceptor dataScopeInterceptor() {
        return new DataScopeInterceptor();
    }

    /**
     * 乐观锁mybatis插件
     */
    @Bean
    public OptimisticLockerInterceptor optimisticLockerInterceptor() {
        return new OptimisticLockerInterceptor();
    }

    /**
     * 数据库id选择器
     */
    @Bean
    public GunsDatabaseIdProvider gunsDatabaseIdProvider() {
        return new GunsDatabaseIdProvider();
    }

}
