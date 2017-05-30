package com.got.print.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.got.print.dto.NoteDTO;
import com.got.print.home.NoteHome;
import com.got.print.persistance.Note;

@Scope(BeanDefinition.SCOPE_SINGLETON)
@Service
@Transactional(propagation = Propagation.REQUIRED, timeout = 14400, rollbackFor = Exception.class)
public class NoteServiceManagerBean {
	
	/**
	 * Reference to the name of this class for logging purposes.
	 */
	private static String CLASS_NAME = NoteServiceManagerBean.class.getName();
	
	private static final Logger log = LoggerFactory.getLogger(CLASS_NAME);
	
	@Autowired
	private NoteHome noteHome;
	
	
	//@Secured ({"ROLE_ADMIN", "ROLE_USER"})
	public NoteDTO getNoteById(int noteId) throws Exception {

		try {
			
			NoteDTO noteDTO = new NoteDTO();
			
			Note note = noteHome.getNoteById(noteId);
			
			noteDTO = toDTO(note);
			
			return noteDTO;
			
		} catch (Exception e) {

			log.error(e.getMessage());
			throw e;
		}
	}
	
	 @Secured ({"ROLE_ADMIN", "ROLE_USER"})
	public synchronized NoteDTO createNote(NoteDTO noteDto) {
	
		try {
			

			Note note = getPersistence(noteDto);
			
			noteHome.createNote(note);
		
			return noteDto;

		} catch (Exception e) {

			log.error(e.getMessage());
			throw e;
		}

	}
	
	 @Secured ({"ROLE_ADMIN", "ROLE_USER"})
	public NoteDTO updateNote(NoteDTO noteDto) {
		
		try {
			
			Note obj =noteHome.updateNote(getPersistence(noteDto));
		
			return toDTO(obj);

		} catch (Exception e) {

			log.error(e.getMessage());
			throw e;
		}

	}
	 
	 @Secured ({"ROLE_ADMIN", "ROLE_USER"})
	public void deleteNote(int noteId) {
		
		try {
			
			noteHome.deleteNote(noteId);
			
		} catch (Exception e) {

			log.error(e.getMessage());
			
			throw e;
		}
	}
	
	public NoteDTO toDTO(Note note){
		
		NoteDTO dto = new NoteDTO();
		
		dto.setId(note.getId());
		dto.setTitle(note.getTitle());
		dto.setNote(note.getNote());
		dto.setNoteUser(note.getUser());
		dto.setCreate_time(note.getCreate_time());
		dto.setUpdate_date_time(note.getUpdate_date_time());
		
		return dto;
	}
	
	public Note getPersistence(NoteDTO dto){
		
        if (dto == null)
        {
            return null;
        }
        
		Note note = new Note();
		
		note.setId(dto.getId());
		note.setNote(dto.getNote());
		note.setTitle(dto.getTitle());
		note.setUpdate_date_time(dto.getUpdate_date_time());
		note.setCreate_time(dto.getCreate_time());
		note.setUser(dto.getNoteUser());
		
		return note;
	}

}
