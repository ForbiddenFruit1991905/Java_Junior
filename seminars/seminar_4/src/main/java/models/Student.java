package models;

import java.util.Random;

public class Student {
    private static final String[] names = new String[]{
            "Александр", "Екатерина", "Михаил",
            "Анна", "Сергей", "Ольга",
            "Дмитрий", "Елизавета", "Иван",
            "Мария"
    };
    private static final Random random = new Random();

    private int id;
    private String name;
    private int age;

    public Student() {}

    public Student(int id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public Student(String name, int age) {
        this.age = age;
        this.name = name;
    }

    public void updateAge() {
        age = random.nextInt(20,26);
    }

    public static Student create() {
       return new Student(names[random.nextInt(names.length)], random.nextInt(20,26));
    }

    public void  updateName() {
        name = names[random.nextInt(names.length)];
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
