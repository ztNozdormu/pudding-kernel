package com.mohism.pudding.kernel.generator.enums;

import lombok.Data;
import lombok.Getter;

/**
 * @ 创建人 zt
 * @ 创建时间 2019/9/2
 * @ 描述
 */
@Getter
public enum ModularEnum {

    ENABLE("interface", "api,dto,entity,vo"),

    DISABLE("server", "config,core.db,core.enums,core.exception,core.util,modular.controller,modular.mapper.mapping,modular.service.impl");
    /**
     * 模块类型
     */
    private String modularType;
    /**
     * 文件目录
     */
    private String dirs;

    ModularEnum(String modularType, String dirs) {
        this.modularType = modularType;
        this.dirs = dirs;
    }
    public static String getDirsBymodularType(String modularType) {
        if (modularType == null) {
            return "";
        } else {
            for (ModularEnum modularEnum : ModularEnum.values()) {
                if (modularEnum.getModularType().equals(modularType)) {
                    return modularEnum.getDirs();
                }
            }
            return "";
        }
    }
    public static ModularEnum toEnum(String modularType) {
        if (null == modularType) {
            return null;
        } else {
            for (ModularEnum modularEnum : ModularEnum.values()) {
                if (modularEnum.modularType.equals(modularType)) {
                    return modularEnum;
                }
            }
            return null;
        }
    }
}
