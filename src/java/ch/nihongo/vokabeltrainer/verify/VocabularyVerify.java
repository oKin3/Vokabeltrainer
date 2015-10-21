package ch.nihongo.vokabeltrainer.verify;

import ch.nihongo.vokabeltrainer.beans.VocabularyData;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Niko Reichardt
 */
@ManagedBean
@SessionScoped
public class VocabularyVerify implements Serializable {

    private static final String PERSISTENCE_UNIT_NAME = "VokabeltrainerPU";
    private static EntityManagerFactory emf;
    private static final long serialVersionUID = 6227771062190138492L;

    @ManagedProperty(value = "#{vocabularyData}")
    private VocabularyData data;

    public VocabularyVerify() {
    }

    public void setData(VocabularyData data) {
        this.data = data;
    }

    

}
