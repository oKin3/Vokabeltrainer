package ch.nihongo.vokabeltrainer.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Niko Reichardt
 */
@Entity
@Table(name = "correct")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = Correct.FIND_ALL, query = "SELECT c FROM Correct c"),
    @NamedQuery(name = Correct.FIND_BY_ID, query = "SELECT c FROM Correct c WHERE c.id = :id"),
    @NamedQuery(name = Correct.FIND_BY_GERMAN_ID_AND_USER_ID, query = "SELECT c FROM Correct c WHERE c.germanId = :germanId AND c.userId = :userId"),
    @NamedQuery(name = Correct.FIND_BY_JAPANESE_ID_AND_USER_ID, query = "SELECT c FROM Correct c WHERE c.japaneseId = :japaneseId AND c.userId = :userId"),
    @NamedQuery(name = Correct.FIND_ALL_GERMAN_BY_USER_ID, query = "SELECT c FROM Correct c WHERE c.userId = :userId AND c.germanId IS NOT NULL"),
    @NamedQuery(name = Correct.FIND_ALL_JAPANESE_BY_USER_ID, query = "SELECT c FROM Correct c WHERE c.userId = :userId AND c.japaneseId IS NOT NULL")})
public class Correct implements Serializable {

    private static final long serialVersionUID = -376763124624343877L;
    public static final String FIND_ALL = "Correct.findAll";
    public static final String FIND_BY_ID = "Correct.findById";
    public static final String FIND_BY_GERMAN_ID_AND_USER_ID = "Correct.findByGermanIdAndUserId";
    public static final String FIND_BY_JAPANESE_ID_AND_USER_ID = "Correct.findByJapaneseIdAndUserId";
    public static final String FIND_ALL_GERMAN_BY_USER_ID = "Correct.findAllGermanByUserId";
    public static final String FIND_ALL_JAPANESE_BY_USER_ID = "Correct.findAllJapaneseByUserId";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @JoinColumn(name = "german_id", referencedColumnName = "id")
    @ManyToOne
    private German germanId;
    @JoinColumn(name = "japanese_id", referencedColumnName = "id")
    @ManyToOne
    private Japanese japaneseId;
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Userlogin userId;

    public Correct() {
    }

    public Correct(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public German getGermanId() {
        return germanId;
    }

    public void setGermanId(German germanId) {
        this.germanId = germanId;
    }

    public Japanese getJapaneseId() {
        return japaneseId;
    }

    public void setJapaneseId(Japanese japaneseId) {
        this.japaneseId = japaneseId;
    }

    public Userlogin getUserId() {
        return userId;
    }

    public void setUserId(Userlogin userId) {
        this.userId = userId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Correct)) {
            return false;
        }
        Correct other = (Correct) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "gg.Correct[ id=" + id + " ]";
    }

}
