CREATE TABLE likes(
    id SERIAL PRIMARY KEY,
    user_id int NOT NULL,
    movie_id int NOT NULL,
    created_at TIMESTAMP DEFAULT now()
)