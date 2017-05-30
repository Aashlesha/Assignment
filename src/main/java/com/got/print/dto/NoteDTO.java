package com.got.print.dto;

import java.util.Date;

import com.got.print.persistance.Note_User;

public class NoteDTO {
	

	private int id;
	
	private String title;
	
	private String note;
	
	private Date create_time;

	private Date update_date_time;
	
	private Note_User noteUser;

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the note
	 */
	public String getNote() {
		return note;
	}

	/**
	 * @param note the note to set
	 */
	public void setNote(String note) {
		this.note = note;
	}

	/**
	 * @return the create_time
	 */
	public Date getCreate_time() {
		return create_time;
	}

	/**
	 * @param create_time the create_time to set
	 */
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

	/**
	 * @return the update_date_time
	 */
	public Date getUpdate_date_time() {
		return update_date_time;
	}

	/**
	 * @param update_date_time the update_date_time to set
	 */
	public void setUpdate_date_time(Date update_date_time) {
		this.update_date_time = update_date_time;
	}

	/**
	 * @return the noteUser
	 */
	public Note_User getNoteUser() {
		return noteUser;
	}

	/**
	 * @param noteUser the noteUser to set
	 */
	public void setNoteUser(Note_User noteUser) {
		this.noteUser = noteUser;
	}

}
