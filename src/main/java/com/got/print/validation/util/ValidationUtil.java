package com.got.print.validation.util;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.got.print.home.NoteHome;
import com.got.print.home.NoteUserHome;
import com.got.print.persistance.Note;
import com.got.print.persistance.Note_User;
import com.got.print.resource.NoteResource;

@Component
public class ValidationUtil {

	public static final int MAX_TITLE_LENGTH = 50;
	
	public static final int MAX_NOTE_LENGTH = 1000;

	@Autowired
	private NoteUserHome noteUserHome;
	
	@Autowired
	private NoteHome noteHome;

	public static boolean isNumber(String s) {
	    
		boolean isNumber = false;		      
		try{		         
	    	Integer.parseInt(s);
	        isNumber = true;		 
		}catch (NumberFormatException ex){
	      //Not a number
			isNumber=false;
	      }		 
	      return isNumber;
	   }
	
	public static void validateResource(NoteResource res) throws Exception{
	
		String title = res.getTitle();
		
		if(null == title){
			
			throw new Exception("Title cannot be blank");
		}
		
		if(title.length() > MAX_TITLE_LENGTH){
			
			throw new Exception("Title length cannot be greater than "+MAX_TITLE_LENGTH);
			
		}
		
		String note = res.getNote();
		
		if(null != note && note.length() > MAX_NOTE_LENGTH ){
			
			throw new Exception("Note length cannot be greater than "+MAX_NOTE_LENGTH+" characters");
			
		}

	}
	
	public void validateUserId(String userId) throws Exception{
		
		if(!isNumber(userId)){
			
			throw new Exception("No Note User object found with id :" + userId);
			
		}
		
		 Note_User note_User = noteUserHome.getNoteUserById(Integer.valueOf(userId));
		 
		 if(null == note_User ){
			 
			 throw new Exception("No Note User object found with id :" + userId);
			 
		 }
		
	}

	public void validateNoteId(String userId, String noteId) throws Exception{
		
		if(!isNumber(noteId)){
			
			throw new Exception("No Note object found with id :" + noteId);
			
		}
		
		 Note note  = noteHome.getNoteById(Integer.valueOf(noteId));
		 
		 if(null == note ){
			 
			 throw new Exception("No Note object found with id :" + noteId);
			 
		 }
		 int user = Integer.valueOf(userId);
		 
		 if(user != note.getUser().getId()){
			 
			 throw new Exception("Note and User details mismatch");
		 }
	}
}
