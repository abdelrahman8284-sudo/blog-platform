package com.abdelrahman.blogplatorm.exceptions;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter@Setter@Builder@AllArgsConstructor
public class ErrorResponseDto {

	private final boolean success =false;
	
	private int status;
	
	private String message;
	
	private List<String> details;
	
	private LocalDateTime timestamp;
	
	private String path;
	
	public ErrorResponseDto() {
		this.timestamp=LocalDateTime.now();
	}
	
	public ErrorResponseDto(String message,List<String> details,String path,int status) {
		this.details=details;
		this.message=message;
		this.path=path;
		this.status=status;
		this.timestamp=LocalDateTime.now();
	}
	
	public ErrorResponseDto(String message,List<String> details,int status) {
		this.details=details;
		this.message=message;
		this.status=status;
		this.timestamp=LocalDateTime.now();
	}
}
