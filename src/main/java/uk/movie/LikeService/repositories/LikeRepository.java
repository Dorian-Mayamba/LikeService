package uk.movie.LikeService.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uk.movie.LikeService.entities.Like;

import java.util.List;
import java.util.Optional;

@Repository
public interface LikeRepository extends JpaRepository<Like, Integer> {
    Optional<Like> findLikeByMovieIdAndUserId(int movieId, int userId);
    List<Like> findLikeByMovieId(int movieId);
    List<Like> findLikeByUserId(int userId);
}
