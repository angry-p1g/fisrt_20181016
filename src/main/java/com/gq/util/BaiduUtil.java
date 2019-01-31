package com.gq.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.gq.common.util.StringUtils;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.net.URL;
import java.net.URLEncoder;
import java.util.*;

/**
 * @Auther:
 * @Date: 2018/9/12 19:08
 * @Description:百度逆地图编码
 */
public class BaiduUtil {
    private static Logger logger = LoggerFactory.getLogger(BaiduUtil.class);
    private static final String BAIDU_APP_KEY = "g4cQxwNtdwaBYMLx39xFycUs3ETYuZKQ";


    /**
     * 输入中文地址
     *
     * @param address
     * @return lng(经度), lat(纬度)
     */
    public static Map<String, Object> getLatitude(String address) {
        String res;
        try {
            // 将地址转换成utf-8的16进制
            address = URLEncoder.encode(address, "UTF-8");
            URL resjson = new URL("http://api.map.baidu.com/geocoder/v2/?callback=renderOption&output=json&address=" + address + "&ak=" + BAIDU_APP_KEY);
            BufferedReader in = new BufferedReader(new InputStreamReader(resjson.openStream()));
            StringBuilder sb = new StringBuilder();
            while ((res = in.readLine()) != null) {
                sb.append(res.trim());
            }
            in.close();
            String str = sb.toString();
            if (StringUtils.isNotBlank(str)) {
                Map<String, Object> map = new HashMap<>();
                String subStr = str.substring(str.indexOf('(') + 1, str.indexOf("})") + 1);
                //字符串转成json格式
                JSONObject jsonObj = JSONObject.parseObject(subStr);
                //获取经度
                Object lng = jsonObj.getJSONObject("result").getJSONObject("location").get("lng");
                //获取纬度
                Object lat = jsonObj.getJSONObject("result").getJSONObject("location").get("lat");
                map.put("lng", lng);
                map.put("lat", lat);
                //返回经纬度
                return map;
            }
        } catch (Exception e) {
            logger.error("地址解析出现异常！", e);
        }
        return null;
    }

    /**
     * 地址的经纬度坐标 key lng(经度),lat(纬度)
     *
     * @param lng(经度),lat(纬度)
     * @return 返回中文地址
     */
    public static String[] getAddress(String lng, String lat) {
        String res;
        String[] address = new String[3];
        try {
            URL resjson = new URL("http://api.map.baidu.com/geocoder/v2/?callback=renderReverse&location=" + lat + "," + lng + "&output=json&pois=1&ak=" + BAIDU_APP_KEY);
            BufferedReader in = new BufferedReader(new InputStreamReader(resjson.openStream()));
            StringBuilder sb = new StringBuilder();
            while ((res = in.readLine()) != null) {
                sb.append(res.trim());
            }
            in.close();
            String str = sb.toString();
            String subStr = str.substring(str.indexOf('(') + 1, str.indexOf("})") + 1);
            JSONObject jsonObj = JSONObject.parseObject(subStr);
            //获取匹配到的中文地址
            JSONObject object = (JSONObject) jsonObj.getJSONObject("result").get("addressComponent");
            if (object != null) {
                address[0] = object.get("province").toString();
                address[1] = object.get("city").toString();
                address[2] = object.get("district").toString();
            }
        } catch (Exception e) {
            logger.error("反向地址解析出现异常！", e);
        }

        return address;
    }

    public static void main(String args[]) {
        MathContext mc = new MathContext(4, RoundingMode.HALF_UP);
//        Map<String, Object> map = BaiDuUtil.getLatitude("福建省福州市仓山区福建农林大学");
        // //输出地址所对应的经纬度
        // if (null != map) {
        //     System.out.print("福建省福州市闽侯县福州大学：");
        //     System.out.print("经度" + map.get("lng"));
        //        //     System.out.println();
        //     System.out.print(",纬度" + map.get("lat"));
        // }

        //输出经纬度对应的中文地址
        // System.out.println("经度" + map.get("lng") + ",纬度" + map.get("lat") + "
        // 对应的中文地址是：" + BaiDuUtil.getAddress("120.12385572053700000000","30.29511582271730000000"));
//        System.out.println("start---"+new Date());
//        String[] split = "116.331398,39.897445".split(",");
//        System.out.println(BaiduUtil.getAddress(split[0], split[1]));
//        System.out.println("END---"+new Date());
//        BigDecimal decimal = new BigDecimal(0.28);
//        BigDecimal decimal1 = new BigDecimal(3);
//        System.out.println(decimal.divide(decimal1,mc).setScale(4,BigDecimal.ROUND_DOWN));
//        System.out.println(new BigDecimal("0.09333").setScale(4,BigDecimal.ROUND_DOWN));
//        System.out.println(new BigDecimal("0.09335").setScale(4,BigDecimal.ROUND_DOWN));
//        System.out.println(new BigDecimal("0.09335").setScale(4,BigDecimal.ROUND_HALF_UP));
//        System.out.println(new BigDecimal("0.09333").setScale(4,BigDecimal.ROUND_HALF_UP));

        Integer type = 8;
        double grossWeight1 = 0.8;
        Map<String,Object> oldMp  = new HashMap<>();
        oldMp.put("type",type);
        oldMp.put("gross_weight",grossWeight1);
        if(oldMp.get("type") != null && ("8".equals(oldMp.get("type")) || 8 == (Integer) oldMp.get("type")) && oldMp.get("gross_weight") != null)
        {  //称重商品计算售价
            BigDecimal grossWeight = new BigDecimal((Double) oldMp.get("gross_weight")).setScale(2, BigDecimal.ROUND_HALF_UP);
//            BigDecimal appPrice = mp.getAppPrice().multiply(grossWeight).setScale(2, BigDecimal.ROUND_HALF_UP);
//            BigDecimal marketPrice = mp.getErpPrice().multiply(grossWeight).setScale(2, BigDecimal.ROUND_HALF_UP);
        }

    }
}

