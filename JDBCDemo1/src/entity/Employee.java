package entity;

public class Employee {
	private int empId;
	private String empName;	
	private String empAddress;
	private String empEmail;
	
	public Employee(int empId, String empName, String empAddress, String empEmail) {
		this.empId = empId;
        this.empName = empName;
        this.empAddress = empAddress;
        this.empEmail = empEmail;;
	}
	
	public int getEmpId() {
        return empId;
    }	
	
    public String getEmpName() {
        return empName;
    }
    
    public String getEmpAddress() {
        return empAddress;
    }
    
    public String getEmpEmail() {
        return empEmail;
    }
    
    public void setEmpId(int empId) {
        this.empId = empId;
    }
    
    public void setEmpName(String empName) {
        this.empName = empName;
    }
    
    public void setEmpAddress(String empAddress) {
        this.empAddress = empAddress;
    }
    
    public void setEmpEmail(String empEmail) {
        this.empEmail = empEmail;
    }
    
    @Override
    public String toString() {
        return "Employee [empId=" + empId + ", empName=" + empName + ", empAddress=" + empAddress + ", empEmailString=" + empEmail + "]";
    }
    
    public boolean isValidEmail() {
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        return empEmail.matches(emailRegex);
    }
}
