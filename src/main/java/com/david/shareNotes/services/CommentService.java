package com.david.shareNotes.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.david.shareNotes.entities.Comment;
import com.david.shareNotes.entities.Notes;
import com.david.shareNotes.entities.User;
import com.david.shareNotes.repositories.CommentRepository;
import com.david.shareNotes.types.returnableComment;
import com.david.shareNotes.types.returnableUser;

@Service
public class CommentService {
    private CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public returnableComment sendComment(User user, Notes note, String content) throws Exception {
        try {
            Comment result = new Comment(user, note, content);
            commentRepository.save(result);
            return new returnableComment(new returnableUser(result.getUser().getName(), result.getUser().getId(),
                    result.getUser().isAdmin()), result.getContent(), result.getNote().getId());
        } catch (Exception e) {
            throw new Exception("Couldn't save the comment. reffer to the CommentService");
        }
    }

    public List<returnableComment> getByCommentId(Long id) throws Exception {
        try {
            var result = commentRepository.findByNoteId(id);
            List<returnableComment> list = new ArrayList<returnableComment>();
            for (Comment comment : result) {
                list.add(new returnableComment(
                        new returnableUser(comment.getUser().getName(), comment.getUser().getId(),
                                comment.getUser().isAdmin()),
                        comment.getContent(), comment.getNote().getId()));
            }
            // for each item in result we'll create a returnableComment to return it.
            return list;
        } catch (Exception e) {
            throw new Exception("Couldn't save the comment. reffer to the CommentService");
        }
    }
}
