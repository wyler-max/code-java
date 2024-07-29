package org.example.practice.practicespring.thread.async;

import org.example.practice.practicespring.PracticeSpring;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = PracticeSpring.class)
public class TestAsync {

    @Autowired
    private AsyncService asyncService;

    @Test
    public void sync() {
        System.out.println("sync");
        asyncService.doSync();
    }

    @Test
    public void async() {
        System.out.println("async");
        asyncService.doAsync();
    }

    @Test
    public void async2() {
        System.out.println("async2");
        asyncService.doAsync2();
    }
}
