package ch.nihongo.vokabeltrainer.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;

/**
 *
 * @author Niko Reichardt
 */
public abstract class GenericDAO<T> implements Serializable {

    private static final long serialVersionUID = 8046441202749967820L;
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("VokabeltrainerPU");
    private EntityManager em;
    private Class<T> entityClass;

    public GenericDAO(Class entityClass) {
        this.entityClass = entityClass;
    }

    public void createEntityManager() {
        em = emf.createEntityManager();
    }

    public void closeEntityManager() {
        if (em.isOpen()) {
            em.close();
        }
    }

    public void beginTransaction() {
        createEntityManager();
        em.getTransaction().begin();
    }

    public void closeTransaction() {
        closeEntityManager();
    }

    public void commit() {
        em.getTransaction().commit();
    }

    public void rollback() {
        em.getTransaction().rollback();
    }

    public void commitAndCloseTransaction() {
        commit();
        closeTransaction();
    }

    public void flush() {
        em.flush();
    }

    // Only with JTA
//    public void joinTransaction() {
//        createEntityManager();
//        em.joinTransaction();
//    }

    public void persist(T entity) {
        em.persist(entity);
    }

    public void delete(Object id) {
        T entityToBeRemoved = em.getReference(entityClass, id);

        em.remove(entityToBeRemoved);
    }

    public T update(T entity) {
        return em.merge(entity);
    }

    public T find(int entityID) {
        return em.find(entityClass, entityID);
    }

    public T findReferenceOnly(int entityID) {
        return em.getReference(entityClass, entityID);
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    public List findAll() {
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        return em.createQuery(cq).getResultList();
    }

    @SuppressWarnings("unchecked")
    protected T findOneResult(String namedQuery, Map parameters) {
        T result = null;

        try {
            Query query = em.createNamedQuery(namedQuery);

            if (parameters != null && !parameters.isEmpty()) {
                populateQueryParameters(query, parameters);
            }

            result = (T) query.getSingleResult();

        } catch (NoResultException e) {
            System.out.println("No result found for named query: " + namedQuery);
        } catch (Exception e) {
            System.out.println("Error while running query: " + e.getMessage());
        }

        return result;
    }

    @SuppressWarnings("unchecked")
    protected List findAllResults(String namedQuery, Map parameters) {
        List result = null;

        try {
            Query query = em.createNamedQuery(namedQuery);

            if (parameters != null && !parameters.isEmpty()) {
                populateQueryParameters(query, parameters);
            }

            result = (List) query.getResultList();

        } catch (NoResultException e) {
            System.out.println("No result found for named query: " + namedQuery);
        } catch (Exception e) {
            System.out.println("Error while running query: " + e.getMessage());
        }

        return result;
    }
    
    @SuppressWarnings("unchecked")
    protected List findMaxResults(String namedQuery, Map parameters, int limit) {
        List result = null;

        try {
            Query query = em.createNamedQuery(namedQuery);

            if (parameters != null && !parameters.isEmpty()) {
                populateQueryParameters(query, parameters);
            }

            result = (List) query.setMaxResults(limit).getResultList();

        } catch (NoResultException e) {
            System.out.println("No result found for named query: " + namedQuery);
        } catch (Exception e) {
            System.out.println("Error while running query: " + e.getMessage());
        }

        return result;
    }

    private void populateQueryParameters(Query query, Map<String, Object> parameters) {
        for (Map.Entry<String, Object> entry : parameters.entrySet()) {
            query.setParameter(entry.getKey(), entry.getValue());
        }
    }
}
