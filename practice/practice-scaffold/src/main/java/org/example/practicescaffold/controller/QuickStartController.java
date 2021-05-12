package org.example.practicescaffold.controller;

import org.example.practicescaffold.common.utils.yaml.YmlListCityUtil;
import org.example.practicescaffold.common.utils.yaml.YmlListStudentUtil;
import org.example.practicescaffold.common.utils.yaml.YmlMapStudentUtil;
import org.example.practicescaffold.common.utils.yaml.YmlObjectPersonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * 快速启动控制器
 */
@RestController
@RequestMapping("/quickStart")
public class QuickStartController {

    @Value("${name}")
    private String name;
    @Value("${person.name}")
    private String personName;
    @Value("${person.age}")
    private int personAge;
    @Value("${person.addr}")
    private String personAddr;

    @Autowired
    private YmlObjectPersonUtil ymlObjectPersonUtil;

    // 集合、字符串
    @Autowired
    private YmlListCityUtil ymlListCityUtil;
    // 集合、字符串
    @Value("#{'${city3}'.split(',')}")
    private List<String> cities;
    // 集合、对象
    @Autowired
    private YmlListStudentUtil ymlListStudentUtil;

    // Map
    @Autowired
    private YmlMapStudentUtil ymlMapStudentUtil;
    // 获取Map
    @Value("#{${map2}}")
    private Map<String,String> maps2;

    @RequestMapping("/quick")
    public String quick() {
        System.out.println(3|9);
        return "SpringBoot 快速访问成功！";
    }

    @RequestMapping("/quick2")
    public String quick2() {
        // 打印获取的配置
        String output = "打印获取的配置:<br/>";
        output += "键值对：<br/>";
        output += "name: " + name + "<br/>";

        output += "对象：<br/>";
        output += "person.name: " + personName + "<br/>";
        output += "person.age: " + personAge + "<br/>";
        output += "person.addr: " + personAddr + "<br/>";

        output += "Object：<br/>";
        output += "person.name: " + ymlObjectPersonUtil.getName() + "<br/>";
        output += "person.age: " + ymlObjectPersonUtil.getAge() + "<br/>";
        output += "person.addr: " + ymlObjectPersonUtil.getAddr() + "<br/>";

        output += "集合、字符串：<br/>";
        output += "city.list: " + ymlListCityUtil.getList().toString() + "<br/>";
        output += "city3.list: " + cities.toString() + "<br/>";

        output += "集合、对象：<br/>";
        output += "student .list: " + ymlListStudentUtil.getList().toString() + "<br/>";

        output += "Map、字符串：<br/>";
        output += "maps.item1: " + ymlMapStudentUtil.getItem1().toString() + "<br/>";
        output += "maps.item2: " + ymlMapStudentUtil.getItem2().toString() + "<br/>";
        output += "map2: " + maps2.toString() + "<br/>";

        output += "<br/>";
        return output;
    }

    @RequestMapping("/quick3")
    public String quick3() {
        return "OK";
    }

}
