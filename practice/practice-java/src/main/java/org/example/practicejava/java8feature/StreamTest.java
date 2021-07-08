package org.example.practicejava.java8feature;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.IntSummaryStatistics;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.example.knowboxTest.tmp.Model;
import org.example.practicejava.common.GroupBrandCateBO;
import org.junit.Test;

import com.google.common.collect.Lists;

/**
 * Stream 新特性Demo
 *
 * Stream 使用一种类似用 SQL 语句从数据库查询数据的直观方式来提供一种对 Java 集合运算和表达的高阶抽象 这种风格将要处理的元素集合看作一种流， 流在管道中传输， 并且可以在管道的节点上进行处理， 比如筛选，
 * 排序，聚合等。 元素流在管道中经过中间操作（intermediate operation）的处理，最后由最终操作(terminal operation)得到前面处理的结果
 *
 * 数据源 流的来源。 可以是集合，数组，I/O channel， 产生器generator 等 聚合操作 类似SQL语句一样的操作， 比如filter, map, reduce, find, match, sorted等。
 *
 * 和以前的Collection操作不同， Stream操作还有两个基础的特征： Pipelining: 中间操作都会返回流对象本身。 这样多个操作可以串联成一个管道， 如同流式风格（fluent style）。
 * 这样做可以对操作进行优化， 比如延迟执行(laziness)和短路( short-circuiting)。 内部迭代： 以前对集合遍历都是通过Iterator或者For-Each的方式, 显式的在集合外部进行迭代， 这叫做外部迭代。
 * Stream提供了内部迭代的方式， 通过访问者模式(Visitor)实现。
 *
 * stream() − 为集合创建串行流。 parallelStream() − 为集合创建并行流。
 *
 * 主要方法: filter/map/reduce/collect/peek/ average/sum/max/min
 */
public class StreamTest {

    // stream.filter
    @Test
    public void filter() {
        List<String> strings = Arrays.asList("abc", "", "bc", "efg", "abcd", "", "jkl");
        List<String> collect = strings.stream().filter(s -> !s.isEmpty()).collect(Collectors.toList());
        System.out.println(collect.toString());
    }

    // forEach
    @Test
    public void forEach() {
        Random random = new Random();
        random.ints().limit(10).forEach(System.out::println);
    }

    // stream.map.distinct.sorted
    @Test
    public void map() {
        List<Integer> integers = Arrays.asList(3, 2, 2, 3, 7, 3, 5);
        List<Integer> collect =
            integers.stream().map(i -> i * i).distinct().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
        System.out.println(collect.toString());
    }

    /**
     * 将一个函数传入map中，然后利用传入的这个函数，将集合中的每个元素处理，并将处理后的结果返回。 而flatMap与map唯一不一样的地方就是传入的函数在处理完后返回值必须是List.
     * 其实这也不难理解，既然是flatMap，那除了map以外必然还有flat的操作，所以需要返回值是List才能执行flat这一步。
     *
     * map 处理前后的元素个数相同 flatMap 处理前后的元素个数可能不一致
     */
    @Test
    public void flatMap() {
        String[] words = new String[] {"Hello", "World"};

        List<String[]> a = Arrays.stream(words).map(word -> word.split("")).distinct().collect(Collectors.toList());
        System.out.println("map: ");
        a.forEach(System.out::print);

        // 正确
        List<String> b = Arrays.stream(words).map(word -> word.split("")).flatMap(Arrays::stream).distinct()
            .collect(Collectors.toList());
        System.out.println("flatMap: ");
        b.forEach(System.out::print);
    }

    // 映射List< Model>到列表< String>在单个流中包含多个对象属性 stringA和stringB
    @Test
    public void flatMapMultiProperty() {
        Model model1 = new Model("A", "B");
        Model model2 = new Model("C", "D");
        List<Model> models = Lists.newArrayList(model1, model2);

        List<String> resultSet =
            models.stream().flatMap(e -> Stream.of(e.getStringA(), e.getStringB())).collect(Collectors.toList());
        System.out.println(resultSet.toString());
    }

    /**
     * 风险： 1、可能因为forEachOrder或sorted导致并行失效 2、可能因ForkJoinPool.common()导致线程阻塞（当阻塞的workers用完配额，所有相关程序都会被block）
     */
    @Test
    public void parallel() {
        List<String> strings = Arrays.asList("abc", "", "bc", "efg", "abcd", "", "jkl");
        long count = strings.parallelStream().filter(String::isEmpty).count();
        long count1 = strings.stream().parallel().filter(s -> !s.isEmpty()).count();
        System.out.println(count);
        System.out.println(count1);
    }

    // stream 收集器
    @Test
    public void collectors() {
        List<String> strings = Arrays.asList("abc", "", "bc", "efg", "abcd", "", "jkl");
        List<String> filtered = strings.stream().filter(string -> !string.isEmpty()).collect(Collectors.toList());
        System.out.println("筛选列表: " + filtered);
        String mergedString = strings.stream().filter(string -> !string.isEmpty()).collect(Collectors.joining(","));
        System.out.println("合并字符串: " + mergedString);

        Model model1 = new Model("A", "B");
        Model model2 = new Model("C", "D");
        List<Model> modelList = Lists.newArrayList(model1, model2);
        Map<String, Model> modelMap = modelList.stream().collect(Collectors.toMap(Model::getStringA, o -> o));
        System.out.println(modelMap.keySet());
        modelMap.values().forEach(o -> {
            System.out.println("model:  stringA=" + o.getStringA() + "; stringB=" + o.getStringB());
        });
    }

    /**
     * IntSummaryStatistics 一些产生统计结果的收集器也非常有用。它们主要用于int、double、long等基本类型上，它们可以用来产生类似如下的统计结果
     */
    @Test
    public void summaryStatistics() {
        List<Integer> numbers = Arrays.asList(3, 2, 2, 3, 7, 3, 5);
        IntSummaryStatistics stats = numbers.stream().mapToInt((x) -> x).summaryStatistics();

        System.out.println("列表中最大的数 : " + stats.getMax());
        System.out.println("列表中最小的数 : " + stats.getMin());
        System.out.println("所有数之和 : " + stats.getSum());
        System.out.println("平均数 : " + stats.getAverage());
        System.out.println("总计个数: " + stats.getCount());
    }

    /**
     * List Convert to Map
     *
     * List Stream 转换 Map时向collect()方法中传递Collector对象，对象由Collectors.toMap()方法返回。
     *
     * (oldVal, currVal) -> currVal) // key相同时当前值替换原始值 (oldVal, currVal) -> oldVal + currVal //key相同时保留原始值和当前值
     */
    @Test
    public void listConvertToMap() {
        List<GroupBrandCateBO> list = new ArrayList<>(Arrays.asList(new GroupBrandCateBO("v1", "g1", "b1"),
            new GroupBrandCateBO("v1", "g2", "b2"), new GroupBrandCateBO("v3", "g3", "b3")));
        /**
         * 错误示范： 抛出：java.lang.IllegalStateException: Duplicate key
         *
         * toMap()函数重载： 未指定合并函数mergeFunction情况下，传入throwingMerger()返回BinaryOperator对象，当出现key重复时，调用合并函数！
         * 未指定Supplier实例情况下，默认生成HashMap实例
         */
        /*Map<String, String> map0 = list.stream().collect(Collectors.toMap(GroupBrandCateBO::getVersion, GroupBrandCateBO::getGroupCode));
        System.out.println(map0.toString());*/

        Map<String, String> map = list.stream().collect(Collectors.toMap(GroupBrandCateBO::getVersion,
            GroupBrandCateBO::getGroupCode, (oldVal, currVal) -> oldVal, LinkedHashMap::new));
        System.out.println(map.getClass());
        System.out.println(map.toString());

        // 新值覆盖旧值
        Map<String, String> map1 = list.stream().collect(
            Collectors.toMap(item -> item.getVersion(), item -> item.getGroupCode(), (oldVal, currVal) -> currVal));
        System.out.println(map1.getClass());
        System.out.println(map1.toString());

        Map<String, String> map2 = list.stream().collect(Collectors.toMap(item -> item.getVersion(),
            item -> item.getGroupCode(), (oldVal, currVal) -> oldVal + currVal));
        System.out.println(map2.getClass());
        System.out.println(map2.toString());
    }

}
