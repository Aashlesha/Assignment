package com.got.print.resource;

import java.util.Date;

import com.got.print.persistance.User;

public class NoteResource {

	@SuppressWarnings("unused")
	private static final long serialVersionUID = 5649969962073117903L;

	private int id;
	
	private String title;
	
	private String note;
	
	private Date create_time;

	private Date update_date_time;
	
	private User user;

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
	 * @return the user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}
	
}
