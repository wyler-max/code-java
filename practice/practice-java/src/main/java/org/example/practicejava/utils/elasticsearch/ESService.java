package org.example.practicejava.utils.elasticsearch;

import java.io.IOException;
import java.util.Map;

import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.MatchAllQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.RangeQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.example.practicejava.utils.JsonUtil;
import org.junit.Test;

import com.google.common.collect.Maps;

import lombok.extern.slf4j.Slf4j;

/**
 * @author wangyulin
 * @date 2022/7/2
 */
@Slf4j
public class ESService {

    public static RequestOptions COMMON_OPTIONS = RequestOptions.DEFAULT.toBuilder().build();

    public static RestHighLevelClient esRestClient(String host, String userName, String password) {
        // 可以指定多个es
        RestClientBuilder builder = RestClient.builder(new HttpHost(host, 9200, "http"));
        // 账户&密码
        CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
        credentialsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials(userName, password));
        // sync
        builder.setHttpClientConfigCallback(
            httpClientBuilder -> httpClientBuilder.setDefaultCredentialsProvider(credentialsProvider));
        // async
        /*builder.setHttpClientConfigCallback((HttpAsyncClientBuilder httpAsyncClientBuilder) -> httpAsyncClientBuilder
            .setDefaultCredentialsProvider(credentialsProvider));*/
        return new RestHighLevelClient(builder);
    }

    public static SearchRequest of(String indices, int from, int size, Map<String, String> matchMap) {
        // 1 创建检索请求
        SearchRequest searchRequest = new SearchRequest();
        searchRequest.indices(indices);
        // 1.1 searchSourceBuilder
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        String start = "2022-06-28T00:00:00.000Z";
        String end = "2022-06-29T00:00:00.000Z";
        RangeQueryBuilder range = QueryBuilders.rangeQuery("@timestamp").from(start).to(end);
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        boolQueryBuilder.must(range);
        matchMap.forEach((key, val) -> {
            boolQueryBuilder.must(QueryBuilders.matchQuery(key, val));
        });
        sourceBuilder.query(boolQueryBuilder);
        // sourceBuilder.fetchSource(new String[]{"uri","request_id"}, new String[]{});
        sourceBuilder.from(from);
        sourceBuilder.size(size);
        sourceBuilder.sort("@timestamp");
        sourceBuilder.timeout(TimeValue.timeValueMinutes(2L));
        // System.out.println("sourceBuilder=" + sourceBuilder.toString());
        searchRequest.source(sourceBuilder);
        return searchRequest;
    }

    public static SearchResponse search(RestHighLevelClient client, SearchRequest request) throws IOException {
        return client.search(request, COMMON_OPTIONS);
    }

    @Test
    public void testSearch() throws IOException {
        RestHighLevelClient client = ESService.esRestClient("127.0.0.1", "kibana", "kibana");
        Map<String, String> matchMap = Maps.newHashMap();
        matchMap.put("uri", "boss/order/queryOrderList");
        matchMap.put("userid", "8608318343063552");
        SearchResponse searchResponse = ESService.search(client, ESService.of("bossaccesslog-info*", 0, 10, matchMap));
        System.out.println("response="+JsonUtil.toJson(searchResponse.getHits()));
    }

    @Test
    public void testDemo() throws IOException {
        RestHighLevelClient client = ESService.esRestClient("127.0.0.1", "kibana", "kibana");
        ofDemoService(client);
    }


    /**
     * 查询条件
     */
    public static SearchRequest ofDemoSearchRequest() {
        String indices = "log-info*";
        // 创建检索请求
        SearchRequest searchRequest = new SearchRequest();
        searchRequest.indices(indices);

        // 查询条件
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        // 查询所有
        sourceBuilder.query(QueryBuilders.matchAllQuery());
        // 匹配单个 name.contains(wang)
        sourceBuilder.query(QueryBuilders.matchQuery("name", "wang"));
        // 匹配多个 name1.contains(wang) || name2.contains(wang)
        sourceBuilder.query(QueryBuilders.multiMatchQuery("wang", "name1", "name2"));
        // 通配符匹配
        sourceBuilder.query(QueryBuilders.wildcardQuery("name", "*wang*"));
        // BoolQueryBuilder 复合查询 must: conditionA && conditionB
        QueryBuilder q1 = QueryBuilders.matchQuery("name", "wang");
        QueryBuilder q2 = QueryBuilders.matchQuery("id", "1");
        BoolQueryBuilder boolQueryA = QueryBuilders.boolQuery();
        boolQueryA.must(q1);
        boolQueryA.must(q2);
        sourceBuilder.query(boolQueryA);
        // BoolQueryBuilder 复合查询 should: conditionA || conditionB
        sourceBuilder.query(QueryBuilders.boolQuery().should(QueryBuilders.matchQuery("name", "wang")).should(QueryBuilders.matchQuery("id", "1")));
        // BoolQueryBuilder 复合查询 minimumShouldMatch: 最少满足2个should
        sourceBuilder.query(QueryBuilders.boolQuery().should(QueryBuilders.matchQuery("name1", "wang"))
                .should(QueryBuilders.matchQuery("name2", "wang"))
                .should(QueryBuilders.matchQuery("name3", "wang"))
                .minimumShouldMatch(2));
        // 等值查询 name==wang
        sourceBuilder.query(QueryBuilders.boolQuery().must(QueryBuilders.termQuery("name", "wang")));
        // 范围查询
        sourceBuilder.query(QueryBuilders.rangeQuery("id").from("10").to("30"));
        // 判空查询 name1有值 && name2无值
        sourceBuilder.query(QueryBuilders.boolQuery().must(QueryBuilders.existsQuery("name1")).mustNot(QueryBuilders.existsQuery("name2")));

        // include / exclude
        sourceBuilder.fetchSource(new String[]{"uri","request_id"}, new String[]{});

        // 其他查询属性
        sourceBuilder.from(0);
        sourceBuilder.size(10);
        //sourceBuilder.sort("@timestamp");
        sourceBuilder.sort(new FieldSortBuilder("@timestamp").order(SortOrder.ASC));
        sourceBuilder.timeout(TimeValue.timeValueMinutes(2L));
        searchRequest.source(sourceBuilder);
        return searchRequest;
    }

    public static void ofDemoService(RestHighLevelClient esRestClient) throws IOException {
        // 1 创建检索请求
        SearchRequest searchRequest = new SearchRequest();
        searchRequest.indices("api-info*");
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();

        /*SearchResponse searchResponse = responseBuilder.setQuery(QueryBuilders.boolQuery()
            // must表示and
            .must(rangequerybuilder) // 根据时间范围查询
            .must(QueryBuilders.existsQuery("api_id")).must(QueryBuilders.matchPhraseQuery("detail", condition)))
            .setExplain(true).execute().actionGet();*/

        // 构造检索条件
        // sourceBuilder.query();
        // sourceBuilder.from();
        // sourceBuilder.size();
        // sourceBuilder.aggregation();
        sourceBuilder.query(QueryBuilders.matchQuery("uri", "apixxx"));
        System.out.println(sourceBuilder.toString());
        searchRequest.source(sourceBuilder);
        // 2 执行检索
        SearchResponse response = esRestClient.search(searchRequest, COMMON_OPTIONS);
        // 3 分析响应结果
        System.out.println(response.toString());

        // 3.1 获取java bean Account是bean类
        SearchHits hits = response.getHits();
        SearchHit[] hits1 = hits.getHits();
        for (SearchHit hit : hits1) {
            hit.getId();
            hit.getIndex();
            String sourceAsString = hit.getSourceAsString();
            System.out.println(sourceAsString);
        }
    }
}
