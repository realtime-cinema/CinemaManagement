package org.example.cinemamanagement.controller;

import org.example.cinemamanagement.dto.CommentDTO;
import org.example.cinemamanagement.request.AddCommentRequest;
import org.example.cinemamanagement.request.CommentResponse;
import org.example.cinemamanagement.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping
    public ResponseEntity<?> addComment(@RequestBody AddCommentRequest addCommentRequest) {
        return ResponseEntity.ok(commentService.addComment(addCommentRequest));
    }

    @GetMapping("/{filmId}")
    public ResponseEntity<List<CommentResponse>> getAllCommentByFilmIdWithoutCommentsOfCurrentUserWhoIsLoggin(@PathVariable UUID filmId)
    {
        return ResponseEntity.ok(commentService
                .getAllCommentsOfFilmWithoutCommentOfCurrentUser(filmId));
    }

    @GetMapping("/user")
    public ResponseEntity<List<CommentResponse>> getCommentByFilmIdAndUserId(@RequestBody CommentDTO commentDTO)
    {
        return ResponseEntity.ok(commentService
                .getCommentByFilmIdAndUserId(commentDTO));
    }

    @PutMapping
    public ResponseEntity<?> updateComment(@RequestBody CommentDTO commentDTO) {
        return ResponseEntity.ok(commentService.updateComment(commentDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteComment(@PathVariable UUID id) {
        commentService.deleteComment(id);
        return ResponseEntity.status(HttpStatus.OK).body("Deleted comment successfully");
    }
}
