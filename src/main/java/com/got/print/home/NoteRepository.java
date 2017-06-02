package com.got.print.home;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.got.print.persistance.Note;

@Repository
public interface NoteRepository extends CrudRepository<Note,Integer> {

//	 @Query("from note n where n.user_id = uId")
//	public List<Note> findNotesByUser(@Param("uId") int uId);
}
