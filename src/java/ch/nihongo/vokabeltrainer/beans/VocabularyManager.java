package ch.nihongo.vokabeltrainer.beans;

import ch.nihongo.vokabeltrainer.entities.Category;
import ch.nihongo.vokabeltrainer.entities.German;
import ch.nihongo.vokabeltrainer.entities.Japanese;
import ch.nihongo.vokabeltrainer.facade.TranslationFacade;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Niko Reichardt
 */
@ManagedBean
@SessionScoped
public class VocabularyManager implements Serializable {

    private static final long serialVersionUID = 1003670875145474329L;
    private TranslationFacade translationFacade;

    @ManagedProperty(value = "#{vocabularyAdd}")
    private VocabularyAdd data;

    @PostConstruct
    public void init() {
        translationFacade = getTranslationFacade();
    }

    public VocabularyManager() {
    }

    public void setData(VocabularyAdd data) {
        this.data = data;
    }

    public void resetData() {
        data.setCategory("");
        data.setGermanWord("");
        data.setGermanSentence("");
        data.setJapaneseKanji("");
        data.setJapaneseKana("");
        data.setJapaneseRomaji("");
        data.setJapaneseSentence("");
        data.setJapaneseJLPT("");
    }

    public String addVocable() {

        // Create japanese vocable
        Japanese japanese = new Japanese();
        japanese.setKanji(data.getJapaneseKanji());
        japanese.setKana(data.getJapaneseKana());
        japanese.setRomaji(data.getJapaneseRomaji());
        japanese.setSentence("");
        japanese.setCategory(Category.valueOf(data.getCategory()));

        // Create german vocable
        German german = new German();
        german.setWord(data.getGermanWord());
        german.setSentence("");
        german.setCategory(Category.valueOf(data.getCategory()));

        translationFacade.createTranslation(japanese, german);
        resetData();
        return "vt_vocable_created";

    }

    public TranslationFacade getTranslationFacade() {
        if (translationFacade == null) {
            translationFacade = new TranslationFacade();
        }
        return translationFacade;
    }

}
