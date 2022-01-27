package org.example.practice.practiceknowbox.common.util;//package org.example.practice.practiceknowbox.common.util;
//
//import java.text.Collator;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.Collections;
//import java.util.Comparator;
//import java.util.List;
//import java.util.stream.Collectors;
//
//import org.apache.commons.lang3.StringUtils;
//
//import lombok.extern.slf4j.Slf4j;
//import net.sourceforge.pinyin4j.PinyinHelper;
//import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
//import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
//import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
//import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;
//
///**
// * 汉语拼音相关
// *
// * @author zhangshuai
// * @date 2020/8/30 10:29 下午
// */
//@Slf4j
//public class PinyinUtils {
//
//    public static final HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
//
//    static {
//        // 大写
//        format.setCaseType(HanyuPinyinCaseType.UPPERCASE);
//        // 没有音标
//        format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
//    }
//
//    public static boolean isChineseChar(char c) {
//        return String.valueOf(c).matches("[\u4e00-\u9fa5]");
//    }
//
//    public static String toPinyinString(String string) {
//        List<String[]> list = new ArrayList<>();
//        try {
//            for (char c : string.toCharArray()) {
//                list.add(PinyinHelper.toHanyuPinyinStringArray(c, format));
//            }
//        } catch (Exception e) {
//            log.error("BadHanyuPinyinOutputFormatCombination error:" + string, e);
//        }
//        return list.stream().flatMap(f -> Arrays.stream(f))
//            .collect(Collectors.joining(StringUtils.SPACE, StringUtils.EMPTY, StringUtils.EMPTY));
//    }
//
//    public static char getPinyinInitial(String str) {
//        if (StringUtils.isEmpty(str)) {
//            return '#';
//        }
//        return PinyinUtils.getPinyinInitial(str.charAt(0));
//    }
//
//    public static char getPinyinInitial(char c) {
//        // 非汉字直接return
//        if (!isChineseChar(c)) {
//            return c;
//        }
//        try {
//            String[] array = PinyinHelper.toHanyuPinyinStringArray(c, format);
//            return array == null || array.length == 0 ? '#' : array[0].charAt(0);
//        } catch (Exception e) {
//            log.error("BadHanyuPinyinOutputFormatCombination error:" + c, e);
//        }
//        return '#';
//    }
//
//    public static void main(String[] args) throws BadHanyuPinyinOutputFormatCombination {
//        // 汉字排序
//        Comparator<Object> com = Collator.getInstance(java.util.Locale.CHINA);
//        String[] newArray = {"中山", "汕头", "广州", "安庆", "阳江", "南京", "武汉", "北京", "安阳", "重庆"};
//        List<String> list = Arrays.asList(newArray);
//        Collections.sort(list, com);
//        for (String i : list) {
//            System.out.print(i);
//            System.out.print(PinyinUtils.getPinyinInitial(i.charAt(0)) + " ");
//        }
//        System.out.println(PinyinUtils.getPinyinInitial('1'));
//    }
//
//}
