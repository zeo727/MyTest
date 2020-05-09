package JavaStudy.java8.testLambda1;

import JavaStudy.java8.pojo.Employee;

public class FilterEmployeesBySalary<T> implements MyPredicate<Employee> {
    @Override
    public boolean test(Employee employee) {
        return employee.getSalary()>=5000;
    }
}
