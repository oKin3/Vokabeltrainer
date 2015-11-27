package ch.nihongo.vokabeltrainer.beans;

import ch.nihongo.vokabeltrainer.entities.Correct;
import ch.nihongo.vokabeltrainer.entities.German;
import ch.nihongo.vokabeltrainer.entities.Japanese;
import ch.nihongo.vokabeltrainer.entities.Notknown;
import ch.nihongo.vokabeltrainer.entities.Userlogin;
import ch.nihongo.vokabeltrainer.entities.Wrong;
import ch.nihongo.vokabeltrainer.facade.CorrectFacade;
import ch.nihongo.vokabeltrainer.facade.GermanFacade;
import ch.nihongo.vokabeltrainer.facade.JapaneseFacade;
import ch.nihongo.vokabeltrainer.facade.NotknownFacade;
import ch.nihongo.vokabeltrainer.facade.UserloginFacade;
import ch.nihongo.vokabeltrainer.facade.WrongFacade;
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

    private static final long serialVersionUID = -4631986712793692734L;
    private JapaneseFacade japaneseFacade;
    private GermanFacade germanFacade;
    private UserloginFacade userloginFacade;
    private CorrectFacade correctFacade;
    private WrongFacade wrongFacade;
    private NotknownFacade notknownFacade;
    private Random random = new Random();
    private String answer;
    private String generatedWord;
    private List<German> listGerman;
    private List<Japanese> listJapanese;

    @ManagedProperty(value = "#{vocabularyData}")
    private VocabularyData data;

    @ManagedProperty(value = "#{vocabularySettings}")
    private VocabularySettings settings;

    @ManagedProperty(value = "#{vocabularyStatistic}")
    private VocabularyStatistic statistic;

    @PostConstruct
    public void init() {
        japaneseFacade = getJapaneseFacade();
        germanFacade = getGermanFacade();
        userloginFacade = getUserloginFacade();
        correctFacade = getCorrectFacade();
        wrongFacade = getWrongFacade();
        notknownFacade = getNotknownFacade();
        listGerman = new ArrayList<>();
        listJapanese = new ArrayList<>();
    }

    public VocabularyTrainer() {
    }

    public void setData(VocabularyData data) {
        this.data = data;
    }

    public void setSettings(VocabularySettings settings) {
        this.settings = settings;
    }

    public void setStatistic(VocabularyStatistic statistic) {
        this.statistic = statistic;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String generateVocable() {
        int index;
        if (listGerman.size() == 1) {
            index = 0;
        } else {
            int count = listGerman.size();
            index = random.nextInt(count);
        }

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

        listGerman.remove(index);
        listJapanese.remove(index);

        return generatedWord;

    }

    public String loadVocableList() {
        listGerman.clear();
        listJapanese.clear();
        listGerman = germanFacade.findByCategory(settings.getCategory());
        listJapanese = japaneseFacade.findByCategory(settings.getCategory());
        return "vt_vocable_test.jsf";
    }

    public String checkVocable() {
        String answerCheck = answer;
        answer = "";
        List<String> correctAnswer = new ArrayList<>();

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
            if (settings.getLanguage().equals(VocabularySettings.GERMAN)) {
                addCorrectJapaneseWord();
            } else {
                addCorrectGermanWord();
            }
            if (listGerman.isEmpty()) {
                return "vt_correct_finish";
            }
            return "vt_correct";
        }
        if (answerCheck.isEmpty()) {
            if (settings.getLanguage().equals(VocabularySettings.GERMAN)) {
                addNotknownJapaneseWord();
            } else {
                addNotknownGermanWord();
            }
        } else {
            if (settings.getLanguage().equals(VocabularySettings.GERMAN)) {
                addWrongJapaneseWrod();
            } else {
                addWrongGermanWord();
            }
        }
        if (listGerman.isEmpty()) {
            return "vt_wrong_finish";
        }
        return "vt_wrong";
    }

    public int getProgress() {
        double percentage = (double) listGerman.size() / (double) statistic.getGermanWordSizeByCategory(settings.getCategory());
        return 100 - (int) (percentage * 100);
    }

    public void addCorrectGermanWord() {
        if (correctFacade.findByGermanIdAndUserId(getGermanWord(), getUserlogin()) == null) {
            Correct correct = new Correct();
            correct.setUserId(getUserlogin());
            correct.setGermanId(getGermanWord());
            correctFacade.addCorrectWord(correct);
            deleteNotknownGermanWord();
            deleteWrongGermanWord();
        }
    }

    public void deleteCorrectGermanWord() {
        Correct word = correctFacade.findByGermanIdAndUserId(getGermanWord(), getUserlogin());
        if (word != null) {
            correctFacade.deleteCorrectWord(word);
        }
    }

    public void addCorrectJapaneseWord() {
        if (correctFacade.findByJapaneseIdAndUserId(getJapaneseKanji(), getUserlogin()) == null) {
            Correct correct = new Correct();
            correct.setUserId(getUserlogin());
            correct.setJapaneseId(getJapaneseKanji());
            correctFacade.addCorrectWord(correct);
            deleteNotknownJapaneseWord();
            deleteWrongJapaneseWord();
        }
    }

    public void deleteCorrectJapaneseWord() {
        Correct word = correctFacade.findByJapaneseIdAndUserId(getJapaneseKanji(), getUserlogin());
        if (word != null) {
            correctFacade.deleteCorrectWord(word);
        }
    }

    public void addWrongGermanWord() {
        if (wrongFacade.findByGermanIdAndUserId(getGermanWord(), getUserlogin()) == null) {
            Wrong wrong = new Wrong();
            wrong.setUserId(getUserlogin());
            wrong.setGermanId(getGermanWord());
            wrongFacade.addWrongWord(wrong);
            deleteCorrectGermanWord();
            deleteNotknownGermanWord();
        }
    }

    public void deleteWrongGermanWord() {
        Wrong word = wrongFacade.findByGermanIdAndUserId(getGermanWord(), getUserlogin());
        if (word != null) {
            wrongFacade.deleteWrongWord(word);
        }
    }

    public void addWrongJapaneseWrod() {
        if (wrongFacade.findByJapaneseIdAndUserId(getJapaneseKanji(), getUserlogin()) == null) {
            Wrong wrong = new Wrong();
            wrong.setUserId(getUserlogin());
            wrong.setJapaneseId(getJapaneseKanji());
            wrongFacade.addWrongWord(wrong);
            deleteCorrectJapaneseWord();
            deleteNotknownJapaneseWord();
        }
    }

    public void deleteWrongJapaneseWord() {
        Wrong word = wrongFacade.findByJapaneseIdAndUserId(getJapaneseKanji(), getUserlogin());
        if (word != null) {
            wrongFacade.deleteWrongWord(word);
        }
    }

    public void addNotknownGermanWord() {
        if (notknownFacade.findByGermanIdAndUserId(getGermanWord(), getUserlogin()) == null) {
            Notknown notknown = new Notknown();
            notknown.setUserId(getUserlogin());
            notknown.setGermanId(getGermanWord());
            notknownFacade.addNotknownWord(notknown);
            deleteCorrectGermanWord();
            deleteWrongGermanWord();
        }
    }

    public void deleteNotknownGermanWord() {
        Notknown word = notknownFacade.findByGermanIdAndUserId(getGermanWord(), getUserlogin());
        if (word != null) {
            notknownFacade.deleteNotknownWord(word);
        }
    }

    public void addNotknownJapaneseWord() {
        if (notknownFacade.findByJapaneseIdAndUserId(getJapaneseKanji(), getUserlogin()) == null) {
            Notknown notknown = new Notknown();
            notknown.setUserId(getUserlogin());
            notknown.setJapaneseId(getJapaneseKanji());
            notknownFacade.addNotknownWord(notknown);
            deleteCorrectJapaneseWord();
            deleteWrongJapaneseWord();
        }
    }

    public void deleteNotknownJapaneseWord() {
        Notknown word = notknownFacade.findByJapaneseIdAndUserId(getJapaneseKanji(), getUserlogin());
        if (word != null) {
            notknownFacade.deleteNotknownWord(word);
        }
    }

    public Userlogin getUserlogin() {
        return userloginFacade.findByUsername(SessionBean.getUserName());
    }

    public German getGermanWord() {
        return germanFacade.findByWord(data.getGermanWord());
    }

    public Japanese getJapaneseKanji() {
        return japaneseFacade.findByKanji(data.getJapaneseKanji());
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

    public UserloginFacade getUserloginFacade() {
        if (userloginFacade == null) {
            userloginFacade = new UserloginFacade();
        }
        return userloginFacade;
    }

    public CorrectFacade getCorrectFacade() {
        if (correctFacade == null) {
            correctFacade = new CorrectFacade();
        }
        return correctFacade;
    }

    public WrongFacade getWrongFacade() {
        if (wrongFacade == null) {
            wrongFacade = new WrongFacade();
        }
        return wrongFacade;
    }

    public NotknownFacade getNotknownFacade() {
        if (notknownFacade == null) {
            notknownFacade = new NotknownFacade();
        }
        return notknownFacade;
    }

}
