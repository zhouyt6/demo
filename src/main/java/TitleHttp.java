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

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * @Author: Zhou YingTao
 * @Date: 2019/2/20
 * @Description:
 */
public class TitleHttp {

    public static void save(String info, BufferedWriter bw) {
        try {
            bw.write(info);
            bw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static final String COMPANY_NAME_ERR_T = "公司名称有误\t";
    private static final String COMPANY_NAME_ERR_N = "不存在标签\r\n";
    private static final String SYSTEM_SEARCH_ERROR = "查询异常\r\n";
    /**
     * 测试
     *
     * @param args
     */
    public static void main(String[] args) throws Exception {

        StringBuilder builder = null;
        Random rand = new Random();
        File file = new File("d:\\title-2.txt");
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
                    CloseableHttpResponse response = getDocumentByUrl(httpClient,"https://www.qichacha.com/search?key=" + companyName,zg_number,hm_number);
                    if (response != null && response.getStatusLine().getStatusCode() == 200) {
                        ArrayList<String> list = getUniqueUrlMethod(builder, response);
                        for (int i = 0; i < list.size(); i++) {
                            if (i==2 || i==list.size()-1) {
                                // 取第三个公司的标签
                                CloseableHttpResponse response_rongzi = getDocumentByUrl(httpClient, list.get(i), zg_number, hm_number);
                                if (response_rongzi != null && response_rongzi.getStatusLine().getStatusCode() == 200) {
                                    HttpEntity entity = response_rongzi.getEntity();  //获取返回实体
                                    if (entity != null) {
                                        String baseInfo = EntityUtils.toString(entity, "utf-8");
                                        Document document = Jsoup.parse(baseInfo);
                                        getBaseInfoMethod(document, builder);
                                    } else  {
                                        builder.append(COMPANY_NAME_ERR_N);
                                    }
                                } else {
                                    builder.append(COMPANY_NAME_ERR_N);
                                }
                                if (response_rongzi != null) {
                                    response_rongzi.close();
                                }
                                break; // 只取三条
                            } else {
                                // 取前两个公司的标签
                                CloseableHttpResponse responseInfo = getDocumentByUrl(httpClient, list.get(i), zg_number, hm_number);
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
                            }
                        }
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


    // 解析基础数据信息
    private static void getBaseInfoMessage(Document document, StringBuilder builder) {
        Elements ntag = document.getElementsByClass("ntag");
        if (CollectionUtils.isEmpty(ntag)) {
            builder.append("-\t");
        } else {
            for (Element element : ntag) {
                builder.append(element.text()+",");
            }
            builder.append("\t");
        }
    }

    private static void getBaseInfoMethod(Document document, StringBuilder builder) {
        Elements ntag = document.getElementsByClass("ntag");
        if (CollectionUtils.isEmpty(ntag)) {
            builder.append("-\r\n");
        } else {
            for (Element element : ntag) {
                builder.append(element.text()+",");
            }
            builder.append("\r\n");
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
    private static CloseableHttpResponse getDocumentByUrl(CloseableHttpClient httpClient,String url,Long zg_number,Long hm_number) throws Exception {
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
        httpGet.setHeader("User-Agent","Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36 Avast/71.0.1037.99");
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
    private static ArrayList<String> getUniqueUrlMethod(StringBuilder builder, CloseableHttpResponse response) throws IOException {
        HttpEntity entity = response.getEntity();  //获取返回实体
        ArrayList<String> list = new ArrayList<>();
        if (entity != null) {
            String html = EntityUtils.toString(entity, "utf-8");
            Document document = Jsoup.parse(html);
            Elements elementsByClass = document.getElementsByClass("proinv-wrap");
            for (Element element : elementsByClass) {
                Element tag_a = element.getElementsByTag("a").first();
                String href = tag_a.attr("href");
                if (href.startsWith("/product")) {
                    list.add("https://www.qichacha.com"+href);
                }
            }
            if (CollectionUtils.isNotEmpty(elementsByClass) && CollectionUtils.isEmpty(list) || CollectionUtils.isEmpty(elementsByClass)) {
                builder.append(COMPANY_NAME_ERR_N);
            }
        } else {
            builder.append(SYSTEM_SEARCH_ERROR);
        }
        if (response !=null) {
            response.close();
        }
        return list;
    }


    // 公司名数据源
    private static List<String> getUrlByCompanyNameList() {
        return Arrays.asList(
                "华为",
                "小米科技有限责任公司",
                "OPPO",
                "VIVO"
        );
    }
}
