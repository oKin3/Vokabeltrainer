package ch.nihongo.vokabeltrainer.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "userlogin")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = Userlogin.FIND_ALL, query = "SELECT u FROM Userlogin u"),
    @NamedQuery(name = Userlogin.FIND_BY_ID, query = "SELECT u FROM Userlogin u WHERE u.id = :id"),
    @NamedQuery(name = Userlogin.FIND_BY_USERNAME, query = "SELECT u FROM Userlogin u WHERE u.username = :username"),
    @NamedQuery(name = Userlogin.FIND_BY_EMAIL, query = "SELECT u FROM Userlogin u WHERE u.email = :email"),
    @NamedQuery(name = Userlogin.FIND_BY_ADMIN, query = "SELECT u FROM Userlogin u WHERE u.admin = :admin")})
public class Userlogin implements Serializable {

    private static final long serialVersionUID = 1L;
    public static final String FIND_ALL = "Userlogin.findAll";
    public static final String FIND_BY_ID = "Userlogin.findById";
    public static final String FIND_BY_USERNAME = "Userlogin.findByUsername";
    public static final String FIND_BY_EMAIL = "Userlogin.findByEmail";
    public static final String FIND_BY_ADMIN = "Userlogin.findByAdmin";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "username")
    private String username;
    @Basic(optional = false)
    @Column(name = "password")
    private String password;
    @Basic(optional = false)
    @Column(name = "email")
    private String email;
    @Basic(optional = false)
    @Column(name = "admin")
    private boolean admin;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userId")
    private List<Correct> correctList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userId")
    private List<Notknown> notknownList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userId")
    private List<Wrong> wrongList;

    public Userlogin() {
    }

    public Userlogin(Integer id) {
        this.id = id;
    }

    public Userlogin(String username, String password, String email, boolean admin) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.admin = admin;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean getAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
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
        if (!(object instanceof Userlogin)) {
            return false;
        }
        Userlogin other = (Userlogin) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "gg.Userlogin[ id=" + id + " ]";
    }

}
