package persistence;

import entity.Product;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Shea Prewett on 6/19/17.
 */
public class ProductDAO {

    /** Return a list of all products
     *
     * @return All products in List form
     */
    public List<Product> getAllProducts() {
        List<Product> products;
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        products = session.createCriteria(Product.class).list();
        return products;
    }

    /** Insert a new product and return its id if successful
     *
     * @param name the name of the product
     * @param price the price of the product in USD
     * @return product's id if successful insert
     */
    public int insertProduct(String name, double price) {
        Product newProduct = new Product();
        newProduct.setName(name);
        newProduct.setPrice(price);

        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        Transaction transaction = null;


        int newProductID = 0;
        try {
            transaction = session.beginTransaction();
            newProductID = (Integer)session.save(newProduct);
        } catch(RuntimeException e) {
            // Check if null to prevent null-pointer exceptions on rollback
            if (transaction != null) {
                transaction.rollback();
            }
            // Make sure ID is set to 0
            newProductID = 0;
        } finally {
            //Make sure to commit any changes, then close the session
            transaction.commit();
            session.flush();
            session.close();
        }

        return newProductID;
    }

    public int deleteProductByID(int id) {
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        Transaction transaction = null;
        int status = 0;

        try {
            transaction = session.beginTransaction();
            Product product = (Product)session.load(Product.class,id);
            session.delete(product);
            transaction.commit();
            status = 1;
        } catch (RuntimeException e) {
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            session.flush();
            session.close();
        }

        return status;
    }


}
