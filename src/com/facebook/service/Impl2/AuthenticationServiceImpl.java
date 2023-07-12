package com.facebook.service.Impl2;

import com.facebook.DAO.AuthenticationDAO;
import com.facebook.DAO.Impl.AuthenticationDAOImpl;
import com.facebook.model.User;
import com.facebook.service.AuthenticationService;

public class AuthenticationServiceImpl implements AuthenticationService {

    private static final AuthenticationDAO AUTHENTICATION_DAO_IMPL = new AuthenticationDAOImpl();

    /**
     * <p>
     * Registers a user in the system
     * </p>
     *
     * @param user The user object containing the information to be used for registration
     * @return true if the user is successfully registered, false otherwise
     */
    public boolean signUp(final User user) {
        return AUTHENTICATION_DAO_IMPL.signUp(user);
    }


    /**
     * <p>
     * Authenticates a user by performing a sign-in operation
     * </p>
     *
     * @param user The user object containing the need for authentication
     * @return true, if the user is successfully authenticated, false otherwise
     */
    public boolean signIn(final User user) {
        return AUTHENTICATION_DAO_IMPL.signIn(user);
    }

    /**
     * <p>
     * Retrieves the user ID associated with the given user
     * </p>
     *
     * @param user The user object for which to retrieve the user id
     * @return Returns user id if found, or null if not found or an error occurs.
     */
    public Long getUserId(final User user) {
        return AUTHENTICATION_DAO_IMPL.getUserId(user);
    }

}