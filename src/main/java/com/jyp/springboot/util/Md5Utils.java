package com.jyp.springboot.util;

import cn.hutool.crypto.SecureUtil;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.MessageDigest;
import java.util.*;

/**
 * Md5加密方法
 * 
 * @author ruoyi
 */
public class Md5Utils
{
    private static final Logger log = LoggerFactory.getLogger(Md5Utils.class);
    private static final String hexDigIts[] = {"0","1","2","3","4","5","6","7","8","9","a","b","c","d","e","f"};


    private static byte[] md5(String s)
    {
        MessageDigest algorithm;
        try
        {
            algorithm = MessageDigest.getInstance("MD5");
            algorithm.reset();
            algorithm.update(s.getBytes("UTF-8"));
            byte[] messageDigest = algorithm.digest();
            return messageDigest;
        }
        catch (Exception e)
        {
            log.error("MD5 Error...", e);
        }
        return null;
    }

    private static final String toHex(byte hash[])
    {
        if (hash == null)
        {
            return null;
        }
        StringBuffer buf = new StringBuffer(hash.length * 2);
        int i;

        for (i = 0; i < hash.length; i++)
        {
            if ((hash[i] & 0xff) < 0x10)
            {
                buf.append("0");
            }
            buf.append(Long.toString(hash[i] & 0xff, 16));
        }
        return buf.toString();
    }

    public static String hash(String s)
    {
        try
        {
            return new String(toHex(md5(s)).getBytes("UTF-8"), "UTF-8");
        }
        catch (Exception e)
        {
            log.error("not supported charset...{}", e);
            return s;
        }
    }

    /**
     * 根据 map 和 key 生成 sign
     * @param map
     * @param key
     * @return sign
     * 1.将 map 排序
     * 2.map 参数拼接,最后加上 key
     * 3.将整串生成 sign
     */

    public static String getSign(Map<String,Object> map, String key){
        ArrayList<String> list = new ArrayList<String>();
        for(Map.Entry<String,Object> entry:map.entrySet()){
            if(null != entry.getValue() && !"".equals(entry.getValue())){
                if(entry.getValue() instanceof JSONObject) {
                    list.add(entry.getKey() + "=" + getSortJson((JSONObject) entry.getValue()) + "&");
                }else {
                    list.add(entry.getKey() + "=" + entry.getValue() + "&");
                }
            }
        }
        int size = list.size();
        String [] arrayToSort = list.toArray(new String[size]);
        Arrays.sort(arrayToSort, String.CASE_INSENSITIVE_ORDER);
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < size; i ++) {
            sb.append(arrayToSort[i]);
        }
        String result = sb.toString();
        result += "key=" + key;
        log.info("Sign Before MD5:" + result);
        result = SecureUtil.md5(result).toUpperCase();
        log.info("Sign Result:" + result);
        return result;
    }

    /**
     * 对json进行排序处理
     * @param json json串
     * @return
     */
    public static String getSortJson(JSONObject json) {
        Set<String> set = json.keySet();
        List<String> list = new ArrayList<>();
        list.addAll(set);
        Collections.sort(list);
        StringBuffer sb = new StringBuffer();
        for (String key : list) {
            sb.append(key).append("=").append(json.getString(key)).append("&");
        }
        sb.replace(sb.length() - 1, sb.length(), "");
        return sb.toString();
    }
}
