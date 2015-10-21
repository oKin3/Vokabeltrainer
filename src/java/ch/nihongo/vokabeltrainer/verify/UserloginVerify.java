package ch.nihongo.vokabeltrainer.verify;

import ch.nihongo.vokabeltrainer.beans.UserloginData;
import ch.nihongo.vokabeltrainer.facade.UserloginFacade;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Niko Reichardt
 */
@ManagedBean
@SessionScoped
public class UserloginVerify implements Serializable {

    private static final long serialVersionUID = 8603021942996924164L;
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
        return data.getPassword().equals(data.getPasswordConfirmation());
    }

    public boolean isUsernameNotExist() {
        return userloginFacade.isUsernameNotExist(data.getUsername());
    }

    public boolean isUsernameExist() {
        return !userloginFacade.isUsernameNotExist(data.getUsername());
    }

    public boolean isEmailNotExist() {
        return userloginFacade.isEmailNotExist(data.getEmail());
    }

    public boolean isPasswordCorrect() {
        return userloginFacade.isPasswordCorrect(data.getUsername(), data.getPassword());
    }

    public UserloginFacade getUserloginFacade() {
        if (userloginFacade == null) {
            userloginFacade = new UserloginFacade();
        }
        return userloginFacade;
    }

}
