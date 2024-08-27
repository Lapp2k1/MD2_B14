package BT3;

import java.util.Objects;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.Comparator;

 class Department {
    private String departmentId;
    private String departmentName;
    private int totalMembers;

    public Department(String departmentId, String departmentName) {
        this.departmentId = departmentId;
        this.departmentName = departmentName;
        this.totalMembers = 0;
    }

    // Getters and Setters

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public int getTotalMembers() {
        return totalMembers;
    }

    public void setTotalMembers(int totalMembers) {
        this.totalMembers = totalMembers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Department that = (Department) o;
        return departmentId.equals(that.departmentId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(departmentId);
    }

    @Override
    public String toString() {
        return "Department{" +
                "departmentId='" + departmentId + '\'' +
                ", departmentName='" + departmentName + '\'' +
                ", totalMembers=" + totalMembers +
                '}';
    }
}

class Employee {
    private String employeeId;
    private String employeeName;
    private LocalDate birthday;
    private boolean sex; // true for male, false for female
    private double salary;
    private Employee manager;
    private Department department;

    public Employee(String employeeId, String employeeName, LocalDate birthday, boolean sex, double salary, Department department) {
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.birthday = birthday;
        this.sex = sex;
        this.salary = salary;
        this.department = department;
        this.manager = null;
    }

    // Getters and Setters

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public boolean isSex() {
        return sex;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public Employee getManager() {
        return manager;
    }

    public void setManager(Employee manager) {
        this.manager = manager;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public int getAge() {
        return Period.between(birthday, LocalDate.now()).getYears();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return employeeId.equals(employee.employeeId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(employeeId);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "employeeId='" + employeeId + '\'' +
                ", employeeName='" + employeeName + '\'' +
                ", birthday=" + birthday +
                ", sex=" + sex +
                ", salary=" + salary +
                ", manager=" + (manager != null ? manager.getEmployeeName() : "None") +
                ", department=" + department.getDepartmentName() +
                '}';
    }
}


 class DepartmentManagement {
    private List<Department> departments = new ArrayList<>();

    public void addDepartment(Department department) {
        if (departments.stream().noneMatch(d -> d.getDepartmentId().equals(department.getDepartmentId()))) {
            departments.add(department);
        } else {
            System.out.println("Department ID already exists.");
        }
    }

    public void editDepartmentName(String departmentId, String newName) {
        departments.stream()
                .filter(d -> d.getDepartmentId().equals(departmentId))
                .findFirst()
                .ifPresent(d -> d.setDepartmentName(newName));
    }

    public void displayDepartments() {
        departments.forEach(System.out::println);
    }

    public void deleteDepartment(String departmentId) {
        Optional<Department> department = departments.stream()
                .filter(d -> d.getDepartmentId().equals(departmentId))
                .findFirst();

        if (department.isPresent() && department.get().getTotalMembers() == 0) {
            departments.remove(department.get());
        } else {
            System.out.println("Department cannot be deleted. Either it does not exist or it has members.");
        }
    }

    public void displayEmployeesByDepartment(String departmentId, List<Employee> employees) {
        List<Employee> departmentEmployees = employees.stream()
                .filter(e -> e.getDepartment().getDepartmentId().equals(departmentId))
                .collect(Collectors.toList());

        departmentEmployees.forEach(System.out::println);
    }
}


class EmployeeManagement {
    private List<Employee> employees = new ArrayList<>();

    public void addEmployee(Employee employee) {
        if (employees.stream().noneMatch(e -> e.getEmployeeId().equals(employee.getEmployeeId()))) {
            employees.add(employee);
            employee.getDepartment().setTotalMembers(employee.getDepartment().getTotalMembers() + 1);
        } else {
            System.out.println("Employee ID already exists.");
        }
    }

    public void editEmployee(String employeeId, String newName, LocalDate newBirthday, boolean newSex, double newSalary) {
        employees.stream()
                .filter(e -> e.getEmployeeId().equals(employeeId))
                .findFirst()
                .ifPresent(e -> {
                    e.setEmployeeName(newName);
                    e.setBirthday(newBirthday);
                    e.setSex(newSex);
                    e.setSalary(newSalary);
                });
    }

    public void deleteEmployee(String employeeId) {
        Optional<Employee> employee = employees.stream()
                .filter(e -> e.getEmployeeId().equals(employeeId))
                .findFirst();

        if (employee.isPresent()) {
            employees.remove(employee.get());
            employee.get().getDepartment().setTotalMembers(employee.get().getDepartment().getTotalMembers() - 1);
        } else {
            System.out.println("Employee not found.");
        }
    }

    public void displayAllEmployees() {
        employees.forEach(e -> System.out.println(e.getEmployeeId() + " - " + e.getEmployeeName()));
    }

    public void displayEmployeeDetails(String employeeId) {
        employees.stream()
                .filter(e -> e.getEmployeeId().equals(employeeId))
                .findFirst()
                .ifPresent(System.out::println);
    }

    public void printAverageNumberOfEmployeesPerDepartment() {
        employees.stream()
                .collect(Collectors.groupingBy(e -> e.getDepartment().getDepartmentId(), Collectors.counting()))
                .forEach((deptId, count) -> System.out.println("Department ID: " + deptId + " - Average: " + count));
    }

    public void printTop5DepartmentsByMembers() {
        employees.stream()
                .collect(Collectors.groupingBy(e -> e.getDepartment().getDepartmentId(), Collectors.counting()))
                .entrySet().stream()
                .sorted((e1, e2) -> e2.getValue().compareTo(e1.getValue()))
                .limit(5)
                .forEach(e -> System.out.println("Department ID: " + e.getKey() + " - Members: " + e.getValue()));
    }

    public void printTopManagerByEmployees() {
        employees.stream()
                .filter(e -> e.getManager() != null)
                .collect(Collectors.groupingBy(e -> e.getManager().getEmployeeId(), Collectors.counting()))
                .entrySet().stream()
                .max(Comparator.comparingLong(e -> e.getValue()))
                .ifPresent(e -> System.out.println("Manager ID: " + e.getKey() + " - Employees: " + e.getValue()));
    }

    public void printTop5OldestEmployees() {
        employees.stream()
                .sorted(Comparator.comparingInt(Employee::getAge).reversed())
                .limit(5)
                .forEach(System.out::println);
    }

    public void printTop5HighestPaidEmployees() {
        employees.stream()
                .sorted(Comparator.comparingDouble(Employee::getSalary).reversed())
                .limit(5)
                .forEach(System.out::println);
    }
}


public class Main {
    public static void main(String[] args) {
        DepartmentManagement departmentManagement = new DepartmentManagement();
        EmployeeManagement employeeManagement = new EmployeeManagement();

        Department itDepartment = new Department("D001", "IT");
        Department hrDepartment = new Department("D002", "HR");

        departmentManagement.addDepartment(itDepartment);
        departmentManagement.addDepartment(hrDepartment);

        Employee emp1 = new Employee("E0001", "John Doe", LocalDate.of(1985, 5, 20), true, 5000, itDepartment);
        Employee emp2 = new Employee("E0002", "Jane Smith", LocalDate.of(1990, 3, 15), false, 6000, itDepartment);

        employeeManagement.addEmployee(emp1);
        employeeManagement.addEmployee(emp2);

        departmentManagement.displayDepartments();
        employeeManagement.displayAllEmployees();
        employeeManagement.printTop5OldestEmployees();
        employeeManagement.printTop5HighestPaidEmployees();

        // Add more operations as needed
    }
}

