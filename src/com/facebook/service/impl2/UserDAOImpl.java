package com.facebook.service.Impl2;

import com.facebook.DAOConnection.JDBCConnection;
import com.facebook.model.User;
import com.facebook.service.UserService;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * <p>
 * Provides the following services for the user
 * </p>
 *
 * @author vasanth
 * @version 1.1
 */
public class UserDAOImpl implements UserService {

    private static UserService userDAOImpl;

    /**
     * <p>
     * Default constructor for user DAO
     * </p>
     */
    public UserDAOImpl() {
    }

    /**
     * <p>
     * Gets the instance of user service implementation
     * </p>
     *
     * @return Returns the singleton instance of the user service implementation class.
     */
    public static UserService getInstance() {
        if (null == userDAOImpl) {
            userDAOImpl = new UserDAOImpl();
        }

        return userDAOImpl;
    }

    /**
     * <p>
     * Signs up a new user by inserting their details into the user table
     * </p>
     *
     * @param user Represents the user details for getting information
     * @return true if the sign-up was successful, false otherwise.
     */
    public boolean signUp(final User user) {
        final String sql = "insert into users(name, phonenumber, password, email, gender, dateofbirth ) values(?, ?, ?, ?, ?::gender, ?)";

        try (final PreparedStatement preparedStatement = JDBCConnection.getConnection().prepareStatement(sql)) {

            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getMobileNumber());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.setString(4, user.getEmail());
            preparedStatement.setString(5, user.getGender().toString());
            preparedStatement.setString(6, user.getDateOfBirth());
            preparedStatement.executeUpdate();

            return true;
        } catch (final Exception exception) {
            System.out.println(exception.getMessage());
        }

        return false;
    }

    /**
     * <p>
     * Checks the users mobile number and password exists in the database
     * </p>
     *
     * @param user {@link User}user object containing the mobile number and password.
     * @return true if a user with the given mobile number and password is found, otherwise false
     */
    public boolean isUserMobileNumberExists(final User user) {
        final String sql = "select * from users where phonenumber = ? and password = ?;";

        try (final PreparedStatement preparedStatement = JDBCConnection.getConnection().prepareStatement(sql)) {

            preparedStatement.setString(1, user.getMobileNumber());
            preparedStatement.setString(2, user.getPassword());
            final ResultSet resultSet = preparedStatement.executeQuery();

            return resultSet.next();
        } catch (final Exception exception) {
            System.out.println(exception.getMessage());
        }

        return false;
    }

    /**
     * <p>
     * Checks the users email and password exists in the database
     * </p>
     *
     * @param user {@link User}user object containing the mobile number and password.
     * @return true if a user with the given mobile number and password is found, otherwise false
     */
    public boolean isUserEmailExists(final User user) {
        final String sql = "select * from users where email = ? and password = ?";

        try (final PreparedStatement preparedStatement = JDBCConnection.getConnection().prepareStatement(sql)) {

            preparedStatement.setString(1, user.getEmail());
            preparedStatement.setString(2, user.getPassword());
            final ResultSet resultSet = preparedStatement.executeQuery();

            return resultSet.next();
        } catch (final Exception exception) {
            System.out.println(exception.getMessage());
        }

        return false;
    }

    /**
     * <p>
     * Retrieves a user from the database through id
     * </p>
     *
     * @param id Retrieves the id of the user
     * @return Returns {@link User}with the specified ID if found, otherwise null
     */
    public User get(final Long id) {
        final String sql = "select * from users where id = ?";

        try (final PreparedStatement preparedStatement = JDBCConnection.getConnection().prepareStatement(sql)) {

            preparedStatement.setLong(1, id);
            final ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                final User user = new User();

                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setEmail(resultSet.getString("email"));
                user.setGender(User.Gender.valueOf(resultSet.getString("gender")));
                user.setDateOfBirth(resultSet.getString("dateofbirth"));
                user.setPassword(resultSet.getString("password"));
                user.setMobileNumber(resultSet.getString("phonenumber"));

                return user;
            }
        } catch (final Exception exception) {
            System.out.println(exception.getMessage());
        }

        return null;
    }

    /**
     * <p>
     * Retrieves the id of the user based on mobile number and email
     * </p>
     *
     * @param user {@link User}user object containing the mobile number and email address
     * @return Returns the id of the user if found, otherwise null
     */
    @Override
    public Long getUserId(final User user) {
        final String sql = "select id from users where phonenumber = ? or email = ?";

        try (final PreparedStatement preparedStatement = JDBCConnection.getConnection().prepareStatement(sql)) {

            preparedStatement.setString(1, user.getMobileNumber());
            preparedStatement.setString(2, user.getEmail());
            final ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getLong("id");
            }
        } catch (final Exception exception) {
            System.out.println(exception.getMessage());
        }

        return null;
    }

    /**
     * <p>
     * Deletes a user from the database based on id
     * </p>
     *
     * @param id Refers the id of the user to delete
     * @return Returns true if the user was successfully deleted, otherwise false
     */
    public boolean delete(final Long id) {
        final String sql = "delete from users where id = ?";

        try (final PreparedStatement preparedStatement = JDBCConnection.getConnection().prepareStatement(sql)) {

            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();

            return true;
        } catch (final Exception exception) {
            System.out.println(exception.getMessage());
        }

        return false;
    }

    /**
     * <p>
     * Signs in a user by checking email or mobile number
     * </p>
     *
     * @param user Refers {@link User}user to sign in
     * @return true if the user is successfully signed in, otherwise false
     */
    @Override
    public boolean signIn(final User user) {
        return (null != user.getEmail() ? isUserEmailExists(user) : isUserMobileNumberExists(user));
    }

    /**
     * <p>
     * Updates the details of a user in the database
     * </p>
     *
     * @param user Refers {@link User}user object containing the updated user information.
     * @return true if the user information is successfully updated, false otherwise.
     */
    public boolean update(final User user) {
        final String sql = "update users set name = ?, phonenumber = ?, email = ?, password = ? where id = ?";

        try (final PreparedStatement preparedStatement = JDBCConnection.getConnection().prepareStatement(sql)){

            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getMobileNumber());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.setLong(5, user.getId());
            preparedStatement.executeUpdate();

            return true;
        } catch (final Exception exception) {
            System.out.println(exception.getMessage());
        }

        return false;
    }
}

