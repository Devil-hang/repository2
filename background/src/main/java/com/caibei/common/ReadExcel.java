package com.caibei.common;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jxl.Sheet;
import jxl.Workbook;

/**
 * 
 * @author Devil
 *读取excel数据，并返回list/二维数组
 */
public class ReadExcel {

	public static void main(String[] args) {
		
		List<Map<String, String>> list = ReadExcel.readExcelToList("login","001");
		System.out.println(list);
	}
/**
 * 	
 * @param module
 * @param caseNumber
 * @return
 * 读取excel返回二维数组
 */
	public static Object[][] readExcel(String module, String caseNumber){
		InputStream is;
		Workbook workBook;
		Sheet sheet;
		String[][] requestData = null;
		String filePath = "data/"+module+".xls";
		try {
			is = new FileInputStream(filePath);
			workBook = Workbook.getWorkbook(is);
			sheet = workBook.getSheet(caseNumber);
			
			//初始化二维数组
			requestData = new String[sheet.getRows()][sheet.getColumns()];
			for(int i = 0; i < sheet.getRows(); i++) {
				for(int j = 0; j < sheet.getColumns();j++) {
					requestData[i][j] = sheet.getCell(j, i).getContents();
					System.out.println(i+"-->"+j+"=="+requestData[i][j]);
				}
				System.out.println("\n");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return requestData;
	}
	

	/**
	 * 
	 * @param module
	 * @param caseNumber
	 * @return
	 * 读取excel返回list
	 */
	public static List<Map<String, String>> readExcelToList(String module, String caseNumber){
		
		InputStream is;
		Workbook workBook;
		Sheet sheet;
		String filePath = "data/"+module+".xls";
		System.out.println(filePath);
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		try {
			
			is = new FileInputStream(filePath);
			workBook = Workbook.getWorkbook(is);
			sheet = workBook.getSheet(caseNumber);
			for(int i = 1; i < sheet.getRows(); i++) {
				
				Map<String, String> map = new HashMap<String, String>();
					for(int j = 0; j < sheet.getColumns(); j++) {
						
						map.put(sheet.getCell(j, 0).getContents(),sheet.getCell(j, i).getContents());
						
//						System.out.println(sheet.getCell(j, 0).getContents());
//						System.out.println(sheet.getCell(j, i).getContents());
//						System.out.println(i+"-->"+j+sheet.getCell(j, i).getContents());
					}
					list.add(map);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	
}
