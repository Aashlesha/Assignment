package com.got.print.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
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
import com.got.print.validation.util.ValidationUtil;


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
	
	@Autowired
	private ValidationUtil validationUtil;
	
	@RequestMapping(value = "/users/{userId}/notes/{noteId}", method = {RequestMethod.GET,RequestMethod.HEAD}, produces = UrlConstants.NOTE_MEDIA_TYPE)
	public ResponseEntity<NoteResource> getNoteById(@PathVariable("userId") String userId,@PathVariable("noteId") String noteId,HttpServletRequest request, Locale locale) throws Exception {
		
		try{
			
			validationUtil.validateUserId(userId);
			validationUtil.validateNoteId(userId, noteId);

			int nId = Integer.valueOf(noteId);
			int uId = Integer.valueOf(userId);

			NoteDTO noteDto = noteServiceManagerBean.getNoteById(nId, uId);

			NoteResource noteResource = NoteDTOConverter.convertToNoteResource(noteDto);

			MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();

			Link link = new Link(UrlConstants.APPLICATION_URI+ UrlConstants.USERS_URI + userId + UrlConstants.NOTE_URI,Link.REL_PREVIOUS);

			noteResource.add(link);

			ResponseEntity<NoteResource> responseEntity = new ResponseEntity<NoteResource>(noteResource, headers, HttpStatus.OK);

			return responseEntity;

		}catch(Exception e){
			
			log.error(e.getMessage());
			throw e;
		}
	}
	
	@RequestMapping(value = "/users/{userId}/notes", method = RequestMethod.POST, consumes = UrlConstants.NOTE_MEDIA_TYPE, produces = UrlConstants.NOTE_MEDIA_TYPE)
	@ResponseBody
	public ResponseEntity<NoteResource> createNote(@PathVariable("userId") String userId,@RequestBody NoteResource noteResource, Locale locale) throws Exception {

		try {
			
			validationUtil.validateUserId(userId);
			
			ValidationUtil.validateResource(noteResource);

			noteResource.setCreate_time(new Date());
			noteResource.setUpdate_date_time(new Date());
			noteResource.setNoteUser(Integer.parseInt(userId));
			
			NoteDTO noteDTO = NoteDTOConverter.convertToNoteDTO(noteResource);

			noteDTO = noteServiceManagerBean.createNote(noteDTO);

			NoteResource res = NoteDTOConverter.convertToNoteResource(noteDTO);

			MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
			
			Link link = new Link(UrlConstants.APPLICATION_URI+ UrlConstants.USERS_URI + userId+ UrlConstants.NOTE_URI, Link.REL_PREVIOUS);

			res.add(link);

			ResponseEntity<NoteResource> responseEntity = new ResponseEntity<NoteResource>(res, headers, HttpStatus.OK);

			return responseEntity;

		} catch (Exception e) {

			log.error(e.getMessage());
			throw e;
		}
	}
	
	@RequestMapping(value = "/users/{userId}/notes/{noteId}", method = RequestMethod.PUT, consumes = UrlConstants.NOTE_MEDIA_TYPE, produces = UrlConstants.NOTE_MEDIA_TYPE)
	@ResponseBody
	public ResponseEntity<NoteResource> updateNote(@PathVariable("userId") String userId,@PathVariable("noteId") String noteId,@RequestBody NoteResource noteResource, Locale locale) throws Exception {

		try {
			
			validationUtil.validateUserId(userId);
			
			validationUtil.validateNoteId(userId, noteId);
			
			ValidationUtil.validateResource(noteResource);

			NoteDTO noteDto = noteServiceManagerBean.getNoteById(Integer.valueOf(noteId), Integer.valueOf(userId));
			
			noteDto.setNote(noteResource.getNote());
			noteDto.setTitle(noteResource.getTitle());
			noteDto.setUpdate_date_time(new Date());
			
			
			noteDto = noteServiceManagerBean.updateNote(noteDto);
			
			NoteResource res = NoteDTOConverter.convertToNoteResource(noteDto);
			
			MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
			
			Link link = new Link(UrlConstants.APPLICATION_URI+ UrlConstants.USERS_URI + userId+ UrlConstants.NOTE_URI, Link.REL_PREVIOUS);

			res.add(link);
			
			ResponseEntity<NoteResource> responseEntity = new ResponseEntity<NoteResource>(res, headers, HttpStatus.OK);
			
			return responseEntity;

		} catch (Exception e) {

			log.error(e.getMessage());
			throw e;
		}
	}
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/users/{userId}/notes/{noteId}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteFolder(@PathVariable("userId") String userId,@PathVariable("noteId") String noteId, Locale locale) throws Exception {

		log.info("Delete Phase for Cycle id and folder id provided.");

		try {

			validationUtil.validateUserId(userId);
			
			validationUtil.validateNoteId(userId,noteId);
			
			noteServiceManagerBean.deleteNote(Integer.valueOf(noteId));

			return new ResponseEntity(HttpStatus.NO_CONTENT);

		} catch (Exception e) {

			log.error(e.getMessage());
			throw e;
		}
	}
	
	@RequestMapping(value = "/authorization", method = {RequestMethod.POST}, produces = "text/plain")
	@ResponseBody
	public ResponseEntity<String> getToken(@RequestBody String userDetails,HttpServletRequest request, Locale locale) throws Exception {
		
		try{
			if(null == userDetails){
				
				throw new Exception("User details missing");
			}
			
			
			List<String> detailList = Arrays.asList(userDetails.split("&"));
			
			if (null == detailList || !userDetails.contains("&") || detailList.size() !=2) {

				throw new Exception("Invalid user details.");
			}
			
			String userId  = detailList.get(0);
			
			String password  = detailList.get(1);
			
			System.out.println(userId + "pass :"+password);

			MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
			
			ResponseEntity<String> responseEntity = new ResponseEntity<String>(userDetails, headers, HttpStatus.OK);
			return responseEntity;

		}catch(Exception e){
			
			log.error(e.getMessage());
			throw e;
		}
	}
	
	@RequestMapping(value = "/users/{userId}/notes/", method = {RequestMethod.GET}, produces = UrlConstants.NOTE_COLLECTION_MEDIA_TYPE)
	public ResponseEntity<List<NoteResource>> getNotes(@PathVariable("userId") String userId,String noteId,HttpServletRequest request, Locale locale) throws Exception {
		
		try{
			
			validationUtil.validateUserId(userId);

			int uId = Integer.valueOf(userId);

			List<NoteDTO> noteDtoList = noteServiceManagerBean.getNotes(uId);

			List<NoteResource> noteResources = new ArrayList<NoteResource>();
			
			for(int i = 0; i< noteDtoList.size();i++){
				
				NoteResource resource = NoteDTOConverter.convertToNoteResource(noteDtoList.get(i));
				
				Link link = new Link(UrlConstants.APPLICATION_URI+ UrlConstants.USERS_URI + userId + UrlConstants.NOTE_URI,Link.REL_PREVIOUS);

				resource.add(link);
				
				noteResources.add(resource);
			}

			MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();



			ResponseEntity<List<NoteResource>> responseEntity = new ResponseEntity<List<NoteResource>>(noteResources, headers, HttpStatus.OK);

			return responseEntity;

		}catch(Exception e){
			
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

			dto.setId(res.getNote_id());
			dto.setTitle(res.getTitle());
			dto.setNote(res.getNote());;
			dto.setCreate_time(res.getCreate_time());
			dto.setUpdate_date_time(res.getUpdate_date_time());
			dto.setNoteUser(res.getNoteUser());

			return dto;
		}
		
		public static NoteResource convertToNoteResource(NoteDTO dto) throws Exception {

			if (dto == null) {
				throw new Exception("Object is null"); 
			}

			NoteResource res = new NoteResource();

			res.setNote_id(dto.getId());
			res.setTitle(dto.getTitle());
			res.setNote(dto.getNote());
			res.setCreate_time(dto.getCreate_time());
			res.setUpdate_date_time(dto.getUpdate_date_time());
			res.setNoteUser(dto.getNoteUser());

			return res;
		}
	}
}
