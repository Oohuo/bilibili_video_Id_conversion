package com.oohuo;/**
 * @ClassName OfclConversion
 * @Description TODO
 * @Author ZH
 * @Date 2020-03-27 1:16
 * @Version 1.0
 **/

import com.alibaba.fastjson.JSONObject;

/**
 * 调用官方api转换
 * @author
 * @create 2020-03-27 1:16
 * @version 1.0
 **/
public class OfclConversion {
    public static String a2b(String aid){
        String bvid = null;
        String url_blbl = HttpRequest.sendGet("https://api.bilibili.com/x/web-interface/archive/stat","aid="+aid);
        JSONObject jsonObject = JSONObject.parseObject(url_blbl);
       // System.out.println(url_blbl);
        JSONObject data1 = jsonObject.getJSONObject("data");
        bvid = data1.getString("bvid");
        String avid = data1.getString("aid");
       // System.out.println(avid);

        return bvid;
    }
    public static String b2a(String bvid){
        String aid = null;
        String url_blbl = HttpRequest.sendGet("https://api.bilibili.com/x/web-interface/view","bvid="+bvid);
        JSONObject jsonObject = JSONObject.parseObject(url_blbl);
       // System.out.println(url_blbl);
        JSONObject data1 = jsonObject.getJSONObject("data");
        String bid = data1.getString("bvid");
       // System.out.println(bid);
         aid = data1.getString("aid");
        return aid;
    }



    public static void main(String[] args) {
        OfclConversion ofclConversion = new OfclConversion();
        String aid =ofclConversion.b2a("BV1H4411P7rE");
        String bvid = ofclConversion.a2b("55443085");

        System.out.println("https://www.bilibili.com/video/av"+aid);
        System.out.println("https://www.bilibili.com/video/"+bvid);


    }
}
