package com.ldy.common.util;

import org.apache.poi.hssf.usermodel.*;

import java.text.Format;
import java.util.List;
import java.util.Map;

/**
 * 描述：Excel工具类
 */
public class ExcelUtil {
    /**
     * 导出Excel
     * @param sheetName sheet名称
     * @param title 标题
     * @param values 内容
     * @param wb HSSFWorkbook对象
     * @return
     */
    public static HSSFWorkbook getHSSFWorkbook(String sheetName,String[] title,String[][] values, HSSFWorkbook wb){

        // 第一步，创建一个HSSFWorkbook，对应一个Excel文件
        if(wb == null){
            wb = new HSSFWorkbook();
        }

        // 第二步，在workbook中添加一个sheet,对应Excel文件中的sheet
        HSSFSheet sheet = wb.createSheet(sheetName);

        // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制
        HSSFRow row = sheet.createRow(0);

        // 第四步，创建单元格，并设置值表头 设置表头居中
        HSSFCellStyle style = wb.createCellStyle();
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式

        //声明列对象
        HSSFCell cell = null;

        //创建标题
        for(int i=0;i<title.length;i++){
            cell = row.createCell(i);
            cell.setCellValue(title[i]);
            cell.setCellStyle(style);
        }

        //创建内容
        for(int i=0;i<values.length;i++){
            row = sheet.createRow(i + 1);
            for(int j=0;j<values[i].length;j++){
                //将内容按顺序赋给对应的列对象
                row.createCell(j).setCellValue(values[i][j]);
            }
        }
        return wb;
    }
    /**
     * 导出Excel
     * @param sheetName sheet名称
     * @param columns 标题列结合
     * @param values 内容
     * @param wb HSSFWorkbook对象
     * @return
     */
    public static HSSFWorkbook getHSSFWorkbook(String sheetName, List<ExcelColumn> columns, List<Map<String, Object>> values, HSSFWorkbook wb){

        // 第一步，创建一个HSSFWorkbook，对应一个Excel文件
        if(wb == null){
            wb = new HSSFWorkbook();
        }

        // 第二步，在workbook中添加一个sheet,对应Excel文件中的sheet
        HSSFSheet sheet = wb.createSheet(sheetName);

        // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制
        HSSFRow row = sheet.createRow(0);

        // 第四步，创建单元格，并设置值表头 设置表头居中
        HSSFCellStyle style = wb.createCellStyle();
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式

        //声明列对象
        HSSFCell cell = null;

        //创建标题
        ExcelColumn column = null;
        for(int i=0;i<columns.size(); i++){
            column = columns.get(i);
            column.setOrder(i);
            cell = row.createCell(column.getOrder());
            cell.setCellValue(column.getTitle());
            cell.setCellStyle(style);
        }

        //创建内容
        Map<String, Object> columnValues = null;
        for(int i=0;i<values.size();i++){
            row = sheet.createRow(i + 1);
            columnValues = values.get(i);
            for(int j=0;j<columns.size();j++){
                //将内容按顺序赋给对应的列对象
                column = columns.get(j);
                row.createCell(j).setCellValue(getStringValue(column, columnValues.get(column.getProperty())));
            }
        }
        return wb;
    }

    private static String getStringValue(ExcelColumn column, Object value) {
        if(column.getFormat() != null && value != null)
            try {
                return column.getFormat().format(value);
            }catch (Exception e) {
                e.printStackTrace();
            }
        return value == null? null : String.valueOf(value);
    }
    private static Double getDoubleValue(ExcelColumn column, Object value) {
        if(column.getFormat() != null && value != null) return Double.valueOf(column.getFormat().format(value));
        return value != null?Double.valueOf(value.toString()):null;
    }
    private static Long getLongValue(ExcelColumn column, Object value) {
        if(column.getFormat() != null && value != null) return Long.valueOf(column.getFormat().format(value));
        return value != null?Long.valueOf(value.toString()):null;
    }
    private static Object getCellValue(ExcelColumn column, Object value) {
        if(column.getFormat() != null) return column.getFormat().format(value);
        return value;
    }
    /**
     * @author 01376040
     * excel列名和取值属性的映射，方便填数据查找
     */
    public static class ExcelColumn{
        private int order;
        private String title;
        private String property;
        private DataType dataType = DataType.STRING;
        private Format format;
        public ExcelColumn() {
        }
        public ExcelColumn(String title, String property) {
            this.title = title;
            this.property = property;
        }
        public ExcelColumn(String title, String property, Format format) {
            this.title = title;
            this.property = property;
            this.format = format;
        }

        private int getOrder() {
            return order;
        }
        private void setOrder(int order) {
            this.order = order;
        }

        public DataType getDataType() {
            return dataType;
        }
        public void setDataType(DataType dataType) {
            this.dataType = dataType;
        }
        public String getTitle() {
            return title;
        }
        public void setTitle(String title) {
            this.title = title;
        }
        public String getProperty() {
            return property;
        }
        public void setProperty(String property) {
            this.property = property;
        }
        public Format getFormat() {
            return format;
        }
        public void setFormat(Format format) {
            this.format = format;
        }

    }
    public enum DataType{
        STRING, NUMBER,DATE
    }
}
