package org.example.practicejava.worlab2;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.util.CollectionUtils;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Author wangyulin
 * @Date 2020/12/11 14:50
 */
public class Temp {

    @Data
    @AllArgsConstructor
    static class QueryClassListDto {
        int subject;
        int classNumber;
        String createTime = "";
    }

    public static void printList(List<QueryClassListDto> clasList ){
        clasList.forEach(System.out::println);
    }

    public static List<QueryClassListDto> popGivenClassNumbers(List<QueryClassListDto> classList, List<Integer> classNumbers) {

        if (CollectionUtils.isEmpty(classList) || CollectionUtils.isEmpty(classNumbers)) {
            return Collections.emptyList();
        }
        int currentSubject = classList.get(0).getSubject();
        int insertIndex = 0;
        int classNumberIndex = 0;
        long currentClassNumber = classNumbers.get(0);
        int size = classList.size();
        Map<Integer, Integer> subjectIndexMap = Maps.newHashMap();
        subjectIndexMap.put(currentSubject, insertIndex);

        List<QueryClassListDto> result = Lists.newArrayListWithExpectedSize(size);
        for (int i = 0; i < size; i++) {
            QueryClassListDto dto = classList.get(i);
            if (dto.getSubject() != currentSubject) {
                currentSubject = dto.getSubject();
                insertIndex = i;
                subjectIndexMap.put(currentSubject, insertIndex);
            }
            if (dto.getClassNumber() == currentClassNumber) {
                result.add(subjectIndexMap.get(dto.getSubject()), dto);
                classNumberIndex++;
                if (classNumberIndex < classNumbers.size()) {
                    currentClassNumber = classNumbers.get(classNumberIndex);
                }
            } else {
                result.add(dto);
            }
        }
        return result;
    }

    public static List<QueryClassListDto> popGivenClassNumbers2(List<QueryClassListDto> classList, List<Integer> classNumbers) {

        if (CollectionUtils.isEmpty(classList) || CollectionUtils.isEmpty(classNumbers)) {
            return Collections.emptyList();
        }
        if (classNumbers.size() <= 1) {
            return popGivenClassNumbers(classList, classNumbers);
        }
        List<QueryClassListDto> classListCopy = classList;
        for (int i = 0; i < classNumbers.size(); i++) {
            classListCopy = popGivenClassNumbers(classListCopy, Collections.singletonList(classNumbers.get(i)));
        }
        return classListCopy;
    }

    /*public static List<QueryClassListDto> popGivenClassNumbers3(List<QueryClassListDto> classList, List<Integer> classNumbers) {

        if (CollectionUtils.isEmpty(classList) || CollectionUtils.isEmpty(classNumbers)) {
            return Collections.emptyList();
        }

        Map<Integer, List<QueryClassListDto>> subjectGroup = classList.stream().collect(Collectors.groupingBy(QueryClassListDto::getSubject));


        Map<Integer, QueryClassListDto> classMap = classList.stream().collect(Collectors.toMap(QueryClassListDto::getClassNumber, o -> o));
        int currentSubject = classList.get(0).getSubject();
        for (int i = 0; i < classNumbers.size(); i++) {
            for (int j = 0; j < classList.size(); j++) {
                int subjectFirstIndex = 0;
                if (classMap.get(i).getSubject() == classList.get(j).getSubject()) {
                    subjectFirstIndex = j;
                    if (classMap.get())
                }
            }
        }
        for (int i = 0; i < classList.size(); i++) {
            QueryClassListDto queryClassListDto = classList.get(i);
            if (queryClassListDto.getSubject() != currentSubject) {

            }
        }
        return classListCopy;
    }*/

    public static void main(String[] args) {
        List<QueryClassListDto> clasList = Lists.newArrayList();
        clasList.add(new QueryClassListDto(10,1, "2020-12-01"));
        clasList.add(new QueryClassListDto(10,2, "2020-12-02"));
        clasList.add(new QueryClassListDto(10,3, "2020-12-07"));
        clasList.add(new QueryClassListDto(20,4, "2020-12-03"));
        clasList.add(new QueryClassListDto(20,5, "2020-12-05"));
        clasList.add(new QueryClassListDto(20,6, "2020-12-06"));
        clasList.add(new QueryClassListDto(30,7, "2020-12-04"));
        clasList.add(new QueryClassListDto(30,8, "2020-12-08"));
        clasList.add(new QueryClassListDto(30,9, "2020-12-09"));
        //printList(clasList);
        System.out.println("++++++分割线++++++");
        clasList.add(0, new QueryClassListDto(31,9, "2020-12-09"));
        //printList(clasList);
        /*List<QueryClassListDto> sorted = clasList.stream().sorted(Comparator.comparing(QueryClassListDto::getSubject).reversed().thenComparing(x -> x.getClassNumber() == 4)).collect(Collectors.toList());
        System.out.println("===========");
        printList(sorted);*/
        /*System.out.println(clasList.hashCode());
        System.out.println("+++++++++ 分界线 +++++++++");
        clasList.get(3).setCreateTime("123");
        clasList.get(3).setSubject(9);
        printList(clasList);*/

        /*int[] numbers = new int[] {1, 2};

        int[] numbers2 = new int[] {1, 2, 5, 6, 8, 9};
        int[] numbers3 = new int[] {9, 8, 6, 5, 2, 1};
        int[] numbers4 = new int[] {2, 1, 6, 5, 9, 8};
        int[] numbers5 = new int[] {1, 1, 6, 1, 8, 9};

        List<Integer> classNumbers = Arrays.stream(numbers4).boxed().collect(Collectors.toList());

        System.out.println(classNumbers);
        List<QueryClassListDto> classListDtos = popGivenClassNumbers(clasList, classNumbers);
        printList(classListDtos);
        System.out.println(classListDtos.hashCode());*/
        List<Integer> list = Lists.newArrayList(1,3,5);
        for (int i = 0; i < list.size(); i++) {
            System.out.println("i="+i);
        }
        list.remove(Integer.valueOf(3));
        System.out.println("=========");
        for (int i = 0; i < list.size(); i++) {
            System.out.println("i="+i);
        }

    }
}
