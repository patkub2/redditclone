package pl.kanterbow.redditClone.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.kanterbow.redditClone.model.Comment;
import pl.kanterbow.redditClone.model.Post;
import pl.kanterbow.redditClone.model.User;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByPost(Post post);

    List<Comment> findAllByUser(User user);
}
