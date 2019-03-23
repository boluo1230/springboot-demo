/**
 * 异常处理拦截类
 *
 * @author : LIUBOLUN
 * @date : 2019-03-12
 */
package com.springboot.demo.base.exception;

import com.springboot.demo.base.bean.Result;
import com.springboot.demo.utils.RequestUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@ControllerAdvice
public class RestErrorController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler(value = CustomException.class)
    public Result customExceptionHandler(HttpServletRequest req, CustomException e) throws CustomException {
        logRequestInfo(req, e);
        return new Result(e.getCode(), e.getMessage());
    }

    @ExceptionHandler(value = Exception.class)
    public Result baseErrorHandler(HttpServletRequest req, Exception e) throws Exception {
        logRequestInfo(req, e);
        return new Result(500, "something wrong on server");
    }

    @ExceptionHandler(value = MissingServletRequestParameterException.class)
    public Result badRequestHandler(HttpServletRequest req, Exception e) throws Exception {
        logRequestInfo(req, null);
        return new Result(400, "param error");
    }

    @ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
    public Result methodNotAllowedHandler(HttpServletRequest req, Exception e) throws Exception {
        logRequestInfo(req, null);
        return new Result(405, "method not allowed");
    }

    private void logRequestInfo(HttpServletRequest req, Exception e) {
        if (req != null) {
            StringBuffer url = req.getRequestURL();
            String msg = String.format("error from ip:%s , port:%s , host:%s, true-ip:%s , param:%s , url:%s",
                    req.getRemoteAddr(), req.getRemotePort(), req.getRemoteHost(), RequestUtil.getIpAddr(req),
                    RequestUtil.getParamString(req),
                    url == null ? null : url.toString());
            if (e == null) {
                logger.error(msg);
            } else {
                logger.error(msg, e);
            }
        } else if (e != null) {
            logger.error("error msg:", e);
        }
    }
}
