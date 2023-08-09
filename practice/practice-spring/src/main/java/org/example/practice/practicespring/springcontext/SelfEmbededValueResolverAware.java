package org.example.practice.practicespring.springcontext;

import org.springframework.context.EmbeddedValueResolverAware;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringValueResolver;

/**
 * 基于Spring解析获取 properties 文件单个属性值
 *
 * 功能和 @Value 注解类似，用于加载配置文件，并可以解析值中的表达式
 *
 * 例如：db.mysql.union=${db.mysql.user}/${db.mysql.password}
 */
@Component
@PropertySource("classpath:/mysql.properties")
public class SelfEmbededValueResolverAware implements EmbeddedValueResolverAware {

    private StringValueResolver resolver;

    @Override
    public void setEmbeddedValueResolver(StringValueResolver resolver) {
        this.resolver = resolver;
    }

    public String getPropertiesValue(String key) {
        StringBuilder keyName = new StringBuilder("${").append(key).append("}");
        return resolver.resolveStringValue(keyName.toString());
    }
}
