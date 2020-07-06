package org.sce.utils;

public class TestUtil {

	public static boolean verifyModuleExecutable(ExcelHelper excel, String suiteName)

	{

		boolean isExecutable = false;
		for (int x = 2; x <= excel.getRowCount("TestSuite"); x++) {
			System.out.println((excel.getCellData("TestSuite", "ModuleName", x)) + "------"
					+ (excel.getCellData("TestSuite", "Runmode", x)));

			String suite = excel.getCellData("TestSuite", "ModuleName", x);
			String suiteRunmode = excel.getCellData("TestSuite", "Runmode", x);

			if (suite.equals(suiteName)) {
				if (suiteRunmode.equalsIgnoreCase("Yes")) {
					isExecutable = true;
				} else if (suiteRunmode.equalsIgnoreCase("No")) {
					isExecutable = false;
				}

			}
		}
		excel = null;
		return isExecutable;
	}

	public static boolean verifyTestCaseExecutable(ExcelHelper excel, String testCaseName) {

		boolean isExecutable = false;

		for (int x = 2; x <= excel.getRowCount("TestCases"); x++) {
			System.out.println((excel.getCellData("TestCases", "TestCaseName", x)) + "------"
					+ (excel.getCellData("TestCases", "Runmode", x)));

			if (excel.getCellData("TestCases", "TestCaseName", x).equalsIgnoreCase(testCaseName)) {
				if (excel.getCellData("TestCases", "Runmode", x).equalsIgnoreCase("Yes")) {
					isExecutable = true;
				} else if (excel.getCellData("TestCases", "Runmode", x).equalsIgnoreCase("No")) {
					isExecutable = false;
				}

			}
		}

		return isExecutable;
	}

	public static Object[][] getdata(ExcelHelper excel, String testCaseName) {

		if (!excel.isSheetExist(testCaseName)) 
		{
			excel = null; 
			return new Object[1][0]; 						

		}
		int rows = excel.getRowCount(testCaseName);
		int columns = excel.getColumnCount(testCaseName);

		Object[][] data = new Object[rows - 1][columns];

		for (int rowNum = 2; rowNum <= rows; rowNum++) {
			for (int ColNum = 0; ColNum <= columns - 1; ColNum++) {

				data[rowNum - 2][ColNum] = excel.getCellData(testCaseName, ColNum, rowNum); 
																							
			}

		}

		return data;

	}

	public static void reportTestResult(ExcelHelper excel, String testCaseName, int rowNum, String Results) {
		excel.setCellData("TestCases", "Results", rowNum, Results);
	}

	public static int getRowNum(ExcelHelper excel, String id) {
		for (int x = 2; x <= excel.getRowCount("TestCases"); x++) {
			String tcid = excel.getCellData("TestCases", "TestCaseName", x);

			if (tcid.equalsIgnoreCase(id)) {
				excel = null;
				return x;
			}
		}

		return -1;
	}

}
