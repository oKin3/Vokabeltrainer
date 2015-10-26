package ch.nihongo.vokabeltrainer.dao;

import ch.nihongo.vokabeltrainer.entities.Userlogin;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Niko Reichardt
 */
public class UserloginDAO extends GenericDAO<Userlogin> {

    private static final long serialVersionUID = 4935138696441415162L;

    public UserloginDAO() {
        super(Userlogin.class);
    }

    public Userlogin findByEmail(String email) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("email", email);

        return super.findOneResult(Userlogin.FIND_BY_EMAIL, parameters);
    }

    public Userlogin findByUsername(String username) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("username", username);
        Userlogin foundEntity = null;
        List<Userlogin> results = super.findAllResults(Userlogin.FIND_BY_USERNAME, parameters);
        if (!results.isEmpty()) {
            foundEntity = results.get(0);
        }
        return foundEntity;
    }

}
