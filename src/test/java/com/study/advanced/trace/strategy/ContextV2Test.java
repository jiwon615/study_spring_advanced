package com.study.advanced.trace.strategy;

import com.study.advanced.trace.strategy.code.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * 전략 패턴
 * - 전략을 실행하는 시점에 직접 파라미터로 전달하여 사용
 * -> Context를 실행하는 시점에는 이미 조립이 끝났기에 전략을 신경쓰지 않고 단순히 실행만 하면 됨
 * - 장점: 실행할 때마다 전략을 유연하게 변경할 수 있음
 * - 단점: 실행할 때마다 전략을 계속 지정해주어야 함
 */
@Slf4j
public class ContextV2Test {

    @Test
    @DisplayName("전략 패턴 사용 - Context 실행할 때마다 전략을 인수로 전달")
    void strategyV1() {
        ContextV2 context = new ContextV2();
        context.execute(new StrategyLogic1());
        context.execute(new StrategyLogic2());
    }

    @Test
    @DisplayName("전략 패턴 익명 내부 클래스")
    void strategyV2() {
        ContextV2 context = new ContextV2();
        // 이렇게 익명클래스 쓰면 execute 안에서 내가 실행할 코드를 넘기는 것
        context.execute(new Strategy() {
            @Override
            public void call() {
                log.info("비즈니스 로직1 실행");
            }
        });

        context.execute(new Strategy() {
            @Override
            public void call() {
                log.info("비즈니스 로직2 실행");
            }
        });
    }

    @Test
    @DisplayName("전략 패턴 익명 내부 클래스: V2 -> 람다로 변경")
    void strategyV3() {
        ContextV2 context = new ContextV2();
        context.execute(() -> log.info("비즈니스 로직1 실행"));
        context.execute(() -> log.info("비즈니스 로직2 실행"));
    }
}

