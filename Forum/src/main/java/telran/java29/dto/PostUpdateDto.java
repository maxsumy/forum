package telran.java29.dto;

import java.util.Set;

import lombok.Getter;

@Getter
public class PostUpdateDto {
	Long id;
	String title;
	String content;
	Set<String> tags;

}
