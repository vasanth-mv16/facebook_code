package com.facebook.service;

import com.facebook.model.User;

/**
 * <p>
 * Provides service for the user details.
 * </p>
 *
 * @author vasanth
 * @version 1.0
 */
public interface UserService {

    /**
     * <p>
     * Adds user details.
     * </p>
     *
     * @param user Refers {@link User} to add
     * @return True if the user is successfully added, false otherwise
     */
    boolean signUp(final User user);

    /**
     * <p>
     * Updates user details
     * </p>
     *
     * @param user Refers {@link User} to update
     * @return True if the user details are updated, false otherwise
     */
    boolean update(final User user);

    /**
     * <p>
     * Deletes user details.
     * </p>
     *
     * @param id Represents the id of the user to be deleted
     * @return True if the user details are successfully deleted, false otherwise
     */
    boolean delete(final Long id);

    /**
     * <p>
     * Validates user sign-in details.
     * </p>
     *
     * @param user Refer {@link User} to sign in
     * @return True if the sign-in is successful, false otherwise
     */
    boolean signIn(final User user);

    /**
     * <p>
     * Retrieves a user by id
     * </p>
     *
     * @param id Represents the id of the user to retrieve
     * @return Returns {@link User} details
     */
    User get(final Long id);

    /**
     * <p>
     * Retrieves user id
     * </p>
     *
     * @param user Refers {@link User} to get the id
     * @return Returns the id of the user
     */
    Long getUserId(final User user);
}

