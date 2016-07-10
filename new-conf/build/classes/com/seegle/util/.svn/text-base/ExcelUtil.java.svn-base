package com.seegle.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
/**
 * excel操作类
 * @author zhangqing
 * @date 2014-2-19 上午11:14:40
 */
public class ExcelUtil {
	/**
	 * 通过文件绝对路劲读取excel的内容
	 * @author zhangqing
	 * @date 2014-2-19 上午11:14:58
	 * @param fileName
	 * @return
	 */
	public static List<List<String>> readExcelByFile(String fileName,int sheetNumber){
		File excelFile = new File(fileName);
		List<List<String>> list = new ArrayList<List<String>>();
		try {
			Workbook workbook = Workbook.getWorkbook(excelFile);
			Sheet sheet = workbook.getSheet(sheetNumber);
			for(int i=0;i<sheet.getRows();i++){  
				List<String> row = new ArrayList<String>();
	            for(int j=0;j<sheet.getColumns();j++){  
	                Cell cell=sheet.getCell(j, i);  //获得单元格  
	                row.add(cell.getContents());
	            }  
	            list.add(row);
	        }  
		} catch (BiffException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return list;
	}
	/**
	 * 通过流读取excel文件内容
	 * @author zhangqing
	 * @date 2014-2-19 上午11:15:28
	 * @param stream
	 * @return
	 */
	public static List<List<String>> readExcelByStream(InputStream stream,int sheetNumber){
		List<List<String>> list = new ArrayList<List<String>>();
		try {
			Workbook workbook = Workbook.getWorkbook(stream);
			Sheet sheet = workbook.getSheet(sheetNumber);
			for(int i=0;i<sheet.getRows();i++){  
				List<String> row = new ArrayList<String>();
	            for(int j=0;j<sheet.getColumns();j++){  
	                Cell cell=sheet.getCell(j, i);  //获得单元格  
	                row.add(cell.getContents());
	            }  
	            list.add(row);
	        }  
		} catch (BiffException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return list;
	}
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
}
