package repository.product;

import domain.entity.product.ProductEntity;
import exception.exceptions.nullExceptions.ProductNullException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import repository.ProductRepository;
import util.Pageable;

import java.util.Collection;

@Repository("productRepository")
public class ProductRepsitoryHibernate implements ProductRepository {

    private final SessionFactory sessionFactory;

    public ProductRepsitoryHibernate(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public ProductEntity findOne(long id) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        String SQL = "SELECT * FROM PRODUCT WHERE PRODUCT_ID = :id";
        ProductEntity productEntity = (ProductEntity)session.createSQLQuery(SQL)
                                .addEntity(ProductEntity.class)
                                .setParameter("id", id)
                                .uniqueResult();
        session.getTransaction().commit();
        if (productEntity == null){
            throw new ProductNullException("findOne: " + id, "repo");
        }
        return productEntity;
    }

    @Override
    public Collection<ProductEntity> findAll() {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        String SQL = "SELECT * FROM PRODUCT";
        Collection<ProductEntity> productEntities = session.createSQLQuery(SQL)
                                        .addEntity(ProductEntity.class)
                                        .list();
        session.getTransaction().commit();
        if (productEntities.size() == 0){
            throw new ProductNullException("findAll: ", "repo");
        }
        return productEntities;
    }

    @Override
    public Collection<ProductEntity> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public void update(ProductEntity product) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        String SQL = "UPDATE PRODUCT "
                    + "SET NAME = :name, PRICE = :price, DESCRIPTION = :description "
                    + "WHERE PRODUCT_ID = :id";
        session.createSQLQuery(SQL)
                .setParameter("name", product.getName())
                .setParameter("price", product.getPrice())
                .setParameter("description", product.getDescription())
                .setParameter("id", product.getId())
                .executeUpdate();
        session.getTransaction().commit();
    }

    @Override
    public void save(ProductEntity product) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        String SQL = "INSERT INTO PRODUCT(NAME, PRICE, DESCRIPTION) "
                    + "VALUES (:name, :price, :description)";
        session.createSQLQuery(SQL)
                .setParameter("name", product.getName())
                .setParameter("price", product.getPrice())
                .setParameter("description", product.getDescription())
                .executeUpdate();
//        String mysql = "SELECT LAST_INSERT_ID()";
//        BigInteger id = (BigInteger)session.createSQLQuery(mysql).uniqueResult();
//        product.setId(id.longValue() + 1);
        session.getTransaction().commit();
    }

    @Override
    public void delete(long id) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        String SQL = "DELETE FROM PRODUCT WHERE PRODUCT_ID = :id";
        session.createSQLQuery(SQL)
                .setParameter("id", id)
                .executeUpdate();
        session.getTransaction().commit();
    }
}
