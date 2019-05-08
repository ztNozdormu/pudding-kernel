package com.mohism.pudding.kernel.model.validator;

/**
 * 用于参数校验的接口
 *
 * @author
 * @date 2018-08-07-上午10:13
 */
public interface BaseValidatingParam {

    /**
     * 校验请求参数是否为空
     *
     * @return String 如果返回null代表没有为空的参数，校验通过，如果不为空，则代表有空的字段，并且返回的内容是为空的提示信息
     * @author fengshuonan
     * @Date 2018/8/6 下午4:28
     */
    String checkParam();
}
