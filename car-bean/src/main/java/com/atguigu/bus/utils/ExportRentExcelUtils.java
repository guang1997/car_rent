package com.atguigu.bus.utils;

import com.atguigu.bus.bean.BusCustomer;
import com.atguigu.bus.bean.BusRent;
import com.atguigu.car.utils.ExcelStyleUtils;
import com.atguigu.car.utils.ZXingCodeEncodeUtils;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.util.CellRangeAddress;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

public class ExportRentExcelUtils {
    @SuppressWarnings("deprecation")
    public static ByteArrayOutputStream exprotRent(BusRent rent, BusCustomer customer, String titleName, String sheetName) {

        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFCellStyle baseStyle = ExcelStyleUtils.getBaseStyle(workbook);
        HSSFCellStyle titleStyle = ExcelStyleUtils.getTitleStyle(workbook);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try {
            // 创建sheet
            HSSFSheet sheet = workbook.createSheet(sheetName);
            // 设置sheet的样式
            sheet.setDefaultColumnWidth(30);
            sheet.setColumnWidth(1, 50*256);
            // sheet.setDefaultRowHeightInPoints(20);
            // 设置合并
            CellRangeAddress range1 = new CellRangeAddress(0, 0, 0, 3);
            sheet.addMergedRegion(range1);
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
            row1.setHeightInPoints(150);
            HSSFCell row1_cell0 = row1.createCell(0);
            row1_cell0.setCellStyle(baseStyle);
            row1_cell0.setCellValue("出租单号:");

            HSSFCell row1_cell1 = row1.createCell(1);
            row1_cell1.setCellStyle(baseStyle);
            row1_cell1.setCellValue(rent.getRentid());

            HSSFCell row1_cell2 = row1.createCell(2);
            row1_cell2.setCellStyle(baseStyle);
            row1_cell2.setCellValue("二维码:");

            //生成二维码
            InputStream logoStream=ExportRentExcelUtils.class.getClassLoader().getResourceAsStream("logo.jpg");
            BufferedImage logo = ZXingCodeEncodeUtils.createZXingCodeLogo(rent.getRentid(), 300, 300, logoStream);
            ByteArrayOutputStream byteArrayOut = new ByteArrayOutputStream();
            try {
                //把图片流写入内存流
                ImageIO.write(logo, "jpg", byteArrayOut);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            //画图的顶级管理器，一个sheet只能获取一个（一定要注意这点）
            HSSFPatriarch patriarch = sheet.createDrawingPatriarch();

            /**
             * 参数4：0-255之间如果设置255那么图片会在当前单元格铺满
             * 参数5  开始画图的单元格索引
             * 参数6  开始画图的行索引
             * 参数7 结束画图的单元格索引
             * 参数8  结束画图的行索引
             */
            HSSFClientAnchor anchor = new HSSFClientAnchor(0, 0, 0, 255,(short) 3, 1, (short) 4, 1);
            //设置图片画上去这后用户不拖动就是能移动
            anchor.setAnchorType(ClientAnchor.AnchorType.DONT_MOVE_AND_RESIZE);
            patriarch.createPicture(anchor, workbook.addPicture(byteArrayOut.toByteArray(), HSSFWorkbook.PICTURE_TYPE_JPEG));

            // 第三行
            row++;
            HSSFRow row2 = sheet.createRow(row);
            HSSFCell row2_cell0 = row2.createCell(0);
            row2_cell0.setCellStyle(baseStyle);
            row2_cell0.setCellValue("客户姓名:");

            HSSFCell row2_cell1 = row2.createCell(1);
            row2_cell1.setCellStyle(baseStyle);
            row2_cell1.setCellValue(customer.getCustname());

            HSSFCell row2_cell2 = row2.createCell(2);
            row2_cell2.setCellStyle(baseStyle);
            row2_cell2.setCellValue("身份证号:");

            HSSFCell row2_cell3 = row2.createCell(3);
            row2_cell3.setCellStyle(baseStyle);
            row2_cell3.setCellValue(customer.getIdentity());

            // 第四行
            row++;
            HSSFRow row3 = sheet.createRow(row);
            HSSFCell row3_cell0 = row3.createCell(0);
            row3_cell0.setCellStyle(baseStyle);
            row3_cell0.setCellValue("起租时间:");

            HSSFCell row3_cell1 = row3.createCell(1);
            row3_cell1.setCellStyle(baseStyle);
            row3_cell1.setCellValue(rent.getBegindate().toLocaleString());

            HSSFCell row3_cell2 = row3.createCell(2);
            row3_cell2.setCellStyle(baseStyle);
            row3_cell2.setCellValue("还车时间:");

            HSSFCell row3_cell3 = row3.createCell(3);
            row3_cell3.setCellStyle(baseStyle);
            row3_cell3.setCellValue(rent.getReturndate().toLocaleString());

            // 第五行
            row++;
            HSSFRow row4 = sheet.createRow(row);
            HSSFCell row4_cell0 = row4.createCell(0);
            row4_cell0.setCellStyle(baseStyle);
            row4_cell0.setCellValue("车辆号牌:");

            HSSFCell row4_cell1 = row4.createCell(1);
            row4_cell1.setCellStyle(baseStyle);
            row4_cell1.setCellValue(rent.getCarnumber());

            HSSFCell row4_cell2 = row4.createCell(2);
            row4_cell2.setCellStyle(baseStyle);
            row4_cell2.setCellValue("出租价格:");

            HSSFCell row4_cell3 = row4.createCell(3);
            row4_cell3.setCellStyle(baseStyle);
            row4_cell3.setCellValue(rent.getPrice());
            //第六行
            row++;
            //第七行
            row++;
            HSSFRow row6 = sheet.createRow(row);
            HSSFCell row6_cell2 = row6.createCell(2);
            row6_cell2.setCellStyle(baseStyle);
            row6_cell2.setCellValue("打印时间:");

            HSSFCell row6_cell3 = row6.createCell(3);
            row6_cell3.setCellStyle(baseStyle);
            row6_cell3.setCellValue(new Date().toLocaleString());


            //第八行
            row++;
            HSSFRow row7 = sheet.createRow(row);
            HSSFCell row7_cell2 = row7.createCell(2);
            row7_cell2.setCellStyle(baseStyle);
            row7_cell2.setCellValue("操作员:");

            HSSFCell row7_cell3 = row7.createCell(3);
            row7_cell3.setCellStyle(baseStyle);
            row7_cell3.setCellValue(rent.getOpername());

            //第九行
            row++;
            HSSFRow row8 = sheet.createRow(row);
            HSSFCell row8_cell2 = row8.createCell(2);
            row8_cell2.setCellStyle(baseStyle);
            row8_cell2.setCellValue("客户签名:");


            workbook.write(outputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return outputStream;

    }

}
