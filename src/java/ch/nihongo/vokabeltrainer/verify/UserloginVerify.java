package ch.nihongo.vokabeltrainer.verify;

import ch.nihongo.vokabeltrainer.beans.UserloginData;
import ch.nihongo.vokabeltrainer.facade.UserloginFacade;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Niko Reichardt
 */
@ManagedBean
@RequestScoped
public class UserloginVerify {

    private UserloginFacade userloginFacade;

    @ManagedProperty(value = "#{userloginData}")
    private UserloginData data;

    @PostConstruct
    public void init() {
        userloginFacade = getUserloginFacade();
    }

    public UserloginVerify() {
    }

    public void setData(UserloginData data) {
        this.data = data;
    }

    public boolean isPasswordConfirmationCorrect() {
        if (data.getUser().getPassword().equals(data.getUser().getPasswordConfirmation())) {
            return true;
        }
        FacesContext.getCurrentInstance().addMessage("create_account:passwordConfirmation", new FacesMessage("Error", "Password confirmation doesn't match"));
        return false;
    }

    public boolean isUsernameNotExist() {
        if (userloginFacade.isUsernameNotExist(data.getUser().getUsername())) {
            return true;
        } 
        FacesContext.getCurrentInstance().addMessage("create_account:username", new FacesMessage("Error", "Username already exist"));
        return false;
    }

    public boolean isUsernameExist() {
        return !userloginFacade.isUsernameNotExist(data.getUser().getUsername());
    }

    public boolean isEmailNotExist() {
        if (userloginFacade.isEmailNotExist(data.getUser().getEmail())) {
            return true;
        }
        FacesContext.getCurrentInstance().addMessage("create_account:email", new FacesMessage("Error", "E-Mail already exist"));
        return false;
    }

    public boolean isPasswordCorrect() {
        if (userloginFacade.isPasswordCorrect(data.getUser().getUsername(), data.getUser().getPassword())) {
            return true;
        }
        FacesContext.getCurrentInstance().addMessage("login:password", new FacesMessage("Error", "Password is not correct"));
        return false;
    }

    public UserloginFacade getUserloginFacade() {
        if (userloginFacade == null) {
            userloginFacade = new UserloginFacade();
        }
        return userloginFacade;
    }

}
