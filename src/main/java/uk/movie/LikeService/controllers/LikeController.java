package uk.movie.LikeService.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    private final LikeService likeService;

    @Operation(summary = "Get movie likes method")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Get Movie likes by movie id",
                    content = {@Content(mediaType = "application/json",
                            array = @ArraySchema(
                                    schema = @Schema(implementation = Like.class)
                            ))
                    }
            ),
            @ApiResponse(responseCode = "500", description = "An error has occurred",
                    content = @Content),
    })
    @GetMapping("{movieId}")
    @ResponseStatus(HttpStatus.OK)
    public List<Like> findByMovieId(@PathVariable int movieId) {
        return likeService.findLikesByMovieId(movieId);
    }

    @Operation(summary = "Like movie method")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Like a movie",
                    content = {@Content(mediaType = "application/json"
                    )
                    }
            ),
            @ApiResponse(responseCode = "500", description = "An error has occurred",
                    content = @Content),
    })
    @PostMapping("{movieId}/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void addOrRemoveLike(@PathVariable int movieId,
                                @PathVariable int userId) {
        likeService.addOrRemoveLike(userId, movieId);
    }
}
