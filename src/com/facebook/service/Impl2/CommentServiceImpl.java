package com.facebook.service.Impl2;

import com.facebook.DAO.CommentDAO;
import com.facebook.DAO.Impl.CommentDAOImpl;
import com.facebook.model.Comment;
import com.facebook.service.CommentService;

public class CommentServiceImpl implements CommentService {

    private static final CommentDAO COMMENT_DAO_IMPL = CommentDAOImpl.getInstance();
    /**
     * <p>
     * Checks the comment is created
     * </p>
     *
     * @param comment Refer {@link Comment} to create
     * @return boolean - True if the comment is created, otherwise false
     */
    @Override
    public boolean create(Comment comment) {
        return COMMENT_DAO_IMPL.create(comment);
    }

    /**
     * <p>
     * Deletes the comment for post by the id
     * </p>
     *
     * @param id Refers the id for delete the comment
     * @return True if the comment is deleted, otherwise false
     */
    @Override
    public boolean delete(Long id) {
        return COMMENT_DAO_IMPL.delete(id);
    }
}
