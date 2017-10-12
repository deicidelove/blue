package com.common.system.util;

import java.beans.PropertyDescriptor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.PropertyUtilsBean;

/**
 * Created by Mr.Yangxiufeng on 2017/6/22.
 * Time:18:00
 * ProjectName:Common-admin
 */
public class Convert {
    /**
     * string 转换成Long数组
     *
     * @param str
     * @param splitStr
     * @return
     */
    public final static List<Integer> toIntegerList(String str, String splitStr) {
        if (str != null) {
            ArrayList<Integer> integerList = new ArrayList<Integer>();
            String[] strList = str.split(splitStr);
            for (String string : strList) {
                integerList.add(Integer.parseInt(string));
            }
            return integerList;
        }
        return null;
    }
    
    public static Map<String, Object> beanToMap(Object obj) { 
        Map<String, Object> params = new HashMap<String, Object>(0); 
        try { 
            PropertyUtilsBean propertyUtilsBean = new PropertyUtilsBean(); 
            PropertyDescriptor[] descriptors = propertyUtilsBean.getPropertyDescriptors(obj); 
            for (int i = 0; i < descriptors.length; i++) { 
                String name = descriptors[i].getName(); 
                if (!"class".equals(name)) { 
                    params.put(name, propertyUtilsBean.getNestedProperty(obj, name)); 
                } 
            } 
        } catch (Exception e) { 
            e.printStackTrace(); 
        } 
        return params; 
}
}
