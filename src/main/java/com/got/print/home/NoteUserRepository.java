package com.got.print.home;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.got.print.persistance.Note_User;

@Repository
public interface NoteUserRepository extends CrudRepository<Note_User,Integer> {
 
}
