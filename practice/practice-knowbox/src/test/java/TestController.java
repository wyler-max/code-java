import java.util.List;

import org.assertj.core.util.Lists;
import org.springframework.http.converter.GenericHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.ResourceHttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;

/**
 * @author wangyulin
 * @date 2021/10/21
 */
public class TestController {

    public static void main(String[] args) {

        List<HttpMessageConverter<?>> messageConverters = Lists.newArrayList();
        messageConverters.add(new StringHttpMessageConverter());
        messageConverters.add(new ResourceHttpMessageConverter(false));
        for (HttpMessageConverter<?> messageConverter : messageConverters) {
            if (messageConverter instanceof GenericHttpMessageConverter) {
                System.out.println("111");
            } else {
                System.out.println("222");
            }
        }
    }
}
