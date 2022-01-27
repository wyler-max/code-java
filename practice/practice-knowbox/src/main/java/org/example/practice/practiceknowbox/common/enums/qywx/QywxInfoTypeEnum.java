package org.example.practice.practiceknowbox.common.enums.qywx;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import lombok.Getter;

/**
 * @author zhangshuai
 * @date 2021/11/5 5:24 下午
 */
@Getter
public enum QywxInfoTypeEnum {

    //@formatter:off
    SUITE_TICKET("suite_ticket", "刷新suite_ticket"),
    CREATE_AUTH("create_auth", "授权成功通知"),
    CHANGE_AUTH("change_auth", "变更授权通知"),
    CANCEL_AUTH("cancel_auth", "取消授权通知"),
    CHANGE_CONTACT("change_contact", "成员通知事件"),
    CHANGE_SCHOOL_CONTACT("change_school_contact", "家校通讯录变更回调"),
    ;

    //@formatter:on

    /**
     * 企业接口类型
     */
    String infoType;

    /**
     * 接口说明
     */
    String desc;

    private static final Set<String> SET;

    QywxInfoTypeEnum(String infoType, String desc) {
        this.infoType = infoType;
        this.desc = desc;
    }

    static {
        SET = Arrays.stream(QywxInfoTypeEnum.values()).map(QywxInfoTypeEnum::getInfoType).collect(Collectors.toSet());
    }

    public boolean support(String infoType) {
        return this.infoType.equals(infoType);
    }

    public static boolean isSupport(String infoType) {
        return SET.contains(infoType);
    }
}
