package org.example.practice.practicecode.skill.designpattern.createType.builder;

/**
 * 构造器不能是单例
 */
public class Test {
    public static void main(String[] args) {
        UserBuilder userBuilder = new UserBuilder();
        // 使用构造器构造 userContext
        userBuilder.setUserName("jack").setUserAge(18).setUserGender(0);
        // 输出构造好的对象
        UserContext userContext = userBuilder.getUserContext();
        System.out.println(userContext);
    }

    @org.junit.Test
    public void testSingleton() {
        // builder 不能是单例，否则可能会导致错乱
        UserBuilderSingleton builder = UserBuilderSingleton.getInstance();
        for (int i = 0; i < 100; i++) {
            String name = "name-" + i;
            int age = i;
            new Thread((() -> {
                builder.setUserName(name).setUserAge(age);
                System.out.println(builder.getUserContext());
            })).start();
        }
    }

    @org.junit.Test
    public void testPrototype() {
        UserBuilder builder = new UserBuilder();
        for (int i = 0; i < 100; i++) {
            String name = "name-" + i;
            int age = i;
            new Thread((() -> {
                builder.setUserName(name).setUserAge(age);
                System.out.println(builder.getUserContext());
            })).start();
        }
    }
}
