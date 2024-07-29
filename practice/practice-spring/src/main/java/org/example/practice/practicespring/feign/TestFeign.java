package org.example.practice.practicespring.feign;

import org.example.practice.practicespring.PracticeSpring;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.extern.slf4j.Slf4j;

/**
 * @author wangyulin
 * @date 2023/9/1
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = PracticeSpring.class)
@Slf4j
public class TestFeign {
    @Autowired
    private KnowboxGatewayClient knowboxGatewayClient;

    @Test
    public void testFeignKnowbox() {
        String rs = knowboxGatewayClient.hello();
        log.info("rs {}", rs);
    }
}
