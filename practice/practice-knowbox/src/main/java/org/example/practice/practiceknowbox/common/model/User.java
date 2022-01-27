package org.example.practice.practiceknowbox.common.model;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

/**
 * 标记类
 *
 * @author zhangshuai
 * @date 2020/11/11 2:57 下午
 */
@Data
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    public long id;

    /**
     * 1,老师;2,家长;3,学生
     */
    private int role;

    /**
     * 班级Id
     */
    private Long departmentId;

    private String userId;

    private String name;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private long corpId;

    private String authCorpId;

    @JsonIgnore
    private String tokenKey;

    private int isSubscribe;

    private List<Child> children;

    private List<SimpleCorpInfo> corps;

    private List<UserClass> classes;

    @Data
    public static class Child {
        @JsonFormat(shape = JsonFormat.Shape.STRING)
        private long userId;

        private String name;

        private List<UserClass> classes;
    }

    @Data
    public static class SimpleCorpInfo {
        @JsonFormat(shape = JsonFormat.Shape.STRING)
        private long corpId;

        private String authCorpId;

        private String corpName;
    }

    @Data
    public static class UserClass {

        @JsonFormat(shape = JsonFormat.Shape.STRING)
        private long classId;

        private String className;
    }

}
