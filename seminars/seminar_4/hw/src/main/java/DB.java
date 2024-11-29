import org.hibernate.Session;

public class DB {
    static final Connector connector = new Connector();
    public static void addPerson(Person person) {
        try(Session session = connector.getSession()) {
            session.beginTransaction();
            session.save(person);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
