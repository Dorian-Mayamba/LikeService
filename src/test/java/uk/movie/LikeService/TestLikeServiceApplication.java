package uk.movie.LikeService;

import org.springframework.boot.SpringApplication;

public class TestLikeServiceApplication {

	public static void main(String[] args) {
		SpringApplication.from(LikeServiceApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
