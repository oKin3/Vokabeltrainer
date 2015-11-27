package ch.nihongo.vokabeltrainer.facade;

import ch.nihongo.vokabeltrainer.dao.GermanDAO;
import ch.nihongo.vokabeltrainer.entities.German;
import java.util.List;

/**
 *
 * @author Niko Reichardt
 */
public class GermanFacade {

    private GermanDAO germanDAO;

    public GermanFacade() {
        germanDAO = new GermanDAO();
    }

    public List<German> findAll() {
        germanDAO.createEntityManager();
        List<German> result = germanDAO.findAll();
        germanDAO.closeEntityManager();
        return result;
    }

    public List<German> findByCategory(String category) {
        germanDAO.createEntityManager();
        List<German> list = germanDAO.findByCategory(category.toUpperCase());
        germanDAO.closeEntityManager();
        return list;
    }
    
    public List<German> findByCategoryMax(String category, int max) {
        germanDAO.createEntityManager();
        List<German> list = germanDAO.findByCategoryMax(category, max);
        germanDAO.closeEntityManager();
        return list;
    }
    
    public German findByWord(String word) {
        germanDAO.createEntityManager();
        German german = germanDAO.findByWord(word);
        germanDAO.closeEntityManager();
        return german;
    }
}
