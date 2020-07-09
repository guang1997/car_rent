package com.atguigu.car.utils;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;

/**
 * 该工具类用于设置表格样式
 */
public class ExcelStyleUtils {
    /**
     * 水平和垂直居中
     *
     * @param workbook
     * @return
     */
    public static HSSFCellStyle getBaseStyle(HSSFWorkbook workbook) {
        HSSFCellStyle style = workbook.createCellStyle();
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        return style;
    }

    /**
     * 基础字体
     */
    public static HSSFFont getBaseFont(HSSFWorkbook workbook) {
        HSSFFont font = workbook.createFont();
        font.setColor(HSSFColor.HSSFColorPredefined.BLACK.getIndex());
        font.setFontHeightInPoints((short) 16);
        return font;
    }

    /**
     * 标题样式
     */
    public static HSSFCellStyle getTitleStyle(HSSFWorkbook workbook) {
        HSSFCellStyle style = getBaseStyle(workbook);

        HSSFFont font = getBaseFont(workbook);
        font.setBold(true);
        font.setFontName("华文行楷");
        font.setFontHeightInPoints((short) 35);
        style.setFont(font);

        return style;
    }

    /**
     * 小标题样式
     */
    public static HSSFCellStyle getSubTitleStyle(HSSFWorkbook workbook) {
        HSSFCellStyle style = getBaseStyle(workbook);
        HSSFFont font = getBaseFont(workbook);
        font.setBold(true);
        font.setFontName("幼圆");
        font.setColor(HSSFColor.HSSFColorPredefined.RED.getIndex());
        font.setFontHeightInPoints((short) 20);
        style.setFont(font);
        return style;
    }

    /**
     * 表头样式
     */
    public static HSSFCellStyle getTableHeaderStyle(HSSFWorkbook workbook) {
        HSSFCellStyle style = getBaseStyle(workbook);
        HSSFFont font = getBaseFont(workbook);
        font.setBold(true);
        font.setFontName("黑体");
        font.setColor(HSSFColor.HSSFColorPredefined.BRIGHT_GREEN.getIndex());
        font.setFontHeightInPoints((short) 25);
        style.setFont(font);
        return style;
    }
}
