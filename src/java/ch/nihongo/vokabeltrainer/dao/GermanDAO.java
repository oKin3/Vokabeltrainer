package ch.nihongo.vokabeltrainer.dao;

import ch.nihongo.vokabeltrainer.entities.German;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Niko Reichardt
 */
public class GermanDAO extends GenericDAO<German> {

    private static final long serialVersionUID = 5874189997972068714L;

    public GermanDAO() {
        super(German.class);
    }

    public List<German> findByCategory(String category) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("category", category);

        return super.findAllResults(German.FIND_BY_CATEGORY, parameters);
    }

    public German findByWord(String word) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("word", word);

        return super.findOneResult(German.FIND_BY_WORD, parameters);
    }

}
