package io.github.kevvy.chat_java.common.exception;

import io.github.kevvy.chat_java.InitHealthCheck;
import io.github.kevvy.chat_java.common.Result;
import io.github.kevvy.chat_java.common.exception.BusinessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 捕获指定异常类
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);//写当前类，等价于告诉日志系统这个 logger 归属于这个类
    @ExceptionHandler(IllegalArgumentException.class)
    public Result<Void> handleIllegalArgument(IllegalArgumentException e) {
        return  Result.error(400,"参数错误："+ e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public Result<Void> handleException(Exception e) {
        logger.error("🔴 服务器异常", e);
        logger.error("\uD83D\uDD34 服务器异常：{}", e.getMessage());

        return  Result.error(500,"服务器异常请稍后重试！");
    }

    @ExceptionHandler(BusinessException.class)
    public Result<Void> handleBusinessException(BusinessException e) {
        return Result.error(e.getCode(), e.getMessage());
    }
}

