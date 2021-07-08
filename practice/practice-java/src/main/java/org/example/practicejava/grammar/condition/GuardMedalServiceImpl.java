package org.example.practicejava.grammar.condition;

/**
 * 守护勋章策略实现类
 */
public class GuardMedalServiceImpl implements IMedalService {
    @Override
    public void showMedal() {
        System.out.println("展示守护勋章");
    }
}
