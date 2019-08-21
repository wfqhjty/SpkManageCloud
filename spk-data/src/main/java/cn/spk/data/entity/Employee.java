package cn.spk.data.entity;

public class Employee {

    private String name;

    private String clazz;

    private Integer year;

    private Integer month;

    private Integer day;

    private double salary;

    public Employee() {
    };

    public Employee(String name, String clazz, Integer year, Integer month, Integer day,
                    double salary) {
        super();
        this.name = name;
        this.clazz = clazz;
        this.year = year;
        this.month = month;
        this.day = day;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClazz() {
        return clazz;
    }

    public void setClazz(String clazz) {
        this.clazz = clazz;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public Integer getDay() {
        return day;
    }

    public void setDay(Integer day) {
        this.day = day;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Employee [name=" + name + ", clazz=" + clazz + ", year=" + year + ", month=" + month
                + ", day=" + day + ", salary=" + salary + "]";
    }

}

