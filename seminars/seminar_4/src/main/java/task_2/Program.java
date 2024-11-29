package task_2;

import com.mysql.cj.xdevapi.Session;
import com.mysql.cj.xdevapi.SessionFactory;
import models.Student;


import javax.security.auth.login.Configuration;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class Program {
    public static void main(String[] args) {
        /*
        Настроить Hibernate, связав его с вашей БД.
        Создать класс Student в Java, аннотируя его как сущность Hibernate.
        Используя Hibernate, реализовать вставку, чтение, обновление и
        удаление данных в таблице students.
        Обратить внимание на использование сессий и транзакций в Hibernate.
         */

        try (SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory()) {
            // Создание сессии
            Session session = sessionFactory.getCurrentSession();
            // Начало транзакции
            session.beginTransaction();
            // Создание объекта
            Student student = Student.create();
            session.save(student);
            System.out.println("Object student save successfully");
            // Чтение объекта из БД
            Student retrievedStudent = session.get(Student.class, student.getId());
            System.out.println("Object student retrieved successfully");
            System.out.println("Retrieved student object: " + retrievedStudent);
            // Обновление объекта
            retrievedStudent.updateName();
            retrievedStudent.updateAge();
            session.update(retrievedStudent);
            System.out.println("Object student update successfully");
            
            session.delete(retrievedStudent);

            session.getTransaction().commit();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
