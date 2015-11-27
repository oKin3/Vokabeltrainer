package ch.nihongo.vokabeltrainer.dao;

import ch.nihongo.vokabeltrainer.entities.German;
import ch.nihongo.vokabeltrainer.entities.Japanese;
import ch.nihongo.vokabeltrainer.entities.Userlogin;
import ch.nihongo.vokabeltrainer.entities.Wrong;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Niko Reichardt
 */
public class WrongDAO extends GenericDAO<Wrong> {

    private static final long serialVersionUID = -3599941766565033685L;

    public WrongDAO() {
        super(Wrong.class);
    }
    
    public void delete(Wrong wrong) {
        super.delete(wrong.getId());
    }

    public Wrong findByGermanIdAndUserId(German germanId, Userlogin userId) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("germanId", germanId);
        parameters.put("userId", userId);

        return super.findOneResult(Wrong.FIND_BY_GERMAN_ID_AND_USER_ID, parameters);
    }

    public Wrong findByJapaneseIdAndUserId(Japanese japaneseId, Userlogin userId) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("japaneseId", japaneseId);
        parameters.put("userId", userId);

        return super.findOneResult(Wrong.FIND_BY_JAPANESE_ID_AND_USER_ID, parameters);
    }

    public List<Wrong> findAllGermanByUserId(Userlogin userId) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("userId", userId);
        return super.findAllResults(Wrong.FIND_ALL_GERMAN_BY_USER_ID, parameters);
    }

    public List<Wrong> findAllJapaneseByUserId(Userlogin userId) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("userId", userId);
        return super.findAllResults(Wrong.FIND_ALL_JAPANESE_BY_USER_ID, parameters);
    }

}
