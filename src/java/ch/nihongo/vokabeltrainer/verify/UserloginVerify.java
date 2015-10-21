package ch.nihongo.vokabeltrainer.verify;

import ch.nihongo.vokabeltrainer.beans.UserloginData;
import ch.nihongo.vokabeltrainer.entities.Userlogin;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import org.apache.commons.codec.digest.Crypt;

/**
 *
 * @author Niko Reichardt
 */
@ManagedBean
@SessionScoped
public class UserloginVerify implements Serializable {

    private static final long serialVersionUID = 8603021942996924164L;
    private static final String PERSISTENCE_UNIT_NAME = "VokabeltrainerPU";
    private static EntityManagerFactory emf;

    @ManagedProperty(value = "#{userloginData}") // Wird automatisch injiziert
    private UserloginData data;

    public UserloginVerify() {
    }

    public void setData(UserloginData data) {
        this.data = data;
    }
    
    public boolean isPasswordConfirmationCorrect() {
        return data.getPassword().equals(data.getPasswordConfirmation());
    }
    
    public boolean isUsernameNotExist() {
        emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager em = emf.createEntityManager();
        
        // Take user information
        TypedQuery<Userlogin> query = em.createNamedQuery("Userlogin.findByUsername",Userlogin.class);
        query.setParameter("username", data.getUsername());
        
        return query.getResultList().isEmpty();
    }
    
    public boolean isUsernameExist() {
        emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager em = emf.createEntityManager();
        
        // Take user information
        TypedQuery<Userlogin> query = em.createNamedQuery("Userlogin.findByUsername",Userlogin.class);
        query.setParameter("username", data.getUsername());
        
        return !query.getResultList().isEmpty();
    }
    
    public boolean isEmailNotExist() {
        emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager em = emf.createEntityManager();
        
        // Take user information
        TypedQuery<Userlogin> query = em.createNamedQuery("Userlogin.findByEmail",Userlogin.class);
        query.setParameter("email", data.getEmail());
        
        return query.getResultList().isEmpty();
    }
    
    public boolean isPasswordCorrect() {
        emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager em = emf.createEntityManager();
        
        // Take user information
        TypedQuery<Userlogin> query = em.createNamedQuery("Userlogin.findByUsername",Userlogin.class);
        query.setParameter("username", data.getUsername());
        Userlogin user = query.getSingleResult();
        
        return user.getPassword().equals(Crypt.crypt(data.getPassword(), user.getPassword()));
    }

}
