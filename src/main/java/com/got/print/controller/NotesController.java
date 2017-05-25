package com.got.print.controller;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.got.print.dto.NoteDTO;
import com.got.print.resource.NoteResource;
import com.got.print.service.NoteServiceManagerBean;
import com.got.print.validation.util.UrlConstants;


@RestController
@RequestMapping(value = "/gotPrint")
public class NotesController {
	
	/**
	 * Reference to the name of this class for logging purposes.
	 */
	private static String CLASS_NAME = NotesController.class.getName();
	private static final Logger logger = LoggerFactory.getLogger(CLASS_NAME);
	
	@Autowired
	private NoteServiceManagerBean noteServiceManagerBean;
	

	@RequestMapping(value = "users/{userId}/notes/{noteId}", method = {RequestMethod.GET,RequestMethod.HEAD}, produces = UrlConstants.NOTE_MEDIA_TYPE)
	public ResponseEntity<NoteResource> getPeriodById(@PathVariable("userId") String userId,@PathVariable("noteId") String noteId,HttpServletRequest request, Locale locale) throws Exception {
		
		try{
			
			NoteDTO noteDto = noteServiceManagerBean.getNoteById();
			
			NoteResource noteResource =NoteDTOConverter.convertToNoteResource(noteDto);
			
			MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
			
			ResponseEntity<NoteResource> responseEntity = new ResponseEntity<NoteResource>(
					noteResource, headers, HttpStatus.OK);
			return responseEntity;
			
		}catch(Exception e){
			
			logger.error(e.getMessage());
			throw e;
		}
	}
	
	public void createNote(){}
	
	public void updateNote(){}
	
	public void deleteNote(){}
	
	public void getAllNotes(){}
	
	private static class NoteDTOConverter {
		
		public static NoteDTO convertToNoteDTO(NoteResource res) throws Exception {

			if (res == null) {
				throw new Exception("Object is null"); 
			}

			NoteDTO dto = new NoteDTO();

			dto.setTitle(res.getTitle());
		//add for all attributes

			return dto;
		}
		
		public static NoteResource convertToNoteResource(NoteDTO dto) throws Exception {

			if (dto == null) {
				throw new Exception("Object is null"); 
			}

			NoteResource res = new NoteResource();

			res.setTitle(dto.getTitle());
		//add for all attributes

			return res;
		}
	}
}
