package org.example.practice.practiceknowbox.common.enums;

import java.util.Map;

import com.google.common.collect.Maps;

import lombok.Getter;

/**
 * @author yijiu.chen
 * @date 2020/04/16
 */
@Getter
public enum ErrorCode {
    // @formatter:off
    SUCCESS(0, "OK"),
    UNKNOWN(1, "未知错误"),
    PARAM_INVALID(2, "请求的参数错误"),
    TOO_FAST(3, "请求太快了"),
    QW_API_ERROR(4, "调用企业微信接口出错"),
    QPS_LIMIT(5, "当前请求量过大,请稍后重试"),
    RETRY(6, "处理出现了点小问题,请重试"),
    INVALID_REQUEST(7, "请求非法"),
    CLIENT_ABORT(8, "客户端断开了"),
    RESTART(9, "服务关闭中"),

    BAD_REQUEST(400, "请求不合法"),
    NOT_FOUND(404, "请求不对哦"),
    UNHANDLER_EXCEPTION(500, "Opps,服务器开小差了"),
    INTERFACE_ERROR(501, "go服务出现异常"),
    // 各业务的错误码

    // 用户10000开始
    NOT_LOGIN(10000, "没有登录"),
    TOKEN_INVALID(10001, "无效登录信息"),
    TOKEN_EXPIRED(10002, "登录信息已失效"),
    USER_INVALID(10003, "用户信息错误"),
    CORP_INVALID(10004, "处理组织错误"),
    GUARDIAN_RELATION_INVALID(10005, "学生家长关系错误"),
    ROLE_INVALID(10006, "身份校验错误"),
    IMPROVE_USER_INFO(10007, "完善用户信息"),

    // 班级20000开始
    NOT_ENTER_CLASS(20000, "当前用户不在班"),
    CLASS_NOT_FOUND(20001, "当前班级不存在"),

    // 答题30000开始
    DUPLICATE_ANSWER(30000, "重复提交答题"),
    ANSWER_SUMMARY_NOT_EXIST(30001, "练习答题不存在"),
    ANSWER_JUDGE_FAIL(30002, "判题失败"),
    ANSWER_QUESTION_INVALID(30003, "答题题目信息不对,请重新提交"),
    ANSWER_NOT_SUBMIT(30004, "练习未提交,不允许订正"),
    HAS_VIEW_ANSWER(30005, "已经查看过解析,不允许订正"),
    INVALID_HOMEWORK_TYPE(30006, "该练习类型不允许部分提交"),
    ANSWER_SUMMARY_REVIEWING(30007, "当前练习正在批阅中"),
    ANSWER_SUMMARY_COMMIT_FORBID(30008, "当前时间段禁止提交"),
    ANSWER_SUMMARY_HAS_COMMIT(30009, "练习已提交，不允许重复提交"),
    ANSWER_SUMMARY_RESEND(30010, "练习补发中，请稍等"),
    UNSUPPORTED_QUESTION(30011, "不支持的题目类型"),
    // 作业40000开始
    HOMEWORK_NOT_FOUND(40000, "练习未找到"),
    HOMEWORK_CLASS_NOT_FOUND(40001, "练习班级关系未找到"),
    HOMEWORK_CREATE_PARAM_INVALID(40002, "创建练习参数不合法"),
    HOMEWORK_CREATE_PUBLISH_TIME_INVALID(40003, "练习发布时间不合法"),
    SECTION_NOT_FOUND(40004, "章节不存在"),
    HOMEWORK_QUESTION_DUPLICATE(40005, "出题重复"),
    HOMEWORK_QUESTION_TOO_MANY(40006, "出题太多"),
    HOMEWORK_CREATE_FINISH_TIME_INVALID(40007, "练习截止时间不合法"),
    HOMEWORK_NOT_PUBLISH(40008, "练习未发布"),
    UNKNOWN_HOMEWORK_TYPE(40009, "未知的练习类型"),
    HOMEWORK_EXCEEDED_MAXIMUM(40010, "练习超过最大次数"),
    UNSUPPORTED_HOMEWORK_TYPE(40011, "不支持的练习类型"),
    UNPUBLISHED_HOMEWORK(40012, "练习未发布"),
    HOMEWORK_HAS_FINISHED(40013, "练习已结束"),
    HOMEWORK_TEACHER_VALID(40014, "练习非该老师布置"),

    // 聚合作业5000开始
    GROUP_HOMEWORK_NOT_FOUND(50000, "练习未找到"),
    GROUP_HOMEWORK_TIME_ERROR(50001, "练习时间范围不对"),
    GROUP_HOMEWORK_WEEK_ERROR(50002, "练习周几选的不对"),
    GROUP_HOMEWORK_LIST_NOT_EXIST(50003, "练习集没有哦"),
    GROUP_HOMEWORK_NUM_ERROR(50004, "练习数量错误"),
    GROUP_HOMEWORK_TITLE_ERROR(50005, "练习数量错误"),
    GROUP_HOMEWORK_QUESTION_ERROR(50006, "练习题目错误"),
    GROUP_HOMEWORK_CLASS_EMPTY(50007, "练习班级不能为空"),
    GROUP_HOMEWORK_CLASS_DUPLICATE(50008, "班级不能重复布置该练习"),
    GROUP_HOMEWORK_CLASS_TIMES(50009, "1小时内只能重置1次练习"),

    // 试卷/比赛相关,homework_manager
    PAPER_GROUP_NOT_FOUND(60001, "试卷组不存在"),
    PAPER_NOT_FOUND(60002, "试卷不存在"),
    PAPER_SECTION_QUESTION_INVALID(60003, "试卷/比赛章节题目信息有误"),
    PAPER_GROUP_NOT_STUDENT(60004, "该试卷没有导入学生"),
    PAPER_NOT_END(60005, "试卷考试试卷未结束，不能查看报告"),
    LOCAL_HAS_OCCUPY(60006, "该座位已被其他人选择，请联系老师"),
    INVITE_CODE_INVALID(60007, "验证码无效"),
    PAPER_HAS_USED(60008, "当前试卷已经创建过了，请不要重复发布"),
    PAPER_HAS_EXPIRED(60009, "验证码已过期"),

    // 报告相关
    ABILITY_NOT_FINISH(70001, "考试未结束"),
    UNSUPPORTED_CROSS_PROVINCE(70002, "暂不支持跨省报告"),
    REPORT_NOT_FOUND(70003, "报告不存在"),
    REPORT_HAS_NOT_AVERAGE(70004, "报告暂无平均成绩")

    ;

    private static final Map<Integer, ErrorCode> map = Maps.newHashMap();

    static {
        for (ErrorCode errorCode : ErrorCode.values()) {
            map.put(errorCode.getCode(), errorCode);
        }
    }

    ErrorCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    private int code;

    private String msg;

    public ErrorCode valueOf(int code) {
        ErrorCode errorCode = map.get(code);
        if (errorCode != null) {
            return errorCode;
        }
        return UNKNOWN;
    }
}
