package task_1;

import java.io.*;

public class Program {
    public static void main(String[] args) throws IOException {
        UserData user = new UserData("V", 33, "secret");
        System.out.println(user);
        try (FileOutputStream fileOutputStream = new FileOutputStream("userdata.bin");
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) { // общий файловый поток
            objectOutputStream.writeObject(user);
            System.out.println("Объект UserData сериализован.");
        }

        try (FileInputStream fileInputStream = new FileInputStream("userdata.bin");
             ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) { // общий файловый поток
            user = (UserData)objectInputStream.readObject();
            System.out.println("Объект UserData десериализован.");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Объект UserData десериализован.");
        System.out.println("Имя: " + user.getName());
        System.out.println("Возраст: " + user.getAge());
        System.out.println("Пароль (должен быть null, так как transient): " + user.getPassword());

    }
}
