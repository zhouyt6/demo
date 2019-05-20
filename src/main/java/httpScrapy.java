import com.google.common.base.Splitter;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;

/**
 * @Author: Zhou YingTao
 * @Date: 2019/2/20
 * @Description:
 */
public class httpScrapy {



//    public static void setCoookie(ChromeDriver driver, Long zg_number,Long hm_number) {
//        Cookie c1 = new Cookie("UM_distinctid", "16923155ebc185-04aeb890c57852-38395c0b-100200-16923155ebde6");
//        Cookie c2 = new Cookie("zg_did", "%7B%22did%22%3A%20%2216923156030a7-0647a54f905f9a-38395c0b-100200-169231560311b5%22%7D");
//        Cookie c3 = new Cookie("hasShow", "1");
//        Cookie c4 = new Cookie("_uab_collina", "155107179800207781187438");
//        Cookie c5 = new Cookie("acw_tc", "7cc1e21715510717985834661ece8b4f712cbb42e5ccf056dd16e73e3d");
//        Cookie c6 = new Cookie("QCCSESSID", "49bcigs4uc7hmsuv59r79vpnh1");
//        Cookie c7 = new Cookie("CNZZDATA1254842228", "1219582918-1551071737-https%253A%252F%252Fwww.baidu.com%252F%7C1551077137");
//        Cookie c8 = new Cookie("Hm_lvt_3456bee468c83cc63fb5147f119f1075", "1551071797,1551076971,1551078228,1551078282");
//        Random random = new Random();
//        int zg = random.nextInt(1000) + 50;
//        zg_number +=zg;
//        Cookie c9 = new Cookie("zg_de1d1a35bfa24ce29bbf2c7eb17e6c4f","%7B%22sid%22%3A%201551076970706%2C%22updated%22%3A%"
//                +zg_number+"%2C%22info%22%3A%201551071797319%2C%22superProperty%22%3A%20%22%7B%7D%22%2C%22platform%22%3A%20%22%7B%7D%22%2C%22utm%22%3A%20%22%7B%7D%22%2C%22referrerDomain%22%3A%20%22www.qichacha.com%22%2C%22cuid%22%3A%20%22efc306c6957b861292c50077dd302422%22%7D");
//        int hm = random.nextInt(500) + 20;
//        hm_number +=hm;
//        Cookie c10 = new Cookie("Hm_lpvt_3456bee468c83cc63fb5147f119f1075",hm_number+"");
//        driver.manage().addCookie(c1);
//        driver.navigate().refresh();
//    }

    @Test
    public void httpRequest() {

        StringBuffer buffer = null;
        BufferedReader bufferedReader = null;
        InputStreamReader inputStreamReader = null;
        InputStream inputStream = null;
        HttpURLConnection httpUrlConn = null;
        String url2 = "https://www.qichacha.com/firm_4aa94ac7e213321248890db59dc712e6.html";
        try {
            // 建立get请求
            URL url = new URL(url2);
            httpUrlConn = (HttpURLConnection) url.openConnection();
            httpUrlConn.setConnectTimeout(200000);
            httpUrlConn.setReadTimeout(300000);
//            httpUrlConn.setDoInput(true);
            httpUrlConn.setRequestMethod("GET");

            int responseCode = httpUrlConn.getResponseCode();
            System.out.println("code :"+responseCode);
            // 获取输入流
            inputStream = httpUrlConn.getInputStream();
            inputStreamReader = new InputStreamReader(inputStream, "utf-8");
            bufferedReader = new BufferedReader(inputStreamReader);

            // 从输入流读取结果
            buffer = new StringBuffer();
            String str = null;
            while ((str = bufferedReader.readLine()) != null) {
                buffer.append(str);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 释放资源
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (inputStreamReader != null) {
                try {
                    inputStreamReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (httpUrlConn != null) {
                httpUrlConn.disconnect();
            }
        }
        Document document = Jsoup.parse(buffer.toString(), url2);
        Element cominfo = document.getElementById("Cominfo");
        System.out.println("Element : "+ cominfo);
        System.out.println(buffer.toString());
//        return buffer.toString();
    }


//    /**
//     * 过滤掉html字符串中无用的信息
//     *
//     * @param html String    html字符串
//     * @return String    有用的数据
//     */
//    private static String htmlFiter(String html, String company) {
//
//        StringBuilder builder = new StringBuilder();
//        builder.append(company + "\t");
//        List<String> list = new ArrayList<String>();
//        // 取出有用的范围
//        Pattern p = Pattern.compile("<td.*?>(.*?)</td>");
//        Matcher m = p.matcher(html);
//        while (m.find()) {
//            String group = m.group();
//            list.add(group);
//        }
//        for (int i = 0; i < list.size(); i++) {
//            if (list.get(i).startsWith("<tdwidth=\"17.3%\">成立日期")) {
//                String s = list.get(i + 1);
//                String substring = s.substring(s.indexOf("lh24\">") + 6, s.indexOf("</"));
//                if (StringUtils.isNotEmpty(substring)) {
//                    Integer[] integers = Arrays.stream(substring.replace("-", "").split(""))
//                            .map(charactor -> chiper[Integer.parseInt(charactor)]).toArray(Integer[]::new);
//                    StringBuilder str = new StringBuilder();
//                    if (integers != null) {
//                        for (int i1 = 0; i1 < integers.length; i1++) {
//                            str.append(integers[i1]);
//                            if (i1 == 3 || i1 == 5) str.append("-");
//                        }
//                    }
//                    builder.append("成立日期: " + str.toString() + "\t");
////                    System.out.println("成立日期: "+str.toString());
//                } else {
//                    System.out.println("成立日期: -");
//                }
//            }
//            if (list.get(i).startsWith("<td>行业")) {
//                String s = list.get(i + 1);
//                String substring = s.substring(s.indexOf(">") + 1, s.indexOf("</"));
//                if (StringUtils.isNotEmpty(substring)) {
//                    builder.append("行业: " + substring + "\t");
////                    System.out.println("行业:"+substring+"\t");
//                } else {
//                    System.out.println("行业:-\t");
//                }
//            }
//            if (list.get(i).startsWith("<td>人员规模")) {
//                String s = list.get(i + 1);
//                String substring = s.substring(s.indexOf(">") + 1, s.indexOf("</"));
//                if (StringUtils.isNotEmpty(substring)) {
//                    builder.append("人员规模: " + substring + "\t");
////                    System.out.println("人员规模:"+substring+"\t");
//                } else {
//                    System.out.println("人员规模:-\t");
//                }
//            }
//        }
//        // 取出有用的范围
//        List<String> list2 = new ArrayList<String>();
//        Matcher m2 = Pattern.compile("<span.*?>(.*?)</span>").matcher(html);
//        while (m2.find()) {
//            String group = m2.group();
//            list2.add(group);
//        }
//        for (int i = 0; i < list2.size(); i++) {
//            if (list2.get(i).contains(">分支机构</span>")) {
//                String s = list2.get(i + 1);
//                String substring = s.substring(s.indexOf(">") + 1, s.indexOf("</span"));
//                if (StringUtils.isNotEmpty(substring)) {
//                    builder.append("分支机构: " + substring + "\t");
////                    System.out.println("分支机构:"+substring+"\t");
//                } else {
//                    System.out.println("分支机构:-\t");
//                }
//            }
//            if (list2.get(i).contains("e\">对外投资</span>")) {
//                String s = list2.get(i + 1);
//                String substring = s.substring(s.indexOf(">") + 1, s.indexOf("</span"));
//                if (StringUtils.isNotEmpty(substring)) {
//                    builder.append("对外投资: " + substring + "\t");
////                    System.out.println("对外投资:"+substring+"\t");
//                } else {
//                    System.out.println("对外投资:-\t");
//                }
//            }
//        }
////        int i = html.indexOf("<!--融资历史-->");
////        int i1 = html.indexOf("<!--融资历史end-->");
////        String substring = html.substring(i, i1);
////        if (StringUtils.isNotEmpty(substring)) {
////            String substring1 = substring.substring(substring.indexOf("新闻来源</th></tr></thead><tbody><tr>"), substring.length());
////            substring1 = substring1.replaceAll("新闻来源</th></tr></thead><tbody><tr>", "");
////            int i2 = substring1.indexOf("<div><a");
////            String substring2 = substring1.substring(0, i2);
////            String[] split = substring2.replaceAll("<td>", "").split("</td>");
////            List<String> list1 = Arrays.asList(split);
////            System.out.println(list1);
////            for (int i3 = 0; i3 < list1.size(); i3++) {
////
////            }
////        } else {
////            System.out.println("融资历史:为空");
////        }
//        System.out.println("数据: " + builder.toString());
//        return builder.toString();
//    }

    public static void save(String info, BufferedWriter bw) {
        try {
            bw.write(info);
            bw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static final String COMPANY_NAME_ERR_T = "公司名称有误\t";
    private static final String COMPANY_NAME_ERR_N = "公司名称有误\r\n";
    private static final String SYSTEM_SEARCH_ERROR = "查询异常\r\n";
    /**
     * 测试
     *
     * @param args
     */
    public static void main(String[] args) throws Exception {

        StringBuilder builder = null;
        Random rand = new Random();
        File file = new File("d:\\saveAdd.txt");
        FileWriter fw = new FileWriter(file, true);
        BufferedWriter bw = new BufferedWriter(fw);
        List<String> companyNameList = getUrlByCompanyNameList();
        int num = 0;
        int count = 0;
        Long zg_number = 1050102L;
        Long hm_number = 1021L;
        for (String companyName : companyNameList) {
            count +=1;
            builder = new StringBuilder();
            try {
                if (StringUtils.isEmpty(companyName)) {
                    num += 1;
                    builder.append(COMPANY_NAME_ERR_N);
                    System.out.println("序号:"+count+";错误:"+companyName);
                } else {
                    companyName = companyName.replace("\u00a0", "")
                            .replace("\u0020", "")
                            .replace("\u0030", "").trim();
                    CloseableHttpClient httpClient = HttpClients.createDefault();
                    if (hm_number >= 930) {
                        hm_number = 1036L;
                    }
                    int hm = rand.nextInt(60) + 5;
                    hm_number +=hm;
                    if (zg_number >=9998980) {
                        zg_number = 1050000L;
                    }
                    int zg = rand.nextInt(800) + 100;
                    zg_number +=zg;
                    CloseableHttpResponse response = getDocumentByUrl(httpClient,"https://www.qichacha.com/search?key=" + companyName,zg_number,hm_number,null);
                    if (response != null && response.getStatusLine().getStatusCode() == 200) {
//                        "https://www.qichacha.com/company_getinfos?unique=dee238bbc7b1c5cd7cc34ef288a9cb1e&companyname=北京天趣互动科技发展有限公司&tab=run"
                        List<String> uniqueUrlMethod = getUniqueUrlMethod(builder, response);
                        String name = uniqueUrlMethod.get(0);
                        // href  "/firm_9cce0780ab7644008b73bc2120479d31.html"
                        String href = uniqueUrlMethod.get(1);
                        String unique = href.substring(href.indexOf("firm_") + 5, href.lastIndexOf("."));
                        String uniqueUrl = "https://www.qichacha.com/company_getinfos?unique="+unique+"&companyname="+name+"&tab=run";
                        if (StringUtils.isNotEmpty(uniqueUrl)) {
                            // 基本信息
                            String baseUrl = "https://www.qichacha.com/firm_"+uniqueUrl.substring(uniqueUrl.indexOf("que=") + 4, uniqueUrl.lastIndexOf("&com"))+".html";
                            CloseableHttpResponse responseInfo = getDocumentByUrl(httpClient, baseUrl, zg_number, hm_number,href);
                            if (responseInfo != null && responseInfo.getStatusLine().getStatusCode() == 200) {
                                HttpEntity entity = responseInfo.getEntity();  //获取返回实体
                                if (entity != null) {
                                    String baseInfo = EntityUtils.toString(entity, "utf-8");
                                    Document document = Jsoup.parse(baseInfo);
                                    // 解析基本信息
                                    getBaseInfoMessage(document, builder);
                                } else  {
                                    builder.append(COMPANY_NAME_ERR_T);
                                }
                            } else {
                                builder.append(COMPANY_NAME_ERR_T);
                            }
                            if (responseInfo != null) {
                                responseInfo.close();
                            }

                            // 财务 and 新闻
//                            String url = "https://www.qichacha.com/firm_6b242b475738f45a4dd180564d029aa9.html#run";
                            CloseableHttpResponse response_caiwu = getDocumentByUrl(httpClient, uniqueUrl, zg_number, hm_number,href);
                            if (response_caiwu != null && response_caiwu.getStatusLine().getStatusCode() == 200) {
                                HttpEntity entity = response_caiwu.getEntity();  //获取返回实体
                                if (entity != null) {
                                    String baseInfo = EntityUtils.toString(entity, "utf-8");
                                    Document document = Jsoup.parse(baseInfo);
                                    // 解析财务 and 新闻
                                    getCaiwuInfoMessage(document, builder);
                                } else  {
                                    builder.append(COMPANY_NAME_ERR_T);
                                }
                            } else {
                                builder.append(COMPANY_NAME_ERR_T);
                            }
                            if (response_caiwu != null) {
                                response_caiwu.close();
                            }

                            // 融资信息
                            String url_rongzi = uniqueUrl.replaceAll("run", "report");;
                            CloseableHttpResponse response_rongzi = getDocumentByUrl(httpClient, url_rongzi, zg_number, hm_number,href);
                            if (response_rongzi != null && response_rongzi.getStatusLine().getStatusCode() == 200) {
                                HttpEntity entity = response_rongzi.getEntity();  //获取返回实体
                                if (entity != null) {
                                    String baseInfo = EntityUtils.toString(entity, "utf-8");
                                    Document document = Jsoup.parse(baseInfo);
                                    // 解析融资信息
                                    getRongziInfoMessage(document, builder);
                                } else  {
                                    builder.append(COMPANY_NAME_ERR_N);
                                }
                            } else {
                                builder.append(COMPANY_NAME_ERR_N);
                            }
                            if (response_rongzi != null) {
                                response_rongzi.close();
                            }
                        }  // 空url已经在getUniqueUrlMethod中处理
                    } else {
                        builder.append(SYSTEM_SEARCH_ERROR);
                    }
                    if (response != null){
                        response.close();
                    }
                    if (httpClient != null){
                        httpClient.close();
                    }
                }
            } catch (Exception e) {
                num += 1;
                builder.append(SYSTEM_SEARCH_ERROR);
                System.out.println(companyName + ":出错了===================================");
            }
            System.out.println("序号:"+count+";companyName:" + companyName + "\n" + builder.toString());
            save(builder.toString(), bw);
        }
        if (num != 0) {
            System.out.println("失败数量 : " + num);
        }
        bw.close();
        fw.close();
    }


    // 解析融资信息
    private static void getRongziInfoMessage(Document document, StringBuilder builder) throws Exception {
        Element financingInfo = document.getElementById("financingInfo");
        if (financingInfo == null) {
            builder.append("-\t-\t-\t-\r\n");
        } else {
            Elements byIdElements_rongzi = financingInfo.getElementsByTag("td");
            if (CollectionUtils.isEmpty(byIdElements_rongzi)) {
                builder.append("-\t-\t-\t-\r\n");
            } else {
                for (int i = 0; i < byIdElements_rongzi.size(); i++) {
                    if (i == 1 || i == 3 || i == 4) {
                        getBaseInfoMethod(builder,byIdElements_rongzi,i);
                    }
                    if (i == 5) {
                        if (byIdElements_rongzi.get(i) == null) {
                            builder.append("-\r\n");
                        } else {
                            String touzi__rongziField = byIdElements_rongzi.get(i).text();
                            if (StringUtils.isEmpty(touzi__rongziField)) {
                                builder.append("-\r\n");
                            } else {
                                builder.append(touzi__rongziField+"\r\n");
                            }
                        }
                        break;
                    }
                }
            }
        }
    }

    // 财务 And 新闻
    private static void getCaiwuInfoMessage(Document document, StringBuilder builder) {
        Element elementById_caiwu = document.getElementById("V3_cwzl");
        if (elementById_caiwu == null) {
            builder.append("-\t-\t-\t-\t");
        } else {
            Elements byIdElements_caiwu = elementById_caiwu.getElementsByTag("td");
            if (CollectionUtils.isEmpty(byIdElements_caiwu)) {
                builder.append("-\t-\t-\t-\t");
            } else {
                for (int i = 0; i < byIdElements_caiwu.size(); i++) {
                    if (i % 2 != 0) {
                        getBaseInfoMethod(builder, byIdElements_caiwu, i);
                    }
                }
            }
        }

        // 新闻舆情
        Element newslistId = document.getElementById("newslist");
        if (newslistId == null) {
            builder.append("-\t");
        } else {
            Elements byIdElements = newslistId.getElementsByClass("news-impact-title");
            if (CollectionUtils.isEmpty(byIdElements)) {
                builder.append("-\t");
            } else {
                for (int i = 0; i < byIdElements.size(); i++) {
                    Elements spans = byIdElements.get(i).getElementsByTag("span");
                    if (CollectionUtils.isNotEmpty(spans)) {
                        builder.append("(" + spans.get(0).text() + ")" + spans.get(1).text() + "; ");
                    }
                    if (i == 4) break;
                }
                builder.append("\t");
            }
        }
    }

    // 解析基础数据信息
    private static void getBaseInfoMessage(Document document, StringBuilder builder) {
        Element elementById_cbase = document.getElementById("Cominfo");
        if (elementById_cbase ==null) {
            builder.append("-\t-\t-\t");
        } else {
            Elements byIdElements_cbase = elementById_cbase.getElementsByTag("tbody");
            if (CollectionUtils.isEmpty(byIdElements_cbase)) {
                builder.append("-\t-\t-\t");
            } else {
                Element element = byIdElements_cbase.get(1);
                if (element == null) {
                    builder.append("-\t-\t-\t");
                } else {
                    Elements td_cbase = element.getElementsByTag("td");
                    getBaseInfoMethod(builder, td_cbase,1);  // 注册资本
                    getBaseInfoMethod(builder, td_cbase,5);  // 经营状态
                    getBaseInfoMethod(builder, td_cbase,7);  // 成立日期
                    getBaseInfoMethod(builder, td_cbase,17);  // 公司类型
                    getBaseInfoMethod(builder, td_cbase,19); // 行业
                    getBaseInfoMethod(builder, td_cbase,21);  // 核准日期
                    getBaseInfoMethod(builder, td_cbase,25);  // 所属地区
                    getBaseInfoMethod(builder, td_cbase,33); // 人员规模
                    getBaseInfoMethod(builder, td_cbase,37);  // 企业地址
                }
            }
        }

        // 三个数字
        Element base_div = document.getElementById("base_div");
        if (base_div == null) {
//            System.out.println("未登录。。。。。");
            base_div = document.getElementsByClass("data_div_login").get(0);
        }
        Elements panel_body_div = base_div.getElementsByClass("panel-body");
        if (CollectionUtils.isEmpty(panel_body_div)) {
            builder.append("-\t-\t-\t");
        } else {
            Elements byIdElements_num = panel_body_div.get(0).getElementsByTag("a");
            if (CollectionUtils.isEmpty(byIdElements_num)) {
                builder.append("-\t-\t-\t");
            } else {
                LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>();
                for (Element element : byIdElements_num) {
                    String text = element.text();
                    if (StringUtils.isNotEmpty(text)) {
                        List<String> list = Splitter.on(" ").omitEmptyStrings().trimResults().splitToList(text);
                        if (CollectionUtils.isNotEmpty(list) && list.size()==2) {
                            linkedHashMap.put(list.get(0),list.get(1));
                        }
                    }
                }
                if (linkedHashMap.containsKey("对外投资")) {
                    builder.append(linkedHashMap.get("对外投资") + "\t");
                } else {
                    builder.append("-\t");
                }
                if (linkedHashMap.containsKey("分支机构")) {
                    builder.append(linkedHashMap.get("分支机构") + "\t");
                } else {
                    builder.append("-\t");
                }
                if (linkedHashMap.containsKey("控股企业")) {
                    builder.append(linkedHashMap.get("控股企业") + "\t");
                } else {
                    builder.append("-\t");
                }
            }
        }
    }

    private static void getBaseInfoMethod(StringBuilder builder, Elements td_cbase,int index) {
        if (td_cbase.get(index) == null) {
            builder.append("-\t");
        } else {
            String baseInfoField = td_cbase.get(index).text();
            if (StringUtils.isEmpty(baseInfoField)) {
                builder.append("-\t");
            } else {
                if (baseInfoField.contains("查看地图")) {
                    baseInfoField = baseInfoField.substring(0, baseInfoField.length()-10);
                }
                builder.append(baseInfoField+"\t");
            }
        }
    }

    /**
     *  执行请求
     * @param httpClient
     * @param url
     * @param zg_number
     * @param hm_number
     * @return
     * @throws Exception
     */
    private static CloseableHttpResponse getDocumentByUrl(CloseableHttpClient httpClient,String url,Long zg_number,Long hm_number, String href) throws Exception {
        Random rand = new Random();
        HttpGet httpGet = new HttpGet(url);
        //                HttpHost proxy = new HttpHost("121.232.199.147",9000);
        RequestConfig requestConfig = RequestConfig.custom()
                //                .setProxy(proxy)
                .setConnectTimeout(10000)
                .setSocketTimeout(30000)
                .setConnectionRequestTimeout(3000)
                .build();
        httpGet.setConfig(requestConfig);
        //设置请求头消息
        httpGet.setHeader("User-Agent","Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/72.0.3626.121 Safari/537.36");
        if (href !=null) {
            httpGet.setHeader("referer","https://www.qichacha.com"+href);
        }
        httpGet.getParams().setParameter("http.protocol.single-cookie-header",true);
        String cookies = "zg_did=%7B%22did%22%3A%20%22169340aa5ae141-0307e4aa0b733b-38395c0b-100200-169340aa5af9a%22%7D; UM_distinctid=169340aa60712e-0336059d8eca1a-38395c0b-100200-169340aa6084f; _uab_collina=155135630767518747562716; acw_tc=7cc1e21815513563107247507e8a99986223681867995861b6d0276e04; QCCSESSID=f0uiipj578kgor0b2kvioupbi6; CNZZDATA1254842228=474631574-1552540565-https%253A%252F%252Fwww.baidu.com%252F%7C1552540565; hasShow=1; Hm_lvt_3456bee468c83cc63fb5147f119f1075=1552545278,1552545293; " +
                "zg_de1d1a35bfa24ce29bbf2c7eb17e6c4f=%7B%22sid%22%3A%201552545277394%2C%22updated%22%3A%20155254"+zg_number+"%2C%22info%22%3A%201552545277405%2C%22superProperty%22%3A%20%22%7B%7D%22%2C%22platform%22%3A%20%22%7B%7D%22%2C%22utm%22%3A%20%22%7B%7D%22%2C%22referrerDomain%22%3A%20%22www.baidu.com%22%2C%22cuid%22%3A%20%22efc306c6957b861292c50077dd302422%22%7D; " +
                " Hm_lpvt_3456bee468c83cc63fb5147f119f1075=155254"+hm_number;
        httpGet.addHeader(new BasicHeader("cookie",cookies));
        CloseableHttpResponse response = httpClient.execute(httpGet);
        Thread.sleep((rand.nextInt(4) + 2)*1000);
        return response;
    }

    /**
     * 获取uniqueUrl
     * @param builder
     * @param response
     * @throws IOException
     */
    private static List<String> getUniqueUrlMethod(StringBuilder builder, CloseableHttpResponse response) throws IOException {
        HttpEntity entity = response.getEntity();  //获取返回实体
        ArrayList<String> list = new ArrayList<>();
        if (entity != null) {
            String html = EntityUtils.toString(entity, "utf-8");
            Document document = Jsoup.parse(html);
            Element element_tbody = document.getElementById("search-result");
            if (element_tbody == null) {
                builder.append(COMPANY_NAME_ERR_N);
            } else {
                Elements tag_a = element_tbody.getElementsByTag("a");
                if (CollectionUtils.isNotEmpty(tag_a)) {
                    Element a = tag_a.first();
                    if (a== null) {
                        builder.append(COMPANY_NAME_ERR_N);
                    } else {
                        String href = a.attr("href");
                        String name = a.text();
                        if (StringUtils.isNotEmpty(href) && StringUtils.isNotEmpty(name)) {
                            builder.append(name+"\t");
                            list.add(name);
                            list.add(href);
                            return list;
//                            String unique = href.substring(href.indexOf("firm_") + 5, href.lastIndexOf("."));
//                            return "https://www.qichacha.com/company_getinfos?unique="+unique+"&companyname="+name+"&tab=run";
                        } else {
                            builder.append(COMPANY_NAME_ERR_N);
                        }
                    }
                } else {
                    builder.append(COMPANY_NAME_ERR_N);
                }
            }
        } else {
            builder.append(COMPANY_NAME_ERR_N);
        }
        if (response !=null) {
            response.close();
        }
        return null;
    }









    // 公司名数据源
    private static List<String> getUrlByCompanyNameList() {
        return Arrays.asList(
                "北京华澳翼时代信息技术有限责任公司",
                "北京链空间信息技术有限公司",
                "广州市有车以后信息科技有限公司",
                "深圳市加推科技有限公司",
                "北京作业盒子科技有限公司",
                "北京量子保科技有限公司",
                "微软移动联新互联网服务有限公司",
                "北京中清龙图网络技术有限公司",
                "上海艾瑞市场咨询股份有限公司",
                "北京善义善美科技有限公司",
                "北京悟空保科技有限公司",
                "深圳有咖互动科技有限公司",
                "北京数美时代科技有限公司",
                "北京聪明核桃教育科技有限公司",
                "北京开天创世科技有限公司",
                "智慧全景",
                "华硕电脑股份有限公司",
                "北京卡路里信息技术有限公司",
                "同心医联科技（北京）有限公司",
                "贵州宽凳智云科技有限公司北京分公司",
                "北京乐柏教育咨询有限公司",
                "飞维美地信息技术（北京）有限公司",
                "山东万腾电子科技有限公司",
                "北京农田管家信息技术有限公司",
                "国付宝信息科技有限公司",
                "深圳超桌网络科技有限公司",
                "芦勤丰",
                "北京空舞文化艺术有限公司",
                "腾讯科技（北京）有限公司",
                "北京燃烧小宇宙传媒有限公司",
                "宜信卓越财富投资管理（北京）有限公司",
                "北京字节跳动网络技术有限公司",
                "北京启萌教育科技有限公司",
                "天津顶悦无限网络科技有限公司",
                "河南聚融优合汽车服务有限公司",
                "贵阳聚融优合汽车服务有限公司",
                "南昌坤之德汽车服务有限公司",
                "吉林省聚融优合汽车服务有限公司",
                "北京玩蟹科技有限公司",
                "天津益趣科技有限公司北京分公司",
                "北京趣玩天橙科技有限公司",
                "天津转转世界科技有限责任公司",
                "北京佳格天地科技有限公司",
                "上海奥美广告有限公司北京分公司",
                "天津五八驾考信息技术有限公司",
                "考拉征信服务有限公司",
                "北京博纳格企业管理有限公司",
                "北京快乐时代科技发展有限公司",
                "北京质享科技有限公司",
                "北京两只老虎电子商务有限公司",
                "北京佳润科技有限公司",
                "陶雪松（个人）",
                "北京国科天创建筑设计院有限责任公司",
                "费埃哲信息技术（北京）有限公司",
                "北京时尚迅达书刊发行有限公司",
                "北京雷动云合智能技术有限公司",
                "北京新聚思信息技术有限公司工会",
                "深圳市客路网络科技有限公司",
                "江苏雷动智能制造有限公司",
                "上海镭栋智能技术有限公司",
                "郑州雷动智能技术有限公司",
                "山东万腾智能科技有限公司",
                "北京建忠锁事科技有限公司",
                "见牛羊（北京）网络科技有限公司",
                "北京五八信息技术有限公司济南分公司",
                "库天下（北京）信息技术有限公司",
                "陶雪松",
                "北京琥珀纷钛科技有限公司",
                "北京我最在行信息技术有限公司",
                "北京智享科技有限公司",
                "北京信和云科技有限公司",
                "中智财务咨询有限公司",
                "北京奇彩帆文化传媒有限公司",
                "深圳海兔互动科技有限公司",
                "北京森德瑞拉科技有限公司",
                "北京全链时代科技有限公司",
                "北京人人清洁服务有限公司",
                "深圳市世纪云芯科技有限公司",
                "深圳新物种科技有限公司",
                "北京通润鸿博科技有限公司",
                "世纪禾光科技发展（北京）有限公司",
                "北京动因体育科技有限公司",
                "北京跳动空间科技有限公司",
                "合茂（北京）数字科技发展股份有限公司",
                "北京快道网络有限公司",
                "北京房互网科技有限公司",
                "北京卓越晨星科技有限公司",
                "北京蓝城兄弟信息技术有限公司",
                "云控蜂核（北京）科技有限公司",
                "二十世纪福斯（北京）咨询有限公司",
                "北京笙华友谊餐饮服务有限公司",
                "北京小川在线网络技术有限公司",
                "北京掌众金融信息服务有限公司",
                "北京智慧基石科技有限公司",
                "北京云测信息技术有限公司",
                "广东猪八戒网络有限公司",
                "北京人人车旧机动车经纪有限公司",
                "途家网-作废，不再使用",
                "云智联网络科技（北京）有限公司",
                "广州博鳌纵横网络科技有限公司",
                "林昌盛威（北京）科技有限公司",
                "聚讯电子",
                "北京地星伟业数码科技有限公司",
                "中科博宏（北京）科技有限公司",
                "北京厚德星云投资管理有限公司",
                "北京猎户星空科技有限公司",
                "北京读我网络技术有限公司",
                "沈阳世纪融诚科技开发有限公司",
                "北京乐信圣文科技有限责任公司",
                "北京五八信息技术有限公司",
                "北京元海慧诚金融服务外包有限公司",
                "塔罗（北京）科技有限公司",
                "东阳正午阳光影视有限公司",
                "北京大声恩特娱乐文化传播有限公司",
                "北京易真学思教育科技有限公司",
                "北京元隆雅图文化传播股份有限公司",
                "北京美联行教育科技有限公司",
                "北京中辰泰禾照明电器有限公司",
                "北京万科物业服务有限公司",
                "北京腾新科技有限公司",
                "摩比未来（北京）教育科技有限公司",
                "思楷教育科技有限公司",
                "北京蝴蝶效应文化传媒有限公司",
                "芝兰玉树（北京）科技股份有限公司",
                "北京三十三加文化传播有限责任公司",
                "北京真乐道文化传播有限公司",
                "民主与法制时报",
                "首汇焦点（北京）科技有限公司",
                "作业帮教育科技（北京）有限公司",
                "湖南无忧娱乐有限公司",
                "北京汉扬葛瑞营销顾问有限公司",
                "北京世相科技文化有限公司",
                "北京正义清尚文化传媒有限公司",
                "快看世界（北京）科技有限公司",
                "火星小镇（长沙）创客教育科技有限公司",
                "北京慧德嘉源教育咨询有限公司",
                "北京太笈文化传播有限公司",
                "金华吾道南来文化传媒有限公司",
                "三节课信息咨询(北京)有限公司",
                "北京环宇星漫文化传播有限公司",
                "潘公凯(北京)建筑设计咨询有限公司",
                "北京爱思普润教育科技有限公司",
                "北京未来橙教育科技有限公司",
                "北京聚师网教育科技有限公司",
                "北京太合音乐文化发展有限公司",
                "北京时尚线路国际模特文化发展有限公司",
                "北京深海十月传媒有限公司",
                "北京启萌教育科技有限公司",
                "广州美联教育科技有限公司",
                "北京睿艺创联教育科技有限公司（小b开荒）",
                "好贷天下信息技术（北京）有限公司",
                "北京创联中人技术服务有限公司",
                "北京中人光华教育科技有限公司",
                "北京领骥影视文化股份有限公司",
                "卡路里体育管理（北京）有限公司",
                "字节跳动有限公司",
                "北京极地公园影视传媒有限公司",
                "上海顺涌管理咨询有限公司",
                "北京一起智能文化传播有限公司",
                "北京萤火虫飞教育科技有限公司",
                "《证券日报》社",
                "北京万年漫道文化传媒有限公司",
                "上海唯臻文化传媒有限公司广州分公司",
                "寰宇慧旅（北京）科技有限公司",
                "南华基金管理有限公司",
                "绿普达（北京）科技有限公司",
                "北京小微律政（北京）管理咨询有限公司",
                "大业信托有限责任公司",
                "上海帷迦迦启网络科技有限公司",
                "北京融兴通达科技有限公司",
                "优享天街（北京）科技服务有限公司",
                "北京知果科技有限公司",
                "北京商务中心通信科技有限公司",
                "天九幸福控股集团",
                "北京鸿坤优客企业管理咨询有限公司",
                "北京河狸家美容服务有限公司",
                "北京东方车云信息技术有限公司",
                "北京新氧科技有限公司",
                "北京金庭国际酒店有限公司",
                "湖南哺源母婴护理有限公司",
                "北京聚融优合技术有限公司",
                "N°元素真人密室逃脱",
                "乾道投资控股集团有限公司",
                "凡普金科集团有限公司",
                "上海云孟企业管理有限公司北京分公司",
                "奇瑞徽银汽车金融股份有限公司",
                "北京锐诩管理有限公司",
                "西安优客工场",
                "上海多来点信息技术有限公司",
                "兴隆县华贸建设开发有限公司",
                "旅悦（天津）酒店管理有限公司",
                "北京市宝盈律师事务所",
                "重庆花果山企业管理",
                "跨境云（北京）网络科技有限公司",
                "北京盛世笔特国际文化创意有限公司",
                "魔方（中国）生活服务集团（广州区域）",
                "魔方（中国）生活服务集团（深圳区域）",
                "帕瓷美乐（北京）国际商务咨询有限公司",
                "发现时空国际旅行社（北京）有限公司",
                "北京信宇佳清洁服务有限责任公司",
                "北京细软智谷知识产权代理有限责任公司",
                "河北中细软知识产权服务有限公司",
                "广州中细软知识产权运营有限公司",
                "天津细软知识产权科技有限公司",
                "北京鑫诚康洁保洁服务有限公司",
                "北京朗丽兹西山花园酒店管理有限公司",
                "高线科技（北京）有限责任公司",
                "北京艺海创业国际商务会馆有限公司",
                "北京五八信息技术有限公司",
                "北京美达联创建筑劳务有限公司",
                "北京房互网科技有限公司",
                "奥赫投资管理（北京）有限公司",
                "北京锐空间科技有限公司",
                "北京燕巢保洁有限公司",
                "北京市朝阳区赫德双语学校",
                "清控资产管理（上海）有限公司",
                "纳什空间创业科技（北京）有限公司-中关村场地",
                "纳什凯旋科技（北京）有限公司-CBD万达",
                "天之草生物新材料有限公司",
                "福州市莹诩酒店管理有限公司",
                "上海景泰建设股份有限公司",
                "知合控股有限公司",
                "纳什空间创业科技（北京）有限公司（博泰国际场地）",
                "纳什空间创业科技（北京）有限公司（博泰场地）",
                "博洛尼家居装饰（北京）有限公司",
                "北京建工投资发展有限责任有限公司",
                "北京凤凰天博网络技术有限公司",
                "北京腾泰亿远置业有限公司",
                "易空间",
                "固安幸福基业资产管理有限公司",
                "武陟孔雀城房地产开发有限公司",
                "北京优客工场京朝科技服务有限公司",
                "北京仟丰科技有限公司",
                "北京集顺工程咨询有限公司",
                "北京金诚泰和房地产经纪有限公司",
                "建元未来城市投资发展有限公司",
                "长桥建设（深圳）有限公司",
                "北京阳光壹佰优客工场创业投资有限公司",
                "壹玖玖叁（北京）建筑装饰工程有限公司",
                "北京江河置业有限公司",
                "北京金水房地产开发有限公司",
                "纳什空间企业服务（北京）有限公司—鸟巢场地",
                "北京万科物业服务有限公司",
                "瑞庭网络技术（上海）有限公司济南分公司",
                "北京仟丰科技有限公司（建国门场地）",
                "北京智选空间商务有限公司",
                "乐元素科技（北京）股份有限公司",
                "北京亮马置业有限公司",
                "七六一工场（北京）科技发展有限公司",
                "纳什凯旋科技（北京）有限公司",
                "紫梧桐（北京）资产管理有限公司",
                "中国邮政储蓄银行股份有限公司北京朝阳区支行",
                "中债金石资产管理有限公司",
                "幸福基业投资有限公司",
                "中电数融投资管理有限公司",
                "北信瑞丰基金管理有限公司",
                "北京华睿晟通科技有限公司",
                "深圳新友科技有限公司",
                "民生证券股份有限公司",
                "杭州金研为政投资管理合伙企业（有限合伙）",
                "天鹰合赢（北京）投资管理有限公司",
                "北京文华海汇投资管理有限公司",
                "仟丰国际融资租赁有限公司",
                "同美企业管理集团有限公司",
                "宜信博诚保险销售服务（北京）股份有限公司",
                "瑞信方正证券有限责任公司",
                "好贷天下信息技术（北京）有限公司",
                "北京金利华文化投资有限公司",
                "北京网信金服信息科技有限公司",
                "喀什圣盈信企业咨询有限公司",
                "北京数据家科技股份有限公司",
                "中联金服互联网信息服务（大连）有限公司",
                "途家网-作废，不再使用",
                "盈维斯科技（北京）有限公司",
                "北京金融街投资管理有限公司",
                "星界资本股权投资管理（深圳）有限公司",
                "广州汇智保险代理有限公司",
                "宁波道合融海投资管理合伙企业（有限合伙）",
                "宜信卓越信息咨询（北京）有限公司",
                "国信证券股份有限公司北京石景山路证券营业部",
                "北京雪球信息科技有限公司",
                "北大方正人寿保险有限公司番禺分公司",
                "深圳泛华联合投资集团有限公司",
                "乾道投资控股集团有限公司",
                "艾里逊变速箱（上海）有限公司",
                "北京市首科太阳能研究所有限公司",
                "上海寰尚建筑设计装饰工程有限公司",
                "北京欧德雅盛国际贸易有限公司",
                "广州丽泽堂医药科技有限公司",
                "北京米图灵动科技有限公司",
                "棒师傅（北京）食品有限公司",
                "北京迪乐空间建筑装饰材料有限公司",
                "纳恩博（天津）科技有限公司工会",
                "纳恩博（常州）科技有限公司",
                "中化石油勘探开发有限公司",
                "北京星际荣耀空间科技有限公司",
                "北京同仁堂健康药业股份有限公司工会",
                "贺三乐",
                "中智财务咨询有限公司",
                "北京中晟新华影院管理有限公司",
                "七幕人生文化产业投资（北京）有限公司",
                "篮战（北京）体育文化传播有限公司",
                "北京千里日成广告有限公司",
                "东方风行(北京)传媒文化有限公司",
                "北京凹凸教育咨询有限公司",
                "北京魔力耳朵科技有限公司",
                "湖南师范大学",
                "北京睿艺创联教育科技有限公司",
                "司空科技股份有限公司",
                "深圳卓康门诊部",
                "北京杨庆培丽扬医疗美容诊所",
                "北京蔡琼霞医疗美容诊所责任有限公司",
                "天津凯莱英医药科技有限公司",
                "乔治全球健康研究院（澳大利亚）北京代表处",
                "北京领医诊所有限公司",
                "西安臻视医疗器械有限公司",
                "北京京东中西医门诊部",
                "北京品驰医疗设备有限公司",
                "北京海德堡联合口腔医院有限公司",
                "臻和（北京）科技有限公司",
                "迈柯唯（上海）医疗设备有限公司",
                "北京科合美生物科技有限公司",
                "华自科技股份有限公司",
                "北京泽地源生态科技发展有限公司",
                "广州辰田生物科技有限公司",
                "北大资源集团有限公司",
                "德祥生物工程有限公司",
                "北京泛生子基因科技有限公司",
                "北京市热力集团有限责任公司",
                "北京洛卡环保技术有限公司",
                "纳气环保科技有限公司",
                "广东省运输规划研究中心",
                "中交信息技术国家工程实验室有限公司",
                "中国纸业投资有限公司",
                "国家林业局",
                "中央美术学院",
                "北京兴丰房地产经营开发公司",
                "广州联瑞知识产权代理有限公司",
                "天河区食品药品监督管理局",
                "南京知行电动汽车有限公司",
                "北京东方园林环境股份有限公司",
                "广州卓正都汇门诊部有限公司",
                "佳士得文化艺术（北京）有限公司",
                "西藏中艺金像科技股份有限公司",
                "友邦创新资讯科技（北京）有限公司",
                "北京乐博特科技有限公司",
                "梦想城堡（天津）电子商务有限公司",
                "北京丽仙玛特美容中心有限公司",
                "北京车和家信息技术有限公司",
                "北京懒财 信息科技有限公司",
                "北京今日头条科技有限公司",
                "北京农品堂食品有限公司",
                "华夏幸福基业股份有限公司",
                "北京清一昌祺科技有限公司",
                "北京千里日成广告有限公司",
                "信美人寿相互保险社",
                "华润怡宝饮料（中国）有限公司华东分公司",
                "西藏弘和志远企业管理有限公司",
                "北京默契破冰科技有限公司",
                "伯乐生命医学产品（上海）有限公司",
                "威马智联科技（北京）有限公司",
                "欧蒙医学诊断（中国）有限公司",
                "深圳市鑫汇科股份有限公司",
                "北京智乐活科技有限公司",
                "苏州思必驰信息科技有限公司",
                "香港华润电力控股有限公司北京代表处",
                "北京盈禾优仕科技有限公司",
                "宜信卓越财富投资管理（北京）有限公司",
                "北京中交派森教育咨询有限公司",
                "福建武夷山国家级自然保护区正山茶业有限公司",
                "北京立入禁止平面设计有限公司",
                "北京风云际会投资管理有限公司",
                "圣皮尔精品酒业（上海）有限公司北京分公司",
                "北京品途数信科技有限公司",
                "乾道金管家（北京）管理顾问有限公司",
                "冠群世纪商务信息咨询（天津）有限公司",
                "北京信达泰利科技有限公司",
                "北京真乐道投资管理有限公司",
                "北京中大盛元投资管理有限公司",
                "天津微分享在线网络技术有限公司",
                "北京和创未来网络科技有限公司——盘古场地",
                "瓦纳卡（北京）科技有限公司",
                "小叶子（天津）科技有限公司",
                "北京碧琦时装有限公司",
                "凌云光技术集团有限责任公司",
                "香港信望爱国际交流有限公司北京代表处",
                "北京恒惠科达医疗器械有限责任公司",
                "树美（深圳）设计有限公司",
                "北京互动峰科技（北京）有限公司",
                "易纳购科技（北京）有限公司",
                "北京新通时代教育咨询有限公司",
                "北京鼎力创世科技有限公司",
                "北京千水莲健身有限公司",
                "北大资源集团文化艺术传播（北京）有限公司",
                "融创物业服务集团有限公司",
                "北京旭天共享空间投资管理有限公司",
                "乾道投资控股集团有限公司",
                "中智财务咨询有限公司",
                "北京云畅游戏科技股份有限公司",
                "福星（北京）传媒有限公司",
                "北京趣拿软件科技有限公司",
                "维欧国际教育科技（北京）有限公司",
                "北京希苑管理咨询服务有限公司",
                "北京市海淀区齐进培训学校",
                "浙江新通留学有限公司",
                "北京格灵深瞳信息技术有限公司",
                "北京车闻公关咨询有限公司",
                "刘丽芹",
                "国福泰（北京）国际信用管理有限公司",
                "宋晓燕",
                "李晶晶",
                "北京慧佳投资有限公司",
                "北京小屯派科技有限责任公司",
                "北京孩思乐商业有限公司",
                "北京汉扬葛瑞营销顾问有限公司",
                "北京电子城物业管理有限公司",
                "北京惠赢天下网络技术有限公司",
                "北京中航之旅航空服务有限公司",
                "北京火谷网络科技股份有限公司",
                "北大资源集团投资有限公司",
                "北京派合文化传播股份有限公司",
                "北京天地新道广告有限公司",
                "北京作业盒子科技有限公司",
                "北京龙图游戏",
                "未来知行（北京）教育科技有限公司",
                "上海帝联信息科技股份有限公司",
                "北京智能管家科技有限公司",
                "天九幸福控股集团",
                "上海哔哩哔哩科技有限公司",
                "好贷天下信息技术（北京）有限公司",
                "北京格上富信基金销售有限公司",
                "稳盈财富（北京）科技有限公司",
                "澄迈优目科技有限公司",
                "北京道仔文化传媒有限公司",
                "北京房互网科技有限公司",
                "北京兰雅迪传播集团",
                "随行付(北京)金融信息服务有限公司",
                "龙湖集团",
                "云丁网络技术（北京）有限公司",
                "北京热云科技有限公司",
                "星界资本股权投资管理（深圳）有限公司",
                "中智财务咨询有限公司",
                "艾奕康环境规划设计（上海）有限公司北京分公司",
                "五八同城信息技术有限公司天津分公司",
                "伟晟嘉业国际贸易（北京）有限公司",
                "寰宇慧旅（北京）科技有限公司",
                "库天下（北京）信息技术有限公司",
                "北京乐信圣文科技有限责任公司"
        );
    }
}
