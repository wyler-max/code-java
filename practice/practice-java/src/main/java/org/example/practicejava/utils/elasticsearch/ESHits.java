package org.example.practicejava.utils.elasticsearch;

import java.util.List;
import java.util.Map;

import lombok.Data;

/**
 * searchResponse.getHits() == > ESHits
 *
 */
@Data
public class ESHits {

    private List<Hit> hits;
    private TotalHits totalHits;
    private String maxScore;
    private Boolean fragment;

    @Data
    public static class Hit {
        private String score;
        private String id;
        private String index;
        /**
         * @see ESSource
         * @since string类型的数据源
         */
        private String sourceAsString;
        private Map<String, Object> sourceAsMap;
    }

    @Data
    public static class TotalHits {
        private Integer value;
        private String relation;
    }
}
