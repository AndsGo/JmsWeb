package com.ldy.util;

import com.baidu.disconf.client.usertools.DisconfDataGetter;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.PropertyPlaceholderHelper;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class SysConfigHolder {
    public static final String SYS_PATH = "sys.properties";
    private static final Logger logger = LoggerFactory.getLogger(SysConfigHolder.class);
    private static Map<String, Properties> localconfig = new ConcurrentHashMap();

    public SysConfigHolder() {
    }

    public String getKey(String key) {
        return this.getKey(key, "sys.properties");
    }

    public String getKey(String key, String fileName) {
        Properties sysconfig = loadConfigFromDisconf(fileName);
        Object val = sysconfig.get(key);
        return val == null?null:val.toString();
    }

    public static Properties loadConfigFromDisconf(String propertiesName) {
        Map map = DisconfDataGetter.getByFile(propertiesName);
        Properties sysconfig = new Properties();
        if(map.isEmpty()) {
            return loadConfigFromClassPath(propertiesName);
        } else {
            sysconfig.putAll(map);
            PropertyPlaceholderHelper util = new PropertyPlaceholderHelper("${", "}");
            Iterator var4 = map.entrySet().iterator();

            while(var4.hasNext()) {
                Entry entry = (Entry)var4.next();
                String newValue = util.replacePlaceholders(entry.getValue().toString(), sysconfig);
                sysconfig.setProperty((String)entry.getKey(), newValue);
            }

            return sysconfig;
        }
    }

    private static Properties loadConfigFromClassPath(String propertiesName) {
        if(localconfig.containsKey(propertiesName)) {
            return (Properties)localconfig.get(propertiesName);
        } else {
            Properties sysconfig = new Properties();

            try {
                PropertiesConfiguration e = new PropertiesConfiguration(propertiesName);
                Iterator iterator = e.getKeys();

                while(iterator.hasNext()) {
                    String key = (String)iterator.next();
                    sysconfig.put(key, e.getString(key));
                }
            } catch (ConfigurationException var5) {
                logger.error("context", var5);
            }

            localconfig.put(propertiesName, sysconfig);
            return sysconfig;
        }
    }
}
