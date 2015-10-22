package ch.nihongo.vokabeltrainer.beans;

import ch.nihongo.vokabeltrainer.beans.VocabularyData;
import ch.nihongo.vokabeltrainer.beans.VocabularySettings;
import ch.nihongo.vokabeltrainer.entities.German;
import ch.nihongo.vokabeltrainer.entities.Japanese;
import ch.nihongo.vokabeltrainer.facade.GermanFacade;
import ch.nihongo.vokabeltrainer.facade.JapaneseFacade;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
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
public class VocabularyTrainer implements Serializable {

    private JapaneseFacade japaneseFacade;
    private GermanFacade germanFacade;
    private static final long serialVersionUID = -4631986712793692734L;
    private Random random = new Random();
    private String answer;
    private String generatedWord;

    @ManagedProperty(value = "#{vocabularyData}")
    private VocabularyData data;

    @ManagedProperty(value = "#{vocabularySettings}")
    private VocabularySettings settings;

    @PostConstruct
    public void init() {
        japaneseFacade = getJapaneseFacade();
        germanFacade = getGermanFacade();
    }

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

        List<German> listGerman = germanFacade.findByCategory(settings.getCategory());
        List<Japanese> listJapanese = japaneseFacade.findByCategory(settings.getCategory());

        int count = listGerman.size();
        int index = random.nextInt(count);

        German german = listGerman.get(index);
        String randomGermanWord = listGerman.get(index).getWord();
        data.setGermanWord(randomGermanWord);
//        data.setGermanSentence(listGerman.get(index).getSentence());

        String randomJapaneseWord = listJapanese.get(index).getKanji();
        data.setJapaneseKanji(randomJapaneseWord);
        data.setJapaneseKana(listJapanese.get(index).getKana());
        data.setJapaneseRomaji(listJapanese.get(index).getRomaji());
//        data.setJapaneseSentence(listJapanese.get(index).getSentence());

        if (settings.getLanguage().equals(VocabularySettings.GERMAN)) {
            generatedWord = randomGermanWord;
        } else {
            generatedWord = randomJapaneseWord;
        }

        return generatedWord;

    }

    public String checkVocable() {
        String answerCheck = answer;
        List<String> correctAnswer = new ArrayList<>();
        answer = "";

        switch (settings.getLanguage()) {
            case VocabularySettings.GERMAN:
                correctAnswer.add(data.getJapaneseKanji());
                correctAnswer.add(data.getJapaneseKana());
                correctAnswer.add(data.getJapaneseRomaji().toLowerCase());
                break;
            case VocabularySettings.JAPANESE:
                correctAnswer.add(data.getGermanWord().toLowerCase());
                break;
        }

        if (correctAnswer.contains(answerCheck.toLowerCase().trim())) {
            return "vt_correct";
        }
        return "vt_wrong";
    }

    public JapaneseFacade getJapaneseFacade() {
        if (japaneseFacade == null) {
            japaneseFacade = new JapaneseFacade();
        }
        return japaneseFacade;
    }

    public GermanFacade getGermanFacade() {
        if (germanFacade == null) {
            germanFacade = new GermanFacade();
        }
        return germanFacade;
    }

}
