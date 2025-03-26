package com.example.user.dto;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.Map;

/**
 * @Author : join
 * @Description :
 * Date : 2025/3/26 下午10:42
 */
@Data
public class VerificationCode implements Serializable {

    @Serial
    private static final long serialVersionUID = -1L;

    //验证码发送总次数
    private int total;
    //发送列表
    private Map<String, Integer> sendTable;
    //验证码
    private String code;


}
