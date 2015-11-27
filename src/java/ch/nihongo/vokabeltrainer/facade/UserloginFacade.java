package ch.nihongo.vokabeltrainer.facade;

import ch.nihongo.vokabeltrainer.dao.UserloginDAO;
import ch.nihongo.vokabeltrainer.entities.Userlogin;
import org.apache.commons.codec.digest.Crypt;

/**
 *
 * @author Niko Reichardt
 */
public class UserloginFacade {

    private UserloginDAO userDAO;

    public UserloginFacade() {
        userDAO = new UserloginDAO();
    }

    public void createAccount(Userlogin login) {
        userDAO.beginTransaction();
        userDAO.persist(login);
        userDAO.commitAndCloseTransaction();
    }

    public Userlogin findByUsername(String username) {
        userDAO.createEntityManager();
        Userlogin user = userDAO.findByUsername(username);
        userDAO.closeEntityManager();
        return user;
    }

    public boolean isUsernameNotExist(String username) {
        userDAO.createEntityManager();
        Userlogin user = userDAO.findByUsername(username);
        userDAO.closeEntityManager();
        return user == null;
    }

    public boolean isEmailNotExist(String email) {
        userDAO.createEntityManager();
        Userlogin user = userDAO.findByEmail(email);
        userDAO.closeEntityManager();
        return user == null;
    }

    public boolean isPasswordCorrect(String username, String password) {
        userDAO.createEntityManager();
        Userlogin user = userDAO.findByUsername(username);
        userDAO.closeEntityManager();
        return user.getPassword().equals(Crypt.crypt(password, user.getPassword()));
    }
}
