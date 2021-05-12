package org.example.practicejava.javaBase.map.treemap;

import org.junit.Test;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeMap;

/**
 * TreeMap中的元素默认按照keys的自然排序排列
 *  - 对Integer来说，其自然排序就是数字的升序；对String来说，其自然排序就是按照字母表排序
 *  - 不允许key=null，允许value=null
 */
public class TreeMapTest {

    TreeMap<Integer, String> treeMap = new TreeMap<>();
    TreeMap<String, Integer> map = new TreeMap<>();

    @Test
    public void testTreeMap() {
        treeMap.put(1, "val1");
        treeMap.put(5, "val5");
        treeMap.put(3, "val3");
        treeMap.put(4, "val4");
        System.out.println("treeMap=" + treeMap);

        map.put("yaoming", 11);
        map.put("T-Mac", 1);
        map.put("Kobe", 24);
        map.put("Dagger", 20);
        System.out.println("map=" + map);
        //map.put(null, null);
        map.put("yulin", null);
        System.out.println("map=" + map);
    }

    public static void sortByKey(TreeMap<Integer, String> treeMap) {
        Set<Integer> keySet = treeMap.keySet();
        Iterator<Integer> iterator = keySet.iterator();
        while (iterator.hasNext()) {
            Integer key = iterator.next();
            System.out.println(key + ":" + treeMap.get(key));
        }
    }

    public static void main(String[] args) {

        TreeMap<Integer, String> treeMap = new TreeMap<>();
        treeMap.put(1, "val1");
        treeMap.put(5, "val5");
        treeMap.put(3, "val3");
        treeMap.put(4, "val4");
        //System.out.println("treeMap=" + treeMap);
        sortByKey(treeMap);
    }
}
