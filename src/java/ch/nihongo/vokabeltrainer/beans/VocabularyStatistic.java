package ch.nihongo.vokabeltrainer.beans;

import ch.nihongo.vokabeltrainer.entities.*;
import ch.nihongo.vokabeltrainer.facade.*;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Niko Reichardt
 */
@ManagedBean
@SessionScoped
public class VocabularyStatistic implements Serializable {

    private static final long serialVersionUID = 8803763494632134336L;
    private JapaneseFacade japaneseFacade;
    private GermanFacade germanFacade;
    private CorrectFacade correctFacade;
    private WrongFacade wrongFacade;
    private NotknownFacade notknownFacade;
    private UserloginFacade userloginFacade;

    @PostConstruct
    public void init() {
        japaneseFacade = getJapaneseFacade();
        germanFacade = getGermanFacade();
        correctFacade = getCorrectFacade();
        wrongFacade = getWrongFacade();
        notknownFacade = getNotknownFacade();
        userloginFacade = getUserloginFacade();
    }

    public VocabularyStatistic() {
    }

    public int getGermanWordSize() {
        List<German> german = germanFacade.findAll();
        int anzahl = 0;
        if (german != null) {
            anzahl = german.size();
        }
        return anzahl;
    }

    public int getJapaneseWordSize() {
        List<Japanese> japanese = japaneseFacade.findAll();
        int anzahl = 0;
        if (japanese != null) {
            anzahl = japanese.size();
        }
        return anzahl;
    }
    
    public int getGermanWordSizeByCategory(String category) {
        List<German> german = germanFacade.findByCategory(category);
        return german.size();
    }
    
    public int getJapaneseWordSizeByCategory(String category) {
        List<Japanese> german = japaneseFacade.findByCategory(category);
        return german.size();
    }

    public int getGermanCorrectSize() {
        List<Correct> german = correctFacade.findAllGermanByUserId(getUserlogin());
        int anzahl = 0;
        if (german != null) {
            anzahl = german.size();
        }
        return anzahl;
    }

    public int getJapaneseCorrectSize() {
        List<Correct> japanese = correctFacade.findAllJapaneseByUserId(getUserlogin());
        int anzahl = 0;
        if (japanese != null) {
            anzahl = japanese.size();
        }
        return anzahl;
    }

    public int getGermanWrongSize() {
        List<Wrong> german = wrongFacade.findAllGermanByUserId(getUserlogin());
        int anzahl = 0;
        if (german != null) {
            anzahl = german.size();
        }
        return anzahl;
    }

    public int getJapaneseWrongSize() {
        List<Wrong> japanese = wrongFacade.findAllJapaneseByUserId(getUserlogin());
        int anzahl = 0;
        if (japanese != null) {
            anzahl = japanese.size();
        }
        return anzahl;
    }

    public int getGermanNotknownSize() {
        List<Notknown> german = notknownFacade.findAllGermanByUserId(getUserlogin());
        int anzahl = 0;
        if (german != null) {
            anzahl = german.size();
        }
        return anzahl;
    }

    public int getJapaneseNotknownSize() {
        List<Notknown> japanese = notknownFacade.findAllJapaneseByUserId(getUserlogin());
        int anzahl = 0;
        if (japanese != null) {
            anzahl = japanese.size();
        }
        return anzahl;
    }

    public Userlogin getUserlogin() {
        return userloginFacade.findByUsername(SessionBean.getUserName());
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

    public UserloginFacade getUserloginFacade() {
        if (userloginFacade == null) {
            userloginFacade = new UserloginFacade();
        }
        return userloginFacade;
    }

}
