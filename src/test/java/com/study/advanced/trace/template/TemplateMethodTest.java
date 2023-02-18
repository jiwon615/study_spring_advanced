package com.study.advanced.trace.template;

import com.study.advanced.trace.template.code.AbstractTemplate;
import com.study.advanced.trace.template.code.SubClassLogic1;
import com.study.advanced.trace.template.code.SubClassLogic2;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@Slf4j
public class TemplateMethodTest {

    @Test
    @DisplayName("다형성을 이용해 변하는 부분과 변하지 않는 부분을 분리 -> 클래스를 계속 만드는 단점")
    void templateMethodV1() {
        AbstractTemplate template1 = new SubClassLogic1();
        template1.execute();

        AbstractTemplate template2 = new SubClassLogic2();
        template2.execute();
    }

    @Test
    @DisplayName("악명클래스 사용하여 변하는 부분과 변하지 않는 부분을 분리 - > 위 단점 보안")
    void templateMethodV2() {
        AbstractTemplate template1 = new AbstractTemplate() {
            @Override
            protected void call() {
                log.info("비즈니스 로직1 실행");
            }
        };
        template1.execute();
        log.info("클래스 이름1={}", template1.getClass());

        AbstractTemplate template2 = new AbstractTemplate() {
            @Override
            protected void call() {
                log.info("비즈니스 로직2 실행");
            }
        };
        template1.execute();
        log.info("클래스 이름2={}", template1.getClass());
    }
}
