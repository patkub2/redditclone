package pl.kanterbow.redditClone.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.kanterbow.redditClone.model.Post;
import pl.kanterbow.redditClone.model.User;
import pl.kanterbow.redditClone.model.Vote;

import java.util.Optional;

@Repository
public interface VoteRepository extends JpaRepository<Vote, Long> {
    Optional<Vote> findTopByPostAndUserOrderByVoteIdDesc(Post post, User currentUser);
}
