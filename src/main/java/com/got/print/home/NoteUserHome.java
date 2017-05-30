package com.got.print.home;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.got.print.persistance.Note_User;

@Repository
@Component
public class NoteUserHome {

	@PersistenceContext	
	private EntityManager entityManager;
	
	public Note_User getCurrentNoteUser(String userId) {
		
		Note_User activeUserInfo = new Note_User();
		
		List<?> list = entityManager.createQuery("SELECT u FROM UserInfo u WHERE user_id=?")
				.setParameter(1, userId).getResultList();
		
		if(!list.isEmpty()) {
			
			activeUserInfo = (Note_User)list.get(0);
		
		}
		return activeUserInfo;
	}
}
