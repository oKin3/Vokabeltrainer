package ch.nihongo.vokabeltrainer.facade;

import ch.nihongo.vokabeltrainer.dao.JapaneseDAO;
import ch.nihongo.vokabeltrainer.entities.Japanese;
import java.util.List;

/**
 *
 * @author Niko Reichardt
 */
public class JapaneseFacade {

    private JapaneseDAO japaneseDAO;

    public JapaneseFacade() {
        japaneseDAO = new JapaneseDAO();
    }

    public List<Japanese> findAll() {
        japaneseDAO.createEntityManager();
        List<Japanese> result = japaneseDAO.findAll();
        japaneseDAO.closeEntityManager();

        return result;
    }

    public List<Japanese> findByCategory(String category) {
        japaneseDAO.createEntityManager();
        List<Japanese> list = japaneseDAO.findByCategory(category.toUpperCase());
        japaneseDAO.closeEntityManager();
        return list;
    }
    
    public Japanese findByKanji(String kanji) {
        japaneseDAO.createEntityManager();
        Japanese japanese = japaneseDAO.findByKanji(kanji);
        japaneseDAO.closeEntityManager();
        return japanese;
    }
}
