package telran.java29.dto;

import java.time.LocalDateTime;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;

@Getter
public class NewPostDto {
	long id;
	String title;
	String content;
	String author;
	Set<String> tags;

}
