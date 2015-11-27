package ch.nihongo.vokabeltrainer.dao;

import ch.nihongo.vokabeltrainer.entities.Correct;
import ch.nihongo.vokabeltrainer.entities.German;
import ch.nihongo.vokabeltrainer.entities.Japanese;
import ch.nihongo.vokabeltrainer.entities.Userlogin;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Niko Reichardt
 */
public class CorrectDAO extends GenericDAO<Correct> {

    private static final long serialVersionUID = 8369160061279480398L;

    public CorrectDAO() {
        super(Correct.class);
    }

    public void delete(Correct correct) {
        super.delete(correct.getId());
    }

    public Correct findByGermanIdAndUserId(German germanId, Userlogin userId) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("germanId", germanId);
        parameters.put("userId", userId);

        return super.findOneResult(Correct.FIND_BY_GERMAN_ID_AND_USER_ID, parameters);
    }

    public Correct findByJapaneseIdAndUserId(Japanese japaneseId, Userlogin userId) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("japaneseId", japaneseId);
        parameters.put("userId", userId);

        return super.findOneResult(Correct.FIND_BY_JAPANESE_ID_AND_USER_ID, parameters);
    }

    public List<Correct> findAllGermanByUserId(Userlogin userId) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("userId", userId);
        return super.findAllResults(Correct.FIND_ALL_GERMAN_BY_USER_ID, parameters);
    }

    public List<Correct> findAllJapaneseByUserId(Userlogin userId) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("userId", userId);
        return super.findAllResults(Correct.FIND_ALL_JAPANESE_BY_USER_ID, parameters);
    }

    public Correct findById(int id) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("id", id);
        return super.findOneResult(Correct.FIND_BY_ID, parameters);
    }

}
