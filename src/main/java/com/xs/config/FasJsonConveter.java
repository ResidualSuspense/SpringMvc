package com.xs.config;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.xs.utils.FastJsonUtils;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageNotWritableException;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Type;
import java.nio.charset.Charset;

/**
 * Created by xiaosong on 2017/3/13.
 */
public class FasJsonConveter extends FastJsonHttpMessageConverter {
    public static final Charset DEFAULT_CHARSET = Charset.forName("UTF-8");

    @Override
    public void write(Object o, Type type, MediaType contentType, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {
        String jsonString = FastJsonUtils.toJSONString(o);
        System.out.println("jsonString:  "+jsonString);
        OutputStream out = outputMessage.getBody();
        jsonString=jsonString+"xxx";
        out.write(jsonString.getBytes(DEFAULT_CHARSET));
        out.flush();
    }
}

