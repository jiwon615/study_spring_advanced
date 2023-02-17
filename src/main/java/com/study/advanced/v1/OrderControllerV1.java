package com.study.advanced.v1;

import com.study.advanced.trace.TraceStatus;
import com.study.advanced.trace.hellotrace.HelloTraceV1;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OrderControllerV1 {

    private final OrderServiceV1 orderService;
    private final HelloTraceV1 trace;  // 적용

    @GetMapping("/v1/request")
    public String request(String itemId) {

        TraceStatus status = null;
        try {
            status = trace.begin("OrderController.request()");
            orderService.orderItem(itemId);
            trace.end(status);
        } catch (Exception e) {
            trace.exception(status, e);
            throw e; // 예외를 꼭 다시 던져주어야 함 (그렇지 않으면 여기서 예외를 먹어버리고 이후에 정상 흐름으로 동작함)
        }
        return "ok";
    }
}
