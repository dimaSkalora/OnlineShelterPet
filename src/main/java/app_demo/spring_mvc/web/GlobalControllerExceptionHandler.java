package app_demo.spring_mvc.web;

import app_demo.spring_mvc.AuthorizedUser;
import app_demo.spring_mvc.util.ValidationUtil;
import app_demo.spring_mvc.util.exception.ErrorType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalControllerExceptionHandler {
    private static final Logger log = LoggerFactory.getLogger(GlobalControllerExceptionHandler.class);

    @Autowired
    private MessageUtil messageUtil;

    @ExceptionHandler(Exception.class)
    public ModelAndView defaultErrorHandler(HttpServletRequest req, Exception e) throws Exception {
        Throwable rootCause = ValidationUtil.getRootCause(e);
        log.error("Exception at request " + req.getRequestURL(), rootCause);
        ModelAndView mav = new ModelAndView("exception/exception");
        mav.addObject("typeMessage", messageUtil.getMessage(ErrorType.APP_ERROR.getErrorCode()));
        mav.addObject("exception", rootCause);
        mav.addObject("message", rootCause.toString());

        return mav;
    }
}
