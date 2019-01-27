package org.jpwh.model.associations.manytomany.linkentity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Immutable;

@Entity
@Table(name="CATEGORY_ITEM")
@Immutable
public class CategorizedItem {
	
	@Embeddable
	public static class Id implements Serializable {
		private static final long serialVersionUID = 4627585905273095453L;

		@Column(name="CATEGORY_ID")
		private long categoryId;
		
		@Column(name="ITEM_ID")
		private long itemId;
		
		public Id() {}

		public Id(long categoryId, long itemId) {
			this.categoryId = categoryId;
			this.itemId = itemId;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + (int) (categoryId ^ (categoryId >>> 32));
			result = prime * result + (int) (itemId ^ (itemId >>> 32));
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Id other = (Id) obj;
			if (categoryId != other.categoryId)
				return false;
			if (itemId != other.itemId)
				return false;
			return true;
		}
	}
	
	@EmbeddedId
	private Id id = new Id();
	
	@NotNull
	@Column(updatable=false)
	private String addedBy;
	
	@NotNull
	@Column(updatable=false)
	private Date addedOn = new Date();
	
	@ManyToOne
	@JoinColumn(name="CATEGORY_ID", insertable=false, updatable=false)
	private Category category;

	@ManyToOne
	@JoinColumn(name="ITEM_ID", insertable=false, updatable=false)
	private Item item;
	
	public CategorizedItem() {}

	public CategorizedItem(String addedByUserName, Category category, Item item) {
		this.addedBy = addedByUserName;
		this.category = category;
		this.item = item;
		
		// Set identifier values
		this.id.categoryId = category.getId();
		this.id.itemId = item.getId();
		
		// Guarantee referential integrity if made bidirectional
		item.getCategorizedItems().add(this);
		category.getCategorizedItems().add(this);
	}

	public String getAddedBy() {
		return addedBy;
	}

	public void setAddedBy(String addedBy) {
		this.addedBy = addedBy;
	}

	public Date getAddedOn() {
		return addedOn;
	}

	public void setAddedOn(Date addedOn) {
		this.addedOn = addedOn;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public Id getId() {
		return id;
	}

	@Override
	public String toString() {
		return "CategorizedItem [id=" + id + ", addedBy=" + addedBy + ", addedOn=" + addedOn + ", category=" + category
				+ ", item=" + item + "]";
	}
}
