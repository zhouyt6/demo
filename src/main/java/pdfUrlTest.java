import com.qf58.email.Email;
import com.qf58.email.EmailUtil;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @Author: Zhou YingTao
 * @Date: 2018/9/30
 * @Description:
 */
public class pdfUrlTest {

    public static final int ZERO = 0;
    public static final int ONE = 1;
    public static final int TWO = 2;
    public static final int SEVEN = 7;
    public static final int EIGHT = 8;
    // 北京分公司
    public static final Long BJ_COMPANYID = 200L;
    // 销售部——小客户部
    public static final Long MIN_CUSTOMER_SECTIONID = 213L;
    public static final String NEWLINE = "<br/>";
    public static final String SPACE = "&emsp;&emsp;";
    public static final String TWO_SPACE = SPACE+SPACE;
    public static final String NEWLINE_SPACE = NEWLINE+SPACE;


    public static void main(String[] args) throws Exception{
        List<PerSion> list = new ArrayList<>();
        PerSion perSion = new PerSion();
        perSion.setId(20);
        perSion.setType(1);
        perSion.setTypeDesc("ere");
        list.add(perSion);
        PerSion perSion1 = new PerSion();
        perSion1.setId(10);
        perSion1.setType(1);
        list.add(perSion1);
        PerSion perSion2 = new PerSion();
        perSion2.setId(30);
        perSion2.setType(2);
        list.add(perSion2);

        Map<Integer, Long> collect = list.stream()
                .collect(Collectors.groupingBy(PerSion::getType, Collectors.counting()));
        for (Map.Entry<Integer, Long> integerLongEntry : collect.entrySet()) {
            System.out.println(integerLongEntry.getKey()+" : "+integerLongEntry.getValue());
        }

    }

    @Test
    public void addTest() {
        Date nowTime = new Date();
        Calendar emailCalendar = Calendar.getInstance();
        // 或者用 Date 来初始化 Calendar 对象
        emailCalendar.setTime(nowTime);
        emailCalendar.set(Calendar.HOUR_OF_DAY, 0);
        emailCalendar.set(Calendar.MINUTE, 0);
        emailCalendar.set(Calendar.SECOND, 0);
        // 今天是一年中的第几天
        int day_of_year = emailCalendar.get(Calendar.DAY_OF_YEAR);
        // ====================================================================================
        Child clueSearchDTO = new Child();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        // is_child=1
        //非测试数据
//        clueSearchDTO.setIsTest(2);
        // 昨天<-----
        clueSearchDTO.setEndCreateTime(emailCalendar.getTime());
        emailCalendar.set(Calendar.DAY_OF_YEAR, day_of_year - 1);
        clueSearchDTO.setStartCreateTime(emailCalendar.getTime());
        String StartCreateTime = format.format(clueSearchDTO.getStartCreateTime());
        String EndCreateTime = format.format(clueSearchDTO.getEndCreateTime());
        System.out.println("StartCreateTime:"+StartCreateTime + "\n"+"EndCreateTime:  "+EndCreateTime);
        // 昨天----->
        // 前天<-----
        clueSearchDTO.setEndCreateTime(emailCalendar.getTime());
        emailCalendar.set(Calendar.DAY_OF_YEAR, day_of_year - 2);
        clueSearchDTO.setStartCreateTime(emailCalendar.getTime());
        System.out.println("前天StartCreateTime:"+format.format(clueSearchDTO.getStartCreateTime()) +
                "\n"+"前天EndCreateTime:  "+format.format(clueSearchDTO.getEndCreateTime()));
        // 前天----->
        // 上周昨天<-----
        emailCalendar.set(Calendar.DAY_OF_YEAR, day_of_year - 7);
        clueSearchDTO.setEndCreateTime(emailCalendar.getTime());
        emailCalendar.set(Calendar.DAY_OF_YEAR, day_of_year - 8);
        clueSearchDTO.setStartCreateTime(emailCalendar.getTime());
        System.out.println("上周昨天StartCreateTime:"+format.format(clueSearchDTO.getStartCreateTime()) +
                "\n"+"上周昨天EndCreateTime:  "+format.format(clueSearchDTO.getEndCreateTime()));
        // 上周昨天----->
        StringBuilder stb = new StringBuilder();
        long sum = 0;
        long maxNum = 0;
        long minNum = 0;
        Map<String, Long> map = new LinkedHashMap<>();
        map.put(SPACE+"昨天新增: ",sum);
        map.put(TWO_SPACE+"前天新增: ",sum);
        map.put(TWO_SPACE+"上周昨天新增: ",sum);
        map.put(NEWLINE_SPACE + "大B: ",maxNum);
        map.put(SPACE + "小B: ",minNum);
        map.put(SPACE + " 大B: ",maxNum);
        map.put(SPACE + " 小B: ",minNum);
        map.put(SPACE + "  大B: ",maxNum);
        map.put(SPACE + "  小B: ",minNum);
        for (Map.Entry<String, Long> stringLongEntry : map.entrySet()) {
            stb.append(stringLongEntry.getKey()+stringLongEntry.getValue());
//            System.out.println(stb.toString());
        }






//      StringBuilder stb = new StringBuilder();
//      stb.append("StartCreateTime: "+StartCreateTime);
//      stb.append("<br/>"+CANCEL_ORDER_REASON+"StartCreateTime: "+StartCreateTime);
//      stb.append("<br/>"+CANCEL_ORDER_REASON+"Test");
//        stb.append("&emsp;&emsp;TestTwo");
//      stb.append("&ensp;Two");
//      stb.append("&nbsp;yige");
        Email email = new Email("每天新增商机数报告",stb.toString());
        email.addReceiver("zhouyingtao@58qf.com");
//        email.addReceiver("jiwenyu@58qf.com");
//        email.addCopyTo("sunrongguo@58qf.com");
        try {
            EmailUtil.send(email);
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

}
