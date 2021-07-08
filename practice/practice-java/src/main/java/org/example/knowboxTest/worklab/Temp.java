package org.example.knowboxTest.worklab;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.junit.Test;
import org.springframework.util.CollectionUtils;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

public class Temp {

    @Test
    public void testStream() {
        ObjB objB1 = new ObjB(21, "stringB1");
        ObjB objB2 = new ObjB(22, "stringB2");

        ObjB objB3 = new ObjB(23, "stringB3");
        ObjB objB4 = new ObjB(24, "stringB4");

        List<ObjB> bList1 = Lists.newArrayList();
        bList1.add(objB1);
        bList1.add(objB2);

        List<ObjB> bList2 = Lists.newArrayList();
        bList2.add(objB3);
        bList2.add(objB4);

        ObjA objA1 = new ObjA();
        objA1.setIntVal(11);
        objA1.setStringVal("stringA1");

        ObjA objA2 = new ObjA();
        objA2.setIntVal(12);
        objA2.setStringVal("stringA2");
        objA2.setObjBList(bList1);

        ObjA objA3 = new ObjA();
        objA3.setIntVal(13);
        objA3.setStringVal("stringA3");
        objA3.setObjBList(bList2);

        ObjA objA4 = new ObjA();
        objA4.setIntVal(14);
        objA4.setStringVal("stringA4");

        Map<Integer, ObjA> aMap = Maps.newHashMap();
        aMap.put(11, objA1);
        // aMap.put(12, objA2);
        aMap.put(13, objA3);

        /*List<Integer> collect = aMap.values().stream()
                .flatMap(x -> x.getObjBList().stream().filter(p -> p.getIntValB() < 25))
                .map(ObjB::getIntValB).distinct().filter(p -> {
            return 1 < 2;
        }).collect(Collectors.toList());*/

        List<Integer> collect = aMap.values().stream().filter(x -> !CollectionUtils.isEmpty(x.getObjBList()))
            .flatMap(x -> x.getObjBList().stream().filter(p -> p.getIntValB() < 25)).map(ObjB::getIntValB).distinct()
            .filter(p -> {
                return 1 > 2;
            }).collect(Collectors.toList());

        System.out.println(collect);
    }

    @Test
    public void testUpdate() {

        ObjA objA = new ObjA();
        objA.setIntVal(1);
        System.out.println(objA.toString());
        updateValue(objA);
        System.out.println(objA.toString());
    }

    private static void updateValue(ObjA objA) {
        objA.setStringVal("123");
    }
}
