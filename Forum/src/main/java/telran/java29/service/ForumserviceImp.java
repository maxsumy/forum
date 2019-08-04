package telran.java29.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import telran.java29.dao.ForumRepository;
import telran.java29.domain.Comment;
import telran.java29.domain.Post;
import telran.java29.dto.DatePeriodDto;
import telran.java29.dto.NewCommentDto;
import telran.java29.dto.NewPostDto;
import telran.java29.dto.PostUpdateDto;

@Service
public class ForumserviceImp implements ForumService {
	@Autowired
	ForumRepository forumRepository;
	
	@Override
	public Post addNewPost(NewPostDto newPost) {
		boolean res = forumRepository.existsById(newPost.getId());
		if(res) {
			return null;
		}
		
		Post post = new Post(newPost.getId(), newPost.getTitle(),newPost.getContent(), newPost.getAuthor(), newPost.getTags() );
		forumRepository.save(post);
		return post;
	}

	@Override
	public Post getPost(String id) {
		Long _id = Long.parseLong(id);
		Post post = forumRepository.findById(_id).orElse(null);
		return post == null ? null : post;
			
	}

	@Override
	public Post removePost(String id) {
		Long _id = Long.parseLong(id);
		boolean res = forumRepository.existsById(_id);
		if(!res) {
			return null;
		}
		
		Post post = forumRepository.findById(_id).orElse(null);
		forumRepository.deleteById(_id);
		
		return post;
	}

	@Override
	public Post updatePost(PostUpdateDto postUpdateDto) {
		boolean res = forumRepository.existsById(postUpdateDto.getId());
		if(!res) {
			return null;
		}
		Post post = forumRepository.findById(postUpdateDto.getId()).orElse(null);
		
		if(postUpdateDto.getTitle() != null) {
			post.setTitle(postUpdateDto.getTitle());
		}
		
		if(postUpdateDto.getContent() != null) {
			post.setContent(postUpdateDto.getContent());
		}
		
		if(postUpdateDto.getTags() != null) {
			for (String tag : postUpdateDto.getTags()) {
				post.addTag(tag);
				
			}
		}
		
		forumRepository.save(post);
		
		return post;
	}

	@Override
	public boolean addLike(String id) {
		Long _id = Long.parseLong(id);
		boolean res = forumRepository.existsById(_id);
		if(!res) {
			return false;
		}
		
		Post post = forumRepository.findById(_id).orElse(null);
		post.addLike();
		forumRepository.save(post);
		return true;
	}

	@Override
	public Post addComment(String id, NewCommentDto newCommentDto) {
		Long _id = Long.parseLong(id);
		boolean res = forumRepository.existsById(_id);
		if(!res) {
			return null;
		}
		
		Comment comment = new Comment(newCommentDto.getUser(), newCommentDto.getMessage());
		Post post = forumRepository.findById(_id).orElse(null);
		post.addComment(comment);
		forumRepository.save(post);
		
		
		return post;
	}

	@Override
	public Iterable<Post> findPostsByTags(List<String> tags) {
		
		return forumRepository.findByTags(tags);
	}

	@Override
	public Iterable<Post> findPostsByAuthor(String author) {
		
		return forumRepository.findByAuthor(author);
				
	}

	@Override
	public Iterable<Post> findPostsByDates(DatePeriodDto periodDto) {
		
		return forumRepository.findBydateCreated(periodDto.getFrom(), periodDto.getTo());
	}

}
