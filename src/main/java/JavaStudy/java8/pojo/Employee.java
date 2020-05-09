package JavaStudy.java8.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

//员工类

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {

    private String name;
    private Integer age;
    private Double salary;
    private Status status;

    public Employee(int age){
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Employee employee = (Employee) o;
        return age.equals(employee.age) &&
                Double.compare(employee.salary, salary) == 0 &&
                Objects.equals(name, employee.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age, salary);
    }


}


