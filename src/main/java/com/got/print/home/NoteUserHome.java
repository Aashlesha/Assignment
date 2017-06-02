package com.got.print.home;

import java.util.Optional;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.got.print.persistance.Note_User;

@Component
public class NoteUserHome {
	
	private static final org.slf4j.Logger log = LoggerFactory.getLogger(NoteUserHome.class);
	
	@Autowired
    private NoteUserRepository noteRepository;
	
	public Note_User getNoteUserById(int userId) throws Exception {

		try {

			Optional<Note_User> noteUser = noteRepository.findById(userId);

			Note_User retObj = null;
			
			if(noteUser.isPresent()){
			
				retObj = noteUser.get();
			
			}
			
			log.info("getNoteUserById: Success");

			return retObj;

		} catch (Exception e) {

			log.info("getNoteUserById: Failed");

			throw e;
		}
	}
}

