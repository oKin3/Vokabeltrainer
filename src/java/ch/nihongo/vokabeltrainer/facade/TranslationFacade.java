package ch.nihongo.vokabeltrainer.facade;

import ch.nihongo.vokabeltrainer.dao.GermanDAO;
import ch.nihongo.vokabeltrainer.dao.JapaneseDAO;
import ch.nihongo.vokabeltrainer.entities.German;
import ch.nihongo.vokabeltrainer.entities.Japanese;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Niko Reichardt
 */
public class TranslationFacade {

    private GermanDAO germanDAO;
    private JapaneseDAO japaneseDAO;

    public TranslationFacade() {
        germanDAO = new GermanDAO();
        japaneseDAO = new JapaneseDAO();
    }

    public boolean createTranslation(Japanese japanese, German german) {
        japaneseDAO.beginTransaction();
        germanDAO.beginTransaction();
        japaneseDAO.persist(japanese);
        List<Japanese> list = new ArrayList();
        list.add(japanese);
        german.setJapaneseList(list);
        germanDAO.persist(german);
        japaneseDAO.commitAndCloseTransaction();
        germanDAO.commitAndCloseTransaction();
        return true;
    }

}
