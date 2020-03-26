package com.oohuo;/**
 * @ClassName BilibiliVideoIdConversion
 * @Description TODO
 * @Author ZH
 * @Date 2020-03-27 0:47
 * @Version 1.0
 **/

import java.util.HashMap;

/**
 * 哔哩哔哩视频ID转换
 * @author
 * @create 2020-03-27 0:47
 * @version 1.0
 **/
public class BilibiliVideoIdConversion {
    //知乎大佬得来的转换算法

    static int[] ss = {11, 10, 3, 8, 4, 6, 2, 9, 5, 7};
    static long xor = 177451812;
    //二进制时加减数1

    static long add = 8728348608L;
    //十进制时加减数2
    //变量初始化工作，加载哈希表

    private static String table = "fZodR9XQDSUm21yCkr6zBqiveYah8bt4xsWpHnJE7jL5VG3guMTKNPAwcF";
    private static HashMap<String, Integer> mp = new HashMap<>();
    private static HashMap<Integer, String> mp2 = new HashMap<>();


    //现在，定义av号和bv号互转的方法
    //定义一个power乘方方法，这是转换进制必要的

    public static long power(int a, int b) {
        long power = 1;
        for (int c = 0; c < b; c++) {
            power *= a;
        }
        return power;
    }
    //bv转av方法

    public static String b2v(String s) {
        long r = 0;
        //58进制转换
        for (int i = 0; i < 58; i++) {
            String s1 = table.substring(i, i + 1);
            mp.put(s1, i);
        }
        for (int i = 0; i < 6; i++) {
            r = r + mp.get(s.substring(ss[i], ss[i] + 1)) * power(58, i);
        }
        //转换完成后，需要处理，带上两个随机数
        return "av" + ((r - add) ^ xor);
    }
    //av转bv方法
    public static String v2b(String st) {
        long s = Long.parseLong(st.split("av")[1]);
        StringBuffer sb = new StringBuffer("BV1  4 1 7  ");
        //逆向思路，先将随机数还原
        s = (s ^ xor) + add;
        //58进制转回
        for (int i = 0; i < 58; i++) {
            String s1 = table.substring(i, i + 1);
            mp2.put(i, s1);
        }
        for (int i = 0; i < 6; i++) {
            String r = mp2.get((int) (s / power(58, i) % 58));
            sb.replace(ss[i], ss[i] + 1, r);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        //放一个党妹视频测试
        System.out.println("https://www.bilibili.com/video/av"+v2b("av55443085"));
        System.out.println("https://www.bilibili.com/video/"+b2v("BV1H4411P7rE"));
    }
}
