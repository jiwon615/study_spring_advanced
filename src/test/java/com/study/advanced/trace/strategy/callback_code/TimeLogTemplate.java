package com.study.advanced.trace.strategy.callback_code;

import lombok.extern.slf4j.Slf4j;

// ContextV2 -> Template
@Slf4j
public class TimeLogTemplate {

    public void execute(Callback callback) {
        long startTime = System.currentTimeMillis();

        // 비즈니스 로직 실행
        callback.call(); // 위임
        // 비즈니스 로직 종료

        long endTime = System.currentTimeMillis();
        long resultTime = endTime - startTime;
        log.info("resultTime={}", resultTime);
    }
}
