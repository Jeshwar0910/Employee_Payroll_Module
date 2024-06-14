package Payroll_Module;

import java.util.List;

public class Payroll_Calculation {

	public static void payrollProcess(List<Employees> employees) {

		for (Employees employee : employees) {

			employee.applyLossOfPay();

		}
	}

	public static void printPayroll(List<Employees> employees) {

		for (Employees employee : employees) {

			System.out.printf("Emp_ID: %d, Emp_Name: %s, Emp_Final Salary: %.2f%n", employee.getEmp_id(), employee.getEmp_name(),
					employee.getEmp_Salary());
		}

	}

}
