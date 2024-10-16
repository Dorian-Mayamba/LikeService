package uk.movie.LikeService.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.sql.Timestamp;

@Entity
@Table(name = "likes")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Like {
    @Id
    private int id;
    @Column(name = "user_id")
    private int userId;
    @Column(name = "movie_id")
    private int movieId;
    @Column(name = "created_at")
    private Timestamp createdAt;
}
