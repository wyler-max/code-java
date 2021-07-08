package org.example.practice.practicespring.springcontext;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

/**
 *
 * 获取外部资源
 *
 * Spring ResourceLoader为我们提供了一个统一的getResource()方法来通过资源路径检索外部资源。
 *
 * 从而将资源或文件(例如文本文件、XML文件、属性文件或图像文件)加载到Spring应用程序上下文中的不同实现
 */
@Component
public class SelfResourceLoaderAware implements ResourceLoaderAware {

    public void setResourceData(ResourceLoader resourceLoader) throws IOException {
        System.out.println("start");
        String fileName =
            "/Users/wangyulin/work/code/github.com/code-java/practice/practice-spring/src/main/resources/files/1.txt";
        /*String path = ResourceUtils.getURL("classpath:").getPath();
        String fileName = path + "/db/tmp.sql";*/
        Resource resource = resourceLoader.getResource("file:" + fileName);
        InputStream inputStream = resource.getInputStream();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        while (true) {
            String s = bufferedReader.readLine();
            if (s == null) {
                break;
            }
            System.out.println(s);
        }
        bufferedReader.close();
    }

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        try {
            setResourceData(resourceLoader);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
