package org.example.practicejava.utils.elasticsearch;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RestHighLevelClient;
import org.example.practicejava.utils.JsonUtil;
import org.example.practicejava.utils.date.DateUtil;
import org.junit.Test;

import com.google.common.collect.Maps;

import lombok.extern.slf4j.Slf4j;

/**
 * @author wangyulin
 * @date 2022/7/2
 */
@Slf4j
public class ESTest {

    @Test
    public void testPrint() throws IOException {
        String fileName = "/Users/wangyulin/tmp/es/order_0628.csv";
        File file = FileUtils.getFile(fileName);
        if (file.exists()) {
            file.delete();
        }
        String title = "执行时间, traceId, 请求IP, 请求参数, 查询结果(数量)\n";
        FileUtils.writeStringToFile(file, title, Charset.defaultCharset(), true);
        Map<String, String> matchMap = Maps.newHashMap();
        matchMap.put("uri", "//boss/order/queryOrderList");
        matchMap.put("userid", "8608318343063552");
        // todo 改成正式的es地址和账户名密码
        RestHighLevelClient client = ESService.esRestClient("127.0.0.1", "kibana", "kibana");
        int total = 40;
        int step = 20;
        int hitCount = 0;
        for (int i = 0; i < total; i = i + step) {
            SearchResponse response = ESService.search(client, ESService.of("bossaccesslog-info*", i, step, matchMap));
            if (response == null || response.getHits() == null) {
                log.info("response or response.getHits() is null i=" + i);
                break;
            }
            ESHits esHits = JsonUtil.toObject(JsonUtil.toJson(response.getHits()), ESHits.class);
            if (esHits == null) {
                log.info("esHits is null i=" + i);
                break;
            }
            for (ESHits.Hit hit : esHits.getHits()) {
                ESSource esSource = JsonUtil.toObject(hit.getSourceAsString(), ESSource.class);
                if (esSource.getUri().contains("//boss/order/queryOrderList")) {
                    System.out.println("requestId=" + esSource.getRequest_id());
                    ESSource.Header header = JsonUtil.toObject(esSource.getHeader(), ESSource.Header.class);
                    QueryOrderList.Param param = JsonUtil.toObject(esSource.getParam(), QueryOrderList.Param.class);
                    QueryOrderList.ReqBody reqBody =
                        JsonUtil.toObject(param.getREQUEST_BODY(), QueryOrderList.ReqBody.class);
                    QueryOrderList.Resp resp = JsonUtil.toObject(esSource.getResp(), QueryOrderList.Resp.class);
                    String content = buildStr(esSource, header, reqBody, resp);
                    try {
                        FileUtils.writeStringToFile(file, content, Charset.defaultCharset(), true);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    hitCount++;
                }
            }
        }
        client.close();
        System.out.println("hitCount=" + hitCount);
    }

    public static String convertTimestampStr(String str) {
        return DateUtil.DATETIME
            .format(DateUtils.addHours(Objects.requireNonNull(DateUtil.dateParse(DateUtil.DATETIME_TZ, str)), 8));
    }

    /**
     * 解析json，更好地展示数据
     */
    public static String parseJson(String json) {
        String strRep = json.replace("{", "").replace("}", "").replace(":", "=").replace(",", " ").replace("\"", "")
            .replace("\"\"", "");
        List<String> strList = Arrays.asList(strRep.split(" "));
        StringBuilder strAppend = new StringBuilder();
        for (String s : strList) {
            if (s.toLowerCase().contains("time") && !s.endsWith("=")) {
                String[] xSplit = s.split("=");
                strAppend.append(xSplit[0]).append("=");
                strAppend.append(DateUtil.DATETIME.format(new Date(Long.parseLong(xSplit[1]) * 1000L)));
            } else {
                strAppend.append(s);
            }
            strAppend.append(" ");
        }
        return strAppend.toString();
    }

    public static String buildStr(ESSource esSource, ESSource.Header header, QueryOrderList.ReqBody reqBody,
        QueryOrderList.Resp resp) {
        if (header == null || reqBody == null || resp == null) {
            return "nothing";
        }
        StringBuilder str = new StringBuilder();
        str.append(convertTimestampStr(esSource.getTimestamp())).append(", ");
        str.append(esSource.getRequest_id()).append(", ");
        str.append(header.getRemoteIp()).append(", ");
        str.append(parseJson(JsonUtil.toJson(reqBody))).append(", ");
        str.append(resp.getPager().getPageSize()).append("\n");
        return str.toString();
    }

    public static void main(String[] args) {
        String str =
            "{\"createBeginTime\":1656345600,\"createEndTime\":1656431999,\"channel\":\"\",\"price\":\"\",\"ownerKey\":\"\",\"orderNumber\":\"\",\"userKey\":\"\",\"pager\":{\"pageNum\":1,\"pageSize\":10}}";
        String strRep = str.replace("{", "").replace("}", "").replace(":", "=").replace(",", " ").replace("\"", "")
            .replace("\"\"", "");
        String[] split = strRep.split(" ");
        StringBuilder strAppend = new StringBuilder();
        for (String s : Arrays.asList(split)) {
            if (s.toLowerCase().contains("time") && !s.endsWith("=")) {
                String[] xSplit = s.split("=");
                strAppend.append(xSplit[0]).append("=");
                strAppend.append(DateUtil.DATETIME.format(new Date(Long.parseLong(xSplit[1]) * 1000L)));
            } else {
                strAppend.append(s);
            }
            strAppend.append(" ");
        }
        System.out.println(strAppend);
    }
}
