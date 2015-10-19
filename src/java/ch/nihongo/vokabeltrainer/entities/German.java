package ch.nihongo.vokabeltrainer.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Niko Reichardt
 */
@Entity
@Table(name = "german")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "German.findAll", query = "SELECT g FROM German g"),
    @NamedQuery(name = "German.findById", query = "SELECT g FROM German g WHERE g.id = :id"),
    @NamedQuery(name = "German.findByWord", query = "SELECT g FROM German g WHERE g.word = :word"),
    @NamedQuery(name = "German.findBySentence", query = "SELECT g FROM German g WHERE g.sentence = :sentence"),
    @NamedQuery(name = "German.findByCategory", query = "SELECT g FROM German g WHERE g.category = :category")})
public class German implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "word")
    private String word;
    @Column(name = "sentence")
    private String sentence;
    @Basic(optional = false)
    @Column(name = "category")
    @Enumerated(EnumType.STRING)
    private Category category;
    @JoinTable(name = "translation", joinColumns = {
        @JoinColumn(name = "german_id", referencedColumnName = "id")}, inverseJoinColumns = {
        @JoinColumn(name = "japanese_id", referencedColumnName = "id")})
    @ManyToMany
    private List<Japanese> japaneseList;
    @OneToMany(mappedBy = "germanId")
    private List<Correct> correctList;
    @OneToMany(mappedBy = "germanId")
    private List<Notknown> notknownList;
    @OneToMany(mappedBy = "germanId")
    private List<Wrong> wrongList;

    public German() {
    }

    public German(Integer id) {
        this.id = id;
    }

    public German(Integer id, String word, Category category) {
        this.id = id;
        this.word = word;
        this.category = category;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getSentence() {
        return sentence;
    }

    public void setSentence(String sentence) {
        this.sentence = sentence;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @XmlTransient
    public List<Japanese> getJapaneseList() {
        return japaneseList;
    }

    public void setJapaneseList(List<Japanese> japaneseList) {
        this.japaneseList = japaneseList;
    }

    @XmlTransient
    public List<Correct> getCorrectList() {
        return correctList;
    }

    public void setCorrectList(List<Correct> correctList) {
        this.correctList = correctList;
    }

    @XmlTransient
    public List<Notknown> getNotknownList() {
        return notknownList;
    }

    public void setNotknownList(List<Notknown> notknownList) {
        this.notknownList = notknownList;
    }

    @XmlTransient
    public List<Wrong> getWrongList() {
        return wrongList;
    }

    public void setWrongList(List<Wrong> wrongList) {
        this.wrongList = wrongList;
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
        if (!(object instanceof German)) {
            return false;
        }
        German other = (German) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "gg.German[ id=" + id + " ]";
    }
    
}