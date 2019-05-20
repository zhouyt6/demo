package jdk;

/**
 * @Author: ZYT
 * @Date: 2019/04/24
 * @Description: 导出测试类
 */

public class ExcelTest {

    public static void main(String[] args) throws Exception{

//        ArrayList<jdk.AreaExport> exportList = new ArrayList<>();
//        for (int i = 0; i < 3; i++) {
//            exportList.add(new jdk.AreaExport(1, "测试名称", new Date()));
//            exportList.add(new jdk.AreaExport(2,null, new Date()));
//            exportList.add(new jdk.AreaExport(2,"空时间", null));
//        }
//        // 生成EXCEL
//        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("标题","sheel页"), jdk.AreaExport.class, exportList);
//        File file = new File("E:/工作表.xls");
//        FileOutputStream fileOutputStream = new FileOutputStream(file);
//        workbook.write(fileOutputStream);
//        workbook.close();
//        // 读取EXCEl
//        HSSFWorkbook sheets = new HSSFWorkbook(new FileInputStream(file));
//        for (int m = 0; m < sheets.getNumberOfSheets(); m++) {
//            HSSFSheet sheetAt = sheets.getSheetAt(m);
//            for (int i = 1; i < sheetAt.getLastRowNum(); i++) {
//                HSSFRow row = sheetAt.getRow(i);
//                for (int j = 0; j < row.getLastCellNum(); j++) {
//                    String stringCellValue = row.getCell(j).getStringCellValue();
//                    System.out.println(stringCellValue);
//                }
//            }
//        }

        String etest = "gdfgd_dfsdfgds_链接";
        String[] arr = etest.split("_");
//        etest = arr[arr.length - 1];
        System.out.println(arr[arr.length - 1]);
    }
}
