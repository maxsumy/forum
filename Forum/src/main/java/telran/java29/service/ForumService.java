package telran.java29.service;

import java.util.List;

import telran.java29.domain.Post;
import telran.java29.dto.DatePeriodDto;
import telran.java29.dto.NewCommentDto;
import telran.java29.dto.NewPostDto;
import telran.java29.dto.PostUpdateDto;

public interface ForumService {
	Post addNewPost(NewPostDto newPost);

	Post getPost(String id);

	Post removePost(String id);
	
	Post updatePost(PostUpdateDto postUpdateDto);
	
	boolean addLike(String id);
	
	Post addComment(String id, NewCommentDto newCommentDto);
	
	Iterable<Post> findPostsByTags(List<String> tags);
	
	Iterable<Post> findPostsByAuthor(String author);
	
	Iterable<Post> findPostsByDates(DatePeriodDto periodDto);	


}
