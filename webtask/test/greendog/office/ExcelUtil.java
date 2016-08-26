package greendog.office;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
/**
 * excel操作工作类
 * @author zhangqing
 * @date 2014-1-10 下午02:48:30
 */
public class ExcelUtil {

	/**
	 * 导出exel文件
	 * @author zhangqing
	 * @date 2014-1-10 下午02:35:59
	 * @param filePath 文件路径
	 * @param sheetName sheet名称
	 * @param title 导出数据的title
	 * @param data 导出数据项
	 */
	public static void exportExel(String filePath,String sheetName,List<String> title,List<List<String>> data){
		File excelFile = new File(filePath);
		try {
			WritableWorkbook workbook = Workbook.createWorkbook(excelFile);
			WritableSheet sheet = workbook.createSheet(sheetName, 0);
			int titleLenght = title.size();
			int dataLength = data.size();
			for(int i=0;i<titleLenght;i++){
				Label cell = new Label(i, 0, title.get(i));
				sheet.addCell(cell);
			}
			for(int i=1;i<=dataLength;i++){
				List<String> dataItem = data.get(i-1);
				int itemLength = dataItem.size();
				for(int j=0;j<itemLength;j++){
					Label cell = new Label(j, i, dataItem.get(j));
					sheet.addCell(cell);
				}
			}
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
	
	/**
	 * 导出exel文件
	 * @author zhangqing
	 * @date 2014-1-10 下午02:35:59
	 * @param out excel输出流
	 * @param sheetName sheet名称
	 * @param title 导出数据的title
	 * @param data 导出数据项
	 */
	public static void exportExel(OutputStream out,String sheetName,List<String> title,List<List<String>> data){
		try {
			WritableWorkbook workbook = Workbook.createWorkbook(out);
			WritableSheet sheet = workbook.createSheet(sheetName, 0);
			int titleLenght = title.size();
			int dataLength = data.size();
			for(int i=0;i<titleLenght;i++){
				Label cell = new Label(i, 0, title.get(i));
				sheet.addCell(cell);
			}
			for(int i=1;i<=dataLength;i++){
				List<String> dataItem = data.get(i-1);
				int itemLength = dataItem.size();
				for(int j=0;j<itemLength;j++){
					Label cell = new Label(j, i, dataItem.get(j));
					sheet.addCell(cell);
				}
			}
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
	
	public static void main(String [] a){
		List<String> title = new ArrayList<String>();
		title.add("学号");
		title.add("姓名");
		List<List<String>> data = new ArrayList<List<String>>();
		List<String> data1 = new ArrayList<String>();
		data1.add("1111");
		data1.add("张飞");
		data.add(data1);
		List<String> data2 = new ArrayList<String>();
		data2.add("22222");
		data2.add("对对对");
		data.add(data2);
		exportExel("d:/aa.xls", "test1", title, data);
	}
}
