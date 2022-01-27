package org.example.practice.practiceknowbox.common.aliyun.acm;

import java.io.IOException;
import java.io.StringReader;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.acm.shaded.com.google.common.collect.Maps;
import com.alibaba.edas.acm.listener.ConfigChangeListener;

import lombok.extern.slf4j.Slf4j;

/**
 * @author yijiu.chen
 * @date 2020/06/05
 */
@Slf4j
public abstract class MapPropertiesListener extends ConfigChangeListener {

    @Override
    public void receiveConfigInfo(String configInfo) {
        if (StringUtils.isBlank(configInfo)) {
            this.onChange(Maps.newHashMap());
            return;
        }
        Properties properties = new Properties();
        try {
            properties.load(new StringReader(configInfo));
            this.onChange(Maps.fromProperties(properties));
        } catch (IOException e) {
            log.error("MapPropertiesListener load properties error:" + configInfo, e);
        }
    }

    public abstract void onChange(Map<String, String> conf);
}
