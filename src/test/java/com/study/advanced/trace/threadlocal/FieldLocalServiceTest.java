package com.study.advanced.trace.threadlocal;

import com.study.advanced.trace.threadlocal.code.FieldLocalService;
import com.study.advanced.trace.threadlocal.code.FieldService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

/**
 * 동시성 문제 테스트 (A스레드 끝날때까지 대기 후 B스레드 실행하면 문제 없는 것처럼 보이지만.
 * 거의 동시에 A,B스레드 실행하면 동시성 이슈 생기는 결과를 볼 수 있음)
 */
@Slf4j
class FieldLocalServiceTest {
    private FieldLocalService service = new FieldLocalService();
    //
    @Test
    void field() {
        log.info("main start");
        Runnable userA = () -> {
            service.logic("userA");
        };
        Runnable userB = () -> {
            service.logic("userB");
        };
        Thread threadA = new Thread(userA);
        threadA.setName("thread-A");
        Thread threadB = new Thread(userB);
        threadB.setName("thread-B");

        threadA.start(); //A실행
//        sleep(2000); //동시성 문제 발생X
        sleep(100); //동시성 문제 발생O
        threadB.start(); //B실행
        sleep(3000); //메인 쓰레드 종료 대기
        log.info("main exit");
    }
    private void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}