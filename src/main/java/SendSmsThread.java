import java.util.ArrayList;

/**
 * @Author: Zhou YingTao
 * @Date: 2019/4/17
 * @Description:
 */
public class SendSmsThread implements Runnable{

    static ArrayList<String> arrayList;
    Object obj = new Object(); //使用上帝创建对象

    public SendSmsThread(ArrayList<String> arrayList) {
        this.arrayList = arrayList;
    }

    static int num = 0;

    @Override
    public void run() {
        synchronized (obj) {
            for (String mobile : arrayList) {
                System.out.println(Thread.currentThread().getName()+" : "+mobile);
                num++;
            }
        }
        System.out.println("成功Num: "+num );
    }
}
