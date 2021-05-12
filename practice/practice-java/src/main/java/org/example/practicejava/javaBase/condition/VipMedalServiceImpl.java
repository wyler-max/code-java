package org.example.practicejava.javaBase.condition;

/**
 * VIP勋章策略实现类
 */
public class VipMedalServiceImpl implements IMedalService {
    @Override
    public void showMedal() {
        System.out.println("会员勋章");
    }
}
