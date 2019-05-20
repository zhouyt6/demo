import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author: Zhou YingTao
 * @Date: 2019/2/20
 * @Description:
 */
public class Scrapy {

    public static String getDataFromSiteByUrl(String Url, String encode) {
        String mUrl = Url;
        if (null != mUrl) {
            java.io.InputStream l_urlStream = null;
            java.net.URL l_url = null;
            // java.net.URI l_uri = null;
            java.net.HttpURLConnection l_connection = null;
            int returnCode = 0;
            ByteArrayOutputStream BAOS = null;
            try {
                l_url = new java.net.URL(mUrl.trim());
                l_connection = (java.net.HttpURLConnection) l_url.openConnection();
                // l_connection.setFollowRedirects(false);
                l_connection.setConnectTimeout(200000);
                l_connection.setReadTimeout(300000);
                returnCode = l_connection.getResponseCode();
                if (returnCode > 300)
                    return "<!-- Exception -->";
                l_urlStream = l_connection.getInputStream();
                if (l_urlStream == null) {
                    return "<!-- Exception -->";
                }
                int k = 0;
                int len = 1024;
                if (l_connection.getContentLength() > 0) {
                    len = l_connection.getContentLength();
                }
                byte[] bytes = new byte[len];
                BAOS = new ByteArrayOutputStream(len);
                while ((k = l_urlStream.read(bytes)) > 0) {
                    BAOS.write(bytes, 0, k);
                }
                // System.out.println(teams[i]+BAOS.toString("gb2312"));
                String result = BAOS.toString(encode);
                System.out.println(result);
                // System.out.println(teams[i]+BAOS.toString("utf-8"));
                // System.out.println(teams[i]+result);
                return result;
            } catch (Exception e) {
                e.printStackTrace();
                return "<!-- Exception -->";
            } finally {
                if (BAOS != null) {
                    try {
                        BAOS.flush();
                        BAOS.close();
                    } catch (Exception e) {
                    }
                }
                if (l_urlStream != null) {
                    try {
                        l_urlStream.close();
                    } catch (Exception e) {
                    }
                }
                if (l_connection != null) {
                    try {
                        l_connection.disconnect();
                    } catch (Exception e) {
                    }
                }
            }
        } else
            return "<!-- Exception -->";
    }

    public static void setCoookie(ChromeDriver driver, Long zg_number,Long hm_number) {
        Cookie c1 = new Cookie("UM_distinctid", "16923155ebc185-04aeb890c57852-38395c0b-100200-16923155ebde6");
        Cookie c2 = new Cookie("zg_did", "%7B%22did%22%3A%20%2216923156030a7-0647a54f905f9a-38395c0b-100200-169231560311b5%22%7D");
        Cookie c3 = new Cookie("hasShow", "1");
        Cookie c4 = new Cookie("_uab_collina", "155107179800207781187438");
        Cookie c5 = new Cookie("acw_tc", "7cc1e21715510717985834661ece8b4f712cbb42e5ccf056dd16e73e3d");
        Cookie c6 = new Cookie("QCCSESSID", "49bcigs4uc7hmsuv59r79vpnh1");
        Cookie c7 = new Cookie("CNZZDATA1254842228", "1219582918-1551071737-https%253A%252F%252Fwww.baidu.com%252F%7C1551077137");
        Cookie c8 = new Cookie("Hm_lvt_3456bee468c83cc63fb5147f119f1075", "1551071797,1551076971,1551078228,1551078282");
        Random random = new Random();
        int zg = random.nextInt(1000) + 50;
        zg_number +=zg;
        Cookie c9 = new Cookie("zg_de1d1a35bfa24ce29bbf2c7eb17e6c4f","%7B%22sid%22%3A%201551076970706%2C%22updated%22%3A%"
                +zg_number+"%2C%22info%22%3A%201551071797319%2C%22superProperty%22%3A%20%22%7B%7D%22%2C%22platform%22%3A%20%22%7B%7D%22%2C%22utm%22%3A%20%22%7B%7D%22%2C%22referrerDomain%22%3A%20%22www.qichacha.com%22%2C%22cuid%22%3A%20%22efc306c6957b861292c50077dd302422%22%7D");
        int hm = random.nextInt(500) + 20;
        hm_number +=hm;
        Cookie c10 = new Cookie("Hm_lpvt_3456bee468c83cc63fb5147f119f1075",hm_number+"");
        driver.manage().addCookie(c1);
        driver.manage().addCookie(c2);
        driver.manage().addCookie(c3);
        driver.manage().addCookie(c4);
        driver.manage().addCookie(c5);
        driver.manage().addCookie(c6);
        driver.manage().addCookie(c7);
        driver.manage().addCookie(c8);
        driver.manage().addCookie(c9);
        driver.manage().addCookie(c10);
        driver.navigate().refresh();
    }

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

    private static Integer[] chiper = {4, 8, 1, 3, 2, 7, 6, 5, 9, 0};

    /**
     * 过滤掉html字符串中无用的信息
     *
     * @param html String    html字符串
     * @return String    有用的数据
     */
    private static String htmlFiter(String html, String company) {

        StringBuilder builder = new StringBuilder();
        builder.append(company + "\t");
        List<String> list = new ArrayList<String>();
        // 取出有用的范围
        Pattern p = Pattern.compile("<td.*?>(.*?)</td>");
        Matcher m = p.matcher(html);
        while (m.find()) {
            String group = m.group();
            list.add(group);
        }
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).startsWith("<tdwidth=\"17.3%\">成立日期")) {
                String s = list.get(i + 1);
                String substring = s.substring(s.indexOf("lh24\">") + 6, s.indexOf("</"));
                if (StringUtils.isNotEmpty(substring)) {
                    Integer[] integers = Arrays.stream(substring.replace("-", "").split(""))
                            .map(charactor -> chiper[Integer.parseInt(charactor)]).toArray(Integer[]::new);
                    StringBuilder str = new StringBuilder();
                    if (integers != null) {
                        for (int i1 = 0; i1 < integers.length; i1++) {
                            str.append(integers[i1]);
                            if (i1 == 3 || i1 == 5) str.append("-");
                        }
                    }
                    builder.append("成立日期: " + str.toString() + "\t");
//                    System.out.println("成立日期: "+str.toString());
                } else {
                    System.out.println("成立日期: -");
                }
            }
            if (list.get(i).startsWith("<td>行业")) {
                String s = list.get(i + 1);
                String substring = s.substring(s.indexOf(">") + 1, s.indexOf("</"));
                if (StringUtils.isNotEmpty(substring)) {
                    builder.append("行业: " + substring + "\t");
//                    System.out.println("行业:"+substring+"\t");
                } else {
                    System.out.println("行业:-\t");
                }
            }
            if (list.get(i).startsWith("<td>人员规模")) {
                String s = list.get(i + 1);
                String substring = s.substring(s.indexOf(">") + 1, s.indexOf("</"));
                if (StringUtils.isNotEmpty(substring)) {
                    builder.append("人员规模: " + substring + "\t");
//                    System.out.println("人员规模:"+substring+"\t");
                } else {
                    System.out.println("人员规模:-\t");
                }
            }
        }
        // 取出有用的范围
        List<String> list2 = new ArrayList<String>();
        Matcher m2 = Pattern.compile("<span.*?>(.*?)</span>").matcher(html);
        while (m2.find()) {
            String group = m2.group();
            list2.add(group);
        }
        for (int i = 0; i < list2.size(); i++) {
            if (list2.get(i).contains(">分支机构</span>")) {
                String s = list2.get(i + 1);
                String substring = s.substring(s.indexOf(">") + 1, s.indexOf("</span"));
                if (StringUtils.isNotEmpty(substring)) {
                    builder.append("分支机构: " + substring + "\t");
//                    System.out.println("分支机构:"+substring+"\t");
                } else {
                    System.out.println("分支机构:-\t");
                }
            }
            if (list2.get(i).contains("e\">对外投资</span>")) {
                String s = list2.get(i + 1);
                String substring = s.substring(s.indexOf(">") + 1, s.indexOf("</span"));
                if (StringUtils.isNotEmpty(substring)) {
                    builder.append("对外投资: " + substring + "\t");
//                    System.out.println("对外投资:"+substring+"\t");
                } else {
                    System.out.println("对外投资:-\t");
                }
            }
        }
//        int i = html.indexOf("<!--融资历史-->");
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
//        } else {
//            System.out.println("融资历史:为空");
//        }
        System.out.println("数据: " + builder.toString());
        return builder.toString();
    }

    public static void save(String info, BufferedWriter bw) {
//        File file = new File("d:\\save.txt");
        try {
//            FileWriter fw = new FileWriter(file, true);
//            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(info);
            bw.flush();
//            bw.close();
//            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 测试
     *
     * @param args
     */
    public static void main(String[] args) throws Exception {

        StringBuilder builder = null;
//        StringBuilder info = new StringBuilder();
        Random rand = new Random();
//        System.setProperty("webdriver.chrome.driver", "D:\\driver\\chromedriver.exe");
//        ChromeDriver driver = new ChromeDriver();

        File file = new File("d:\\save.txt");
        FileWriter fw = new FileWriter(file, true);
        BufferedWriter bw = new BufferedWriter(fw);
        List<String> list = getStringsList();
        Long zg_number = 201551078326082L;
        Long hm_number = 1551078312L;
        for (String company : list) {
            builder = new StringBuilder();
            if (StringUtils.isEmpty(company) || !company.startsWith("https")) {
                builder.append(company + ": 网址有误\r\n");
                System.out.println(company + ": 网址有误\r\n");
            } else {
                System.setProperty("webdriver.chrome.driver", "D:\\driver\\chromedriver.exe");
                //参数
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("user-agent=Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/72.0.3626.119 Safari/537.36");
//                chromeOptions.addArguments("user-data-dir=C:/Users/Administrator/AppData/Local/Google/Chrome/User Data/Default");
                ChromeDriver driver = new ChromeDriver(chromeOptions);
                setCoookie(driver, zg_number, hm_number);
                driver.get(company);
                Thread.sleep((rand.nextInt(9) + 2)*1000);
                String href = company.replaceAll("firm", "cbase").replaceAll(".html", "");
                driver.get(href);
                Thread.sleep((rand.nextInt(9) + 2)*1000);
                builder = htmlFiter2qichacha(href, driver, builder);
                System.out.println(builder.toString());
                driver.close();
//                save(string);
//                info.append(htmlFiter2qichacha(href, driver, builder));
            }
            save(builder.toString(), bw);
        }
        bw.close();
        fw.close();
        // 保存
//        save(info.toString());
//        driver.close();

        // 企查查
//        String company = "北京字节跳动科技有限公司";
//        driver.get("https://www.qichacha.com/search?key=" + company);
//
//        WebElement elementByClassName2 = driver.findElementByCssSelector("#searchlist > table > tbody > tr:nth-child(1) > td:nth-child(2) > a");
//        String href = elementByClassName2.getAttribute("href");
//        driver.close();
        //        save(info);
    }


    private static StringBuilder htmlFiter2qichacha(String url, ChromeDriver driver, StringBuilder builder) throws Exception {

        // 基本信息
        WebElement elementById_cbase = driver.findElementById("Cominfo");
        if (elementById_cbase ==null) {
            builder.append("-\t-\t-\t");
        }
        List<WebElement> byIdElements_cbase = elementById_cbase.findElements(By.tagName("tbody"));
        if (CollectionUtils.isEmpty(byIdElements_cbase)) {
            builder.append("-\t-\t-\t");
        } else {
            List<WebElement> td_cbase = byIdElements_cbase.get(1).findElements(By.tagName("td"));
            String text = td_cbase.get(19).getText() + "\t" +
                    td_cbase.get(7).getText() + "\t" +
                    td_cbase.get(33).getText() + "\t";
            builder.append(text);
        }

        WebElement elementById_num = driver.findElementByClassName("data_div_login").findElements(By.tagName("section")).get(0);
        if (elementById_num == null) {
            builder.append("-\t-\t-\r\n");
        }
        List<WebElement> byIdElements_num = elementById_num.findElements(By.tagName("a"));
        if (CollectionUtils.isEmpty(byIdElements_num)) {
            builder.append("-\t-\t-\r\n");
        } else {
            for (int i = 0; i < byIdElements_num.size(); i++) {
                if (i == 3 || i == 5) {
                    String text = byIdElements_num.get(i).getText().trim();
                    text = text.substring(text.indexOf(";") + 1, text.length());
                    if (StringUtils.isEmpty(text)) {
                        builder.append("-\t");
                    } else {
                        text = text.split(" ")[1];
                        if (StringUtils.isEmpty(text)) {
                            builder.append("-\t");
                        } else {
                            builder.append(text + "\t");
                        }
                    }
                }
                if (i == 11) {
                    String text = byIdElements_num.get(i).getText().trim();
                    text = text.substring(text.indexOf(";") + 1, text.length());
                    if (StringUtils.isEmpty(text)) {
                        builder.append("-\r\n");
                    } else {
                        text = text.split(" ")[1];
                        if (StringUtils.isEmpty(text)) {
                            builder.append("-\r\n");
                        } else {
                            builder.append(text + "\r\n");
                        }
                    }
                    break;
                }
            }
        }

//        https://www.qichacha.com/cbase_e725c5535cbb1b7e35f3825d1070731a
//        经营状况:https://www.qichacha.com/crun_e725c5535cbb1b7e35f3825d1070731a
        // 财务总览
//        String url_caiwu = url.replaceAll("cbase", "crun");
//        driver.get(url_caiwu);
//        Thread.sleep((rand.nextInt(4) + 2)*1000);
//        WebElement elementById_caiwu = driver.findElementById("V3_cwzl");
//        if (elementById_caiwu == null) {
//            builder.append("-\t-\t-\t-\t");
//        } else {
//            List<WebElement> byIdElements_caiwu = elementById_caiwu.findElements(By.tagName("td"));
//            if (CollectionUtils.isEmpty(byIdElements_caiwu)) {
//                builder.append("-\t-\t-\t-\t");
//            } else {
//                for (int i = 0; i < byIdElements_caiwu.size(); i++) {
//                    if (i % 2 != 0) {
//                        String text = byIdElements_caiwu.get(i).getText();
//                        builder.append(text + "\t");
//                    }
//                }
//            }
//        }
//
//        // 新闻舆情
//        WebElement newslistId = driver.findElementById("newslist");
//        if (newslistId == null) {
//            builder.append("\t\t\t\t-\r\n");
//        } else {
//            List<WebElement> byIdElements = newslistId.findElements(By.className("news-impact-title"));
//            if (CollectionUtils.isEmpty(byIdElements)) {
//                builder.append("\t\t\t\t-\r\n");
//            } else {
//                for (int i = 0; i < byIdElements.size(); i++) {
//                    List<WebElement> spans = byIdElements.get(i).findElements(By.tagName("span"));
//                    if (CollectionUtils.isEmpty(spans)) {
//                        builder.append("\t\t\t\t-\r\n");
//                    } else {
//                        builder.append("(" + spans.get(0).getText() + ")" + spans.get(1).getText() + "; ");
//                    }
//                    if (i == 5) break;
//                }
//            }
//        }
//
//        // 融资信息
//        String url_rongzi = url.replaceAll("cbase", "creport");
//        driver.get(url_rongzi);
//        Thread.sleep((rand.nextInt(4) + 2)*1000);
//        WebElement elementById = driver.findElementById("financingInfo");
//        if (elementById == null) {
//            builder.append("-\t-\t-\t-\t");
//        } else {
//            List<WebElement> byIdElements_rongzi = elementById.findElements(By.tagName("td"));
//            if (CollectionUtils.isEmpty(byIdElements_rongzi)) {
//                builder.append("-\t-\t-\t-\t");
//            } else {
//                for (int i = 0; i < byIdElements_rongzi.size(); i++) {
//                    if (i == 1 || i == 3 || i == 4 || i == 5) {
//                        String text = byIdElements_rongzi.get(i).getText();
//                        builder.append(text + "\t");
//                        if (i == 5) break;
//                    }
//                }
//            }
//        }

        return builder;
    }

    private static List<String> getStringsList() {
        return Arrays.asList("https://www.qichacha.com/firm_hbab2bef51e205b0769571d57f98a60f.html",
                "https://www.qichacha.com/firm_1b6ae2479c5104fbeef6215455773aaf.html",
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
                "https://www.qichacha.com/firm_78f66107b9ef6e8c5870ee94fbb0827d.html");
    }
}
