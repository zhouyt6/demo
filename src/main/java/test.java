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
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * @Author: Zhou YingTao
 * @Date: 2018/9/17
 * @Description:
 */
public class test {

//    @Test
//    public void setTable() throws Exception {
//        Document document = new Document();// 横向打印
//        String hHmmss = new SimpleDateFormat("HHmmss").format(new Date());
//        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("C:\\Users\\Administrator\\Desktop\\crm\\pdf"+"/"+"报价单" +hHmmss+ ".pdf"));
//        writer.setStrictImageSequence(true);
//        //打开文件
//        document.open();
//        //添加内容
//        PdfPTable datatable  = new PdfPTable(3);
//        // 定义表格的宽度
//        int[] cellsWidth = {1,1,1};
//        datatable.setWidths(cellsWidth);// 单元格宽度
//        datatable.setWidthPercentage(100);// 表格的宽度百分比
//        datatable.getDefaultCell().setPadding(1);// 单元格的间隔
//        datatable.getDefaultCell().setUseAscender(true);
//        datatable.getDefaultCell().setUseDescender(true);
//        datatable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);//水平居中
//        datatable.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);//垂直居中
//        Image image = Image.getInstance("http://cdn.58qf.com/crm/m/static-page/images/banner.jpg");
////        Image image2 = image;
////        image2.setAlignment(Image.UNDERLYING);
////        image2.setAbsolutePosition(0,0);
////        image2.scaleAbsolute(595,842);
////        document.add(image2);
//        image.scaleAbsolute(50f,50f);
//        image.setAlignment(Image.MIDDLE);
//        PdfPCell pCTitle = new PdfPCell(image);
//        pCTitle.setHorizontalAlignment(Element.ALIGN_CENTER);
//        pCTitle.setVerticalAlignment(Element.ALIGN_MIDDLE);
//        pCTitle.setBorder(0);
//        float height = image.getHeight();
//        float imameWidth = image.getWidth();
//        System.out.println("heigth：----"+height);
//        System.out.println("width：-----"+imameWidth);
//        for (int i = 0; i <6 ; i++) {
//            datatable.addCell(pCTitle);
//            datatable.addCell(new Paragraph("https://www.baidu.com\nhttp://weixin.qq.com/"));
//            datatable.addCell(new Paragraph(new Chunk("https://www.baidu.com")));//Code 3
//        }
//        document.add(datatable);
//        //关闭文档
//        document.close();
//        //关闭书写器
//        writer.close();
//    }

    private static Integer[] chiper = {4, 8, 1, 3, 2, 7, 6, 5, 9, 0};

    @Test
    public void ssss() {

        String text = "https://www.qichacha.com/company_getinfos?unique=dee238bbc7b1c5cd7cc34ef288a9cb1e&companyname=北京天趣互动科技发展有限公司&tab=run";
        text = text.substring(text.indexOf("que=") + 4, text.lastIndexOf("&com"));
        System.out.println("num : " + text);
//        String  href= "dfdf\tdfd\tdfd\t\thaha\t\t\t\tgag\n";
//        File file = new File("d:\\aaa.txt");
//        try {
//            FileWriter fw = new FileWriter(file, true);
//            BufferedWriter bw = new BufferedWriter(fw);
//            bw.write(href);
//            bw.flush();
//            bw.close();
//            fw.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        System.out.println(href);

//        String pageSource2 = driver.getPageSource();
//        String string2 = pageSource2.replaceAll("\n", "")
//                .replaceAll(" ", "")
//                .replaceAll("\t", "");

        // 天眼查
//        String company = "北京字节跳动科技有限公司";
//        driver.get("https://www.tianyancha.com/search?key="+company);
//        WebElement elementByClassName = driver.findElementByCssSelector(".result-list :nth-of-type(1) .search-result-single .name");
//        String href = elementByClassName.getAttribute("href");
//        driver.get(href);
//        String pageSource = driver.getPageSource();
//        String string = pageSource.replaceAll("\n", "")
//                .replaceAll(" ","")
//                .replaceAll("\t", "");
//        String s = htmlFiter(string);

    }

    public static void main(String[] args) throws Exception {

        String html = "";
        System.setProperty("webdriver.chrome.driver", "D:\\driver\\chromedriver.exe");
        ChromeDriver driver = new ChromeDriver();
//        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
//        driver.get("https://www.qichacha.com/search?key=国机汽车股份有限公司");
//        WebElement m_srchList = driver.findElementByCssSelector(".m_srchList .ma_h1");
//
//        Thread.sleep(1000*1);
//        driver.get(m_srchList.getAttribute("href"));
//        WebElement cssSelector = driver.findElementByCssSelector(".container.m-t");
//        List<WebElement> elementList = cssSelector.findElements(By.className("company-nav-head"));
//        String href = null;
//        for (int i = 0; i < elementList.size(); i++) {
//            WebElement webElement = elementList.get(i);
//            String hrefTmp = webElement.getAttribute("href");
//            if(StringUtils.isNotEmpty(hrefTmp) && hrefTmp.contains("creport")){
//                href = hrefTmp;
//            }
//        }

        Thread.sleep(1000 * 2);
        driver.get("https://www.qichacha.com/cbase_9cce0780ab7644008b73bc2120479d31");
        WebElement elementById_num = driver.findElementByClassName("panel b-a clear m_dataTab");
        List<WebElement> byIdElements_num = elementById_num.findElements(By.tagName("a"));
        for (int i = 0; i < byIdElements_num.size(); i++) {
            if (i == 3 || i == 5 || i == 11) {
                String text = byIdElements_num.get(i).getText();
                text = text.substring(text.length() - 1, text.length());
                System.out.println(text);
            }
        }

    }

    @Test
    public void httpRequestTest() {

        StringBuffer buffer = null;
        BufferedReader bufferedReader = null;
        InputStreamReader inputStreamReader = null;
        InputStream inputStream = null;
        HttpURLConnection httpUrlConn = null;
        String url2 = "https://www.qichacha.com/firm_4aa94ac7e213321248890db59dc712e6.html";
        try {
//            // 创建httpClient 实例
//            CloseableHttpClient aDefault = HttpClients.createDefault();
//            // 建立get请求
//            HttpGet httpGet = new HttpGet(url2);
            URL url = new URL(url2);
            httpUrlConn = (HttpURLConnection) url.openConnection();
            httpUrlConn.setConnectTimeout(200000);
            httpUrlConn.setReadTimeout(300000);
//            httpUrlConn.setDoInput(true);
            httpUrlConn.setRequestMethod("GET");

            int responseCode = httpUrlConn.getResponseCode();
            System.out.println("code :" + responseCode);
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
        org.jsoup.nodes.Document document = Jsoup.parse(buffer.toString(), url2);
        org.jsoup.nodes.Element cominfo = document.getElementById("Cominfo");
        System.out.println("Element : " + cominfo);
        System.out.println(buffer.toString());
//        return buffer.toString();
    }

    @Test
    public void clientTest() throws Exception {
        //创建httpClient实例
//        CloseableHttpClient httpClient = HttpClients.createDefault();
        //创建httpGet实例
        StringBuilder builder = new StringBuilder();
        Random rand = new Random();
        Thread.sleep((rand.nextInt(9) + 2) * 1000);
        List<String> stringsList = getStringsList();
        String firm = null;
        Long zg_number = 1050102L;
        Long hm_number = 1021L;
        for (String string22 : stringsList) {
            firm = string22;
//            CloseableHttpClient httpClient = getMyHttpClient(rand, zg_number, hm_number);
            CloseableHttpClient httpClient = HttpClients.createDefault();
//            String crun = firm.replaceAll("firm", "creport").replaceAll(".html", "");
            HttpGet httpGet = new HttpGet(firm);
            //设置代理IP，设置连接超时时间 、 设置 请求读取数据的超时时间 、 设置从connect Manager获取Connection超时时间、
//        HttpHost proxy = new HttpHost("119.176.197.117",9999);
            RequestConfig requestConfig = RequestConfig.custom()
//                .setProxy(proxy)
                    .setConnectTimeout(10000)
                    .setSocketTimeout(30000)
                    .setConnectionRequestTimeout(3000)
                    .build();
            httpGet.setConfig(requestConfig);
            //设置请求头消息
            httpGet.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36 Avast/71.0.1037.99");
            httpGet.getParams().setParameter("http.protocol.single-cookie-header", true);
            int hm = rand.nextInt(60) + 5;
            hm_number += hm;
            if (hm_number >= 930) {
                hm_number = 1036L;
            }
            int zg = rand.nextInt(800) + 100;
            zg_number += hm;
            if (zg_number >= 9998980) {
                zg_number = 1050000L;
            }
            String cookies = "acw_tc=7cc1e21815512553004023846e0888e116e93c25afb1cb03354b8fd5f3; QCCSESSID=fp982th3pedbqsco0fof6j2475; zg_did=%7B%22did%22%3A%20%221692e056e02170-02405777ed6d3f-38395c0b-1fa400-1692e056e031f%22%7D; UM_distinctid=1692e057476142-0b31125e615ab-38395c0b-1fa400-1692e057479cd; CNZZDATA1254842228=1421862605-1551255269-%7C1551255269; Hm_lvt_3456bee468c83cc63fb5147f119f1075=1551255304; _uab_collina=155125530412199529341327; zg_de1d1a35bfa24ce29bbf2c7eb17e6c4f=%7B%22sid%22%3A%201551255301653%2C%22updated%22%3A%20155125" + zg_number + "%2C%22info%22%3A%201551187256813%2C%22superProperty%22%3A%20%22%7B%7D%22%2C%22platform%22%3A%20%22%7B%7D%22%2C%22utm%22%3A%20%22%7B%7D%22%2C%22referrerDomain%22%3A%20%22www.baidu.com%22%2C%22cuid%22%3A%20%22efc306c6957b861292c50077dd302422%22%7D; Hm_lpvt_3456bee468c83cc63fb5147f119f1075=155125" + hm_number;
            httpGet.addHeader(new BasicHeader("cookie", cookies));
//            httpClient.execute(httpGet);
            Thread.sleep((rand.nextInt(4) + 2) * 1000);
//            String crun = firm.replaceAll("firm", "crun").replaceAll(".html", "");
            String crun = "https://www.qichacha.com/company_getinfos?unique=e725c5535cbb1b7e35f3825d1070731a&companyname=国机汽车股份有限公司&tab=run";
            httpGet.setURI(URI.create(crun));
            CloseableHttpResponse response = httpClient.execute(httpGet);
            System.out.println(response.getStatusLine().getStatusCode());

            if (response.getStatusLine().getStatusCode() == 200 && response != null) {
                HttpEntity entity = response.getEntity();  //获取返回实体
                if (entity != null) {
                    String string1 = EntityUtils.toString(entity, "utf-8");
//                    System.out.println("Html: "+string1);
                    if (string1.contains("普通登录")) {
                        System.out.println("还是要登录===============");
                        System.exit(0);
                    }
                    Document document = Jsoup.parse(string1);
                    // 新闻舆情
                    Element newslistId = document.getElementById("newslist");
                    if (newslistId == null) {
                        System.out.println("newslistId 为空");
                        builder.append("-\t");
                    } else {
                        Elements byIdElements = newslistId.getElementsByClass("news-impact-title");
                        if (CollectionUtils.isEmpty(byIdElements)) {
                            builder.append("-\t");
                        } else {
                            for (int i = 0; i < byIdElements.size(); i++) {
                                Elements spans = byIdElements.get(i).getElementsByTag("span");
                                if (CollectionUtils.isEmpty(spans)) {
//                        builder.append("-\t"); // 为空无操作
                                } else {
                                    System.out.println("(" + spans.get(0).text() + ")" + spans.get(1).text() + "; ");
                                    builder.append("(" + spans.get(0).text() + ")" + spans.get(1).text() + "; ");
                                }
                                if (i == 4) break;
                            }
                            builder.append("\t");
                        }
                    }
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
//                    // 基本信息
//                    Element elementById_cbase = document.getElementById("Cominfo");
//                    if (elementById_cbase ==null) {
//                        builder.append("-\t-\t-\t");
//                    }
//                    Elements byIdElements_cbase = elementById_cbase.getElementsByTag("tbody");
//                    if (CollectionUtils.isEmpty(byIdElements_cbase)) {
//                        builder.append("-\t-\t-\t");
//                    } else {
//                        Elements td_cbase = byIdElements_cbase.get(1).getElementsByTag("td");
//                        String text = td_cbase.get(19).text() + "\t" +
//                                td_cbase.get(7).text() + "\t" +
//                                td_cbase.get(33).text() + "\t";
//                        builder.append(text);
//                    }
//                    String string = builder.toString();
//                    System.out.println(string);
//                    Element base_div = document.getElementsByClass("data_div_login").get(0);
//                    Elements panel_body_div = base_div.getElementsByClass("panel-body");
//                    Elements byIdElements_num = panel_body_div.get(0).getElementsByTag("a");
//                    for (Element element : byIdElements_num) {
//                        String data = element.text();
//                        String href = element.attr("href");
//                        List<String> list = Splitter.on(" ").omitEmptyStrings().trimResults().splitToList(data);
//                        if (CollectionUtils.isNotEmpty(list) && list.size()==2) {
//                            System.out.println(href+" : "+list.get(0)+" : "+list.get(1));
//                        }
//                    }
                } else {
                    System.out.println("获取返回实体为空");
                }
            } else System.out.println("response  为空");
            if (response != null) {
                response.close();
            }
            if (httpClient != null) {
                httpClient.close();
            }
        }
    }


    private HttpGet getHttpGet(String url) {
        HttpGet httpGet = new HttpGet(url);
        //设置代理IP，设置连接超时时间 、 设置 请求读取数据的超时时间 、 设置从connect Manager获取Connection超时时间、
//        HttpHost proxy = new HttpHost("125.123.142.135",9999);
        RequestConfig requestConfig = RequestConfig.custom()
//                .setProxy(proxy)
                .setConnectTimeout(10000)
//                .setSocketTimeout(10000)
                .setConnectionRequestTimeout(3000)
                .build();
        httpGet.setConfig(requestConfig);
        //设置请求头消息
//        httpGet.setHeader("User-Agent","Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/72.0.3626.119 Safari/537.36");
        httpGet.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/55.0.2883.87 UBrowser/6.2.4094.1 Safari/537.36");
        return httpGet;
    }

    private static List<String> getStringsList() {
        return Arrays.asList(
//                "https://www.qichacha.com/firm_hbab2bef51e205b0769571d57f98a60f.html",
//                "https://www.qichacha.com/firm_1b6ae2479c5104fbeef6215455773aaf.html",
                "https://www.qichacha.com/firm_f5bb165b812a8867a1d4f22ec1bc64b6.html",
                "https://www.qichacha.com/firm_ead0726ab8198c68adf9d3605621d1e4.html",
                "https://www.qichacha.com/firm_f5bb165b812a8867a1d4f22ec1bc64b6.html",
                "https://www.qichacha.com/firm_c98822ec879824e17b05fe0a5f6f79f8.html",
                "https://www.qichacha.com/firm_64a1a14d3502a1e089924e557fff8637.html",
                "https://www.qichacha.com/firm_8b8da5a9ed56365851a8c85ba07c370f.html",
                "https://www.qichacha.com/firm_5e113dd9f55e03223d8d42c08684f150.html",
                "https://www.qichacha.com/firm_0ce4fe3bd9f91cd9b67b436b1b5a132f.html",
                "https://www.qichacha.com/firm_f470d4e4d2bf1fc59650255443707461.html",
                "https://www.qichacha.com/firm_38d9b57157ec9f637313e3522316dcac.html",
                "https://www.qichacha.com/firm_cd4cf1fb28cb19efbed0ecca8d729714.html",
                "https://www.qichacha.com/firm_78f66107b9ef6e8c5870ee94fbb0827d.html"
        );
    }

    private static void getBaseInfoMethod(StringBuilder builder, Elements td_cbase, int index) {
        if (td_cbase.get(index) == null) {
            builder.append("-\t");
        } else {
            String baseInfoField = td_cbase.get(index).text();
            if (StringUtils.isEmpty(baseInfoField)) {
                builder.append("-\t");
            } else {
                System.out.print(baseInfoField + "\t");
                builder.append(baseInfoField + "\t");
            }
        }
    }

    @Test
    public void getUrlByCompanyName() throws Exception {
        List<String> companyNameList = Arrays.asList("松下电器" +
                "(中国)有限公司", "  恒   信汽车  ", "  松下  电器(中国)有限公司");
        for (int i = 0; i < 3; i++) {
            String s = companyNameList.get(i);
            String trim = s.replace("\u00a0", "")
                    .replace("\u0020", "")
                    .replace("\u0030", "").trim();
            System.out.println(trim);
        }
//        CloseableHttpClient httpClient = HttpClients.createDefault();
//        for (String companyName : companyNameList) {
//            if (StringUtils.isNotEmpty(companyName)) {
//                Pattern pattern = Pattern.compile("\\s*|\t|\r|\n|\f");
//                Matcher matcher = pattern.matcher(companyName);
//                companyName = matcher.replaceAll("");
//                String trim = companyName.trim();
////                companyName = companyName.replaceAll("\\s*", "");
//                HttpGet httpGet = new HttpGet("https://www.qichacha.com/search?key=" + companyName);
//                httpGet.setHeader("User-Agent","Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36 Avast/71.0.1037.99");
//                CloseableHttpResponse response = httpClient.execute(httpGet);
//                getfirmUrlMethod(companyName, response);
//            } else {
//                System.out.println(companyName+" :公司名称有误！");
//            }
//        }
//        if (httpClient !=null) {
//            httpClient.close();
//        }

    }

    public static String unicode(String source) {
        StringBuffer sb = new StringBuffer();
        char[] source_char = source.toCharArray();
        String unicode = null;
        for (int i = 0; i < source_char.length; i++) {
            unicode = Integer.toHexString(source_char[i]);
            if (unicode.length() <= 2) {
                unicode = "00" + unicode;
            }
            sb.append("\\u" + unicode);
        }
        System.out.println(sb);
        return sb.toString();
    }

    private void getfirmUrlMethod(String companyName, CloseableHttpResponse response) throws IOException {
        if (response.getStatusLine().getStatusCode() == 200 && response != null) {
            HttpEntity entity = response.getEntity();  //获取返回实体
            if (entity != null) {
                String html = EntityUtils.toString(entity, "utf-8");
                Document document = Jsoup.parse(html);
                Elements m_srchList = document.getElementsByClass("m_srchList");
                if (CollectionUtils.isEmpty(m_srchList)) {
                    System.out.println("newslistId 为空");
//                            builder.append("-\t");
                } else {
                    Element element = m_srchList.first();
                    if (element == null) {

                    } else {
                        Elements tds = element.getElementsByTag("td");
                        if (CollectionUtils.isNotEmpty(tds) && tds.size() >= 2) {
                            Element td = tds.get(1);
                            Element a = td.getElementsByTag("a").first();
                            String href = a.attr("href");
                            String name = a.text();
                            String unique = href.substring(href.indexOf("firm_") + 5, href.lastIndexOf("."));
                            System.out.println("\"https://www.qichacha.com/company_getinfos?unique=" + unique + "&companyname=" + name + "&tab=run\",");
                        }
                    }
                }
            } else {
                System.out.println(companyName + " :未查询到结果！");
            }
        } else {
            System.out.println(companyName + " :未查询到结果！");
        }
        if (response != null) {
            response.close();
        }
    }


    @Test
    public void gag () {
        String baseInfoField = "深圳市龙岗区坂田华为总部办公楼 查看地图 附近公司";
        if (baseInfoField.contains("查看地图")) {
            baseInfoField = baseInfoField.substring(0, baseInfoField.length()-10);
        }
        System.out.println(baseInfoField);
    }

//    // 财务总览
//    String url2 = url.replaceAll("cbase", "crun");
//        driver.get(url2);
//    String pageSource3 = driver.getPageSource();
//    ArrayList<String> list3 = new ArrayList<>();
//    Matcher matcher = Pattern.compile("<td.*?>(.*?)</td>").matcher(pageSource3);
//        while (matcher.find()) {
//        list3.add(matcher.group());
//    }
//        for (int i = 0; i < list3.size(); i++) {
//        if (list3.get(i).contains("公司实力等级")) {
//            String s = list3.get(i + 1);
//            String substring = s.substring(s.indexOf(">") + 1, s.indexOf("</"));
//            if (StringUtils.isNotEmpty(substring)) {
////                    builder.append("成立日期: " + str.toString() + "\t");
//                System.out.println("公司实力等级:"+substring);
//            }
//        }
//        if (list3.get(i).contains("纳税区间")) {
//            String s = list3.get(i + 1);
//            String substring = s.substring(s.indexOf(">") + 1, s.indexOf("</"));
//            if (StringUtils.isNotEmpty(substring)) {
////                    builder.append("成立日期: " + str.toString() + "\t");
//                System.out.println("纳税区间:"+substring);
//            }
//        }
//        if (list3.get(i).contains("销售净利润率：")) {
//            String s = list3.get(i + 1);
//            String substring = s.substring(s.indexOf(">") + 1, s.indexOf("</"));
//            if (StringUtils.isNotEmpty(substring)) {
////                    builder.append("成立日期: " + str.toString() + "\t");
//                System.out.println("销售净利润率:"+substring);
//            }
//        }
//        if (list3.get(i).contains("销售毛利率：")) {
//            String s = list3.get(i + 1);
//            String substring = s.substring(s.indexOf(">") + 1, s.indexOf("</"));
//            if (StringUtils.isNotEmpty(substring)) {
////                    builder.append("成立日期: " + str.toString() + "\t");
//                System.out.println("销售毛利率:"+substring);
//            }
//        }
//    }

    //        int i = html.indexOf("<h3 class=\"title\">融资信息</h3>");
//        int i1 = html.indexOf("<!--融资历史end-->");
//        String substring = html.substring(i, i1);
//        if (StringUtils.isNotEmpty(substring)) {
//            String substring1 = substring.substring(substring.indexOf("新闻来源</th></tr></thead><tbody><tr>"), substring.length());
//            substring1 = substring1.replaceAll("新闻来源</th></tr></thead><tbody><tr>", "");
//            int i2 = substring1.indexOf("<div><a");
//            String substring2 = substring1.substring(0, i2);
//            String[] split = substring2.replaceAll("<td>", "").split("</td>");
//            List<String> list1 = Arrays.asList(split);
//            System.out.println(list1);
//            for (int i3 = 0; i3 < list1.size(); i3++) {
//
//            }
//        }

//    List<String> list = new ArrayList<String>();
//    // 取出有用的范围
//    Pattern p = Pattern.compile("<td.*?>(.*?)</td>");
//    Matcher m = p.matcher(html);
//        while (m.find()) {
//        String group = m.group();
//        list.add(group);
//    }
//
//        for (int i = 0; i < list.size(); i++) {
//        if (list.get(i).startsWith("<tdclass=\"tb\">所属行业</td>")) {
//            String s = list.get(i + 1);
//            String substring = s.substring(s.indexOf(">") + 1, s.indexOf("</"));
//            if (StringUtils.isNotEmpty(substring)) {
//                builder.append(substring + "\t");
//                System.out.println("所属行业:" + substring + "\t");
//            } else {
//                builder.append("-\t");
//                System.out.println("所属行业:-\t");
//            }
//        }
//        if (list.get(i).startsWith("<tdclass=\"tb\"width=\"18%\">成立日期")) {
//            String s = list.get(i + 1);
//            String substring = s.substring(s.indexOf(">") + 1, s.indexOf("</"));
//            if (StringUtils.isNotEmpty(substring)) {
//                builder.append(substring + "\t");
//                System.out.println("成立日期: " + substring);
//            } else {
//                builder.append("-\t");
//                System.out.println("成立日期: -");
//            }
//        }
//        if (list.get(i).startsWith("<tdclass=\"tb\">人员规模")) {
//            String s = list.get(i + 1);
//            String substring = s.substring(s.indexOf(">") + 1, s.indexOf("</"));
//            if (StringUtils.isNotEmpty(substring)) {
//                System.out.println("人员规模:" + substring + "\t");
//            } else {
//                System.out.println("人员规模:-\t");
//            }
//        }
//    }
//    // 取出有用的范围
//    List<String> list2 = new ArrayList<String>();
//    Matcher m2 = Pattern.compile("<a.*?>(.*?)</a>").matcher(html);
//        while (m2.find()) {
//        String group = m2.group();
//        list2.add(group);
//    }
//
//        for (int i = 0; i < list2.size(); i++) {
//        if (list2.get(i).contains("nowrap;\">分支机构")) {
//            String s = list2.get(i);
//            String substring = s.substring(s.length() - 6, s.length() - 4);
//            if (StringUtils.isNotEmpty(substring)) {
//                System.out.println("分支机构:" + substring + "\t");
//            } else {
//                System.out.println("分支机构:-\t");
//            }
//        }
//        if (list2.get(i).contains("nowrap;\">对外投资")) {
//            String s = list2.get(i);
//            String substring = s.substring(s.length() - 6, s.length() - 4);
//            if (StringUtils.isNotEmpty(substring)) {
//                System.out.println("对外投资:" + substring + "\t");
//            } else {
//                System.out.println("对外投资:-\t");
//            }
//        }
//        if (list2.get(i).contains("nowrap;\">控股企业")) {
//            String s = list2.get(i);
//            String substring = s.substring(s.length() - 6, s.length() - 4);
//            if (StringUtils.isNotEmpty(substring)) {
//                System.out.println("控股企业:" + substring + "\t");
//            } else {
//                System.out.println("控股企业:-\t");
//            }
//        }
//    }

}
