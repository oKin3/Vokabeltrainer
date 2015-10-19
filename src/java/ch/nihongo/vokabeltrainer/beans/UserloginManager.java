package ch.nihongo.vokabeltrainer.beans;

import ch.nihongo.vokabeltrainer.entities.Userlogin;
import ch.nihongo.vokabeltrainer.model.UserloginVerify;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.http.HttpSession;
import org.apache.commons.codec.digest.Crypt;

/**
 *
 * @author Niko Reichardt
 */
@ManagedBean
@SessionScoped
public class UserloginManager implements Serializable {

    private static final long serialVersionUID = 1153676756194853248L;
    private static final String PERSISTENCE_UNIT_NAME = "VokabeltrainerPU";
    private static EntityManagerFactory emf;

    @ManagedProperty(value = "#{userloginData}")
    private UserloginData data;

    @ManagedProperty(value = "#{userloginVerify}")
    private UserloginVerify verify;

    public UserloginManager() {
    }

    public void setData(UserloginData data) {
        this.data = data;
    }

    public void setVerify(UserloginVerify verify) {
        this.verify = verify;
    }

    public String createUser() {
        if (verify.isUsernameNotExist() && verify.isPasswordConfirmationCorrect() && verify.isEmailNotExist()) {

            emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
            EntityManager em = emf.createEntityManager();

            // Create new user
            em.getTransaction().begin();
            Userlogin login = new Userlogin(data.getUsername(), Crypt.crypt(data.getPassword()), data.getEmail(), false);
            em.persist(login);
            em.getTransaction().commit();
            em.close();
            return "ok";
        } else {
            return "create_account";
        }
    }

    public String loginUser() {

        if (verify.isUsernameExist() && verify.isPasswordCorrect()) {
            HttpSession session = SessionBean.getSession();
            session.setAttribute("username", data.getUsername());
            return "vt_welcome";
        }
        return "login";
    }

    public String logoutUser() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "login.jsf?faces-redirect=true";
    }

}
