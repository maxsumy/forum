package telran.java29.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;

@Getter
public class NewCommentDto {
	String user;
	String message;
//	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
//	LocalDateTime dateCreated;

}
