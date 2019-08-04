package telran.java29.dao;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import telran.java29.domain.Post;


public interface ForumRepository extends MongoRepository<Post, Long> {
	List<Post> findByAuthor(String author);
	
	@Query("{dateCreated:{'$gte':?0, '$lte':?1}}")
	List<Post> findBydateCreated(LocalDateTime from, LocalDateTime to);
	
	@Query("{tags:{$in:[?0]}}")
	List<Post> findByTags(List<String> tags);

}
