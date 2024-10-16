package uk.movie.LikeService.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import uk.movie.LikeService.entities.Like;
import uk.movie.LikeService.services.LikeService;

import java.util.List;

@RestController
@RequestMapping(path = "/likes")
@RequiredArgsConstructor
public class LikeController {
    private LikeService likeService;

    @GetMapping("{movieId}")
    @ResponseStatus(HttpStatus.OK)
    public List<Like> findByMovieId(@PathVariable int movieId){
        return likeService.findLikesByMovieId(movieId);
    }

    @PostMapping("{movieId}/{userId}")
    public void addOrRemoveLike(@PathVariable int movieId,
                                @PathVariable int userId){
        likeService.addOrRemoveLike(userId, movieId);
    }
}
