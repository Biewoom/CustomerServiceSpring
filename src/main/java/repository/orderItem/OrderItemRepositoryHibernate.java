package repository.orderItem;

import domain.entity.orderItem.OrderItemEntity;
import exception.exceptions.nullExceptions.OrderItemNullException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import repository.OrderItemRepository;
import util.Pageable;

import java.math.BigInteger;
import java.sql.SQLException;
import java.util.Collection;

@Repository("orderItemRepository")
public class OrderItemRepositoryHibernate implements OrderItemRepository {
    private final SessionFactory sessionFactory;

    public OrderItemRepositoryHibernate(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public OrderItemEntity findOne(long id) throws SQLException {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        String SQL = "SELECT * " +
                     "FROM ORDER_ITEM " +
                     "WHERE ORDER_ITEM_ID = :id ";
        OrderItemEntity orderItemEntity = (OrderItemEntity) session.createSQLQuery(SQL)
                                                        .addEntity(OrderItemEntity.class)
                                                        .setParameter("id", id)
                                                        .uniqueResult();
        session.getTransaction().commit();
        if (orderItemEntity == null){
            throw new OrderItemNullException("findOne: " + id, "Repo");
        }
        return orderItemEntity;
    }

    @Override
    public Collection<OrderItemEntity> findAll() throws SQLException {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        String SQL = "SELECT * " +
                     "FROM ORDER_ITEM ";
        Collection<OrderItemEntity> orderItemEntities = session.createSQLQuery(SQL)
                                                               .addEntity(OrderItemEntity.class)
                                                               .list();
        session.getTransaction().commit();
        if (orderItemEntities.size() == 0){
            throw new OrderItemNullException("findAll ", "Repo");
        }
        return orderItemEntities;
    }

    @Override
    public Collection<OrderItemEntity> findAll(Pageable page) throws SQLException {
        return null;
    }

    @Override
    public Collection<OrderItemEntity> findAllByProduct(long product_id) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        String SQL = "SELECT * " +
                     "FROM ORDER_ITEM " +
                     "WHERE PRODUCT_ID = :product_id";
        Collection<OrderItemEntity> orderItemEntities = session.createSQLQuery(SQL)
                                                               .addEntity(OrderItemEntity.class)
                                                               .setParameter("product_id", product_id)
                                                               .list();
        session.getTransaction().commit();
        if (orderItemEntities.size() == 0){
            throw new OrderItemNullException("findAllByProduct: " + product_id, "Repo");
        }
        return orderItemEntities;
    }

    @Override
    public void save(OrderItemEntity orderItemEntity) throws SQLException {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        String SQL = "INSERT INTO ORDER_ITEM (AMOUNT, PRODUCT_ID) " +
                     "VALUES (:amount, :product_id) ";
        session.createSQLQuery(SQL)
               .addEntity(OrderItemEntity.class)
               .setParameter("amount", orderItemEntity.getAmount())
               .setParameter("product_id", orderItemEntity.getProduct().getId())
               .executeUpdate();
//        String ID_SQL = "SELECT LAST_INSERT_ID()";
//        BigInteger id = (BigInteger)session.createSQLQuery(ID_SQL).uniqueResult();
//        orderItemEntity.setId(id.longValue());
        session.getTransaction().commit();
    }

    @Override
    public void delete(long id) throws SQLException {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        String SQL = "DELETE FROM ORDER_ITEM " +
                     "WHERE ORDER_ITEM_ID = :id";
        session.createSQLQuery(SQL)
               .addEntity(OrderItemEntity.class)
               .setParameter("id", id)
               .executeUpdate();
        session.getTransaction().commit();
    }

    @Override
    public void update(OrderItemEntity orderItemEntity) throws SQLException {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        String SQL = "UPDATE ORDER_ITEM " +
                     "SET AMOUNT = :amount, PRODUCT_ID = :product_id " +
                     "WHERE ORDER_ITEM_ID = :id";
        session.createSQLQuery(SQL)
               .addEntity(OrderItemEntity.class)
               .setParameter("amount", orderItemEntity.getAmount())
               .setParameter("product_id", orderItemEntity.getProduct().getId())
               .setParameter("id", orderItemEntity.getId())
               .executeUpdate();
        session.getTransaction().commit();
    }
}
