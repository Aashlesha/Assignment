package com.got.print.home;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.got.print.persistance.Note;

@Component
@Repository
public class NoteHome {

	private static final Logger log = LoggerFactory.getLogger(NoteHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public Note getNoteById(int noteId) {
		
		try {
			
			Note note = entityManager.find(Note.class, noteId);
			
			log.info("getNoteById: Success");

			return note;
			
		} catch (Exception e) {

			log.info("getNoteById: Failed");

			throw e;
		}
	}

	public void createNote(Note note) {

		try {

			entityManager.persist(note);

			log.info("createNote: Success");

		} catch (Exception e) {
			
			log.info("createNote: Failed");
			
			throw e;
		}
	}

	public Note updateNote(Note note) {

		try {

			Note obj = getNoteById(note.getId());

			obj.setNote(note.getNote());
			obj.setTitle(note.getTitle());
			obj.setUpdate_date_time(new Date());

			log.info("updateNote: Success");

			entityManager.flush();
			
			return getNoteById(note.getId());

		} catch (Exception e) {

			log.info("updateNote: Failed");

			throw e;
		}
	}

	public void deleteNote(int noteId) {
	
		try {
		
			entityManager.remove(getNoteById(noteId));

			log.info("deleteNote: Success");
			
		} catch (Exception e) {

			log.info("deleteNote: Failed");

			throw e;
		}
	}
}
