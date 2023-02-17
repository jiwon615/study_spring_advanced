package com.study.advanced.config;

import com.study.advanced.trace.logtrace.FiledLogTrace;
import com.study.advanced.trace.logtrace.LogTrace;
import com.study.advanced.trace.logtrace.ThreadLocalLogTrace;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LogTraceConfig {

    @Bean
    public LogTrace logTrace() {
        return new ThreadLocalLogTrace();
    }
}
