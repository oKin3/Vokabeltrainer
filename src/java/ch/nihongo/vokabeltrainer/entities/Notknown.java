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
@Table(name = "notknown")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = Notknown.FIND_ALL, query = "SELECT n FROM Notknown n"),
    @NamedQuery(name = Notknown.FIND_BY_ID, query = "SELECT n FROM Notknown n WHERE n.id = :id"),
    @NamedQuery(name = Notknown.FIND_BY_GERMAN_ID_AND_USER_ID, query = "SELECT n FROM Notknown n WHERE n.germanId = :germanId AND n.userId = :userId"),
    @NamedQuery(name = Notknown.FIND_BY_JAPANESE_ID_AND_USER_ID, query = "SELECT n FROM Notknown n WHERE n.japaneseId = :japaneseId AND n.userId = :userId"),
    @NamedQuery(name = Notknown.FIND_ALL_GERMAN_BY_USER_ID, query = "SELECT n FROM Notknown n WHERE n.userId = :userId AND n.germanId IS NOT NULL"),
    @NamedQuery(name = Notknown.FIND_ALL_JAPANESE_BY_USER_ID, query = "SELECT n FROM Notknown n WHERE n.userId = :userId AND n.japaneseId IS NOT NULL")})
public class Notknown implements Serializable {

    private static final long serialVersionUID = -8696880500499417160L;
    public static final String FIND_ALL = "Notknown.findAll";
    public static final String FIND_BY_ID = "Notknown.findById";
    public static final String FIND_BY_GERMAN_ID_AND_USER_ID = "Notknown.findByGermanIdAndUserId";
    public static final String FIND_BY_JAPANESE_ID_AND_USER_ID = "Notknown.findByJapaneseIdAndUserId";
    public static final String FIND_ALL_GERMAN_BY_USER_ID = "Notknown.findAllGermanByUserId";
    public static final String FIND_ALL_JAPANESE_BY_USER_ID = "Notknown.findAllJapaneseByUserId";
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

    public Notknown() {
    }

    public Notknown(Integer id) {
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
        if (!(object instanceof Notknown)) {
            return false;
        }
        Notknown other = (Notknown) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "gg.Notknown[ id=" + id + " ]";
    }

}
