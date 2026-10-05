package utilities;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;

public class ExcelFileManager {
    private Logger log = LogManager.getLogger(ExcelFileManager.class);

    public XSSFWorkbook workbook;
    public XSSFSheet sheet;

    public ExcelFileManager(String filePath, String sheetName) {
        try {
            FileInputStream fileInputStream = new FileInputStream(filePath);
            workbook = new XSSFWorkbook(fileInputStream);
            sheet = workbook.getSheet(sheetName);
            log.info("first constructor with filePath: {} and sheetName : {}",filePath,sheetName);



        } catch (Exception e) {
            log.error("catch error in reading excel file in first constructor :{}",e);
        }

    }

    public ExcelFileManager(String filePath, int sheetIndex) {
        try {
            FileInputStream fileInputStream = new FileInputStream(filePath);
            workbook = new XSSFWorkbook(fileInputStream);
            sheet = workbook.getSheetAt(sheetIndex);
            log.info("second constructor with filePath: {} and sheetIndex : {}",
                    filePath,sheetIndex);

        } catch (Exception e) {

            log.error("catch error in reading excel file in second constructor :{}",e);
        }

    }
    public  int getRowsCount()
    {
        log.info("get rows count :{}",sheet.getPhysicalNumberOfRows());
        return  sheet.getPhysicalNumberOfRows();
    }
    public  int getColumnsCount()
    {
        log.info("get columns count :{}",sheet.getRow(0).getPhysicalNumberOfCells());

        return  sheet.getRow(0).getPhysicalNumberOfCells();
    }
    public String getFormula(int rowIndex,int columnIndex)
    {
        Cell cell=sheet.getRow(rowIndex).getCell(columnIndex);
       log.info("get formula:{}",cell.getCellFormula());
        return cell.getCellFormula();
    }
    public  String getSpecificCellValue(int rowIndex,int columnIndex)
    {
        Cell cell =sheet.getRow(rowIndex).getCell(columnIndex);
        DataFormatter dataFormatter=new DataFormatter();
        log.info("get specific cell value:{}",dataFormatter.formatCellValue(cell));
        return  dataFormatter.formatCellValue(cell);

    }


}
