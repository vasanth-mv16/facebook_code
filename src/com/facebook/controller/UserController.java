package com.facebook.controller;

import com.facebook.model.User;
import com.facebook.service.Impl.UserServiceImpl;
import com.facebook.service.UserService;

/**
 * <p>
 * Given controller act us for request and respond
 * </p>
 *
 * @author vasanth
 * @version 1.0
 */
public class UserController {

    private static UserController userController;
    private static final UserService USER_SERVICE = UserServiceImpl.getInstance();

    /**
     * <p>
     * Default constructor for user controller
     * </p>
     */
    private UserController() {
    }

    /**
     * <p>
     * Gets the instance of user controller
     * </p>
     *
     * @return Returns the singleton instance of the user controller class.
     */
    public static UserController getInstance() {
        if (null == userController) {
            userController = new UserController();
        }

        return userController;
    }

    /**
     * <p>
     * Checks the user to be created
     * </p>
     *
     * @param user Refers {@link User} has to created
     * @return True if the user is created, false otherwise.
     */
    public boolean signUp(final User user) {
        return USER_SERVICE.signUp(user);
    }

    /**
     * <p>
     * Updates the user details.
     * </p>
     *
     * @param user Refers {@link User} has to updated
     * @return True if the user is updated, false otherwise.
     */
    public boolean update(final User user) {
        return USER_SERVICE.update(user);
    }

    /**
     * <p>
     * Checks the user sign in
     * </p>
     *
     * @param user Refers {@link User} has to sign in
     * @return True if the user is sign in, false otherwise.
     */
    public boolean signIn(final User user) {
        return USER_SERVICE.signIn(user);
    }

    /**
     * <p>
     * Checks the user delete details
     * </p>
     *
     * @param id Represents the user id to delete
     * @return True if the user is deleted, false otherwise.
     */
    public boolean delete(final Long id) {
        return USER_SERVICE.delete(id);
    }

    /**
     * <p>
     * Gets the user detail
     * </p>
     *
     * @param id Represents the user through id
     * @return Returns {@link User} details
     */
    public User get(final Long id) {
        return USER_SERVICE.get(id);
    }

    /**
     * <p>
     * Retrieves the id of the user
     * </p>
     *
     * @param user Refers {@link User} to get id
     * @return The id of the user
     */
    public Long getUserId(final User user) {
        return USER_SERVICE.getUserId(user);
    }
}

