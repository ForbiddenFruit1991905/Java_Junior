package task3;

import java.util.UUID;

public class Program {
    public static void main(String[] args) {
        Employee user = new Employee("Vika", "email@ya.ru");
        UUID pk = UUID.randomUUID();
        user.setId(pk);
    }
}
