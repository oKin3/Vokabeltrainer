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
@Table(name = "japanese")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Japanese.findAll", query = "SELECT j FROM Japanese j"),
    @NamedQuery(name = "Japanese.findById", query = "SELECT j FROM Japanese j WHERE j.id = :id"),
    @NamedQuery(name = "Japanese.findByKanji", query = "SELECT j FROM Japanese j WHERE j.kanji = :kanji"),
    @NamedQuery(name = "Japanese.findByKana", query = "SELECT j FROM Japanese j WHERE j.kana = :kana"),
    @NamedQuery(name = "Japanese.findByRomaji", query = "SELECT j FROM Japanese j WHERE j.romaji = :romaji"),
    @NamedQuery(name = "Japanese.findBySentence", query = "SELECT j FROM Japanese j WHERE j.sentence = :sentence"),
    @NamedQuery(name = "Japanese.findByJlpt", query = "SELECT j FROM Japanese j WHERE j.jlpt = :jlpt"),
    @NamedQuery(name = "Japanese.findByCategory", query = "SELECT j FROM Japanese j WHERE j.category = :category")})
public class Japanese implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final String FIND_ALL = "Japanese.findAll";
    public static final String FIND_KANJI = "Japanese.findByKanji";
    public static final String FIND_KANA = "Japanese.findByKana";
    public static final String FIND_ROMAJI = "Japanese.findByRomaji";
    public static final String FIND_JLPT = "Japanese.findByJlpt";
    public static final String FIND_BY_CATEGORY = "Japanese.findByCategory";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "kanji")
    private String kanji;
    @Basic(optional = false)
    @Column(name = "kana")
    private String kana;
    @Basic(optional = false)
    @Column(name = "romaji")
    private String romaji;
    @Column(name = "sentence")
    private String sentence;
    @Basic(optional = false)
    @Column(name = "jlpt")
    private int jlpt;
    @Basic(optional = false)
    @Column(name = "category")
    @Enumerated(EnumType.STRING)
    private Category category;
    @ManyToMany(mappedBy = "japaneseList")
    private List<German> germanList;
    @OneToMany(mappedBy = "japaneseId")
    private List<Correct> correctList;
    @OneToMany(mappedBy = "japaneseId")
    private List<Notknown> notknownList;
    @OneToMany(mappedBy = "japaneseId")
    private List<Wrong> wrongList;

    public Japanese() {
    }

    public Japanese(Integer id) {
        this.id = id;
    }

    public Japanese(Integer id, String kanji, String kana, String romaji, int jlpt, Category category) {
        this.id = id;
        this.kanji = kanji;
        this.kana = kana;
        this.romaji = romaji;
        this.jlpt = jlpt;
        this.category = category;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getKanji() {
        return kanji;
    }

    public void setKanji(String kanji) {
        this.kanji = kanji;
    }

    public String getKana() {
        return kana;
    }

    public void setKana(String kana) {
        this.kana = kana;
    }

    public String getRomaji() {
        return romaji;
    }

    public void setRomaji(String romaji) {
        this.romaji = romaji;
    }

    public String getSentence() {
        return sentence;
    }

    public void setSentence(String sentence) {
        this.sentence = sentence;
    }

    public int getJlpt() {
        return jlpt;
    }

    public void setJlpt(int jlpt) {
        this.jlpt = jlpt;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @XmlTransient
    public List<German> getGermanList() {
        return germanList;
    }

    public void setGermanList(List<German> germanList) {
        this.germanList = germanList;
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
        if (!(object instanceof Japanese)) {
            return false;
        }
        Japanese other = (Japanese) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "gg.Japanese[ id=" + id + " ]";
    }
    
}