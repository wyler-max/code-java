package org.example.practice.practicespring.annotation.lombok;

import org.springframework.lang.NonNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * @author wangyulin
 * @date 2023/8/31
 */
@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class LombokConstructor {
    @NonNull
    private String name;
    private final String age = "18";
    private String sex;

    @NonNull
    private int a1;
    @NonNull
    private Integer a2;

    private int b1;
    private Integer b2;

    /*public LombokConstructor(final String name, final int a1, final Integer a2) {
        this.name = name;
        this.a1 = a1;
        this.a2 = a2;
    }*/
}
