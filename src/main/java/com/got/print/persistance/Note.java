package com.got.print.persistance;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

	@Entity
	@Table(name="note")
	public class Note implements Serializable {

		/**
		 *
		 */
		private static final long serialVersionUID = 5649969962073117903L;
		
		  public static final String NOTE_ID = "note_id";

		private int id;
		
		private String title;
		
		private String note;
		
		private Date create_time;

		private Date update_date_time;
		
		private User user;

		/**
		 * @return the id
		 */
		@Id
		@Column(name="note_id")
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
		@Column(name="title")
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
		@Column(name="note")
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
		@Column(name="create_time")
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
		@Column(name="update_date_time")
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
	    @ManyToOne(fetch = FetchType.LAZY)
	    @JoinColumn(name = "user_id", unique = false, nullable = true, insertable = true, updatable = true)
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

