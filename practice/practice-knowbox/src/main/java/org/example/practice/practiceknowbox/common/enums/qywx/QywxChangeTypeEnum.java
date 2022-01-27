package org.example.practice.practiceknowbox.common.enums.qywx;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import lombok.Getter;

/**
 * @author zhangshuai
 * @date 2021/11/7 8:27 下午
 */
@Getter
public enum QywxChangeTypeEnum {

    //@formatter:off
    CREATE_STUDENT("create_student", "新增学生"),
    UPDATE_STUDENT("update_student", "编辑学生"),
    DELETE_STUDENT("delete_student", "删除学生"),
    CREATE_PARENT("create_parent","新增家长"),
    UPDATE_PARENT("update_parent","编辑家长"),
    DELETE_PARENT("delete_parent","删除家长"),
    CREATE_USER("create_user","新增成员事件"),
    UPDATE_USER("update_user","编辑成员事件"),
    DELETE_USER("delete_user","删除成员事件"),
    SUBSCRIBE("subscribe", "家长关注"),
    UNSUBSCRIBE("unsubscribe","家长取消关注"),
    CREATE_DEPARTMENT("create_department", "部门变更事件"),
    UPDATE_DEPARTMENT("update_department", "部门变更事件"),
    DELETE_DEPARTMENT("delete_department", "部门变更事件"),
    ;

    //@formatter:on

    /**
     * 企业接口类型
     */
    String changeType;

    /**
     * 接口说明
     */
    String desc;

    private static final Set<String> SET;

    QywxChangeTypeEnum(String changeType, String desc) {
        this.changeType = changeType;
        this.desc = desc;
    }

    static {
        SET = Arrays.stream(QywxInfoTypeEnum.values()).map(QywxInfoTypeEnum::getInfoType).collect(Collectors.toSet());
    }

    public boolean support(String infoType) {
        return this.changeType.equals(infoType);
    }

    public static boolean isSupport(String infoType) {
        return SET.contains(infoType);
    }
}
