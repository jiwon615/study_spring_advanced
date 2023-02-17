package com.study.advanced.trace.hellotrace;

import com.study.advanced.trace.TraceStatus;
import org.junit.jupiter.api.Test;

/**
 * V2. 파라미터 동기화 했지만, 동기화를 위해 TraceId 관련 모두 파라미터로 넘기도록 수정해야함 :(
 */
class HelloTraceV2Test {

    @Test
    void begin_end_level2() {
        HelloTraceV2 trace = new HelloTraceV2();
        TraceStatus status1 = trace.begin("hello1");
        // status1를 통해 동일한 트랜잭션 ID를 유지한체 레벨을 증가하여 beginSync() 실행
        TraceStatus status2 = trace.beginSync(status1.getTraceId(), "hello2");
        trace.end(status2);ㅠ
        trace.end(status1);
    }

    @Test
    void begin_exception_level2() {
        HelloTraceV2 trace = new HelloTraceV2();
        TraceStatus status1 = trace.begin("hello1");
        TraceStatus status2 = trace.beginSync(status1.getTraceId(), "hello2");
        trace.exception(status2, new IllegalStateException());
        trace.exception(status1, new IllegalStateException());
    }
}