package com.study.advanced.trace.strategy;

import com.study.advanced.trace.strategy.callback_code.Callback;
import com.study.advanced.trace.strategy.callback_code.TimeLogTemplate;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@Slf4j
public class TemplateCallBackTest {

    @Test
    @DisplayName("템플릿 콜백 패턴 익명 내부 클래스")
    void callBackV1() {
        TimeLogTemplate template = new TimeLogTemplate();
        template.execute(new Callback() {
            @Override
            public void call() {
                log.info("비즈니스 로직1 실행");
            }
        });

        template.execute(new Callback() {
            @Override
            public void call() {
                log.info("비즈니스 로직2 실행");
            }
        });
    }

    @Test
    @DisplayName("템플릿 콜백 패턴 익명 내부 클래스: V1 -> 람다로 변경")
    void callBackV2() {
        TimeLogTemplate template = new TimeLogTemplate();
        template.execute(() -> log.info("비즈니스 로직1 실행"));
        template.execute(() -> log.info("비즈니스 로직2 실행"));
    }
}
