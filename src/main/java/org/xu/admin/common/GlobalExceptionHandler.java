package org.xu.admin.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice("org.xu.admin.contoller")
public class GlobalExceptionHandler {

    /**
     * 处理自定义业务异常
     */
    @ExceptionHandler(BaseException.class)
    @ResponseBody
    public Result<?> handleBaseException(BaseException ex) {
        log.error("业务异常信息: {}", ex.getMessage());
        return Result.error(ex.getCode(), ex.getMessage());
    }

    /**
     * 处理其他未知异常 (如空指针、SQL错误等)
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result<?> handleException(Exception ex) {
        log.error("系统运行异常: ", ex); // 打印详细堆栈到日志
        return Result.error("服务器开小差了，请稍后再试");
    }
}