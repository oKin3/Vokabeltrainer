package ch.nihongo.vokabeltrainer.handler;

import ch.nihongo.vokabeltrainer.entities.Category;
import ch.nihongo.vokabeltrainer.entities.German;
import ch.nihongo.vokabeltrainer.entities.Japanese;
import java.util.List;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 *
 * @author Niko Reichardt
 */
@ManagedBean
@ApplicationScoped
public class VocabularyTrainerHandler {

    public VocabularyTrainerHandler() {
    }

    private static final String PERSISTENCE_UNIT_NAME = "VokabeltrainerPU";
    private static EntityManagerFactory emf;

    public List<German> getGermanList(String category) {
        emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager em = emf.createEntityManager();

        TypedQuery<German> query = em.createNamedQuery(German.FIND_BY_CATEGORY, German.class);
        query.setParameter("category", Category.valueOf(category));
        List<German> list = query.getResultList();

        em.close();
        emf.close();

        return list;
    }

    public List<Japanese> getJapaneseList(String category) {
        emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager em = emf.createEntityManager();

        TypedQuery<Japanese> query = em.createNamedQuery(Japanese.FIND_BY_CATEGORY, Japanese.class);
        query.setParameter("category", Category.valueOf(category));
        List<Japanese> list = query.getResultList();

        em.close();
        emf.close();

        return list;
    }
}
