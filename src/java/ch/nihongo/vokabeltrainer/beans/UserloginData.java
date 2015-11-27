package ch.nihongo.vokabeltrainer.beans;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author Niko Reichardt
 */
@ManagedBean
@RequestScoped
public class UserloginData {

    private ch.nihongo.vokabeltrainer.model.UserloginData user;

    public UserloginData() {
    }

    @PostConstruct
    public void init() {
        user = new ch.nihongo.vokabeltrainer.model.UserloginData();
    }

    public ch.nihongo.vokabeltrainer.model.UserloginData getUser() {
        return user;
    }

    public void setUser(ch.nihongo.vokabeltrainer.model.UserloginData user) {
        this.user = user;
    }

}
