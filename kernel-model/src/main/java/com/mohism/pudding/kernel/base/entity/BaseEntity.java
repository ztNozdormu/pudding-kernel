package com.mohism.pudding.kernel.base.entity;

import com.baomidou.mybatisplus.annotation.TableId;
//import javax.persistence.Id;
import com.mohism.pudding.kernel.model.auth.context.CommonConstant;
import com.mohism.pudding.kernel.model.util.SnowFlakeUtil;
import lombok.Data;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.Date;


/**
 * @author real earth
 * @date   2019-06-30
 */
@Data
@MappedSuperclass
public abstract class BaseEntity implements Serializable {

        private static final long serialVersionUID = 1L;

        @Id
        @TableId
//        @ApiModelProperty(value = "唯一标识")
        private String id = String.valueOf(SnowFlakeUtil.getFlowIdInstance().nextId());

//        @ApiModelProperty(value = "创建者")
        private String createBy;

//        @ApiModelProperty(value = "创建时间")
        private Date createTime;

//        @ApiModelProperty(value = "更新者")
        private String updateBy;

//        @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
//        @ApiModelProperty(value = "更新时间")
        private Date updateTime;

//        @ApiModelProperty(value = "删除标志 默认0")
        private Integer delFlag = CommonConstant.STATUS_NORMAL;
    }
