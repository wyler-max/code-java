import java.util.List;

import lombok.Data;

/**
 * @author wangyulin
 * @date 2021/6/23
 */
@Data
public class Y {

    private List<SkuInfoY> skuInfoList;

    @Data
    public static class SkuInfoY {
        private int number;
        private int subNumber;
    }
}
