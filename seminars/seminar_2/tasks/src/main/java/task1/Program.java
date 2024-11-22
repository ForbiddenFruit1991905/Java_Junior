package task1;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Program {
    /*
     * Задача 1: Основы Reflection API
     * Получите ин-ию о классе task1.Person с исп-ем Reflection API:
     * выведите на экран все поля и методы класса.
     * Создайте экземпляр класса task1.Person c исп-ем Reflection API,
     * установите значение полей и вызовите методы.
     * Реализуйте обработку исключений для обеспечения корректного
     * взаимодействия с Reflection API.
     */
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchFieldException {
        Class<?> person = Class.forName("task1.Person");
        Constructor<?>[] constructors = person.getConstructors();
        Object personInstance = constructors[0].newInstance("Vika", 33);
        System.out.println(personInstance);
        // Получить список всех полей
        Field[] fields = person.getDeclaredFields();
        for (Field field : fields) {
            System.out.println("Поля: " + field.getName());
        }
        // Получить список всех конструкторов
        Constructor<?>[] constructors1 = person.getConstructors();
        // Создаем экземпляр класса
        Object personInstance1 = constructors1[0].newInstance("Alice", 25);
        Field nameField = person.getDeclaredField("name");
        nameField.setAccessible(true);
        nameField.set(personInstance1, "Alice");
        Field ageField = person.getDeclaredField("age");
        ageField.setAccessible(true);
        ageField.set(personInstance1, 30);

        System.out.printf("Значение поля name = %s и поля age = %d после установки\n", ((Person) personInstance1).getName(), ((Person) personInstance1).getAge());

        Method toStringMethod = person.getMethod("toString");
        String result = (String) toStringMethod.invoke(personInstance1);
        System.out.println(result);
    }
}

