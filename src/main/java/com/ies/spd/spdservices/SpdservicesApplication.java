package com.ies.spd.spdservices;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.ies.spd.spdservices.tcp.core.TcpServer;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.HttpMessageConverter;

import javax.annotation.Resource;

@SpringBootApplication
public class SpdservicesApplication implements CommandLineRunner {
//    @Bean
//    public HttpMessageConverters fastJsonHttpMessageConverters() {
//        // 1.定义一个converters转换消息的对象
//        FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();
//        // 2.添加fastjson的配置信息，比如: 是否需要格式化返回的json数据
//        FastJsonConfig fastJsonConfig = new FastJsonConfig();
////        fastJsonConfig.setSerializerFeatures(SerializerFeature.WriteMapNullValue);
//        // 3.在converter中添加配置信息
//        fastConverter.setFastJsonConfig(fastJsonConfig);
//        // 4.将converter赋值给HttpMessageConverter
//        HttpMessageConverter<?> converter = fastConverter;
//        // 5.返回HttpMessageConverters对象
//        return new HttpMessageConverters(converter);
//    }
    @Resource
    private TcpServer tcpServer;

    public static void main(String[] args) {
        SpringApplication.run(SpdservicesApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        tcpServer.run();
    }
}
