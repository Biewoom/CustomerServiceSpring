package repository.customer;

import domain.entity.customer.CustomerEntity;
import exception.exceptions.nullExceptions.CustomerNullException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import repository.CustomerRepository;
import util.Pageable;

import java.sql.SQLException;
import java.util.Collection;

@Repository("customerRepository")
public class CustomerRepositoryHibernate implements CustomerRepository {

    private final SessionFactory sessionFactory;

    public CustomerRepositoryHibernate(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public CustomerEntity findOne(long id) throws SQLException {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        String SQL = "SELECT * FROM CUSTOMER " +
                     "WHERE CUSTOMER_ID = :id";
        CustomerEntity customerEntity = (CustomerEntity) session.createSQLQuery(SQL)
                                                                .addEntity(CustomerEntity.class)
                                                                .setParameter("id", id)
                                                                .uniqueResult();
        session.getTransaction().commit();
        if(customerEntity == null){
            throw new CustomerNullException("findOne: " + id, "Repo");
        }
        return customerEntity;
    }

    @Override
    public Collection<CustomerEntity> findAll() throws SQLException {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        String SQL = "SELECT * FROM CUSTOMER";
        Collection<CustomerEntity> customerEntities = session.createSQLQuery(SQL)
                                                             .addEntity(CustomerEntity.class)
                                                             .list();
        session.getTransaction().commit();
        if(customerEntities.size() == 0){
            throw new CustomerNullException("findAll: ", "Repo");
        }
        return customerEntities;
    }

    @Override
    public Collection<CustomerEntity> findAll(Pageable page) throws SQLException {
        return null;
    }

    @Override
    public Collection<CustomerEntity> findByAddress(String address) throws SQLException {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        String SQL = "SELECT * FROM CUSTOMER " +
                     "WHERE ADDRESS LIKE :address";
        Collection<CustomerEntity> customerEntities = session.createSQLQuery(SQL)
                                                             .addEntity(CustomerEntity.class)
                                                             .setParameter("address", address + "%")
                                                             .list();
        session.getTransaction().commit();
        if(customerEntities.size() == 0){
            throw new CustomerNullException("findByAddress: " + address, "Repo");
        }
        return customerEntities;
    }

    @Override
    public Collection<CustomerEntity> findByName(String name) throws SQLException {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        String SQL = "SELECT * FROM CUSTOMER " +
                     "WHERE NAME = :name";
        Collection<CustomerEntity> customerEntities = session.createSQLQuery(SQL)
                                                             .addEntity(CustomerEntity.class)
                                                             .setParameter("name", name)
                                                             .list();
        session.getTransaction().commit();
        if(customerEntities.size() == 0){
            throw new CustomerNullException("findByName: " + name, "Repo");
        }
        return customerEntities;
    }

    @Override
    public void save(CustomerEntity customer) throws SQLException {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        String SQL = "INSERT INTO CUSTOMER(CUSTOMER_ID, NAME, ADDRESS, EMAIL) " +
                     "VALUES (:id, :name, :address, :email)";
        session.createSQLQuery(SQL)
               .setParameter("id", customer.getId())
               .setParameter("name", customer.getName())
               .setParameter("address", customer.getAddress())
               .setParameter("email", customer.getEmail())
               .executeUpdate();

        session.getTransaction().commit();
    }

    @Override
    public void delete(long id) throws SQLException {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        String SQL = "DELETE FROM CUSTOMER WHERE CUSTOMER_ID = :id";
        session.createSQLQuery(SQL)
               .setParameter("id", id)
               .executeUpdate();

        session.getTransaction().commit();
    }

    @Override
    public void update(CustomerEntity customer) throws SQLException {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        String SQL = "UPDATE CUSTOMER " +
                     "SET NAME = :name, ADDRESS = :address, EMAIL = :email " +
                     "WHERE CUSTOMER_ID = :id";
        session.createSQLQuery(SQL)
               .setParameter("name", customer.getName())
               .setParameter("address", customer.getAddress())
               .setParameter("email", customer.getEmail())
               .setParameter("id", customer.getId())
               .executeUpdate();

        session.getTransaction().commit();
    }

}
