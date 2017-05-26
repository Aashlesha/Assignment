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
import com.got.print.persistance.Note;

@Scope(BeanDefinition.SCOPE_SINGLETON)
@Service
@Transactional(propagation = Propagation.REQUIRED, timeout = 14400, rollbackFor = Exception.class)
public class NoteServiceManagerBean {
	
	/**
	 * Reference to the name of this class for logging purposes.
	 */
	private static String CLASS_NAME = NoteServiceManagerBean.class.getName();
	private static final Logger logger = LoggerFactory.getLogger(CLASS_NAME);
	
	@Autowired
	private NoteHome noteHome;

	public NoteDTO getNoteById(int userId,int noteId) throws Exception {

		try {
			
			NoteDTO noteDTO = new NoteDTO();
			
			Note note = noteHome.findNoteById(noteId);
			
			noteDTO = toDTO(note);
			
			return noteDTO;
			
		} catch (Exception e) {

			logger.error(e.getMessage());
			throw e;
		}
	}
	
	
	
	public NoteDTO toDTO(Note note){
		
		NoteDTO dto = new NoteDTO();
		
		return dto;
	}

}
