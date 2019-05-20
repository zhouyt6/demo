import com.alibaba.fastjson.JSON;
import com.itextpdf.text.*;
import com.itextpdf.text.log.Logger;
import com.itextpdf.text.log.LoggerFactory;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: Zhou YingTao
 * @Date: 2018/9/14
 * @Description:
 */
public class ArrTest {
    private static Logger logger = LoggerFactory.getLogger(ArrTest.class);

    public static void main(String[] args) {

    }
    @Test
    public void downloadQuoteTable() {

        // 1.新建document对象
        // 1- 页面的属性
        Rectangle tRectangle = new Rectangle(PageSize.A4); // 页面大小
        tRectangle.setBackgroundColor(BaseColor.ORANGE); // 页面背景色
        tRectangle.setBorder(10);
        Document document = new Document(tRectangle.rotate());// 横向打印
        String pdfName = "报价单" + "-" + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        File file = new File("C:\\Users\\Administrator\\Desktop\\crm\\pdf");
        String pathName = "C:\\Users\\Administrator\\Desktop\\crm\\pdf"+"/"+pdfName + ".pdf";
        if(!file.exists() && !file.isDirectory())
        {
            file.mkdirs();
        }
        // 2.建立一个书写器(Writer)与document对象关联，通过书写器(Writer)可以将文档写入到磁盘中。
        // 创建 PdfWriter 对象 第一个参数是对文档对象的引用，第二个参数是文件的实际名称，在该名称中还会给出其输出路径。
        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            PdfWriter writer1 = PdfWriter.getInstance(document, outputStream);
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(pathName));

            // 3.打开文档
            document.open();
            // 中文字体(现在高版本的不支持中文包)
            BaseFont bfChinese = BaseFont.createFont("STSongStd-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
            Font fontChinese = new Font(bfChinese, 12, Font.NORMAL);// 中文、12、正常
            // 4.添加一个内容段落
            java.util.List detailList =new ArrayList();

            User user0 = new User();
            user0.setId( 3);
            user0.setName("zhangame");
            user0.setJsonStr("{\"城市\":\"北京3\",\"公司\":\"58企服3\"}");
            detailList.add(user0);

            User user1 = new User();
            user1.setId(1);
            user1.setName("aame");
            user1.setJsonStr("{\"城市\":\"北京1\",\"公司\":\"58企服1\"}");
            detailList.add(user1);

            User user2 = new User();
            user2.setId(4);
            user2.setName("bame");
            user2.setJsonStr("{\"城市\":\"北京4\",\"公司\":\"58企服4\"}");
            detailList.add(user2);
            User user3 = new User();
            user3.setId(1);
            user3.setName("bame");
            user3.setJsonStr("{\"城市\":\"北京11\",\"公司\":\"58企服11\"}");
            detailList.add(user3);
            User user4 = new User();
            user4.setId(3);
            user4.setName("bame");
            user4.setJsonStr("{\"城市\":\"北京33\",\"公司\":\"58企服33\"}");
            detailList.add(user4);

            Collections.sort(detailList, new Comparator<User>() {
                @Override
                public int compare(User o1, User o2) {
                    User user1 = (User)o1;
                    User user2 = (User)o2;
                    if (user1.getId() > user2.getId()) {
                        return 1;
                    }
                    return -1;
                }
            });

            //生成pdf头部
            PdfPTable datatable = this.generatePdfTemp(bfChinese, detailList, document);

            document.add(datatable);//把表格加入到文档中
            //关闭文档流
            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 5.关闭文档
        }
    }

    /**
     * 生成pdf表头部分
     * @param bfChinese
     * @param detailList
     * @throws DocumentException
     */
    private PdfPTable generatePdfTemp(BaseFont bfChinese, List<User> detailList, Document document) throws DocumentException {
        PdfPTable datatable =null;
        if (!detailList.isEmpty()) {
            Font fontChinese = new Font(bfChinese, 12, Font.NORMAL);// 中文、12、正常
            Font titleFont = new Font(bfChinese, 20, Font.NORMAL);
            titleFont.setColor(BaseColor.GREEN);
            document.add(new Paragraph("报价单信息:\n\n",titleFont));
            int colNumber = detailList.size()+1;
            datatable = new PdfPTable(colNumber);
            // 定义表格的宽度
            int[] cellsWidth = new int[colNumber];
            for (int i = 0; i < cellsWidth.length; i++) {
                if (i==0) {
                    cellsWidth[i] = 2;
                    continue;
                }
                cellsWidth[i] = 3;
            }
            datatable.setWidths(cellsWidth);// 单元格宽度
            datatable.setWidthPercentage(100);// 表格的宽度百分比
            datatable.getDefaultCell().setPadding(1);// 单元格的间隔
            // 设置表格的底色
            datatable.getDefaultCell().setUseAscender(true);
            datatable.getDefaultCell().setUseDescender(true);
            datatable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
            datatable.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);


            List<Integer> collectId = detailList.stream()
                    .map(User::getId)
                    .collect(Collectors.toList());
            List<String> collectName = detailList.stream()
                    .map(User::getName)
                    .collect(Collectors.toList());
            List<String> collectJson = detailList.stream()
                    .map(User::getJsonStr)
                    .collect(Collectors.toList());

            // 添加表头元素
            datatable.addCell(new Paragraph("商家", fontChinese));
            for (Integer id : collectId) {
                datatable.addCell(new Paragraph(id+"", fontChinese));
            }
            datatable.addCell(new Paragraph("真实商家", fontChinese));
            for (String name : collectName) {
                datatable.addCell(new Paragraph(name+"", fontChinese));
            }
            List<String> headList = new ArrayList<>();
            List<String> jsonValueList = new ArrayList<>();
            //头
            if (detailList !=null) {
                    String formtempValue = collectJson.get(0);
                    if (org.apache.commons.lang.StringUtils.isNotEmpty(formtempValue) && formtempValue.startsWith("{")) {
                        LinkedHashMap formtempValueMap = JSON.parseObject(formtempValue, LinkedHashMap.class);
                        for (Object head : formtempValueMap.keySet()) {
                            headList.add(head.toString());
                        }
                    }
            }
            //value
            for (String realHead : headList) {
                for (String formtempValue : collectJson) {
                    LinkedHashMap formtempValueMap = JSON.parseObject(formtempValue, LinkedHashMap.class);
                    for (Object head : formtempValueMap.keySet()) {
                        if (realHead.equals(head.toString())) {
                            jsonValueList.add(formtempValueMap.get(head).toString());
                            break;
                        }
                    }
                }
            }

            //添加json数据的值
            for (int i = 0; i < headList.size(); i++) {
                datatable.addCell(new Paragraph(headList.get(i), fontChinese));
                for (int j = i*(colNumber-1); j < (i+1)*(colNumber-1); j++) {
                    datatable.addCell(new Paragraph(jsonValueList.get(j), fontChinese));
                }
            }

        }
        if (datatable == null) {
            logger.error("datatable: " + datatable);
        }
        return datatable;
    }
}
