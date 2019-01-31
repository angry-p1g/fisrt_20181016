//package com.gq.util;
//
//
//import org.apache.poi.hssf.usermodel.HSSFCell;
//import org.apache.poi.hssf.usermodel.HSSFDateUtil;
//import org.apache.poi.hssf.usermodel.HSSFWorkbook;
//import org.apache.poi.poifs.filesystem.POIFSFileSystem;
//import org.apache.poi.ss.usermodel.Cell;
//import org.apache.poi.ss.usermodel.Row;
//import org.apache.poi.ss.usermodel.Sheet;
//import org.apache.poi.ss.usermodel.Workbook;
//
//import java.io.IOException;
//import java.io.InputStream;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//
//public class ExcelReadUtil {
//    private POIFSFileSystem fs;
//    private Workbook wb;
//    private Sheet sheet;
//    private Row row;
//    private Integer totalRow = 0;
//
//    public ExcelReadUtil(InputStream is, String suffix) throws IOException {
//        try {
//            if (suffix.equals("xls")) {
//                fs = new POIFSFileSystem(is);
//                wb = new HSSFWorkbook(fs);
//            } else if (suffix.equals("xlsx")) {
////                wb = new XSSFWorkbook(is);
//            }
//        } catch (IOException e) {
//            throw new IOException("读取excel文件异常");
//        }
//    }
//
//
//    /**
//     * 读取Excel表格表头的内容
//     * @param is
//     * @return String 表头内容的数组
//     */
//    public String[] readExcelTitle(InputStream is, int titleRow) {
//        sheet = wb.getSheetAt(0);
//        row = sheet.getRow(titleRow);
//        // 标题总列数
//        int colNum = row.getPhysicalNumberOfCells();
//        System.out.println("colNum:" + colNum);
//        String[] title = new String[colNum];
//        for (int i = 0; i < colNum; i++) {
//            //title[i] = getStringCellValue(row.getCell((short) i));
//            title[i] = getCellFormatValue(row.getCell((short) i));
//        }
//        return title;
//    }
//
//    /**
//     * 根据HSSFCell类型设置数据
//     * @param cell
//     * @return
//     */
//    private String getCellFormatValue(Cell cell) {
//        String cellvalue = "";
//        if (cell != null) {
//            // 判断当前Cell的Type
//            switch (cell.getCellType()) {
//                // 如果当前Cell的Type为NUMERIC
//                case HSSFCell.CELL_TYPE_NUMERIC:
//                case HSSFCell.CELL_TYPE_FORMULA: {
//                    // 判断当前的cell是否为Date
//                    if (HSSFDateUtil.isCellDateFormatted(cell)) {
//                        // 如果是Date类型则，转化为Data格式
//
//                        //方法1：这样子的data格式是带时分秒的：2011-10-12 0:00:00
//                        //cellvalue = cell.getDateCellValue().toLocaleString();
//
//                        //方法2：这样子的data格式是不带带时分秒的：2011-10-12
//                        Date date = cell.getDateCellValue();
//                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//                        cellvalue = sdf.format(date);
//
//                    }
//                    // 如果是纯数字
//                    else {
//                        // 取得当前Cell的数值
//                        cellvalue = String.valueOf(cell.getNumericCellValue());
//                    }
//                    break;
//                }
//                // 如果当前Cell的Type为STRIN
//                case HSSFCell.CELL_TYPE_STRING:
//                    // 取得当前的Cell字符串
//                    cellvalue = cell.getRichStringCellValue().getString().trim();
//                    break;
//                // 默认的Cell值
//                default:
//                    cellvalue = " ";
//            }
//        } else {
//            cellvalue = "";
//        }
//        return cellvalue;
//
//    }
//}
