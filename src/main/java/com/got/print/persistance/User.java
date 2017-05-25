package com.got.print.persistance;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

	@Entity
	@Table(name="note_user")
	public class User implements Serializable {

		/**
		 *
		 */
		private static final long serialVersionUID = 5649969962073117903L;

		@Id
		@Column(name="user_id")
		private int id;
		
		@Column(name="email")
		private String email;
		
		@Column(name="password")
		private String password;
		
		@Column(name="create_time")
		private Date create_time;

		@Column(name="update_date_time")
		private Date update_date_time;
		
		public User()
		{
			
		}

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
		 * @return the email
		 */
		public String getEmail() {
			return email;
		}

		/**
		 * @param email the email to set
		 */
		public void setEmail(String email) {
			this.email = email;
		}

		/**
		 * @return the password
		 */
		public String getPassword() {
			return password;
		}

		/**
		 * @param password the password to set
		 */
		public void setPassword(String password) {
			this.password = password;
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
		
}
