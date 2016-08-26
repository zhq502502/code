package greendog.office;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.Number;
import jxl.write.WritableImage;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.junit.Test;

public class ExcelTest {
	
	@Test
	public void readExcel(){
		File excelFile = new File("d:/a.xls");
		try {
			Workbook workbook = Workbook.getWorkbook(excelFile);
			System.out.println(workbook.getNumberOfSheets());
			Sheet sheet = workbook.getSheet(0);
			for(int i=0;i<sheet.getRows();i++){  
	            for(int j=0;j<sheet.getColumns();j++){  
	                Cell cell=sheet.getCell(j, i);  //获得单元格  
	                System.out.print(cell.getContents()+" ");   
	            }  
	            System.out.print("\n");  
	        }  
		} catch (BiffException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void writeExcel(){
		File excelFile = new File("d:/b.xls");
		try {
			WritableWorkbook workbook = Workbook.createWorkbook(excelFile);
			/*如果是web页面导出*/
			/*OutputStream out = response.getOutputStream();
			response.reset();// 清空输出流   
			response.setHeader("Content-disposition", "attachment; filename="+new String("测试文档".getBytes(),"ISO-8859-1")+".xls");// 设定输出文件头   
			response.setContentType("application/vnd.ms-excel;charset=UTF-8");// 定义输出类型   
			WritableWorkbook workbook = Workbook.createWorkbook(out);*/
			
			WritableSheet sheet = workbook.createSheet("报表页面", 0);
			jxl.write.Number cell = new Number(1, 0, 1.00);
			WritableImage img = new WritableImage(2, 1, 3, 3, new File("E:\\work\\picture\\testnew.png"));
			sheet.addCell(cell);
			sheet.addImage(img);
			workbook.write();
			workbook.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (RowsExceededException e) {
			e.printStackTrace();
		} catch (WriteException e) {
			e.printStackTrace();
		}
	}
}
