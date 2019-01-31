package test;


import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import org.apache.tomcat.util.http.fileupload.FileUtils;

import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.util.Date;

public class TestReadExcel {

    public static void main(String[] args) {
        try{
            StringBuilder result = new StringBuilder();
            StringBuilder conmontStr = new StringBuilder("INSERT INTO misc.sys_jkl_picture_temp (sku, product_name, picture_url, picture_title, type, update_username, update_time,last_handle_result) VALUES (");
            File file = new File("C:\\Users\\daienmei\\Desktop\\备份.xls");
            Workbook book = Workbook.getWorkbook(file);
            Sheet sheet = book.getSheet(0);
            System.out.println("行："+sheet.getRows());
            System.out.println("列："+sheet.getColumns());
            for(int i = 1;i<sheet.getRows(); i++){
                result.append(conmontStr);
                for(int j = 0;j < sheet.getColumns(); j++){
                    Cell cell = sheet.getCell(j,i);
                    if(j == 2){
                        result.append("'").append(cell.getContents().substring(cell.getContents().indexOf("uploads")+7)).append("',");
                    }else{
                        result.append("'").append(cell.getContents()).append("',");
                    }
                }
                result.append("'clj',").append("now(),").append("0").append(");\r\n");
            }
            System.out.println("result \r"+result.toString());

            File file1 =new File("C:\\Users\\daienmei\\Desktop\\1.txt");
            Writer out =new FileWriter(file1);
            out.write(result.toString());
            out.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
