package com.servicebase.exceptionhandler;
//统一异常处理类,就是说只要可以使用这个组件的,controller方法里面出现了指定异常,就让这个处理类来处理
import com.commonutils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice  //大概意思就是能进行全局化服务,aop思想的体现,比如这个注解 + @ExceptionHandler就能实现全局异常处理
@Slf4j
public class GlobalExceptionHandler {

    //指定出现什么异常执行这个方法
    @ExceptionHandler(Exception.class)
    @ResponseBody //为了返回数据
    public R error(Exception e) {
        e.printStackTrace();
        return R.error().message("执行了全局异常处理..");
    }


    //特定异常处理
    @ExceptionHandler(ArithmeticException.class)
    @ResponseBody //为了返回数据
    public R error(ArithmeticException e) {
        e.printStackTrace();
        return R.error().message("执行了ArithmeticException异常处理..");
    }


    //自定义异常处理,出现了GuliException异常,就返回对应的R信息
    @ExceptionHandler(GuliException.class)
    @ResponseBody
    public R error(GuliException e){

        log.error(e.getMsg());//这个错误信息将被输出到文件中去(logback-spring.xml配置的D:/guli_log/edu)
                              //至于为什么Slf4j的日志可以输出到logback配置的文件里去,可能是因为logback-spring.xml配置的是所有日志吧
        e.printStackTrace();
        return R.error().message(e.getMsg()).code(e.getCode());
    }
}
