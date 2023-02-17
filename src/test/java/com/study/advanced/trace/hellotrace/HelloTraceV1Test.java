package com.study.advanced.trace.hellotrace;

import com.study.advanced.trace.TraceStatus;
import org.junit.jupiter.api.Test;

/**
 * V1: 로그 추적기를 적용했는데 로그를 남기기 위한 코드가 생각보다 너무 복잡함
 * - 또한, 로그는 남겼지만 메소드 호출의 깊이 표현은 미구현
 */
public class HelloTraceV1Test {

    @Test
    void begin_end() {
        HelloTraceV1 trace = new HelloTraceV1();
        TraceStatus status = trace.begin("hello");
        trace.end(status);
    }
    @Test
    void begin_exception() {
        HelloTraceV1 trace = new HelloTraceV1();
        TraceStatus status = trace.begin("hello");
        trace.exception(status, new IllegalStateException());
    }
}
