import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.google.common.collect.Lists;

/**
 * @author wangyulin
 * @date 2021/6/23
 */
public class TestX {

    @Test
    public void testNpe() {

        X x = new X();
        List<X.SkuInfoX> xList = Lists.newArrayList();
        for (int i = 1; i <= 2; i++) {
            X.SkuInfoX skuInfoX = new X.SkuInfoX();
            skuInfoX.setNumber(10 * i);
            skuInfoX.setSubNumber(100 * i);
            xList.add(skuInfoX);
        }
        x.setSkuInfoList(xList);
        System.out.println(x);

        Y y = new Y();
        List<Y.SkuInfoY> yList = new ArrayList<>();
        x.getSkuInfoList().forEach(p -> {
            Y.SkuInfoY skuInfoY = new Y.SkuInfoY();
            skuInfoY.setNumber(p.getNumber());
            skuInfoY.setSubNumber(p.getSubNumber());
            yList.add(skuInfoY);
        });
        y.setSkuInfoList(yList);
        System.out.println(y);
    }
}
