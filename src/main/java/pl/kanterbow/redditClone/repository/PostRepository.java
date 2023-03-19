package pl.kanterbow.redditClone.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.kanterbow.redditClone.model.Comment;
import pl.kanterbow.redditClone.model.Post;
import pl.kanterbow.redditClone.model.Subreddit;
import pl.kanterbow.redditClone.model.User;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findAllBySubreddit(Subreddit subreddit);

    List<Post> findByUser(User user);
}
