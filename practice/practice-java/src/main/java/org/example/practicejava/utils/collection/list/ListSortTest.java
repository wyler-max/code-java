package org.example.practicejava.utils.collection.list;

import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * List 排序
 */
public class ListSortTest {

    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    public static class UserInfo {
        private String name;
        private String birthday;
        private Integer age;
        private Double deviation;
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    public static class OrderInfo {
        private Long orderNumber;
        // 0-未购，1-待支付，2-已购买，3-已过期，4-已取消，5-已废弃
        //private Integer orderStatus;
        private String status;
        private Integer payAmount;
        private String createTime;
        //private String updateTime;
    }

    // stream 简单排序
    public static void listSort1(List<UserInfo> userInfoList) {
        // 按波动降序--绝对值
        List<UserInfo> sortedList1 = userInfoList.stream().sorted(Comparator.comparing(a -> Double.valueOf(Math.abs(a.getDeviation())))).collect(Collectors.toList());
        Collections.reverse(sortedList1);
        //sortedList1.forEach(System.out::println);

        // 按年龄降序
        List<UserInfo> sortedList2 = userInfoList.stream().sorted(Comparator.comparing(UserInfo::getAge).reversed()).collect(Collectors.toList());
        //sortedList2.forEach(System.out::println);

        // 多字段排序 thenComparing可以一直链下去
        userInfoList.forEach(o -> {
            if ("Jack2".equals(o.getName()) || "Jack3".equals(o.getName())) {
                o.setAge(29);
            }
        });
        userInfoList.forEach(System.out::println);
        System.out.println("==================");
        List<UserInfo> sortedList3 = userInfoList.stream().sorted(Comparator.comparing(UserInfo::getAge).reversed().thenComparing(UserInfo::getBirthday).reversed()).collect(Collectors.toList());
        sortedList3.forEach(System.out::println);
    }

    // stream 自定义排序，按照指定的顺序排序
    public static void listSort2(List<OrderInfo> orderInfoList) {

        // 订单状态指定顺序-->订单价格倒序-->订单创建时间正序(如果有null，则为放到最后)
        List<String> sortList = Arrays.asList("待支付","已购买","已过期","已取消");
        // stream().sorted()排序
        List<OrderInfo> sortedOrderList = orderInfoList.stream().sorted(Comparator.comparing(OrderInfo::getStatus, (x, y) -> {
            for (String sort : sortList) {
                if (sort.equals(x) || sort.equals(y)) {
                    if (x.equals(y)) {
                        return 0;
                    } else if (sort.equals(x)) {
                        return -1;
                    } else {
                        return 1;
                    }
                }
            }
            return 0;
        }).thenComparing(OrderInfo::getPayAmount, Comparator.nullsLast(Integer::compareTo).reversed())
                .thenComparing(OrderInfo::getCreateTime, Comparator.nullsLast(String::compareTo)))
                .collect(Collectors.toList());
        sortedOrderList.forEach(System.out::println);
    }

    public static void main(String[] args) {
        List<UserInfo> userInfoList = Lists.newArrayList();
        int size = 30;
        for (int i = 0; i < size; i++) {
            userInfoList.add(new UserInfo("Jack" +i, "2020-09-" + (30-i), 30-i, Double.parseDouble(20-i + "")));
        }
        userInfoList.get(3).setBirthday("2020-09-25");
        listSort1(userInfoList);

        List<OrderInfo> orderInfoList = Lists.newArrayList(
                new OrderInfo(1001L, "待支付", 200, "2020-09-01"),
                new OrderInfo(1010L, "待支付", 300, null),
                new OrderInfo(1002L, "待支付", 300, "2020-09-03"),
                new OrderInfo(1008L, "待支付", 300, null),
                new OrderInfo(1009L, "待支付", 300, "2020-09-06"),
                new OrderInfo(1006L, "已过期", 400, "2020-09-02"),
                new OrderInfo(1003L, "待支付", 400, "2020-09-03"),
                new OrderInfo(1007L, "已取消", 300, "2020-09-03"),
                new OrderInfo(1005L, "已购买", 400, "2020-09-04"),
                new OrderInfo(1004L, "已购买", 200, "2020-09-01")
        );
        //listSort2(orderInfoList);

    }
}
