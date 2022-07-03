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
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.RangeQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
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
        System.out.println(JsonUtil.toJson(searchResponse.getHits()));
    }

    @Test
    public void testDemo() throws IOException {
        RestHighLevelClient client = ESService.esRestClient("127.0.0.1", "kibana", "kibana");
        ESService esService = new ESService();
        esService.demo(client);
    }

    public void demo(RestHighLevelClient esRestClient) throws IOException {
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
