import java.util.ArrayList;
import java.util.List;

import com.google.common.collect.Lists;

/**
 * @author wangyulin
 * @date 2021/6/23
 */
public class Test {
    public static void main(String[] args) {
        /*List<Integer> list = Lists.newArrayList(10, 30, 50);
        System.out.println(list.indexOf(40));*/

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
