package com.study.advanced.trace.hellotrace;

import com.study.advanced.trace.TraceId;
import com.study.advanced.trace.TraceStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class HelloTraceV2 {

    private static final String START_PREFIX = "-->";
    private static final String COMPLETE_PREFIX = "<--";
    private static final String EX_PREFIX = "<X-";

    /**
     * 로그를 시작
     * @param message 로그 메시지
     * @return 시작 로그를 출력
     */
    public TraceStatus begin(String message) {
        TraceId traceId = new TraceId();
        Long startTimeMs = System.currentTimeMillis();
        log.info("[{}] {}{}", traceId.getId(), addSpace(START_PREFIX, traceId.getLevel()), message);
        return new TraceStatus(traceId, startTimeMs, message);
    }


    // V2에서 추가
    public TraceStatus beginSync(TraceId beforeTraceId, String message) {
        TraceId nextId = beforeTraceId.createNextId();
        Long startTimeMs = System.currentTimeMillis();
        log.info("[" + nextId.getId() + "] " + addSpace(START_PREFIX, nextId.getLevel()) + message);
        return new TraceStatus(nextId, startTimeMs, message);
    }

    /**
     * 로그를 정상 종료
     * @param status 시작 로그의 상태
     */
    public void end(TraceStatus status) {
        complete(status, null);
    }

    /**
     * 로그를 예외 상황으로 종료
     * @param status 시작 로그의 상태
     * @param e 예외 정보를 포함
     */
    public void exception(TraceStatus status, Exception e) {
        complete(status, e);
    }

    /**
     * 로그 상태를 활용하여 실행 시간 계산 및 롱료시에도 동일한 메시지 출력
     * @param status 시작 로그의 상태
     * @param e 예외 있을 시 에외정보 담김
     */
    private void complete(TraceStatus status, Exception e) {
        Long stopTimeMs = System.currentTimeMillis();
        long resultTimeMs = stopTimeMs - status.getStartTimeMs();
        TraceId traceId = status.getTraceId();
        if (e == null) {
            log.info("[{}] {}{} time={}ms", traceId.getId(),
                    addSpace(COMPLETE_PREFIX, traceId.getLevel()), status.getMessage(),
                    resultTimeMs);
        } else {
            log.info("[{}] {}{} time={}ms ex={}", traceId.getId(),
                    addSpace(EX_PREFIX, traceId.getLevel()), status.getMessage(), resultTimeMs,
                    e.toString());
        }
    }

    private static String addSpace(String prefix, int level) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < level; i++) {
            sb.append( (i == level - 1) ? "|" + prefix : "|   ");
        }
        return sb.toString();
    }
}
