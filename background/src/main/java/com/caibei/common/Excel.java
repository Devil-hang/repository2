package com.caibei.common;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

/**
 * 
 * @author Devil
 *使用poi.jar读取excel
 */
public class Excel {

	static InputStream is;
	static Workbook workBook;
	
	public static List<Map<String,String>> getTestData(String filePath,String moudel) {
		List<Map<String,String>> result = new ArrayList<Map<String,String>>();
		try {
//		path = System.getProperty("user.dir")+"\\data\\"+moudel+".xls";
		is = new FileInputStream(filePath);
		
		workBook = new HSSFWorkbook(is);
		Sheet sheet = workBook.getSheet(moudel);
/*
 * 读取excel数据。getLastRowNum()获取数据最后一行  getFirstNum()获取一行行号
 */
		int rowCount = sheet.getLastRowNum()-sheet.getFirstRowNum();
		
		Map<Integer,String> title = new HashMap<Integer,String>();
		
		for(int i =0; i < rowCount+1; i++) {
			
			Map<String,String> rowList = new HashMap<String,String>();
			Row row = sheet.getRow(i);
			/*
			 * 声明一个数组储存读取的数据。数组的大小用getLastCellNum-2进行动态获取
			 * excel中最后一列是执行结果，倒数第二列是执行状态。所以 -2
			 */
//			String[] strings = new String[row.getLastCellNum()-2];
				for(int j = 0;j<row.getLastCellNum()-1;j++) {
					if(getValue(row.getCell(j)).equals("")) {
						continue;
					}
					if(i == 0) {
						title.put(j, getValue(row.getCell(j)));//标题
						System.out.println("----"+i+"--------->"+getValue(row.getCell(j)));
					}else {
						String cellValue = getValue(row.getCell(j));
//						System.out.println(title.get(j)+"========="+cellValue);
						rowList.put(title.get(j), cellValue);
					}
				}
			/*
			 * 使用if判断数据是否需要执行
			 */
			if(rowList.size() != 0) {
				if(rowList.containsKey("是否执行") && rowList.get("是否执行").equals("Y")) {
					result.add(rowList);
					}
			}
			}
		
		}catch(Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 根据单元格存储的不同类型数据，或者对应类型的值
	 */

	@SuppressWarnings("deprecation")
	private static String getValue(Cell cell) {
		if (null == cell) {
			return "";
		} else if (cell.getCellType()== HSSFCell.CELL_TYPE_NUMERIC ) {
			return String.valueOf(cell.getNumericCellValue());

		} else if (cell.getCellType() == HSSFCell.CELL_TYPE_BOOLEAN ) {
			return String.valueOf(cell.getBooleanCellValue());

		} else if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING) {
			return cell.getStringCellValue();
		}
		return null;
	}
	
}

