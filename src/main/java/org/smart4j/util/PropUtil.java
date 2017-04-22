package org.smart4j.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by JERRY ZHANG on 2017-4-22.
 */
public class PropUtil {
    private static Logger logger = LoggerFactory.getLogger(PropUtil.class);
    public static Properties loadProp(String fileName){

        Properties prop = null;
        InputStream is = null;

        try {
            is = Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);

            if(is == null){
                throw new FileNotFoundException("数据库配置文件找不到");
            }
            prop.load(is);
        } catch (Exception e) {
            logger.error("load propties has a error",e);
        } finally {
            if(is != null){
                try {
                    is.close();
                } catch (IOException e) {
                   logger.error("close is has a error",e);
                }
            }
        }
        return prop;

    }

    public static String getString(Properties prop, String key){
        return getString(prop,key,"");
    }

    public static String getString(Properties prop,String key ,String defaultValue){
        String value = defaultValue;
        if(prop.containsKey(key)){
            value = prop.getProperty(key);
        }

        return value;
    }
}
