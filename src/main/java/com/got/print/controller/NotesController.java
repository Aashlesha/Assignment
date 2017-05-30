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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
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
	
	private static final Logger log = LoggerFactory.getLogger(CLASS_NAME);
	
	@Autowired
	private NoteServiceManagerBean noteServiceManagerBean;
	

	@RequestMapping(value = "/users/{userId}/notes/{noteId}", method = {RequestMethod.GET,RequestMethod.HEAD}, produces = UrlConstants.NOTE_MEDIA_TYPE)
	public ResponseEntity<NoteResource> getNoteById(@PathVariable("userId") int userId,@PathVariable("noteId") int noteId,HttpServletRequest request, Locale locale) throws Exception {
		
		try{
			
			NoteDTO noteDto = noteServiceManagerBean.getNoteById(userId);
			
			NoteResource noteResource =NoteDTOConverter.convertToNoteResource(noteDto);
			
			MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
			
			ResponseEntity<NoteResource> responseEntity = new ResponseEntity<NoteResource>(noteResource, headers, HttpStatus.OK);
			
			return responseEntity;
			
		}catch(Exception e){
			
			log.error(e.getMessage());
			throw e;
		}
	}
	
	@RequestMapping(value = "/users/{userId}/notes", method = RequestMethod.POST, consumes = UrlConstants.NOTE_MEDIA_TYPE, produces = UrlConstants.NOTE_MEDIA_TYPE)
	@ResponseBody
	public ResponseEntity<NoteResource> createNote(@PathVariable("userId") int userId,@RequestBody NoteResource noteResource, Locale locale) throws Exception {

		try {
			
			NoteDTO noteDTO = NoteDTOConverter.convertToNoteDTO(noteResource);
			
			noteDTO = noteServiceManagerBean.createNote(noteDTO);
			
			NoteResource res = NoteDTOConverter.convertToNoteResource(noteDTO);
			
			MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
			
			ResponseEntity<NoteResource> responseEntity = new ResponseEntity<NoteResource>(res, headers, HttpStatus.OK);
			
			return responseEntity;

		} catch (Exception e) {

			log.error(e.getMessage());
			throw e;
		}
	}
	
	@RequestMapping(value = "/users/{userId}/notes/{noteId}", method = RequestMethod.PUT, consumes = UrlConstants.NOTE_MEDIA_TYPE, produces = UrlConstants.NOTE_MEDIA_TYPE)
	@ResponseBody
	public ResponseEntity<NoteResource> updateNote(@PathVariable("userId") int userId,@PathVariable("noteId") int noteId,@RequestBody NoteResource noteResource, Locale locale) throws Exception {

		try {
			
			NoteDTO noteDTO = NoteDTOConverter.convertToNoteDTO(noteResource);
			
			noteDTO.setId(noteId);
			
			noteDTO = noteServiceManagerBean.updateNote(noteDTO);
			
			NoteResource res = NoteDTOConverter.convertToNoteResource(noteDTO);
			
			MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
			
			ResponseEntity<NoteResource> responseEntity = new ResponseEntity<NoteResource>(res, headers, HttpStatus.OK);
			
			return responseEntity;

		} catch (Exception e) {

			log.error(e.getMessage());
			throw e;
		}
	}
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/users/{userId}/notes/{noteId}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteFolder(@PathVariable("userId") int userId,@PathVariable("noteId") int noteId, Locale locale) {

		log.info("Delete Phase for Cycle id and folder id provided.");

		try {

			noteServiceManagerBean.deleteNote(noteId);

			return new ResponseEntity(HttpStatus.NO_CONTENT);

		} catch (Exception e) {

			log.error(e.getMessage());
			throw e;
		}
	}
	
	
	private static class NoteDTOConverter {
		
		public static NoteDTO convertToNoteDTO(NoteResource res) throws Exception {

			if (res == null) {
				throw new Exception("Object is null"); 
			}

			NoteDTO dto = new NoteDTO();

			dto.setTitle(res.getTitle());
			dto.setNote(res.getNote());;
			dto.setCreate_time(res.getCreate_time());
			dto.setUpdate_date_time(res.getUpdate_date_time());

			return dto;
		}
		
		public static NoteResource convertToNoteResource(NoteDTO dto) throws Exception {

			if (dto == null) {
				throw new Exception("Object is null"); 
			}

			NoteResource res = new NoteResource();

			res.setId(dto.getId());
			res.setTitle(dto.getTitle());
			res.setNote(dto.getNote());
			res.setCreate_time(dto.getCreate_time());
			res.setUpdate_date_time(dto.getUpdate_date_time());
			
			
		//add for all attributes

			return res;
		}
	}
}
