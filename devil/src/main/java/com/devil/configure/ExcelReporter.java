package com.devil.configure;

import java.util.List;

import org.testng.IReporter;
import org.testng.ISuite;
import org.testng.xml.XmlSuite;

public class ExcelReporter implements IReporter{

	@Override
	public void generateReport(List<XmlSuite> xml, List<ISuite> suite, String str) {
		ExcelResult.creatExcelResult(xml, suite, str);
		
	}

}
