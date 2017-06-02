package com.got.print.home;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.got.print.persistance.Note;

@Repository
public interface NoteRepository extends CrudRepository<Note,Integer> {

}
