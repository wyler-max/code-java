package org.example.practice.practiceknowbox.common.model;

import java.io.Serializable;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

/**
 * @author zhangshuai
 * @date 2021/10/27 2:51 下午
 */
@Data
public class QywxParam implements Serializable {

    private static final long serialVersionUID = 3157049937127411244L;

    @JsonProperty("SuiteId")
    private String suiteId;

    @JsonProperty("InfoType")
    private String infoType;

    @JsonProperty("TimeStamp")
    private long timestamp;

    private Map<String, String> others;

    public String getOther(String key) {
        return others.get(key);
    }

    public String put(String key, String value) {
        return others.put(key, value);
    }
}
