package com.got.print.resource;

import java.util.Date;

import org.springframework.hateoas.ResourceSupport;

import com.fasterxml.jackson.annotation.JsonFormat;

public class NoteResource extends ResourceSupport {

	public static final int MEDIA_TYPE_VERSION = 1;
	
	private int version;
	
	private int note_id;
	
	private String title;
	
	private String note;
	
	@JsonFormat(pattern="dd-MM-yyyy HH:mm:ss")
	private Date create_time;

	@JsonFormat(pattern="dd-MM-yyyy HH:mm:ss")
	private Date update_date_time;
	
	private int noteUser;
	
	public NoteResource() {
		super();
		setVersion(MEDIA_TYPE_VERSION);
		
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
	public int getNoteUser() {
		return noteUser;
	}

	/**
	 * @param noteUser the noteUser to set
	 */
	public void setNoteUser(int noteUser) {
		this.noteUser = noteUser;
	}

	/**
	 * @return the version
	 */
	public int getVersion() {
		return version;
	}

	/**
	 * @param version the version to set
	 */
	public void setVersion(int version) {
		this.version = version;
	}

	/**
	 * @return the note_id
	 */
	public int getNote_id() {
		return note_id;
	}

	/**
	 * @param note_id the note_id to set
	 */
	public void setNote_id(int note_id) {
		this.note_id = note_id;
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
		result = prime * result + note_id;
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
		if (note_id != other.note_id)
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
		return "NoteResource [id=" + note_id + ", title=" + title + ", note=" + note
				+ ", create_time=" + create_time + ", update_date_time="
				+ update_date_time + ", userResource=" + noteUser + "]";
	}
	
	
	
}
