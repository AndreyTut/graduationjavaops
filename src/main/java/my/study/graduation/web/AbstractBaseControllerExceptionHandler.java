package my.study.graduation.web;

import com.fasterxml.jackson.databind.JsonMappingException;
import my.study.graduation.util.ErrorInfo;
import my.study.graduation.util.exceptions.NotFoundInDataBaseException;
import my.study.graduation.util.exceptions.VotingException;
import my.study.graduation.util.exceptions.WrongMenuException;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;

public abstract class AbstractBaseControllerExceptionHandler {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({
            NotFoundInDataBaseException.class,
            VotingException.class,
            ConstraintViolationException.class,
            MethodArgumentNotValidException.class,
            JsonMappingException.class,
            WrongMenuException.class})
    @ResponseBody
    ErrorInfo handleExceptions(HttpServletRequest req, Exception ex) {
        return new ErrorInfo(req.getRequestURL().toString(), ex, getRootCause(ex));
    }

    private Throwable getRootCause(Throwable t) {
        Throwable result = t;
        Throwable cause;

        while (null != (cause = result.getCause()) && (result != cause)) {
            result = cause;
        }
        return result;
    }
}
