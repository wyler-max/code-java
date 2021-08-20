package org.example.practicescaffold.services;

import java.util.Random;
import java.util.concurrent.Future;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TestService {

    @Async("secondThreadPool")
    public Future<Integer> exectuorService(int i) {
        int random = new Random().nextInt(3000);
        try {
            Thread.sleep(random);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (random > 1500) {
            return new AsyncResult<>(1);
        }
        log.info("random小于1500, random=" + random);
        return new AsyncResult<>(0);
    }
}
