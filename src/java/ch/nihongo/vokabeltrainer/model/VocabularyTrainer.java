package ch.nihongo.vokabeltrainer.model;

import ch.nihongo.vokabeltrainer.beans.VocabularyData;
import ch.nihongo.vokabeltrainer.beans.VocabularySettings;
import ch.nihongo.vokabeltrainer.entities.Category;
import ch.nihongo.vokabeltrainer.entities.German;
import ch.nihongo.vokabeltrainer.entities.Japanese;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 *
 * @author Niko Reichardt
 */
@ManagedBean
@SessionScoped
public class VocabularyTrainer implements Serializable {

    private static final String PERSISTENCE_UNIT_NAME = "VokabeltrainerPU";
    private static EntityManagerFactory emf;
    private static final long serialVersionUID = -4631986712793692734L;
    private Random random = new Random();
    private String answer;
    private String generatedWord;

    @ManagedProperty(value = "#{vocabularyData}")
    private VocabularyData data;

    @ManagedProperty(value = "#{vocabularySettings}")
    private VocabularySettings settings;

    public VocabularyTrainer() {
    }

    public void setData(VocabularyData data) {
        this.data = data;
    }

    public void setSettings(VocabularySettings settings) {
        this.settings = settings;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String generateVocable() {
        emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager em = emf.createEntityManager();

        TypedQuery<German> queryGerman = em.createNamedQuery(German.FIND_BY_CATEGORY, German.class);
        queryGerman.setParameter("category", Category.valueOf(settings.getCategory()));
        List<German> listGerman = queryGerman.getResultList();

        TypedQuery<Japanese> queryJapanese = em.createNamedQuery(Japanese.FIND_BY_CATEGORY, Japanese.class);
        queryJapanese.setParameter("category", Category.valueOf(settings.getCategory()));
        List<Japanese> listJapanese = queryJapanese.getResultList();

        int count = listGerman.size();
        int index = random.nextInt(count);

        String randomGermanWord = listGerman.get(index).getWord();
        data.setGermanWord(randomGermanWord);
        data.setGermanSentence(listGerman.get(index).getSentence());

        String randomJapaneseWord = listJapanese.get(index).getKanji();
        data.setJapaneseKanji(randomJapaneseWord);
        data.setJapaneseKana(listJapanese.get(index).getKana());
        data.setJapaneseRomaji(listJapanese.get(index).getRomaji());
        data.setJapaneseSentence(listJapanese.get(index).getSentence());

        if (settings.getLanguage().equals(VocabularySettings.GERMAN)) {
            generatedWord = randomGermanWord;
        } else {
            generatedWord = randomJapaneseWord;
        }

        em.close();
        emf.close();

        return generatedWord;

    }

    public String checkVocable() {
        String answerCheck = answer;
        List<String> correctAnswer = new ArrayList<>();
        answer = "";

        if (settings.getLanguage().equals(VocabularySettings.GERMAN)) {
            correctAnswer.add(data.getJapaneseKanji());
            correctAnswer.add(data.getJapaneseKana());
            correctAnswer.add(data.getJapaneseRomaji().toLowerCase());
        } else if (settings.getLanguage().equals(VocabularySettings.JAPANESE)) {
            correctAnswer.add(data.getGermanWord().toLowerCase());
        }

        if (correctAnswer.contains(answerCheck.toLowerCase().trim())) {
            return "vt_correct";
        }
        return "vt_wrong";
    }

}
