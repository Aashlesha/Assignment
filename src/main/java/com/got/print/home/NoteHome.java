package com.got.print.home;

import java.util.Optional;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.got.print.persistance.Note;


@Component
public class NoteHome {

	private static final org.slf4j.Logger log = LoggerFactory.getLogger(NoteHome.class);

	@Autowired
    private NoteRepository noteRepository;
	
	public Note getNoteById(int noteId) throws Exception {

		try {

			Optional<Note> note = noteRepository.findById(noteId);
			
			Note retObj = null;
			
			if(note.isPresent()){

			 retObj = note.get();
			
			}
			
			log.info("getNoteById: Success");

			return retObj;

		} catch (Exception e) {

			log.info("getNoteById: Failed");

			throw e;
		}
	}

	public Note createNote(Note note) {

		try {

			Note noteCreated =noteRepository.save(note);

			log.info("createNote: Success");
			
			return noteCreated;

		} catch (Exception e) {
			
			log.info("createNote: Failed");
			
			throw e;
		}
	}

	public Note updateNote(Note note) throws Exception {

		try {

			note = noteRepository.save(note);

			log.info("updateNote: Success");
			
			return note;

		} catch (Exception e) {

			log.info("updateNote: Failed");

			throw e;
		}
	}

	public void deleteNote(int noteId) throws Exception {
	
		try {
		
			noteRepository.delete(getNoteById(noteId));

			log.info("deleteNote: Success");
			
		} catch (Exception e) {

			log.info("deleteNote: Failed");

			throw e;
		}
	}
}
