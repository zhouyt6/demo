package util;

/**
 * @Author: Zhou YingTao
 * @Date: 2019/3/29
 * @Description:
 */
public class RandomUtil {

    public static void main(String[] args) {
        // 获取指定数位的随机数
        System.out.println(getChar(12));
    }
    public static String getChar(int length) {
        char[] ss = new char[length];
        int i=0;
        while(i<length) {
            int f = (int) (Math.random()*3);
            if(f==1)
                ss[i] = (char) ('a'+Math.random()*26);
            else
                ss[i] = (char) ('0'+Math.random()*10);
            i++;
        }
        String str=new String(ss);
        return str;
    }

}
