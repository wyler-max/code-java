import java.util.List;

import lombok.Data;

/**
 * @author wangyulin
 * @date 2021/6/23
 */
@Data
public class X {

    private List<SkuInfoX> skuInfoList;
    private List<Long> list1;
    private String str1;

    @Data
    public static class SkuInfoX {
        private int number;
        private int subNumber;
    }
}
