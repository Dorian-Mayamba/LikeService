package uk.movie.LikeService.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uk.movie.LikeService.entities.Like;
import uk.movie.LikeService.repositories.LikeRepository;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class LikeService {
    private final LikeRepository likeRepository;

    public List<Like> findLikesByMovieId(int movieId){
        return likeRepository.findLikeByMovieId(movieId);
    }

    public List<Like> findLikeByUserId(int userId){
        return likeRepository.findLikeByUserId(userId);
    }

    @Transactional
    public void addOrRemoveLike(int userId, int movieId){
        //Send like entity to kafka topic as a json object
        Optional<Like> likeOptional = likeRepository
                .findLikeByMovieIdAndUserId(movieId,userId);
        if(likeOptional.isPresent()){
            Like like = likeOptional.get();
            log.info("Found like to remove {}", like);
            likeRepository.delete(like);
        }else{
            //Send like entity to kafka topic as a json object
            log.info("Adding new like");
            Like newLike = Like.builder()
                    .movieId(movieId)
                    .userId(userId)
                    .createdAt(Timestamp.from(Instant.now()))
                    .build();
            likeRepository.save(newLike);
        }
    }
}
