package org.example.practicescaffold.common.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 自定义异常类
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ServiceException extends Exception{

    private int code;
    private String msg;

    public ServiceException(String msg) {
        this.msg = msg;
    }
}
