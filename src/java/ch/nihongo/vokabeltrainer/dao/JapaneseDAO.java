package ch.nihongo.vokabeltrainer.dao;

import ch.nihongo.vokabeltrainer.entities.Japanese;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Niko Reichardt
 */
public class JapaneseDAO extends GenericDAO<Japanese> {

    private static final long serialVersionUID = 5874189997972068714L;

    public JapaneseDAO() {
        super(Japanese.class);
    }

    public List<Japanese> findByCategory(String category) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("category", category);

        return super.findAllResults(Japanese.FIND_BY_CATEGORY, parameters);
    }

    public Japanese findByKanji(String kanji) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("kanji", kanji);

        return super.findOneResult(Japanese.FIND_KANJI, parameters);
    }

    public Japanese findByKana(String kana) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("kana", kana);

        return super.findOneResult(Japanese.FIND_KANA, parameters);
    }

    public Japanese findByRomaji(String romaji) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("romaji", romaji);

        return super.findOneResult(Japanese.FIND_ROMAJI, parameters);
    }

    public Japanese findByJLPT(String jlpt) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("jlpt", jlpt);

        return super.findOneResult(Japanese.FIND_JLPT, parameters);
    }

}
