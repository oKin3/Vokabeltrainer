package ch.nihongo.vokabeltrainer.beans;

import ch.nihongo.vokabeltrainer.entities.Category;
import java.io.Serializable;
import java.util.Map;
import java.util.TreeMap;
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
public class VocabularySettings implements Serializable {

    private static final long serialVersionUID = -6307470472840700735L;

    private String language;
    private String mode;
    private String category;
    private Map<String, String> languageMap;
    private Map<String, String> modeMap;
    private Map<String, Category> categoryMap;

    public static final String GERMAN = "german";
    public static final String JAPANESE = "japanese";
    public static final String RANDOM = "random";
    public static final String WRONG = "wrong";

    @ManagedProperty(value = "#{vocabularyStatistic}")
    private VocabularyStatistic statistic;

    @PostConstruct
    public void init() {
        createLanguageMap();
        createCategoryMap();
    }

    public VocabularySettings() {
    }

    public void setStatistic(VocabularyStatistic statistic) {
        this.statistic = statistic;
    }

    public void createLanguageMap() {
        languageMap = new TreeMap<>();
        languageMap.put("DE -> JP", GERMAN);
        languageMap.put("JP -> DE", JAPANESE);
    }

    public void createModeMap() {
        modeMap = new TreeMap<>();
        modeMap.put("Random", RANDOM);
        modeMap.put("Only Wrong", WRONG);
    }

    public void createCategoryMap() {
        categoryMap = new TreeMap<>();
        for (Category categoryItem : Category.values()) {
            categoryMap.put(categoryItem.getName() + " " + statistic.getGermanWordSizeByCategory(categoryItem.getName()), categoryItem);
        }
    }

    public Map<String, Category> getCategoryMap() {
        return categoryMap;
    }

    public Map<String, String> getLanguageMap() {
        return languageMap;
    }

    public Map<String, String> getModeMap() {
        return modeMap;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String addSettings() {
        return "vt_vocable_test.jsf";
    }

}
