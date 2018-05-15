package com.devil.configure;

import java.io.File;
import java.io.FileOutputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.RegionUtil;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFDataFormat;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.ISuite;
import org.testng.ISuiteResult;
import org.testng.ITestContext;
import org.testng.ITestNGMethod;
import org.testng.xml.XmlSuite;


/**
 * 创建Excel报告
 * @author Devil
 *
 */

public class ExcelResult {

	public static Logger logger = Logger.getLogger(ExcelResult.class);
	//excel文件
	static File excel;
	//写入文件流
	static FileOutputStream fos;
	//excel
	 static XSSFWorkbook workBook;
	//log储存位置
	 static File logDir = new File("./results/log/");
	
	public static void creatExcelResult(List<XmlSuite> xml, List<ISuite> suites, String str) {
		//定义报告的储存目录
		String reporterDir = str + File.separator + "reporter";
		File fileDir = new File(reporterDir);
		if(!fileDir.exists()) {
			fileDir.mkdir();
		}
		//excel文件名日期格式
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd_HH-mm");
		//Excel运行时间格式
		SimpleDateFormat runTime = new SimpleDateFormat("yyyy-MM-dd_HH");
		//excel报告中开始、结束时间格式
		SimpleDateFormat startAndEndTime = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
		str = reporterDir + File.separator + sdf.format(new Date()) + "-" + suites.get(0).getName() + ".xlsx";
		excel = new File(str);
		
		try {
			//创建Excel
			workBook = new XSSFWorkbook();
			//设置Excel自动计算
			workBook.setForceFormulaRecalculation(true);
			//sheet名称
			XSSFSheet sheet = workBook.createSheet("汇总");
			//合并单元格
			sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 5));
			XSSFRow row = sheet.createRow(0);
			XSSFCell cell = row.createCell(0);
			cell.setCellValue(new XSSFRichTextString("AUTO Test Reporter"));
			
			//运行日期文本
			XSSFRow row2 = sheet.createRow(1);
			XSSFCell cell2 = row2.createCell(0);
			cell2.setCellValue(new XSSFRichTextString("Run Time"));
			
			//运行日期
			XSSFCell cell3 = row2.createCell(1);
			cell3.setCellValue(runTime.format(new Date()));
			
			//项目名称文本
			XSSFCell cell4 = row2.createCell(2);
			cell4.setCellValue(new XSSFRichTextString("Project Name"));
			cell4.setCellStyle(setCell0(workBook));
			
			//合并单元格
			sheet.addMergedRegion(new CellRangeAddress(1, 1, 3, 5));
			
			//项目名称
			XSSFCell cell5 = row2.createCell(3);
			setRangeCellBorder1(new CellRangeAddress(1, 1, 3, 5), sheet, workBook);
			cell5.setCellValue(suites.get(0).getName());
			
			//开始时间
			Date startTime = null;
			Date endTime = null;

			//用例总数文本
			XSSFRow row4 = sheet.createRow(3);
			row4.createCell(0).setCellValue(new XSSFRichTextString("Case Number"));
			
			//通过用例总数
			row4.createCell(2).setCellValue(new XSSFRichTextString("Pass Case Number"));
			
			//失败用例总数
			row4.createCell(2).setCellValue(new XSSFRichTextString("Failed Case Number"));

			//测试模块
			sheet.addMergedRegion(new CellRangeAddress(4, 4, 0, 5));
			XSSFRow row5 = sheet.createRow(4);
			row5.createCell(0).setCellValue(new XSSFRichTextString("Test Module"));
			
			XSSFRow row6 = sheet.createRow(5);
			String str2[] = {"NO","Module Name","Case Number","Success Case","Failed Case","Pass rate"};
			for(int i = 0; i < 6; i++) {
				XSSFCell cell0 = row6.createCell(i);
				cell0.setCellValue(new XSSFRichTextString(str2[i]));
				cell0.setCellStyle(setCell0(workBook));
			}
			
			//获取模块名称
	    	for (ISuite suite : suites){
	    		Map<String, ISuiteResult> tests = suite.getResults();
	    		//多少个tests
	    		int testNum = tests.values().size();
	    		int totalCases[] = new int[testNum];
	    		int successCases[] = new int[testNum];
	    		int failCases[] = new int[testNum];
	    		int skipCases[] = new int[testNum];
	    		//定义计数器，来循环存用例总数、成功条数、失败条数、跳过条数等
	    		int temp = 0;
				
	    		for (ISuiteResult r : tests.values()) {//这里循环的是每个tests
		    		
					//ITestContext获取各个test的 内容
	    			ITestContext overview = r.getTestContext();
	    				//把各个test的总数，成功的、失败的、跳过的存入数组
	    				totalCases[temp] = overview.getAllTestMethods().length;
	    				successCases[temp] = overview.getPassedTests().size();
	    				failCases[temp] = overview.getFailedTests().size();
	    				skipCases[temp] = overview.getSkippedTests().size();
	    				if(temp==0){
	    					startTime = overview.getStartDate();
	    				}
	    				if(temp==(testNum-1)){
	    				endTime= overview.getEndDate();
	    				}
	    				
	    				temp++;
					
				//模块名称
					workBook.createSheet(overview.getName());
				//获取新建的sheet对象
					XSSFSheet sheet2 = workBook.getSheet(overview.getName());
					//创建第一行
					XSSFRow newRow = sheet2.createRow(0);
					sheet2.addMergedRegion(new CellRangeAddress(0, 0, 0, 4));
					newRow.createCell(0).setCellValue(new XSSFRichTextString(overview.getName()+"模块测试详情:"));
					XSSFRow  newRow2 = sheet2.createRow(1);
					String newStr[] = {"NO","CaseName","TestResult","Log","Picture"};
					for(int j = 0; j < 5; j++) {
						XSSFCell newCell = newRow2.createCell(j);
						newCell.setCellValue(new XSSFRichTextString(newStr[j]));
					}
				}
	    		
				for(int k = 0; k < testNum; k++) {
					XSSFRow row3 = sheet.createRow(6+k);
					for(int l = 0; l < 6; l++) {
						XSSFCell cell6 = row3.createCell(l);
						switch (l) {
						case 1:
							cell6.setCellValue(new XSSFRichTextString(workBook.getSheetName(k+1)));//设置模块名称
							cell6.setCellStyle(setClickableLinkToBlueColor1(workBook));
							cell6.setCellFormula("HYPERLINK(\"" + "#"+workBook.getSheetName(k+1)+"!A1"+ "\",\"" +workBook.getSheetName(k+1)+ "\")");//设置跳转链接
							break;
						case 2:
							cell6.setCellValue(totalCases[k]);//设置用例总数
							break;
						case 3:
							cell6.setCellValue(successCases[k]);//设置成功的用例
							break;
						case 4:
							cell6.setCellValue(failCases[k]);//设置失败的用例
							break;
						case 5:
							cell6.setCellValue(getPercent1((float)successCases[k], (float)totalCases[k]));//设置通过率
							break;

						}
					}
				}
			}
			for(int i = 2; i < 4; i++) {
				XSSFRow row3 = sheet.createRow(i);
				for( int j = 0; j < 6;j++) {
					XSSFCell cell6 = row3.createCell(j);
					String str1[] = {"Start Time",startAndEndTime.format(startTime),"End Time",startAndEndTime.format(endTime),"Use Time",""};
					String str3[] = {"Case Total","","Pass Case","","Failed Case",""};
					if(i == 2) {
						cell6.setCellValue(str1[j]);
						if(j == 0 || j == 2 || j == 4) {
							cell.setCellStyle(setCell0(workBook));
						}
						if (j == 5) {
							cell6.setCellFormula("D3-B3");//使用时间
						    XSSFCellStyle cellStyle = workBook.createCellStyle();
	        	            XSSFDataFormat format= workBook.createDataFormat();
	        	            cellStyle.setDataFormat(format.getFormat("HH:mm:ss"));
//	        	            setCellBorder(workBook, cellStyle);
	        	            cell.setCellStyle(cellStyle);
						}
					} else if (i == 3) {
						cell.setCellValue(str3[j]);
						if(j==1){
	        				//用例总数
	        				cell.setCellFormula("SUM(C7:C1000)");
	        	            XSSFCellStyle cellStyle = workBook.createCellStyle();
//	        				setCellBorder(workBook, cellStyle);
	        				cell.setCellStyle(cellStyle);
	        			}
	        			if(j==3){
	        				//通过用例总数
	        				cell.setCellFormula("SUM(D7:D1000)");	
	         	            XSSFCellStyle cellStyle = workBook.createCellStyle();
	                		cell.setCellStyle(cellStyle);
	        			}
	        			if(j==5){
	        				//失败用例总数
	        				cell.setCellFormula("SUM(E7:E1000)");
	         	            XSSFCellStyle cellStyle = workBook.createCellStyle();
//	                		setCellBorder(workbook, cellStyle);
	                		cell.setCellStyle(cellStyle);
	        			}
					}
				}
			}
			
	        //以下处理每个模块详情
	    	for (ISuite suite2 : suites){
	    		//获取当前suite的运行结果
	    		Map<String, ISuiteResult> tests = suite2.getResults();
	    		//多少个tests
	    		int testNum = tests.values().size();
	    		//用例总数数组
	    		int totalCases[] = new int[testNum];
	  
	    		//定义计数器，来循环存用例总数。
	    		int temp = 0;
	    		for (ISuiteResult r : tests.values()) {//这里循环的是每个tests
					//ITestContext获取各个test的 内容
	    			ITestContext overview = r.getTestContext();
	    				//把各个test的总数存入数组
	    				totalCases[temp] = overview.getAllTestMethods().length;
	    				temp++;
	    		}
	    		
	    		
	    		for (int k = 0; k < testNum; k++) {//test的个数作为外层循环
	    			
	    			for (int k2 = 0; k2 < totalCases[k]; k2++) {//以每个test下有多少个用例来做循环
	    				//开始创建行,从第三行开始，所以是k2+2
	    				XSSFRow functionRow = workBook.getSheetAt(k+1).createRow(k2+2) ;			
	    				for (int l = 0; l < 5; l++) {//以每个单元格来作为循环，5个标签
	    					XSSFCell functionCell = functionRow.createCell(l);
	    					if(l==0){
	    					//生成序号
	    						functionCell.setCellValue(k2+1);
//	    						functionCell.setCellStyle(setContentCellBorder(workBook));
	    					}
						}			
					}
					
				}
	    		
	    		int count = 0;
				//处理 “用例名字”、“完整日志”和“截图”
		   		for (ISuiteResult r : tests.values()) {//多少个模块第一层循环
	    			ITestContext overview = r.getTestContext();
	    			String function = overview.getName();
	    			int failTestNum = overview.getFailedTests().size();
	    			int skipTestNum = overview.getSkippedTests().size();
	    			int passTestNum = overview.getPassedTests().size();
	    			
	    			
	    			if(failTestNum!=0){//处理失败的
	    				XSSFCellStyle xcs = workBook.createCellStyle();
					 for (ITestNGMethod im : overview.getFailedTests().getAllMethods()) {	//模块下有多少个用例循环
	 					String caseName = im.getTestClass().getRealClass().getSimpleName();
	 					String functionPack = caseName.substring(0, caseName.indexOf("P")).toLowerCase();
	 					//设置用例名字
	 					workBook.getSheet(function).getRow(count+2).getCell(1).setCellValue(new XSSFRichTextString(caseName));
						//设置用例运行的状态
	 					workBook.getSheet(function).getRow(count+2).getCell(2).setCellValue(new XSSFRichTextString("Failed"));
						//设置log，这是一个超链接
	 					workBook.getSheet(function).getRow(count+2).getCell(3).setCellFormula
						("HYPERLINK(\"" +new File("result/log/"+functionPack+"/"+caseName+".log").getAbsolutePath()+ "\",\"" +caseName+ ".log\")");
						xcs.setFillForegroundColor((short) 10);// 设置背景色 -红色
//						xcs.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
						//设置背景色为红色，将此style应用成功
//						setCellBorder(workBook, xcs);
						//用例名称、测试结果、截图和日志分别设置样式
						workBook.getSheet(function).getRow(count+2).getCell(2).setCellStyle(xcs);
//						workBook.getSheet(function).getRow(count+2).getCell(1).setCellStyle(setContentCellBorder(workBook));
						workBook.getSheet(function).getRow(count+2).getCell(3).setCellStyle(setClickableLinkToBlueColor1(workBook));
				        //添加截图
				        File png = new File("result/screenshot/"+caseName+".png");
				        if(png.exists()){
				        	//设置截图图片，这是超链接
				        	workBook.getSheet(function).getRow(count+2).getCell(4).setCellFormula
							("HYPERLINK(\"" +new File("result/screenshot/"+caseName+".png").getAbsolutePath()+ "\",\"" +caseName+ ".png\")");
				        }else{
				        	workBook.getSheet(function).getRow(count+2).getCell(4).setCellValue(new XSSFRichTextString("此用例没有截图"));
				        }
				        workBook.getSheet(function).getRow(count+2).getCell(4).setCellStyle(setClickableLinkToBlueColor1(workBook));
			
				      
						count++;
					}
					
	    			}
	    			count=0;
	    			
	    			if(skipTestNum!=0){//处理跳过的
	    				XSSFCellStyle xcs = workBook.createCellStyle();
	   				 for (ITestNGMethod im : overview.getSkippedTests().getAllMethods()) {	//模块下有多少个用例循环
	   					String caseName = im.getTestClass().getRealClass().getSimpleName();
	   					String functionPack = caseName.substring(0, caseName.indexOf("P")).toLowerCase();
	   					workBook.getSheet(function).getRow(count+2+failTestNum).getCell(1).setCellValue(new XSSFRichTextString(caseName));
	   					workBook.getSheet(function).getRow(count+2+failTestNum).getCell(2).setCellValue(new XSSFRichTextString("Skipped"));
	   					workBook.getSheet(function).getRow(count+2+failTestNum).getCell(3).setCellFormula
						("HYPERLINK(\"" +new File("result/log/"+functionPack+"/"+caseName+".log").getAbsolutePath()+ "\",\"" +caseName+ ".log\")");
						xcs.setFillForegroundColor((short) 13);// 设置背景色 -黄色
//						setCellBorder(workBook, xcs);
//						workBook.getSheet(function).getRow(count+2+failTestNum).getCell(1).setCellStyle(setContentCellBorder(workBook));
						workBook.getSheet(function).getRow(count+2+failTestNum).getCell(3).setCellStyle(setClickableLinkToBlueColor1(workBook));
//						xcs.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
						workBook.getSheet(function).getRow(count+2+failTestNum).getCell(2).setCellStyle(xcs);
				        File png = new File("result/screenshot/"+caseName+".png");
				        if(png.exists()){
				        	workBook.getSheet(function).getRow(count+2+failTestNum).getCell(4).setCellFormula
						("HYPERLINK(\"" +new File("result/screenshot/"+caseName+".png").getAbsolutePath()+ "\",\"" +caseName+ ".png\")");
				        }else{
				        	workBook.getSheet(function).getRow(count+2+failTestNum).getCell(4).setCellValue(new XSSFRichTextString("此用例没有截图"));
				        }
				        workBook.getSheet(function).getRow(count+2+failTestNum).getCell(4).setCellStyle(setClickableLinkToBlueColor1(workBook));
						
	 					count++;
	 				}
	    			}
	    			count=0;
	    			
	    			if(passTestNum!=0){//处理成功的
	    				XSSFCellStyle xcs = workBook.createCellStyle();
	      				 for (ITestNGMethod im : overview.getPassedTests().getAllMethods()) {	//模块下有多少个用例循环
	      					String caseName = im.getTestClass().getRealClass().getSimpleName();
	      					String functionPack = caseName.substring(0, caseName.indexOf("P")).toLowerCase();
	      					workBook.getSheet(function).getRow(count+2+failTestNum+skipTestNum).getCell(1).setCellValue(new XSSFRichTextString(caseName));
	      					workBook.getSheet(function).getRow(count+2+failTestNum+skipTestNum).getCell(2).setCellValue(new XSSFRichTextString("Passed"));
	      					workBook.getSheet(function).getRow(count+2+failTestNum+skipTestNum).getCell(3).setCellFormula
	    					("HYPERLINK(\"" +new File("result/log/"+functionPack+"/"+caseName+".log").getAbsolutePath()+ "\",\"" +caseName+ ".log\")");
	    					xcs.setFillForegroundColor((short) 57);// 设置背景色 -绿色
//	    					setCellBorder(workBook, xcs);
//	    					workBook.getSheet(function).getRow(count+2+failTestNum+skipTestNum).getCell(1).setCellStyle(setContentCellBorder(workBook));
	    					workBook.getSheet(function).getRow(count+2+failTestNum+skipTestNum).getCell(3).setCellStyle(setClickableLinkToBlueColor1(workBook));
//	    					xcs.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
	    					workBook.getSheet(function).getRow(count+2+failTestNum+skipTestNum+skipTestNum).getCell(2).setCellStyle(xcs);
	    					workBook.getSheet(function).getRow(count+2+failTestNum+skipTestNum).getCell(4).setCellStyle(setClickableLinkToBlueColor1(workBook));
	    	    			count++;
	    				}
	       			}
	    			count=0;
	    		
	    		}
	    		
	    		}
	    	
	    	    	int sheetNum = workBook.getNumberOfSheets();
	    	for (int i = 0; i < sheetNum; i++) {
	    		if(i==0){
	    			//i=0的时候表示 第一个sheet就是汇总setColumnWidth(0, 4*512)参数讲解：0表示第一列；4表示有多个个字符，比如你这个cell有4个字，那么就是四个字符；
	    			//乘以512是什么意思？如果你是4个汉字，那么列宽就是4*512，如果你是4个数字或者英文字符，那么就是4*256。
	    			workBook.getSheetAt(i).setColumnWidth(0, 4*512);
	    			workBook.getSheetAt(i).setColumnWidth(1, 20*256);
	    			workBook.getSheetAt(i).setColumnWidth(2, 6*512);
	    			workBook.getSheetAt(i).setColumnWidth(3, 20*256);
	    			workBook.getSheetAt(i).setColumnWidth(4, 6*512);
	    			workBook.getSheetAt(i).setColumnWidth(5, 9*256);
	    		}else{
	    			//i>0的时候表示其他模块sheet
	    			workBook.getSheetAt(i).setColumnWidth(0, 2*512);
	    			workBook.getSheetAt(i).setColumnWidth(1, 32*256);
	    			workBook.getSheetAt(i).setColumnWidth(2, 4*512);
	    			workBook.getSheetAt(i).setColumnWidth(3, 40*256);
	    			workBook.getSheetAt(i).setColumnWidth(4, 40*256);
	    		}
				
			}
	   
	        // 工作薄建立完成，下面将工作薄存入文件
				fos = new FileOutputStream(excel);
	        // 把相应的Excel 工作簿存盘
				workBook.write(fos);
				fos.flush();
				fos.close();
				logger.info("Excel报告已经生成，请查看：["+excel.getAbsolutePath()+"]");
		    }catch(Exception e){
		    	e.printStackTrace();
		    }
		}
			
			
	
		/**计算百分比*/
		public static String getPercent1(float x,float total){
			   String result="";//接受百分比的值
			   float tempresult=x/total;
			   DecimalFormat df1 = new DecimalFormat("0.00%");    //##.00%   百分比格式，后面不足2位的用0补齐
			   result= df1.format(tempresult);  
			   return result;
			}
		
		/**设置汇总sheet页*/
		public static XSSFCellStyle setCell0(XSSFWorkbook workbook){
			XSSFCellStyle xssfCellStyle = workbook.createCellStyle();
			XSSFFont font = workbook.createFont();
			font.setFontName("微软雅黑");
	        font.setColor((short)8);
	        font.setFontHeightInPoints((short) 11);    
			xssfCellStyle.setFont(font);
			XSSFColor xssfColor = new XSSFColor();
			byte[] rgb = {(byte) 244,(byte)164,(byte)96};
			xssfColor.setRGB(rgb);
	        xssfCellStyle.setFillForegroundColor(xssfColor);
			return xssfCellStyle;
		} 
		
		/**设置可以点击的部分为蓝色字体*/
		public static XSSFCellStyle setClickableLinkToBlueColor1(XSSFWorkbook workbook){
			XSSFCellStyle xssfCellStyle = workbook.createCellStyle();
			XSSFFont font = workbook.createFont();
			XSSFColor xssfColor = new XSSFColor();
			byte[] rgb = {0,0,(byte) 255};//纯蓝色
			xssfColor.setRGB(rgb);
			font.setColor(xssfColor);
			xssfCellStyle.setFont(font);
			return xssfCellStyle;
			
		}
		
		/**合并单元格之后设置边框 - 合并之后的单元格必须用这种方式设置边框*/
		@SuppressWarnings("deprecation")
		public static void setRangeCellBorder1(CellRangeAddress cellRangeAddress, XSSFSheet sheet,XSSFWorkbook wb) {
			RegionUtil.setBorderLeft(1, cellRangeAddress, sheet);
			RegionUtil.setBorderBottom(1, cellRangeAddress, sheet);
			RegionUtil.setBorderRight(1, cellRangeAddress, sheet);
			RegionUtil.setBorderTop(1, cellRangeAddress, sheet);
			
		}
	
}

