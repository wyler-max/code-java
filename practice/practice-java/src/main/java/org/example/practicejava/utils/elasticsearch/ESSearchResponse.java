package org.example.practicejava.utils.elasticsearch;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

/**
 * 因使用高版本，且通过 searchResponse.getHits() 获取结果，废弃了
 */
@Deprecated
@Data
public class ESSearchResponse {
    private Integer took;
    @JsonProperty("timed_out")
    private Boolean timed_out;
    @JsonProperty("_shards")
    private Shareds _shards;
    private Hits hits;

    @Data
    public static class Shareds {
        private Integer total;
        private Integer successful;
        private Integer skipped;
        private Integer failed;
    }

    @Data
    public static class Hits {
        private Total total;
        @JsonProperty("max_score")
        private Double max_score;
        private List<Hit> hits;

        @Data
        public static class Total {
            private Integer value;
            private String relation;
        }

        @Data
        public static class Hit {
            @JsonProperty("_index")
            private String _index;
            @JsonProperty("_type")
            private String _type;
            @JsonProperty("_id")
            private String _id;
            @JsonProperty("_score")
            private String _score;
            /**
             * 对象资源所在
             */
            @JsonProperty("_source")
            private String _source;
        }
    }

}
