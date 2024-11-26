package test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;

import java.util.List;

public class Db {
    private static final String URL = "jdbc:mysql://localhost:3306";
    private static final String USER = "root";
    private static final String PASSWORD = "!ForbiddenFruit19911905";

    public static void con() {
//        Заполнение базы данных
//        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
//                .configure()
//                .build();
//        SessionFactory sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
//        Session session = sessionFactory.openSession();
//        Magic magic = new Magic("Magic arrow", 10, 0);
//        session.beginTransaction();
//        session.save(magic);
//        magic = new Magic("Молния", 25, 0);
//        session.save(magic);
//        magic = new Magic("Каменная кожа", 0, 0);
//        session.save(magic);
//        magic = new Magic("Жажда крови", 0, 6);
//        session.save(magic);
//        magic = new Magic("Жажда крови", 0, 6);
//        session.save(magic);
//        magic = new Magic("Проклятие", 0, -3);
//        session.save(magic);
//        magic = new Magic("Лечение", -30, 0);
//        session.save(magic);
//        session.getTransaction().commit();
//        session.close();

        Connector connector = new Connector();
        try(Session session = connector.getSession()){

//            Вывод в консоль базы данных
//            List<Magic> books = session.createQuery("FROM Magic", Magic.class).getResultList();
//            books.forEach(System.out::println);

//            Обновление элементов в БД
//            String hql = "from Magic where id = :id";
//            Query<Magic> query = session.createQuery(hql, Magic.class);
//            query.setParameter("id", 4);
//            Magic magic = query.getSingleResult();
//            System.out.println(magic);
//            magic.setAttBonus(12);
//            magic.setName("Ярость");
//            session.beginTransaction();
//            session.update(magic);
//            session.getTransaction().commit();

            Transaction t = session.beginTransaction();
            List<Magic> magics = session.createQuery("FROM Magic",
                    Magic.class).getResultList();
            magics.forEach(m -> {
                session.delete(m);
            });
            t.commit();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
