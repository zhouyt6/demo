import org.joda.time.DateTime;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;

/**
 * @Author: Zhou YingTao
 * @Date: 2018/9/27
 * @Description:
 */
public class CalendarTest {
//    private static final Logger logger = LoggerFactory.getLogger(CalendarTest.class);

    public static void main(String[] args) throws Exception{
        ArrayList<Date> dateList = new ArrayList<>();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date nowDate = new DateTime().toDate();
        String str = "2019-04-14 00:00:00";
        Date parse = format.parse(str);
        dateList.add(parse);
        DateTime sourceDateTime = new DateTime(parse);
        DateTime begin = new DateTime("2015-02-14");
        Date end = new DateTime("2015-05-01").toDate();
        System.out.println("parse : "+parse.getDate());
//        Period period = new Period(begin, end, PeriodType.months());
        System.out.println("end : "+end.getDate());
        Date date = new Date();
        java.time.LocalDate localDate = java.time.LocalDate.now();
        ZonedDateTime zonedDateTime = localDate.atStartOfDay(ZoneId.systemDefault());
        Date from = Date.from(zonedDateTime.toInstant());
        System.out.println("from : "+new DateTime(from).toString("yyyy-MM-dd HH:mm:ss"));

//        int num =0;
//        while (true) {
//
//            DateTime dateTime = begin.plusMonths(++num);
//            System.out.println("增加后时间"+dateTime.toString("yyyy-MM-dd HH:mm:ss"));
//            if (dateTime.isAfter(end)) {
//                break;
//            }
//        }
//        System.out.println(format.format(date));

//        long l = System.currentTimeMillis();
//        Calendar todayDate = Calendar.getInstance();  //smsCalendar
//        Calendar lastFollwDate = Calendar.getInstance();
//        lastFollwDate.set(Calendar.DAY_OF_YEAR, todayDate.get(Calendar.DAY_OF_YEAR) - 80);
//        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(todayDate.getTime()));
//        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(lastFollwDate.getTime()));
//        int days = todayDate.get(Calendar.DAY_OF_YEAR) - lastFollwDate.get(Calendar.DAY_OF_YEAR);
//        String msg = "该客户将在" + days + "天后，自动释放至公海。";
//            System.out.print(msg);
//        long l2 = System.currentTimeMillis();
//        System.out.println(l2-l);
    }

    @Test
    public void trueTest() {

        ArrayList<Entity58> list = new ArrayList<>();
        TongCheng tongCheng = new TongCheng();
        tongCheng.setFuli("dfdf");
        tongCheng.setYaoqiu("dfd");
        tongCheng.setSalary("dfd");
        tongCheng.setGuimo("dfd");
        tongCheng.setHangye("dfdf232");
        tongCheng.setComp_name("523");
        tongCheng.setUrl("Urlererere");

        System.out.println(tongCheng);


    }

    private boolean trueAndFalseTest(int i,int b ,int c) {
        return i>0;
    }
}
