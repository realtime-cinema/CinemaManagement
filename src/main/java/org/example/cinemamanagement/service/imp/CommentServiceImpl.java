package org.example.cinemamanagement.service.imp;

import org.example.cinemamanagement.dto.CommentDTO;
import org.example.cinemamanagement.model.Comment;
import org.example.cinemamanagement.model.Film;
import org.example.cinemamanagement.model.User;
import org.example.cinemamanagement.repository.CommentRepository;
import org.example.cinemamanagement.repository.FilmRepository;
import org.example.cinemamanagement.repository.UserRepository;
import org.example.cinemamanagement.request.AddCommentRequest;
import org.example.cinemamanagement.request.CommentResponse;
import org.example.cinemamanagement.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private FilmRepository filmRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<CommentResponse> getAllCommentByFilmIdAndExceptForUserID(CommentDTO commentDTO) {
        List<Comment> comments = commentRepository
                .findAllCommentByFilmIdAndExceptForUserId(commentDTO.getFilmId(), commentDTO.getUserId());
        return comments.stream()
                .map(comment -> CommentResponse.builder()
                                    // Khong can tra ve commentId va userId
                                    .fullName(comment.getUser().getLastname() + " " + comment.getUser().getFirstname())
                                    .comment(comment.getBody())
                                    .build()
                             ).collect(Collectors.toList());
    }

    @Override
    public List<CommentResponse> getCommentByFilmIdAndUserId(CommentDTO commentDTO) {
        List<Comment> comments = commentRepository.findCommentByFilmIdAndUserId(commentDTO.getFilmId(), commentDTO.getUserId());
        return comments.stream()
                .map(comment -> CommentResponse.builder()
                        .commentId(comment.getId()) // Tra them ve commentId de sua va xoa
                        .fullName(comment.getUser().getLastname() + " " + comment.getUser().getFirstname())
                        .comment(comment.getBody())
                        .build()
                ).collect(Collectors.toList());
    }

    @Override
    public String addComment(AddCommentRequest addCommentRequest) {
        Comment comment = new Comment();
        User user = userRepository.findById(addCommentRequest.getUserId())
                        .orElseThrow(() -> new RuntimeException("User not found"));
        Film film = filmRepository.findById(addCommentRequest.getDestId())
                        .orElseThrow(() -> new RuntimeException("Film not found"));
        if (addCommentRequest.getBody() == null) {
            throw new RuntimeException("Comment not allow null");
        }

        // Sao sai builder db khong nhan tham so :v
        comment.setUser(user);
        comment.setFilm(film);
        comment.setBody(addCommentRequest.getBody());

        commentRepository.save(comment);
        return "Display: " + user.getLastname() + " " + user.getFirstname() + " / " + film.getTitle() + " / " + addCommentRequest.getBody();
    }

    @Override
    public CommentDTO updateComment(CommentDTO commentDTO) {
        Comment comment = commentRepository.findById(commentDTO.getCommentId())
                .orElseThrow(() -> new RuntimeException("Comment not found"));
        if (commentDTO.getBody() == null)
            throw new RuntimeException("Comment not allow NULL");
        comment.setBody(commentDTO.getBody());
        commentRepository.save(comment);
        return CommentDTO.builder()
                .commentId(comment.getId())  // test tra ve 2 tham so can kiem tra
                .body(comment.getBody())
                .build();
    }

    @Override
    public void deleteComment(UUID id) {
        commentRepository.deleteById(id);
    }
}
