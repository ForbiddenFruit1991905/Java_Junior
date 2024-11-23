package task_1;

import java.io.Serializable;

public class UserData implements Serializable {
    private String name;
    private int age;
    private transient String password;

    public UserData(String name, int age, String password) {
        this.name = name;
        this.age = age;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "name='" + name + '\'' +
                ", age=" + age +
                ", password='" + password + '\'';
    }
}
