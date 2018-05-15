package com.devil.utils;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.log4j.Logger;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

public class ExcelData implements Iterator<Object[]> {

	public static Logger logger = Logger.getLogger(ExcelData.class);
	
	public String path;
	public Workbook workBook;
	public Sheet sheet;
	public InputStream is;
	public int rows;
	public int columnNum;
	public String[] columnName;
	public int currentRowNo;
	
	public ExcelData(String module, String caseNum) {
		logger.info("准备读取excel文件");
		try {
			path = "data/"+module+".xls";
			is = new FileInputStream(path);
			
			workBook = Workbook.getWorkbook(is);
			sheet = workBook.getSheet(caseNum);//根据用例编号获取sheet页
			rows = sheet.getRows();//获取所有的行
			Cell[] cell = sheet.getRow(0);//获取第一行的单元格
			columnName = new String[cell.length];
			columnNum = cell.length;
			for(int i = 0; i<cell.length; i++) {
				columnName[i] = cell[i].getContents().toString();
			}
			this.currentRowNo++;
			
		}catch(Exception e) {
			
		}
	}

	/**
	 * 是否还有下一行内容
	 */
	public boolean hasNext() {
		
		if(this.rows == 0 || this.currentRowNo >= this.rows) {
			try {
				is.close();
				workBook.close();
			}catch(Exception e) {
				logger.error("读取用例数据异常", e);
			}
			return false;
		}else {
			if((sheet.getRow(currentRowNo))[0].getContents().equals("")) {
				return false;
			}
			return true;
		}
	}

	
	/**
	 * 返回内容
	 */
	public Object[] next() {
		
		Cell[] cell = sheet.getRow(this.currentRowNo);
		
		Map<String, String> data = new HashMap<String, String>();
		for(int i = 0; i<this.columnNum; i++) {
			String temp = "";
			try {
				temp = cell[i].getContents().toString();
			}catch (Exception e) {
				temp = "";
			}
			data.put(this.columnName[i], temp);
			
		}
		Object object[] = new Object[1];
		
		object[0] = data;
		
		this.currentRowNo++;
		
		for(String key : data.keySet()) {
			System.out.println(key + " = " + data.get(key));
		}
		
		return object;
	}
	
	public void remove() {
		throw new UnsupportedOperationException("remove unsupported.");
	}

}
