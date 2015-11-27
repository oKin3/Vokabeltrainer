package ch.nihongo.vokabeltrainer.facade;

import ch.nihongo.vokabeltrainer.dao.WrongDAO;
import ch.nihongo.vokabeltrainer.entities.German;
import ch.nihongo.vokabeltrainer.entities.Japanese;
import ch.nihongo.vokabeltrainer.entities.Userlogin;
import ch.nihongo.vokabeltrainer.entities.Wrong;
import java.util.List;

/**
 *
 * @author Niko Reichardt
 */
public class WrongFacade {

    private WrongDAO wrongDAO;

    public WrongFacade() {
        wrongDAO = new WrongDAO();
    }

    public void addWrongWord(Wrong wrong) {
        wrongDAO.beginTransaction();
        wrongDAO.persist(wrong);
        wrongDAO.commitAndCloseTransaction();

    }
    
    public void deleteWrongWord(Wrong wrong) {
        wrongDAO.beginTransaction();
        wrongDAO.delete(wrong);
        wrongDAO.commitAndCloseTransaction();
    }

    public Wrong findByGermanIdAndUserId(German germanId, Userlogin userId) {
        wrongDAO.createEntityManager();
        Wrong result = wrongDAO.findByGermanIdAndUserId(germanId, userId);
        wrongDAO.closeEntityManager();
        return result;
    }

    public Wrong findByJapaneseIdAndUserId(Japanese japaneseId, Userlogin userId) {
        wrongDAO.createEntityManager();
        Wrong result = wrongDAO.findByJapaneseIdAndUserId(japaneseId, userId);
        wrongDAO.closeEntityManager();
        return result;
    }

    public List<Wrong> findAllGermanByUserId(Userlogin id) {
        wrongDAO.createEntityManager();
        List<Wrong> result = wrongDAO.findAllGermanByUserId(id);
        wrongDAO.closeEntityManager();
        return result;
    }

    public List<Wrong> findAllJapaneseByUserId(Userlogin id) {
        wrongDAO.createEntityManager();
        List<Wrong> result = wrongDAO.findAllJapaneseByUserId(id);
        wrongDAO.closeEntityManager();
        return result;
    }

}
