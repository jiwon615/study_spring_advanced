package com.study.advanced.trace;

import lombok.Getter;

@Getter
public class TraceStatus {

    private TraceId traceId;
    private Long startTimeMs;  // 로그 시작시간
    private String message; // 시작시 사용한 메시지 (로그 종료후에도 이 메시지 사용하여 출력)

    public TraceStatus(TraceId traceId, Long startTimeMs, String message) {
        this.traceId = traceId;
        this.startTimeMs = startTimeMs;
        this.message = message;
    }


}
