package ch.nihongo.vokabeltrainer.dao;

import ch.nihongo.vokabeltrainer.entities.German;
import ch.nihongo.vokabeltrainer.entities.Japanese;
import ch.nihongo.vokabeltrainer.entities.Notknown;
import ch.nihongo.vokabeltrainer.entities.Userlogin;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Niko Reichardt
 */
public class NotknownDAO extends GenericDAO<Notknown> {

    private static final long serialVersionUID = -4300986030055209961L;

    public NotknownDAO() {
        super(Notknown.class);
    }

    public void delete(Notknown notknown) {
        super.delete(notknown.getId());
    }

    public Notknown findByGermanIdAndUserId(German germanId, Userlogin userId) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("germanId", germanId);
        parameters.put("userId", userId);

        return super.findOneResult(Notknown.FIND_BY_GERMAN_ID_AND_USER_ID, parameters);
    }

    public Notknown findByJapaneseIdAndUserId(Japanese japaneseId, Userlogin userId) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("japaneseId", japaneseId);
        parameters.put("userId", userId);

        return super.findOneResult(Notknown.FIND_BY_JAPANESE_ID_AND_USER_ID, parameters);
    }

    public List<Notknown> findAllGermanByUserId(Userlogin userId) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("userId", userId);
        return super.findAllResults(Notknown.FIND_ALL_GERMAN_BY_USER_ID, parameters);
    }

    public List<Notknown> findAllJapaneseByUserId(Userlogin userId) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("userId", userId);
        return super.findAllResults(Notknown.FIND_ALL_JAPANESE_BY_USER_ID, parameters);
    }

}
