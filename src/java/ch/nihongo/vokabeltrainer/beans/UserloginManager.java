package ch.nihongo.vokabeltrainer.beans;

import ch.nihongo.vokabeltrainer.entities.Userlogin;
import ch.nihongo.vokabeltrainer.facade.UserloginFacade;
import ch.nihongo.vokabeltrainer.verify.UserloginVerify;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import org.apache.commons.codec.digest.Crypt;

/**
 *
 * @author Niko Reichardt
 */
@ManagedBean
@RequestScoped
public class UserloginManager {

    private UserloginFacade userloginFacade;

    @ManagedProperty(value = "#{userloginData}")
    private UserloginData data;

    @ManagedProperty(value = "#{userloginVerify}")
    private UserloginVerify verify;

    @PostConstruct
    public void init() {
        userloginFacade = getUserloginFacade();
    }

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
            Userlogin login = new Userlogin(data.getUser().getUsername(), Crypt.crypt(data.getUser().getPassword()), data.getUser().getEmail(), false);
            userloginFacade.createAccount(login);
            return "account_created";
        } else {
            return "create_account";
        }
    }

    public String loginUser() {

        if (verify.isUsernameExist() && verify.isPasswordCorrect()) {
            HttpSession session = SessionBean.getSession();
            session.setAttribute("username", data.getUser().getUsername());
            return "vt_welcome";
        }
        return "login";
    }

    public String logoutUser() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "login.jsf?faces-redirect=true";
    }

    public UserloginFacade getUserloginFacade() {
        if (userloginFacade == null) {
            userloginFacade = new UserloginFacade();
        }
        return userloginFacade;
    }

}
