package telran.java29.dao;

import java.util.List;
import java.util.stream.Stream;

import org.springframework.data.mongodb.repository.MongoRepository;

import telran.java29.domain.Post;

public interface ForumRepository extends MongoRepository<Post, Long> {
	List<Post> findByAuthor(String author);

}
