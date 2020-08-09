package repository.order;

import domain.entity.order.OrderEntity;
import exception.exceptions.nullExceptions.OrderNullException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import repository.OrderRepository;
import util.Pageable;

import java.util.Collection;

@Repository("orderRepository")
public class OrderRepositoryHibernate implements OrderRepository {

    private final SessionFactory sessionFactory;

    public OrderRepositoryHibernate(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public OrderEntity findOne(long id) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        String SQL = "SELECT * " +
                     "FROM ORDERS " +
                     "WHERE ORDER_ID = :id ";
        OrderEntity orderEntity = (OrderEntity)session.createSQLQuery(SQL)
                                                      .addEntity(OrderEntity.class)
                                                      .setParameter("id", id)
                                                      .uniqueResult();
        session.getTransaction().commit();
        if (orderEntity == null){
            throw new OrderNullException("findOne: " + id, "Repo");
        }
        return orderEntity;
    }

    @Override
    public Collection<OrderEntity> findAll(){
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        String SQL = "SELECT * " +
                     "FROM ORDERS O " +
                     "LEFT JOIN ORDER_ITEM OI " +
                     "ON O.order_id = OI.order_id " +
                     "LEFT JOIN CUSTOMER C " +
                     "ON C.customer_id = O.customer_id " +
                     "GROUP BY O.order_id";
        Collection<OrderEntity> orderEntities = session.createSQLQuery(SQL)
                                                        .addEntity(OrderEntity.class)
                                                        .list();
        session.getTransaction().commit();
        if (orderEntities.size() == 0){
            throw new OrderNullException("findAll: ", "Repo");
        }
        return orderEntities;
    }

    @Override
    public Collection<OrderEntity> findAll(Pageable page) {
        return null;
    }

    @Override
    public void save(OrderEntity order) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        String SQL = "INSERT INTO ORDERS (ORDER_DATE, CUSTOMER_ID) " +
                     "VALUES (:order_date, :customer_id)";
        session.createSQLQuery(SQL)
                .addEntity(OrderEntity.class)
                .setParameter("order_date", order.getOrderDate())
                .setParameter("customer_id", order.getCustomer().getId())
                .executeUpdate();
        session.getTransaction().commit();
    }

    @Override
    public void update(OrderEntity order) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        String SQL = "UPDATE ORDERS " +
                     "SET ORDER_DATE = :order_date, CUSTOMER_ID = :customer_id " +
                     "WHERE ORDER_ID = :id";
        session.createSQLQuery(SQL)
               .addEntity(OrderEntity.class)
               .setParameter("id", order.getId())
               .setParameter("order_date", order.getOrderDate())
               .setParameter("customer_id", order.getCustomer().getId())
               .executeUpdate();
        session.getTransaction().commit();
    }

    @Override
    public void delete(long id) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        String SQL = "DELETE FROM ORDERS " +
                     "WHERE ORDER_ID = :id";
        session.createSQLQuery(SQL)
               .addEntity(OrderEntity.class)
               .setParameter("id", id)
               .executeUpdate();
        session.getTransaction().commit();
    }

}
