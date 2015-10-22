package ch.nihongo.vokabeltrainer.beans;

import ch.nihongo.vokabeltrainer.entities.Category;
import java.io.Serializable;
import java.util.Map;
import java.util.TreeMap;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Niko Reichardt
 */
@ManagedBean
@SessionScoped
public class VocabularyAdd implements Serializable {

    private static final long serialVersionUID = 7368208979026414221L;

    private String germanWord;
    private String germanSentence;
    private String japaneseKanji;
    private String japaneseKana;
    private String japaneseRomaji;
    private String japaneseSentence;
    private String japaneseJLPT;
    private String category;
    private static Map<String, Category> categoryMap;

    static {
        categoryMap = new TreeMap<>();
        for (Category categoryItem : Category.values()) {
            categoryMap.put(categoryItem.getName(), categoryItem);
        }
    }

    public Map<String, Category> getCategoryMap() {
        return categoryMap;
    }

    public VocabularyAdd() {
    }

    public String getGermanWord() {
        return germanWord;
    }

    public void setGermanWord(String germanWord) {
        this.germanWord = germanWord;
    }

    public String getGermanSentence() {
        return germanSentence;
    }

    public void setGermanSentence(String germanSentence) {
        this.germanSentence = germanSentence;
    }

    public String getJapaneseKanji() {
        return japaneseKanji;
    }

    public void setJapaneseKanji(String japaneseKanji) {
        this.japaneseKanji = japaneseKanji;
    }

    public String getJapaneseKana() {
        return japaneseKana;
    }

    public void setJapaneseKana(String japaneseKana) {
        this.japaneseKana = japaneseKana;
    }

    public String getJapaneseRomaji() {
        return japaneseRomaji;
    }

    public void setJapaneseRomaji(String japaneseRomaji) {
        this.japaneseRomaji = japaneseRomaji;
    }

    public String getJapaneseSentence() {
        return japaneseSentence;
    }

    public void setJapaneseSentence(String japaneseSentence) {
        this.japaneseSentence = japaneseSentence;
    }

    public String getJapaneseJLPT() {
        return japaneseJLPT;
    }

    public void setJapaneseJLPT(String japaneseJLPT) {
        this.japaneseJLPT = japaneseJLPT;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
    

}
