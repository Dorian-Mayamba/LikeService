package uk.movie.LikeService.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uk.movie.LikeService.entities.Like;
import uk.movie.LikeService.repositories.LikeRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LikeService {
    private final LikeRepository likeRepository;

    public List<Like> findLikesByMovieId(int movieId){
        return likeRepository.findLikeByMovieId(movieId);
    }

    public List<Like> findLikeByUserId(int userId){
        return likeRepository.findLikeByUserId(userId);
    }

    public void addOrRemoveLike(int userId, int movieId){
        likeRepository.findLikeByMovieIdAndUserId(
                movieId,userId
        ).ifPresentOrElse((like -> {
            likeRepository.delete(like);
            //Send like entity to kafka topic as a json object
        }), ()->{
            Like like = Like.builder()
                    .movieId(movieId)
                    .userId(userId)
                    .build();
            likeRepository.save(like);
            //Send like entity to kafka topic as a json object
        });
    }
}
