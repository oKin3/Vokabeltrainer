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
@Table(name = "wrong")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Wrong.findAll", query = "SELECT w FROM Wrong w"),
    @NamedQuery(name = "Wrong.findById", query = "SELECT w FROM Wrong w WHERE w.id = :id")})
public class Wrong implements Serializable {

    private static final long serialVersionUID = 1L;
    public static final String FIND_BY_GERMAN_ID = "Wrong.findByUserIdAndGermanId";
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

    public Wrong() {
    }

    public Wrong(Integer id) {
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
        if (!(object instanceof Wrong)) {
            return false;
        }
        Wrong other = (Wrong) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "gg.Wrong[ id=" + id + " ]";
    }

}
