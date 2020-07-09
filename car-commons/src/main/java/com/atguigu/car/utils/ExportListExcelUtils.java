package com.atguigu.car.utils;



import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;

import java.io.ByteArrayOutputStream;
import java.lang.reflect.Field;
import java.util.Date;
import java.util.List;

/**
 * 该工具类用于将数据表格信息导出到excel中
 */
public class ExportListExcelUtils {
    static HSSFWorkbook workbook = new HSSFWorkbook();
    static HSSFCellStyle baseStyle = ExcelStyleUtils.getBaseStyle(workbook);
    static HSSFCellStyle titleStyle = ExcelStyleUtils.getTitleStyle(workbook);
    static HSSFCellStyle subTitleStyle = ExcelStyleUtils.getSubTitleStyle(workbook);
    static HSSFCellStyle tableHeadStyle = ExcelStyleUtils.getTableHeaderStyle(workbook);

    /**
     * 导出数据方法
     */
    public static ByteArrayOutputStream exportList(List<?> lists, String titleName, String sheetName) {
        ByteArrayOutputStream outputStream=new ByteArrayOutputStream();
        try {
            // 创建sheet
            HSSFSheet sheet = workbook.createSheet(sheetName);
            // 设置sheet的样式
            sheet.setDefaultColumnWidth(20);
            // sheet.setDefaultRowHeightInPoints(20);
            // 设置合并
            Integer langth=0;
            if (null != lists && lists.size() > 0) {
                Object object = lists.get(0);
                Field[] fields = object.getClass().getDeclaredFields();
                langth=fields.length;
            }
            CellRangeAddress range1 = new CellRangeAddress(0, 0, 0, langth-1);
            CellRangeAddress range2 = new CellRangeAddress(1, 1, 0, langth-1);
            sheet.addMergedRegion(range1);
            sheet.addMergedRegion(range2);
            // 写数据
            // 第一行
            int row = 0;
            HSSFRow row0 = sheet.createRow(row);
            HSSFCell row0_cell0 = row0.createCell(0);
            row0_cell0.setCellStyle(titleStyle);
            row0_cell0.setCellValue(titleName);
            // 第二行
            row++;
            HSSFRow row1 = sheet.createRow(row);
            HSSFCell row1_cell0 = row1.createCell(0);
            row1_cell0.setCellStyle(subTitleStyle);
            row1_cell0.setCellValue("总数:" + lists.size() + ",  导出时间:" + new Date().toLocaleString());
            // 第三行
            row++;
            HSSFRow row2 = sheet.createRow(row);
            if (null != lists && lists.size() > 0) {
                Object obj = lists.get(0);
                Field[] fields = obj.getClass().getDeclaredFields();
                for (int i = 0; i < fields.length; i++) {
                    String key = obj.getClass().getSimpleName() + "." + fields[i].getName();
                    HSSFCell cell = row2.createCell(i);
                    cell.setCellStyle(tableHeadStyle);
                    cell.setCellValue(PropertiesUtils.getProperty(key));
                }

                // 第四行
                for (int i = 0; i < lists.size(); i++) {
                    Object object = lists.get(i);
                    row++;
                    HSSFRow rowx = sheet.createRow(row);
                    for (int j = 0; j < fields.length; j++) {
                        Field field = fields[j];
                        field.setAccessible(true);
                        Object value = field.get(object);
                        // 第一列
                        HSSFCell rowx_cell0 = rowx.createCell(j);
                        rowx_cell0.setCellStyle(baseStyle);
                        if(field.getType().getSimpleName().equals("Date")) {
                            Date d=(Date) value;
                            rowx_cell0.setCellValue(d.toLocaleString());
                        }else if(field.getType().getSimpleName().equals("Integer")){
                            if(field.getName().contains("sex")) {
                                Integer sex=(Integer) value;
                                rowx_cell0.setCellValue(sex==1?"男":"女");
                            }else {
                                rowx_cell0.setCellValue(value.toString());
                            }
                        }else {
                            rowx_cell0.setCellValue(value.toString());
                        }
                    }
                }
            }
            workbook.write(outputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return outputStream;
    }


}
