/**
 * @Author: Zhou YingTao
 * @Date: 2019/4/16
 * @Description:
 */
public class ReadFileTest {

    public static void main(String[] args) throws Exception {

//        String jarWholePath = ReadFileTest.class.getProtectionDomain().getCodeSource().getLocation().getFile();
//        try {
//            jarWholePath = java.net.URLDecoder.decode(jarWholePath, "UTF-8");
//        } catch (UnsupportedEncodingException e) { System.out.println(e.toString()); }
//        String jarPath = new File(jarWholePath).getParentFile().getAbsolutePath();
//        System.out.println(jarWholePath);
//        System.out.println(jarPath);
//        FileInputStream inputStream = new FileInputStream(new File("D:/data/sendPhone.txt"));
//        Scanner sc = new Scanner(inputStream, "UTF-8");
//        try {
////            inputStream = new FileInputStream(new File("D:/data/sendPhone.txt"));
////            sc = new Scanner(inputStream, "UTF-8");
//            while (sc.hasNextLine()) {
//                String line = sc.nextLine();
//                System.out.println(line);
//            }
//        } finally {
//            if (sc != null) {
//                sc.close();
//            }
//            if (inputStream != null) {
//                inputStream.close();
//            }
//        }

//        ArrayList<Integer> list = new ArrayList<>();
//        for (int i = 0; i < 10000; i++) {
//            list.add(i);
//        }
//
//        // 统计并行执行list的线程
//        Set<Thread> threadSet = new CopyOnWriteArraySet<>();
//        // 并行执行
//        list.parallelStream().forEach(integer -> {
//            Thread thread = Thread.currentThread();
//            // System.out.println(thread);
//            System.out.println(integer);
//            // 统计并行执行list的线程
//            threadSet.add(thread);
//        });
//        System.out.println("threadSet一共有" + threadSet.size() + "个线程");
//        System.out.println("系统一个有"+Runtime.getRuntime().availableProcessors()+"个cpu");

//        DateTime parse = DateTime.parse("2019-02-21", "yyyy-MM-dd");
//        String string = "";
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
//        Date date = new Date();
//        date.setDate(8);
//        String format = simpleDateFormat.format(date);
//        Date parse = simpleDateFormat.parse(format);
//        System.out.println(parse);
//        int date1 = parse.getDay();
//        System.out.println("date1   "+date1);
//        int date2 = parse.getDate();
//        System.out.println("date2   "+date2);
//        String string = new DateTime(parse).toString("yyyy-MM-dd HH:mm:ss");
//        System.out.println(string);
        String str = "日常保洁,开荒保洁,玻璃清洁,";

        String substring = str.substring(0, str.length() - 1);
        System.out.println(substring+"____"+str.length());
    }
}
