package Utilities;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;


//xls- hssf
//xlsx- xssf
public class Excelread {
    public static String[] user;
    int high = 0;
    int medium = 0;
    int low = 0;

    public void excelRead() throws IOException {


        String home = System.getProperty("user.home");
        System.out.println(home);
        File dir = new File("/Users/edwinaz/Downloads");
        int fl = 0;
        String count = "";
        for (File file : dir.listFiles()) {
            if (file.getName().contains("xls")) {
                fl++;
                String s = file.getName();
                System.out.println(s);
            }
        }
        System.out.println(count);
        System.out.println(fl);


        int val = 2;
        String path = "/Users/edwinaz/Downloads/documentSearch_edwinaz (" + val + ").xls";
        FileInputStream fs = new FileInputStream(path);
        HSSFWorkbook workbook = new HSSFWorkbook(fs);
        //  XSSFWorkbook workbook = new XSSFWorkbook(fs);
        HSSFSheet sheet = workbook.getSheetAt(0);
        //XSSFSheet sheet = workbook.getSheetAt(0);
        Row row = sheet.getRow(3);
        Cell cell = row.getCell(1);
        int total = sheet.getLastRowNum();
        System.out.println(total);

        for (int i = 1; i <= total; i++) {
            String use = sheet.getRow(i).getCell(4).getStringCellValue();
            if (use.equals("High")) {
                high++;
            } else if (use.equals("Medium")) {
                medium++;
            } else if (use.equals("Low")) {
                low++;
            }

        }
        System.out.println("High priority bugs" + high);
        System.out.println("Medium priority bugs" + medium);
        System.out.println("Low priority bugs" + low);
    }
}
