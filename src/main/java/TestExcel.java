import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.junit.Test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @Author: Zhou YingTao
 * @Date: 2018/9/3
 * @Description:
 */
public class TestExcel {

    private static final String uploadPath = null;

    public static void main(String[] args) {
//        List<Integer> list = Arrays.asList(2, 3, 4, 5, 6, 78, 46);
//        list.stream()
//                .filter((e) ->e>5)
//                .forEach(System.out::println);
//        List<Long> list1 = new ArrayList<>();
//        list1.add(1L);
//        list1.add(4L);
//        list1.add(3L);
//        Long[] array = new Long[list1.size()];
//        Long[] longs = list1.toArray(array);
//        Arrays.sort(longs);
//        System.out.println(Arrays.toString(longs));
        String formtempValue = "{\"layout\":1,\"notNull\":true,\"title\":\"商品\",\"sections\":[{\"name\":\"选项\",\"id\":1},{\"name\":\"小当家\",\"id\":2},{\"name\":\"红烧牛肉\",\"id\":3},{\"name\":\"牛奶\",\"id\":4}],\"desc\":\"\"}";
        JSONObject jsonObject = JSON.parseObject(formtempValue);
        int size = jsonObject.size();
        String sections = jsonObject.getString("sections");
        System.out.println(size);
        //        LinkedHashMap<String, Object> formtempValueMap = JSON.parseObject(formtempValue, LinkedHashMap.class);
//        for (String s : formtempValueMap.keySet()) {
//            Object o = formtempValueMap.get(s);
//           if (o instanceof String) {
//               System.out.println("is String");
//           }
//            System.out.println(o);
//        }

    }

    @Test
    public void streamTest() {
        java.util.List<User> detailList = new ArrayList();

        User user = new User();
        user.setId(3);
        user.setName("zhangame");
        user.setJsonStr("{\"1\":\"北京\",\"2\":\"58企服\"}");
        detailList.add(user);

        User user1 = new User();
        user1.setId(1);
        user1.setName("aame");
        user1.setJsonStr("{\"1\":\"北京\",\"2\":\"58企服\"}");
        detailList.add(user1);

        User user2 = new User();
        user2.setId(4);
        user2.setName("bame");
        user2.setJsonStr("{\"1\":\"北京\",\"2\":\"58企服\"}");
        detailList.add(user2);
        System.out.println(JSON.toJSONString(detailList));
        detailList.stream().sorted(Comparator.comparing(User::getId).reversed()).collect(Collectors.toList());
        System.out.println(detailList);

//        User user3 = new User();
//        user3.setId(1);
//        user3.setName("bame");
//        user3.setJsonStr("{\"1\":\"北京\",\"2\":\"58企服\"}");
//        detailList.add(user3);
//        User user4 = new User();
//        user4.setId(3);
//        user4.setName("bame");
//        user4.setJsonStr("{\"1\":\"北京\",\"2\":\"58企服\"}");
//        detailList.add(user4);

        Collections.sort(detailList, new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                User user1 = (User) o1;
                User user2 = (User) o2;
                if (user1.getId() > user2.getId()) {
                    return -1;
                }
                return 1;
            }
        });
        System.out.println(JSON.toJSONString(detailList));


        Map<Integer, User> collect = detailList.stream()
                .collect(Collectors.toMap(User::getId, obj -> obj));
        for (Integer integer : collect.keySet()) {
            if (integer.intValue() == 1) {
                collect.replace(-1, null);
            }
            User user3 = collect.get(integer);
            System.out.println(integer + " : " + user3);
        }

//        System.out.println(detailList.toString());

    }

    @Test
    public void arrsyTest() {

    }

    private String getRequireVersionDesc(String requireVersion) {
        if (StringUtils.isEmpty(requireVersion) || "0".equals(requireVersion)) {
            return "";
        }
        int version = Integer.parseInt(requireVersion);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("客户需求");
        if (version > 0 && version < 10) {
            stringBuilder.append("00");
        } else if (version >= 10 && version < 100) {
            stringBuilder.append("0");
        }
        stringBuilder.append(version);

        return stringBuilder.toString();
    }

    @Test
    public void listTojson() {
        TreeMap<String, Integer> map = new TreeMap<>();
        map.put("3", 1);
        map.put("1", 3);
        TreeMap<Integer, Integer> LongMap = new TreeMap<>();
        LongMap.put(2, 1);
        LongMap.put(1, 1);
        LongMap.put(4, 1);
        LongMap.put(3, 1);
        for (Integer id : LongMap.keySet()) {
            if (!map.containsKey(id.toString())) {
                System.out.println("此对象不存在的Id: " + id);
                continue;
            }
            System.out.println("继续执行下面的程序idList: " + id);

        }
    }

    @Test
    public void jsonTest() {

        String string = "{\"title\":32,\"sections\":[{\"name\":\"选项\",\"id\":1}," +
                "{\"id\":2,\"name\":\"河北\"},{\"id\":3,\"name\":\"山东\"}," +
                "{\"id\":4,\"name\":\"河南\"}],\"desc\":\"省说明\"," +
                "\"notNull\":true,\"layout\":1}";
        JSONObject jsonObject = JSON.parseObject(string);
        int title = jsonObject.getIntValue("title");
//        for (int i = 0; i < sections.size(); i++) {
//            JSONObject jsonObject1 = sections.getJSONObject(i);
//            String id = jsonObject1.getString("id");
//            if ("3".equals(id)) {
//                String name = jsonObject1.getString("name");
//                System.out.println(name);
////                break;
//            }
//        }
        System.out.println(title);
    }

    @Test
    public void varTest() {
        String json = "{\"name\":\"刘德华\",\"age\":35,\"some\":[{\"k1\":\"v1\",\"k2\":\"v2\"},{\"k3\":\"v3\",\"k4\":\"v4\"}]}";
        JSONObject jso = JSON.parseObject(json);//json字符串转换成jsonobject对象
        System.out.println("初始jsonObject:\n" + jso + "\n");
        JSONArray jsarr = jso.getJSONArray("some");//jsonobject对象取得some对应的jsonarray数组
        System.out.println("jsonObject里面的jsonarray:\n" + jsarr + "\n");
        JSONObject ao = jsarr.getJSONObject(0);//jsonarray对象通过getjsonobjext(index)方法取得数组里面的jsonobject对象
        System.out.println("jsonObject里面的jsonarray里面的第一个jsonobject：\n" + ao + "\n");
        String vString = ao.getString("k1");//jsonobject对象通过key直接取得String的值
        System.out.println("jsonObject里面的jsonarray里面的第一个jsonobject里的键值对对k1取值：\n" + vString + "\n");
    }

    @Test
    public void dateFormatTest() throws Exception {
        String s = "2023-12-01 23:00:00";
        String formatdate = this.formatdate(s, 31);
//        DateFormat format = new SimpleDateFormat("YYYY-MM-DD HH:mm:ss");
//        Date date = format.parse(s);
//
//        DateFormat yyyy = new SimpleDateFormat("YYYY");
//        String format1 = yyyy.format(date);
        System.out.println(formatdate);
    }

    private String formatdate(String value, Integer type) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY-MM-DD HH:mm:ss");
        String realTime = "";
        DateFormat format = new SimpleDateFormat("YYYY");
        try {
            Date date = simpleDateFormat.parse(value);
            switch (type) {
                case 1:  //年
                    realTime = format.format(date);
                    break;
                case 2: //年 月
                    format = new SimpleDateFormat("YYYY/MM");
                    realTime = format.format(date);
                    break;
                case 3: //年 月 日
                    format = new SimpleDateFormat("YYYY/MM/DD");
                    realTime = format.format(date);
                    break;
                case 4: //时
                    format = new SimpleDateFormat("HH");
                    realTime = format.format(date);
                    break;
                case 5: //时 分
                    format = new SimpleDateFormat("HH:mm");
                    realTime = format.format(date);
                    break;
                case 8: //年月日 时分
                    format = new SimpleDateFormat("YYYY/MM/DD HH:mm");
                    realTime = format.format(date);
                    break;
                default:
                    break;

            }
//            realTime = format.format(date);
        } catch (ParseException e) {
//            logger.error("时间解析失败: " + value + "; "+ e );
        }
        return realTime;
    }

    @Test
    public void testOne() {
        List<String> list = Arrays.asList("https://cdn.58qf.com/files/qifutong/local/1538116497733.form-data?x-oss-process=image/resize,w_150", "rere");
        StringBuffer sb = new StringBuffer();
        sb.append(JSONObject.toJSONString(list));
        List<String> jsonValueList = new ArrayList<>();
        jsonValueList.add("fdgfdgfdgfdg");
        jsonValueList.add(sb.toString());
        for (String s : jsonValueList) {
            if (s.startsWith("[")) {
                List<String> list1 = JSON.parseArray(s, String.class);
                list1.stream()
                        .forEach(System.out::println);
            }
        }
    }

    @Test
    public void NpeTest() {
        long time = new Date().getTime();
        System.out.println("Date: " + new Date());
        System.out.println(time);
    }
}
