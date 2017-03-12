package com.xs.config;

import com.xs.domain.TokenModel;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * Created by xiaosong on 2017/3/12.
 */
//@ControllerAdvice(basePackages = "com.xs.web")
public class ResponseBodyAdviceConfig implements ResponseBodyAdvice {
    @Override
    public boolean supports(MethodParameter methodParameter, Class aClass) {
        System.out.println(methodParameter.toString()+",aClass:   "+aClass.toString());
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType, Class aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {

        if(o instanceof TokenModel){
            TokenModel tokenModel =(TokenModel) o;
            tokenModel.setUserId(1000L);
            return  tokenModel;
        }
//        else if(o instanceof DeferredResult){
//            System.out.println("...........beforeBodyWrite");
//            ((DeferredResult) o) .setResult("ControllerAdvice修改的值");
//            return o;
//        }
        else if(o instanceof  String){
            return  "默认返回值："+ o + ", ControllerAdvice修改后： " + "我修改responseBody返回值！";
        }
        return o;
    }
}
