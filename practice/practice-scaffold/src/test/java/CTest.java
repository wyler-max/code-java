import java.util.List;

import com.google.common.collect.Lists;

import lombok.Data;

public class CTest {

    @Data
    public static class AClassDto {
        private long number;
        // ...
        // 未初始化
        private List<Long> classNumbers;
        // 初始化
        private List<Long> classNumberList = Lists.newArrayList();
    }

    public static void main(String[] args) {
        List<Long> list = Lists.newArrayList();
        AClassDto aClassDto = new AClassDto();
        list.addAll(aClassDto.getClassNumbers());
    }

}
