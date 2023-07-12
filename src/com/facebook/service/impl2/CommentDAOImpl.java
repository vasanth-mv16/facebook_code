package com.facebook.service.impl2;

import com.facebook.DAOConnection.JDBCConnection;
import com.facebook.model.Comment;
import com.facebook.service.CommentService;

import java.sql.PreparedStatement;

/**
 * <p>
 * Provides the service for comment DAO
 * </p>
 *
 * @author vasanth
 * @version 1.1
 */
public class CommentDAOImpl implements CommentService {

    private static CommentService commentDAOImpl;

    /**
     * <p>
     * Default constructor for comment DAO
     * </p>
     */
    private CommentDAOImpl() {
    }

    /**
     * <p>
     * Gets the instance of comment service implementation
     * </p>
     *
     * @return Returns the singleton instance of the comment service implementation class.
     */
    public static CommentService getInstance() {
        if (null == commentDAOImpl) {
            commentDAOImpl = new CommentDAOImpl();
        }

        return commentDAOImpl;
    }

    /**
     * <p>
     * Creates a comment and inserts it into the database
     * </p>
     *
     * @param comment Refers the like to create and insert.
     * @return True, if the post was created and inserted successfully, otherwise false
     */
    @Override
    public boolean create(final Comment comment) {
        final String sql = "insert into comments(user_id, post_id, message) values (?,?,?);";

        try {
            PreparedStatement preparedStatement = JDBCConnection.getConnection().prepareStatement(sql);

            preparedStatement.setLong(1, comment.getUserId());
            preparedStatement.setLong(2, comment.getPostId());
            preparedStatement.setString(3, comment.getMessage());
            preparedStatement.executeUpdate();

            return true;
        } catch (final Exception exception) {
            System.out.println(exception.getMessage());
        }

        return false;
    }

    /**
     * <p>
     * Deletes comment with the id from the database
     * </p>
     *
     * @param id Refer the id of the comment to delete
     * @return True, if the comment was deleted successfully, otherwise false
     */
    @Override
    public boolean delete(Long id) {
        final String sql = "delete from comments where id = ?";

        try (final PreparedStatement preparedStatement = JDBCConnection.getConnection().prepareStatement(sql)) {

            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();

            return true;
        } catch (final Exception exception) {
            System.out.println(exception.getMessage());
        }

        return false;
    }
}
