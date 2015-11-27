package ch.nihongo.vokabeltrainer.facade;

import ch.nihongo.vokabeltrainer.dao.NotknownDAO;
import ch.nihongo.vokabeltrainer.entities.German;
import ch.nihongo.vokabeltrainer.entities.Japanese;
import ch.nihongo.vokabeltrainer.entities.Notknown;
import ch.nihongo.vokabeltrainer.entities.Userlogin;
import java.util.List;

/**
 *
 * @author Niko Reichardt
 */
public class NotknownFacade {

    private NotknownDAO notknownDAO;

    public NotknownFacade() {
        notknownDAO = new NotknownDAO();
    }

    public void addNotknownWord(Notknown notknown) {
        notknownDAO.beginTransaction();
        notknownDAO.persist(notknown);
        notknownDAO.commitAndCloseTransaction();
    }
    
    public void deleteNotknownWord(Notknown notknown) {
        notknownDAO.beginTransaction();
        notknownDAO.delete(notknown);
        notknownDAO.commitAndCloseTransaction();
    }

    public Notknown findByGermanIdAndUserId(German germanId, Userlogin userId) {
        notknownDAO.createEntityManager();
        Notknown result = notknownDAO.findByGermanIdAndUserId(germanId, userId);
        notknownDAO.closeEntityManager();
        return result;
    }

    public Notknown findByJapaneseIdAndUserId(Japanese japaneseId, Userlogin userId) {
        notknownDAO.createEntityManager();
        Notknown result = notknownDAO.findByJapaneseIdAndUserId(japaneseId, userId);
        notknownDAO.closeEntityManager();
        return result;
    }

    public List<Notknown> findAllGermanByUserId(Userlogin id) {
        notknownDAO.createEntityManager();
        List<Notknown> result = notknownDAO.findAllGermanByUserId(id);
        notknownDAO.closeEntityManager();
        return result;
    }

    public List<Notknown> findAllJapaneseByUserId(Userlogin id) {
        notknownDAO.createEntityManager();
        List<Notknown> result = notknownDAO.findAllJapaneseByUserId(id);
        notknownDAO.closeEntityManager();
        return result;
    }

}
