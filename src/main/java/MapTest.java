import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.util.*;

/**
 * @Author: Zhou YingTao
 * @Date: 2018/9/25
 * @Description:
 */
public class MapTest {
    public static void main(String[] args) {

        Map<String,Integer> map = new HashMap<>();
        map.put("id",11);
        map.put("count",22);
        Map<String,Integer> map2 = new HashMap<>();
        map2.put("id",1);
        map2.put("count",2);
        List<Map<String,Integer>> list = new ArrayList<>();
        list.add(map);
        list.add(map2);
        String string= map.containsKey("id")?map.get("id")+"":"test";
        System.out.println(string);
    }

    @Test
    public  void   test() {

        List<String> list = new ArrayList<>();
        list.add("北京天趣互动科技发展有限公司");
        list.add("维康金磊（北京）国际文化传媒有限公司");
        list.add("中爱科技（北京）有限责任公司");
        list.add("东峡大通（北京）管理咨询有限公司");
        list.add("北京爱康鼎科技有限公司");
        List<String> companyList = Arrays.asList(
                "https://www.qichacha.com/firm_dee238bbc7b1c5cd7cc34ef288a9cb1e.html",
                "https://www.qichacha.com/firm_d2c706d4ff507af15ffc93552f3ad149.html",
                "https://www.qichacha.com/firm_019e8c53abe307cb3123e0ae47f5dada.html",
                "https://www.qichacha.com/firm_c850bd7a1fa0262b04c63cc1a13d40ed.html",
                "https://www.qichacha.com/firm_bbe7986c074af4eafcf320f3a5e73c44.html"
        );
        //        https://www.qichacha.com/company_getinfos?unique=f5bb165b812a8867a1d4f22ec1bc64b6&companyname=杭州娃哈哈集团有限公司&tab=run
        for (int i = 0; i < companyList.size(); i++) {
            String company = companyList.get(i);
            if (StringUtils.isEmpty(company) || !company.contains("firm")) {
                System.out.println("\"网址有误！\",");
            } else {
                String unique = company.substring(company.indexOf("firm_") + 5, company.lastIndexOf("."));
                String name = list.get(i);
                System.out.println("\"https://www.qichacha.com/company_getinfos?unique="+unique+"&companyname="+name+"&tab=run\",");
            }
        }
    }


}
