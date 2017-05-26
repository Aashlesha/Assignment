package com.got.print.home;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.got.print.persistance.Note;

@Component
public class NoteHome {

	private static final Logger log = LoggerFactory.getLogger(NoteHome.class);

	private static NoteHome SINGLETON = new NoteHome();

	@Resource
	private SessionFactory sessionFactory;

	public static NoteHome getInstance() {
		return SINGLETON;
	}

	private SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
	public Note findNoteById(Integer noteId) throws Exception {
		
		log.debug("In findNoteById");
		
		Session session = null;
		
		try {
			session = getSessionFactory().getCurrentSession();

			Criteria criteria = session.createCriteria(Note.class);

			criteria.add(Restrictions.eq(Note.NOTE_ID, noteId));

			Note result = (Note) criteria.uniqueResult();

			if (result == null) {
				log.debug("findNoteById unsuccessful, no instance found");

				throw new Exception("findNoteById unsuccessful, no instance found"); 
			}


			log.debug("findByUUId findNoteById, instance found");

			return result;

		} catch (RuntimeException e) {
			
			log.error("findNoteById failed", e);
			throw e;
		}
	}
}
