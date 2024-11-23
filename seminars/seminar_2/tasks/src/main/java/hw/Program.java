package hw;

import java.lang.reflect.Method;
import java.util.Arrays;

public class Program {
    /*
    Используя Reflection API, напишите программу,
    которая выводит на экран все методы класса String.
     */
    public static void main(String[] args) {
        Class<?> stringClass = StringClass.class;
        Method[] methods = stringClass.getDeclaredMethods();
        System.out.println("======================Var.1======================");
        System.out.println("Методы класса StringClass:");
        for (Method method : methods) {
            System.out.println(method.getName());
        }

        System.out.println("======================Var.2======================");
        System.out.println("Методы класса StringClass:");
        Arrays.stream(StringClass.class.getDeclaredMethods())
//                .filter(method -> method.getReturnType().equals(String.class))  // *getReturnType().equals(String.class) - выводит методы, которые возвращют стринговое значение
//                .map(method -> method.getName())
                .forEach(methodName -> System.out.println(methodName.getName() + "\n"));
    }
}
