package Payroll_Module;

import java.io.IOException;
import java.util.List;

public class Employee_Main {

	public static void main(String args[]) {

		String filePath = "C:\\Users\\HP\\Desktop\\Payroll_Module\\Payroll_Module\\Employee_Payroll_Module\\Employee_Data_Timesheet_May.xlsx";
		try {

			List<Employees> employees = ExcelFileReader.readEmployeesFromExcel(filePath);

			Payroll_Calculation.payrollProcess(employees);
			Payroll_Calculation.printPayroll(employees);
		}

		catch (IOException e) {
			e.printStackTrace();
		}

	}

}
