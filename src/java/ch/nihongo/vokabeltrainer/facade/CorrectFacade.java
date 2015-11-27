package ch.nihongo.vokabeltrainer.facade;

import ch.nihongo.vokabeltrainer.dao.CorrectDAO;
import ch.nihongo.vokabeltrainer.entities.Correct;
import ch.nihongo.vokabeltrainer.entities.German;
import ch.nihongo.vokabeltrainer.entities.Japanese;
import ch.nihongo.vokabeltrainer.entities.Userlogin;
import java.util.List;

/**
 *
 * @author Niko Reichardt
 */
public class CorrectFacade {

    private CorrectDAO correctDAO;

    public CorrectFacade() {
        correctDAO = new CorrectDAO();
    }

    public List<Correct> findAll() {
        correctDAO.createEntityManager();
        List<Correct> result = correctDAO.findAll();
        correctDAO.closeEntityManager();
        return result;
    }

    public void addCorrectWord(Correct correct) {
        correctDAO.beginTransaction();
        correctDAO.persist(correct);
        correctDAO.commitAndCloseTransaction();
    }
    
    public void deleteCorrectWord(Correct correct) {
        correctDAO.beginTransaction();
        correctDAO.delete(correct);
        correctDAO.commitAndCloseTransaction();
    }

    public Correct findByGermanIdAndUserId(German germanId, Userlogin userId) {
        correctDAO.createEntityManager();
        Correct result = correctDAO.findByGermanIdAndUserId(germanId, userId);
        correctDAO.closeEntityManager();
        return result;
    }

    public Correct findByJapaneseIdAndUserId(Japanese japaneseId, Userlogin userId) {
        correctDAO.createEntityManager();
        Correct result = correctDAO.findByJapaneseIdAndUserId(japaneseId, userId);
        correctDAO.closeEntityManager();
        return result;
    }

    public List<Correct> findAllGermanByUserId(Userlogin id) {
        correctDAO.createEntityManager();
        List<Correct> result = correctDAO.findAllGermanByUserId(id);
        correctDAO.closeEntityManager();
        return result;
    }

    public List<Correct> findAllJapaneseByUserId(Userlogin id) {
        correctDAO.createEntityManager();
        List<Correct> result = correctDAO.findAllJapaneseByUserId(id);
        correctDAO.closeEntityManager();
        return result;
    }

    public Correct findById(int id) {
        correctDAO.createEntityManager();
        Correct result = correctDAO.findById(id);
        correctDAO.closeEntityManager();
        return result;
    }

}
