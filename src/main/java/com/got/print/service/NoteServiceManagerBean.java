package com.got.print.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.got.print.dto.NoteDTO;
import com.got.print.home.NoteHome;
import com.got.print.home.NoteUserHome;
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
	
	@Autowired
	private NoteUserHome noteUserHome;
	
	
	//@Secured ({"ROLE_ADMIN", "ROLE_USER"})
	public NoteDTO getNoteById(int noteId, int userId) throws Exception {

		try {
			
			NoteDTO noteDTO = new NoteDTO();
			
			Note note = noteHome.getNoteById(noteId);
			
			if(userId !=  note.getUser().getId()){
				
				throw new Exception("Specified user id : "+userId+"  is not associated with Note object :" + noteId);
			}
			
			noteDTO = toDTO(note);
			
			log.info("getNoteById: Success");
			
			return noteDTO;
			
		} catch (Exception e) {

			throw e;
		}
	}
	
	// @Secured ({"ROLE_ADMIN", "ROLE_USER"})
	public synchronized NoteDTO createNote(NoteDTO noteDto) throws Exception {
	
		try {

			Note note = getPersistence(noteDto);
			
			Note noteCreated =	noteHome.createNote(note);
			
			noteDto = toDTO(noteCreated); 
			
			log.info("createNote: Success");
		
			return noteDto;

		} catch (Exception e) {

			throw e;
		}

	}
	
	// @Secured ({"ROLE_ADMIN", "ROLE_USER"})
	public NoteDTO updateNote(NoteDTO noteDto) throws Exception {
		
		try {
			
			Note obj =noteHome.updateNote(getPersistence(noteDto));
		
			return toDTO(obj);

		} catch (Exception e) {

			throw e;
		}

	}
//	 
//	 @Secured ({"ROLE_ADMIN", "ROLE_USER"})
	public void deleteNote(int noteId) throws Exception {
		
		try {
			
			noteHome.deleteNote(noteId);
			
		} catch (Exception e) {

			throw e;
		}
	}
	
	public NoteDTO toDTO(Note note){
		
		NoteDTO dto = new NoteDTO();
		
		dto.setId(note.getId());
		dto.setTitle(note.getTitle());
		dto.setNote(note.getNote());
		dto.setCreate_time(note.getCreate_time());
		dto.setUpdate_date_time(note.getUpdate_date_time());
		dto.setNoteUser(note.getUser().getId());
		
		return dto;
	}
	
	public Note getPersistence(NoteDTO dto) throws Exception{
		
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
		note.setUser(noteUserHome.getNoteUserById(dto.getNoteUser()));
		
		return note;
	}

}
