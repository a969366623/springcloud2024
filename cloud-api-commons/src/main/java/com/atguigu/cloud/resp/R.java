package com.atguigu.cloud.resp;

import lombok.Data;
import lombok.experimental.Accessors;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * @auther jiaronghao
 * @create 2023-11-04 11:59
 */
@Data
@Accessors(chain = true)
public class R<T> {

    private String code;

    private String message;

    private T data;

    private String timestamp;

    public R() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        this.timestamp = LocalDateTime.now().format(formatter); // 使用当前时间初始化
    }

    public static <T> R<T> success(T data) {
        R<T> resultData = new R<>();
        resultData.setCode(ReturnCodeEnum.RC200.getCode());
        resultData.setMessage(ReturnCodeEnum.RC200.getMessage());
        resultData.setData(data);
        return resultData;
    }

    public static <T> R<T> fail(String code, String message) {
        R<T> resultData = new R<>();
        resultData.setCode(code);
        resultData.setMessage(message);
        resultData.setData(null);
        return resultData;
    }
}
