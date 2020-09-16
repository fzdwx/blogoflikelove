package com.lk.blog.entity.back;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * 统一的数据返回类
 *
 * @author likeLove
 * @time 2020-09-04  13:23
 */
@Data
@ApiModel ("全局统一返回结果")
public class Result {

    @ApiModelProperty ("是否成功")
    private Boolean success;

    @ApiModelProperty ("状态码 = 20000表示成功，其余失败")
    private Integer code;

    @ApiModelProperty ("返回的消息")
    private String message;

    @ApiModelProperty ("返回的数据")
    private Map<String, Object> data = new HashMap<>();

    /**
     * 成功
     *
     * @return ResultCodeEnum.SUCCESS
     */
    public static Result ok() {
        Result res = new Result();
        res.setSuccess(ResultCodeEnum.SUCCESS.getSuccess())
           .setCode(ResultCodeEnum.SUCCESS.getCode())
           .setMessage(ResultCodeEnum.SUCCESS.getMessage());
        return res;
    }

    /**
     * 错误
     * @return ResultCodeEnum.UNKNOWN_REASON
     */
    public static Result error() {
        Result result = new Result();
        result.setSuccess(ResultCodeEnum.UNKNOWN_REASON.getSuccess())
              .setCode(ResultCodeEnum.UNKNOWN_REASON.getCode())
              .setMessage(ResultCodeEnum.UNKNOWN_REASON.getMessage());
        return result;
    }

    private Result() {};

    public Result setSuccess(Boolean success) {
        this.success = success;
        return this;
    }

    public Result setCode(Integer code) {
        this.code = code;
        return this;
    }

    public Result setMessage(String message) {
        this.message = message;
        return this;
    }

    public Result setData(Map<String, Object> data) {
        this.data = data;
        return this;
    }
}
