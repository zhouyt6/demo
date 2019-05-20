import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import util.RandomUtil;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.*;

public class zhaoPinTest {

//    public static void main(String[] args) throws IOException {
//        CloseableHttpClient httpClient = HttpClients.createDefault();
//        HttpGet httpGet = new HttpGet("https://bj.58.com/jiazhengbaojiexin/pn2/?key=%E4%BF%9D%E6%B4%81&cmcskey=%E4%BF%9D%E6%B4%81&final=1&jump=1&specialtype=gls&PGTID=0d30366b-0000-125a-d042-17c1331a93ac&ClickID=3");
//        RequestConfig requestConfig = RequestConfig.custom()
//                .setConnectTimeout(10000)
//                .setSocketTimeout(30000)
//                .setConnectionRequestTimeout(3000)
//                .build();
//        httpGet.setConfig(requestConfig);
//        //设置请求头消息
//        httpGet.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36 Avast/71.0.1037.99");
//        httpGet.getParams().setParameter("http.protocol.single-cookie-header", true);
//        CloseableHttpResponse response = httpClient.execute(httpGet);
//        int statusCode = response.getStatusLine().getStatusCode();
//        System.out.println(statusCode);
//
//        if (statusCode == 200) {
//            HttpEntity entity = response.getEntity();  //获取返回实体
//            if (entity != null) {
//                String res = EntityUtils.toString(entity, "utf-8");
//                Document document = Jsoup.parse(res);
//                System.out.println(document);
//            }
//        }
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
    private static final String COMPANY_NAME_ERR_N = "不存在标签\r\n";
    private static final String SYSTEM_SEARCH_ERROR = "查询异常\r\n";

    public static void main(String[] args) throws Exception {

        int b= 0;
        List<String> urlList = getUrlList();
        for (String href : urlList) {
            b+=1;

            File file = new File("d:\\tit58.txt");
            FileWriter fw = new FileWriter(file, true);
            BufferedWriter bw = new BufferedWriter(fw);
            int total = 1;
            int num = 0;
            ArrayList<TongCheng> tongChengs = new ArrayList<>();
            while (true) {
                CloseableHttpClient httpClient = HttpClients.createDefault();
                num += 1;
                //            String href = "https://bj.58.com/baoanl/pn{0}/pve_5363_264/?utm_source=market&spm=u-2d2yxv86y3v43nkddh1.BDPCPZ_BT&PGTID=0d3035f7-0000-{1}-{2}-{3}&ClickID=1";
                href = MessageFormat.format(href, total, RandomUtil.getChar(4), RandomUtil.getChar(4), RandomUtil.getChar(12));
                System.out.println("href :" + href);
                CloseableHttpResponse response = getDocumentByUrl(httpClient, href, null);

                if (response != null && response.getStatusLine().getStatusCode() == 200) {
                    ArrayList<Entity58> list = getUniqueUrlMethod(null, response);
                    list.forEach(
                            Entity58 -> {
                                CloseableHttpClient httpClientBase = HttpClients.createDefault();
                                try {
                                    TongCheng tongCheng = new TongCheng();
                                    tongCheng.setComp_name(Entity58.getComp_name());
                                    tongCheng.setUrl(Entity58.getUrl());
                                    CloseableHttpResponse responseBase = getDocumentByUrl(httpClient, Entity58.getUrl(), null);
                                    getBaseInfoMessage(responseBase, tongCheng);

                                    System.out.println(tongCheng);
                                    if (!tongChengs.contains(tongCheng)) {
                                        tongChengs.add(tongCheng);
                                    }

                                    if (responseBase != null) {
                                        responseBase.close();
                                    }
                                    if (httpClientBase != null) {
                                        httpClientBase.close();
                                    }
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            });
                    int total_page = list.stream().findFirst().get().getTotal_page();
                    if (total_page == total) {
                        break;
                    }
                    total += 1;
                }
                if (response != null) {
                    response.close();
                }
                if (httpClient != null) {
                    httpClient.close();
                }
            }
            tongChengs.forEach(tongCheng -> {
                save(tongCheng.toString(), bw);
            });

            bw.close();
            fw.close();
            System.out.println("循环次数 num: " + num);

        }
        System.out.println("dfdfgdfdfd:"+b);
    }


    // 解析基础数据信息
    private static void getBaseInfoMessage(CloseableHttpResponse response, TongCheng tongCheng) throws Exception {
        HttpEntity entity = response.getEntity();
        if (entity != null) {
            String html = EntityUtils.toString(entity, "utf-8");
            Document doc = Jsoup.parse(html);
            String hangye = doc.getElementsByClass("comp_baseInfo_link").first().text();
            tongCheng.setHangye(hangye);
            String guimo = doc.getElementsByClass("comp_baseInfo_scale").first().text();
            tongCheng.setGuimo(guimo);
            String salary = doc.getElementsByClass("pos_salary").first().text();
            tongCheng.setSalary(salary);

            // 要求
            StringBuilder item_condition = new StringBuilder();
            Elements elements = doc.getElementsByClass("item_condition");
            elements.stream().forEach(element -> {
                String yaoqiu = element.text();
                item_condition.append(yaoqiu+",");
            });
            tongCheng.setYaoqiu(item_condition.toString());

            // 福利
            StringBuilder fuliBuilder = new StringBuilder();
            doc.getElementsByClass("pos_welfare_item")
                    .stream().forEach(element -> {
                String fuli = element.text();
                fuliBuilder.append(fuli+",");
            });
            tongCheng.setFuli(fuliBuilder.toString());

        }
        if (response !=null) {
            response.close();
        }
    }


    private static CloseableHttpResponse getDocumentByUrl(CloseableHttpClient httpClient,String url,String one) throws Exception {
        Random rand = new Random();
        HttpGet httpGet = new HttpGet(url);
        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectTimeout(10000)
                .setSocketTimeout(30000)
                .setConnectionRequestTimeout(3000)
                .build();
        httpGet.setConfig(requestConfig);
        //设置请求头消息
        httpGet.setHeader("User-Agent","Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/73.0.3683.86 Safari/537.36");
        httpGet.getParams().setParameter("http.protocol.single-cookie-header",true);
//        String cookies = "zg_did=%7B%22did%22%3A%20%22169340aa5ae141-0307e4aa0b733b-38395c0b-100200-169340aa5af9a%22%7D; UM_distinctid=169340aa60712e-0336059d8eca1a-38395c0b-100200-169340aa6084f; _uab_collina=155135630767518747562716; acw_tc=7cc1e21815513563107247507e8a99986223681867995861b6d0276e04; QCCSESSID=f0uiipj578kgor0b2kvioupbi6; CNZZDATA1254842228=474631574-1552540565-https%253A%252F%252Fwww.baidu.com%252F%7C1552540565; hasShow=1; Hm_lvt_3456bee468c83cc63fb5147f119f1075=1552545278,1552545293; " +
//                "zg_de1d1a35bfa24ce29bbf2c7eb17e6c4f=%7B%22sid%22%3A%201552545277394%2C%22updated%22%3A%20155254"+zg_number+"%2C%22info%22%3A%201552545277405%2C%22superProperty%22%3A%20%22%7B%7D%22%2C%22platform%22%3A%20%22%7B%7D%22%2C%22utm%22%3A%20%22%7B%7D%22%2C%22referrerDomain%22%3A%20%22www.baidu.com%22%2C%22cuid%22%3A%20%22efc306c6957b861292c50077dd302422%22%7D; " +
//                " Hm_lpvt_3456bee468c83cc63fb5147f119f1075=155254"+hm_number;
//        httpGet.addHeader(new BasicHeader("cookie",cookies));
        CloseableHttpResponse response = httpClient.execute(httpGet);
        Thread.sleep((rand.nextInt(3) + 2)*1000);
        return response;
    }

    /**
     * 获取uniqueUrl
     * @param builder
     * @param response
     * @throws IOException
     */
    private static ArrayList<Entity58> getUniqueUrlMethod(StringBuilder builder, CloseableHttpResponse response) throws IOException {
        HttpEntity entity = response.getEntity();  //获取返回实体
        ArrayList<Entity58> list = new ArrayList<>();
        if (entity != null) {
            String html = EntityUtils.toString(entity, "utf-8");
            Document doc = Jsoup.parse(html);
            String total = doc.getElementsByClass("total_page").first().text();
            int total_page = Integer.parseInt(total);

            Elements job_itemS = doc.getElementsByClass("job_item");
            for (Element job_item : job_itemS) {
                String url = job_item.getElementsByClass("job_name").first().
                        getElementsByTag("a").first().attr("href");
                String comp_name = job_item.getElementsByClass("comp_name").first()
                        .getElementsByTag("a").first().text();
                Entity58 dto = new Entity58(total_page,comp_name,url);
                if (!list.contains(dto)) {
                    list.add(dto);
                }
            }
            LinkedHashSet<Entity58> tmpSet = new LinkedHashSet<Entity58>(list.size());
            tmpSet.addAll(list);
            list.clear();
            list.addAll(tmpSet);
        }
        if (response !=null) {
            response.close();
        }
        return list;
    }


    public static List<String> getUrlList() {
        return Arrays.asList(
                "https://bj.58.com/baoanl/pn{0}/pve_5363_247/?utm_source=market&spm=u-2d2yxv86y3v43nkddh1.BDPCPZ_BT&PGTID=0d3035f7-0000-{1}-{2}-{3}&ClickID=1",
                "https://bj.58.com/baoanl/pn{0}/pve_5363_264/?utm_source=market&spm=u-2d2yxv86y3v43nkddh1.BDPCPZ_BT&PGTID=0d3035f7-0000-{1}-{2}-{3}&ClickID=1",
                "https://bj.58.com/baoanl/pn{0}/pve_5363_246/?utm_source=market&spm=u-2d2yxv86y3v43nkddh1.BDPCPZ_BT&PGTID=0d3035f7-0000-{1}-{2}-{3}&ClickID=1"
        );
    }
}