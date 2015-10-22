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
        germanDAO.beginTransaction();
        List<German> result = germanDAO.findAll();
        germanDAO.closeTransaction();

        return result;
    }

    public List<German> findByCategory(String category) {
        germanDAO.createEntityManager();
        List<German> list = germanDAO.findByCategory(category);
        germanDAO.closeEntityManager();
        return list;
    }
}
