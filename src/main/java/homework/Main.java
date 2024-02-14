package homework;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class Main {

    private static SessionFactory sessionFactory;

    public static void main(String[] args) {
        sessionFactory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Courses.class).buildSessionFactory();

        Session session = sessionFactory.getCurrentSession();

        insertCourse(1, "Course 1", 12);
        Courses course = readCourse(1);
        updateCourse(8, "New2312312e 1", 231232);
        deleteCourse(5);

        sessionFactory.close();
    }

    private static void insertCourse(int id, String title, Integer dutration) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();

            Courses course = Courses.create(title, dutration);
            course.setTitle(title);
            course.setDuration(dutration);
            session.save(course);
            transaction.commit();
            System.out.println("Course inserted successfully.");
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    private static Courses readCourse(int id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        Courses course = null;
        try {
            transaction = session.beginTransaction();
            course = session.get(Courses.class, id);
            transaction.commit();
            System.out.println("Course read successfully.");
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }

        return course;
    }

    private static void updateCourse(int id, String newTitle, Integer newDuration) {
        Session session = sessionFactory.openSession();

        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();

            Courses course = session.get(Courses.class, id);
            course.setTitle(newTitle);
            course.setDuration(newDuration);

            session.update(course);

            transaction.commit();
            System.out.println("Course updated successfully.");
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    private static void deleteCourse(int id) {
        Session session = sessionFactory.openSession();

        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();

            Courses course = session.get(Courses.class, id);

            session.delete(course);

            transaction.commit();
            System.out.println("Course deleted successfully.");
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}