package hibernate;

import java.util.HashSet;
import java.util.Set;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class main {

    public static void main(String[] args) {
        
        
        Book book = new Book();
        book.setTitle("Java");
        book.setPageNo(235);
        
        Publisher publisher = new Publisher();
        publisher.setName("ITA");
        publisher.setAddress("High Street");
        
        book.setPublisher(publisher);
        
        Set<Book> books = new HashSet(){{
            add(book);
            
        }};
        publisher.setBooks(books);
        

        Session session = HibernateUtil.createSessionFactory().openSession();

        Transaction tx = null;
        
       

            try {
         tx = session.beginTransaction();
         session.persist(publisher);
       

      tx.commit();
           } catch (HibernateException e) {
        if (tx != null) {
      tx.rollback();
     }
      System.out.println(e);
       } finally {
          HibernateUtil.close();
      }
   
    }

}
