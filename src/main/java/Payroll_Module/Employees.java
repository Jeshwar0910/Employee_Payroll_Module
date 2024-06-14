package Payroll_Module;

public class Employees {

	private int Emp_id;
	private String Emp_name;
	private String Emp_type;
	private double Emp_Salary;
	private int Emp_lateCount;
	private int Emp_EarlyCount;
	private int totalDaysCount;

	public Employees(int Emp_id, String Emp_name, String Emp_type, double Emp_Salary) {

		this.Emp_id = Emp_id;
		this.Emp_name = Emp_name;
		this.Emp_type = Emp_type;
		this.Emp_Salary = Emp_Salary;
	
	}

	public int getEmp_id() {
		return Emp_id;
	}

	public String getEmp_name() {
		return Emp_name;
	}

	public String getEmp_type() {
		return Emp_type;
	}

	public double getEmp_Salary() {
		return Emp_Salary;
	}

	public void getEmpLateCount() {
		this.Emp_lateCount++;
	}

	public void getEmpEarlyCount() {
		this.Emp_EarlyCount++;

	}

	public void getTotalDaysCount() {
		this.totalDaysCount++;
	}

	public int TotalDaysCount() {
		return totalDaysCount;
	}

	public int empLateCount() {
		return Emp_lateCount;
	}

	public void applyLossOfPay() {

		if (!Emp_type.equals("Management")) {

			int totalDays = TotalDaysCount();

			int Emp_lateCount = empLateCount();

			double lopCalculation = 31 - ((Emp_lateCount / 3) * 0.5); // 1.Month of May Contains 31days and Applying LOP
																		// of 0.5 For Instances
			double daySalaryOfEmployee = Emp_Salary / 31;
			Emp_Salary = daySalaryOfEmployee * lopCalculation;

		}

	}

}
