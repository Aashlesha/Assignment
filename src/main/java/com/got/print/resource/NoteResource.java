package com.got.print.resource;

import java.util.Date;

import org.springframework.hateoas.Links;

public class NoteResource extends Links {

	private int id;
	
	private String title;
	
	private String note;
	
	private Date create_time;

	private Date update_date_time;
	
	private UserResource userResource;
	
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
	 * @return the userResource
	 */
	public UserResource getUserResource() {
		return userResource;
	}

	/**
	 * @param userResource the userResource to set
	 */
	public void setUserResource(UserResource userResource) {
		this.userResource = userResource;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((create_time == null) ? 0 : create_time.hashCode());
		result = prime * result + id;
		result = prime * result + ((note == null) ? 0 : note.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		result = prime
				* result
				+ ((update_date_time == null) ? 0 : update_date_time.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		NoteResource other = (NoteResource) obj;
		if (create_time == null) {
			if (other.create_time != null)
				return false;
		} else if (!create_time.equals(other.create_time))
			return false;
		if (id != other.id)
			return false;
		if (note == null) {
			if (other.note != null)
				return false;
		} else if (!note.equals(other.note))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		if (update_date_time == null) {
			if (other.update_date_time != null)
				return false;
		} else if (!update_date_time.equals(other.update_date_time))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "NoteResource [id=" + id + ", title=" + title + ", note=" + note
				+ ", create_time=" + create_time + ", update_date_time="
				+ update_date_time + ", userResource=" + userResource + "]";
	}
	
	
	
}
