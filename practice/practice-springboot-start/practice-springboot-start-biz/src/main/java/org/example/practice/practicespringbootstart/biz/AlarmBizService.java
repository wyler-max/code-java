package org.example.practice.practicespringbootstart.biz;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

/**
 * @author wangyulin
 * @date 2023/8/9
 */

@Service
public class AlarmBizService implements ApplicationContextAware {

    /*@Value("${spring.profiles.active}")
    private String activeProfile;*/

    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if (AlarmBizService.applicationContext == null) {
            AlarmBizService.applicationContext = applicationContext;
        }
    }

    public static String getActiveProfiles() {
        if (applicationContext != null) {
            String[] activeProfiles = applicationContext.getEnvironment().getActiveProfiles();
            if (activeProfiles != null && activeProfiles.length > 0) {
                return activeProfiles[0];
            }
        }
        return "未知环境";
    }

    public static void main(String[] args) {
        System.out.println(StringUtils.stripStart("aly-prod", "aly-"));
    }
}
