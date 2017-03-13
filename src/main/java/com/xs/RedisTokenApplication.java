package com.xs;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xs.config.FasJsonConveter;
import com.xs.config.JackSonConvert;
import com.xs.config.MyMappingJackson2HttpMessageConverter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.HttpMessageConverter;

@SpringBootApplication
public class RedisTokenApplication {
//	@Bean
//	public HttpMessageConverters fastJsonHttpMessageConverters() {
//		FasJsonConveter fastConverter = new FasJsonConveter();
//		FastJsonConfig fastJsonConfig = new FastJsonConfig();
//		fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat);
//		fastConverter.setFastJsonConfig(fastJsonConfig);
//		HttpMessageConverter<?> converter = fastConverter;
//		return new HttpMessageConverters(converter);
//	}


@Bean
	public HttpMessageConverters jackSonHttpMessageConverters(){
		return  new HttpMessageConverters(new JackSonConvert(new ObjectMapper()));
	}

	public static void main(String[] args) {
		SpringApplication.run(RedisTokenApplication.class, args);
	}
}
