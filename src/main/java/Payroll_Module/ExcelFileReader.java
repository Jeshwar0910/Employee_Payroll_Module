package Payroll_Module;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class ExcelFileReader {

	public static List<Employees> readEmployeesFromExcel(String filePath) throws IOException {

		List<Employees> employee_List = new ArrayList<>();

		FileInputStream fis = new FileInputStream(filePath);
		Workbook wb = new XSSFWorkbook(fis);

		// Getting Values From Sheet 1 "Employee Data"

		Sheet employeeSheet = wb.getSheet("Employee Data");
		for (Row row : employeeSheet) {
			if (row.getRowNum() == 0) { // Ignores the Heading Row
				continue;
			}

			int Emp_id = (int) row.getCell(0).getNumericCellValue();
			String Emp_name = row.getCell(1).getStringCellValue();
			String Emp_type = row.getCell(2).getStringCellValue();
			Double Emp_salary = row.getCell(3).getNumericCellValue();

			Employees employees = new Employees(Emp_id, Emp_name, Emp_type, Emp_salary);

			employee_List.add(employees);

		}
		// Getting Data From the 2nd Sheet "TimeSheet"

		Map<Integer, List<Employee_Worklog>> Emp_worklogs = new HashMap<>();

		Sheet worklogSheet = wb.getSheet("Timesheet");

		for (Row row : worklogSheet) {
			if (row.getRowNum() == 0) { // Ignores the Heading Row
				continue;
			}

			int id = (int) row.getCell(0).getNumericCellValue();
			Double hoursWorked = row.getCell(4).getNumericCellValue(); //Getting Working Hours For LOP Calculation

			Employee_Worklog worklog = new Employee_Worklog(hoursWorked);

			Emp_worklogs.computeIfAbsent(id, list -> new ArrayList<>()).add(worklog);
		}

		wb.close();
		fis.close();

		for (Employees employee_list : employee_List) {

			List<Employee_Worklog> logs = Emp_worklogs.get(employee_list.getEmp_id());

			if (logs != null) {

				for (Employee_Worklog log : logs) {
					employee_list.getTotalDaysCount();

					if (log.gethoursWorked() < 9) {
						employee_list.getEmpLateCount();
					} else if (log.gethoursWorked() >= 9) {
						employee_list.getEmpEarlyCount();

					}
				}
			}
		}

		return employee_List;

	}

}
