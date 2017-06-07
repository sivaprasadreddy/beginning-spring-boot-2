/**
 * 
 */
package com.apress.demo.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * @author Siva
 *
 */
@Entity
@Table(name="todos")
public class Todo {
	@Id 
	@SequenceGenerator(name="todo_id_generator", sequenceName="todo_id_sequence", initialValue = 3)
	@GeneratedValue(generator = "todo_id_generator")
	private int id;
	private String text;
	private boolean done;
	
	public Todo() {
	}
	
	public Todo(int id, String text, boolean done) {
		this.id = id;
		this.text = text;
		this.done = done;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public boolean isDone() {
		return done;
	}
	public void setDone(boolean done) {
		this.done = done;
	}
	
}
