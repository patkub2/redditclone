package pl.kanterbow.redditClone.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.kanterbow.redditClone.model.Subreddit;


import java.util.Optional;

@Repository
public interface  SubredditRepository extends JpaRepository<Subreddit, Long> {
    Optional<Subreddit> findByName(String subredditName);

}
