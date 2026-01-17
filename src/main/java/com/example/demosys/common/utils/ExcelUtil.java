package com.example.demosys.common.utils;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.ByteArrayOutputStream;

/**
 * ExcelUtil（最小版）
 * TODO：后续按你的导出模板封装 sheet/样式。
 */
public final class ExcelUtil {
    private ExcelUtil() {}

    public static byte[] emptyXlsx() {
        try (Workbook wb = new XSSFWorkbook();
             ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
            wb.createSheet("sheet1");
            wb.write(bos);
            return bos.toByteArray();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
