package org.example.practice.practicecode.javalang.utils.elasticsearch;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

/**
 * api="//boss/order/queryOrderList" 的请求参数和返回值
 */
@Data
public class QueryOrderList {

    @Data
    public static class Param {
        @JsonProperty("REQUEST_BODY")
        private String REQUEST_BODY;
        @JsonProperty("EMPLOYEE_NAME")
        private String EMPLOYEE_NAME;
    }

    @Data
    public static class ReqBody {
        private Long createBeginTime;
        private Long createEndTime;
        private Long payBeginTime;
        private Long payEndTime;
        private Long refundBeginTime;
        private Long refundEndTime;
        private String channel;
        private String price;
        private String ownerKey;
        private String orderNumber;
        private String userKey;
        private KnowboxResponse.Pager pager;
    }

    @Data
    public static class Resp extends KnowboxResponse {
        private List<RespData> data;

        @Data
        public static class RespData {
            private Long orderNumber;
        }
    }
}
