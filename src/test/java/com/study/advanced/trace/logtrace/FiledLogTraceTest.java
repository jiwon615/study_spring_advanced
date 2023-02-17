package com.study.advanced.trace.logtrace;

import com.study.advanced.trace.TraceStatus;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * FieldLogTrace의 traceHolder를 통해 TraceId(트랜잭션id)가 잘 동기화 되도록 했음
 * 그러나, 동시성 이슈 해결 못함
 */
class FiledLogTraceTest {

    FiledLogTrace trace = new FiledLogTrace();

    @Test
    void begin_end_level2() {
        TraceStatus status1 = trace.begin("hello1");
        TraceStatus status2 = trace.begin("hello2");
        trace.end(status2);
        trace.end(status1);
    }

    @Test
    void begin_exception_level2() {
        TraceStatus status1 = trace.begin("hello");
        TraceStatus status2 = trace.begin("hello2");
        trace.exception(status2, new IllegalStateException());
        trace.exception(status1, new IllegalStateException());
    }
}