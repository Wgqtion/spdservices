//package com.ies.spd.spdservices.config;
//
//import com.google.common.cache.CacheBuilder;
//import org.springframework.cache.CacheManager;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import java.util.concurrent.TimeUnit;
//
//@Configuration
//public class SpringCacheConfig {
//    /**
//     * spring缓存配置，使用guava
//     * @return
//     */
//    @Bean
//    public CacheManager cacheManager(){
//        GuavaCache cacheManager = new GuavaCacheManager();
//        cacheManager.setCacheBuilder(CacheBuilder.newBuilder().expireAfterWrite(3, TimeUnit.SECONDS));
//        return cacheManager;
//    }
//}