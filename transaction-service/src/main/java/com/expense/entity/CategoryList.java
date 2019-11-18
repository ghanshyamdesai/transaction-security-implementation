package com.expense.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.expense.entity.util.StringListConverter;


@Entity
@Table(name = "categoryList" ,schema="nci01")
public class CategoryList implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2915661892771059506L;
	
	@Id
	@Column(name = "categoryId")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "category")
	@Convert(converter = StringListConverter.class)
	private List<String> category;

	@OneToOne(fetch = FetchType.LAZY)
	@MapsId
	@NotNull
    private UserDetails userDetails;

	public Long getId() {
		return id;
	}

	public List<String> getCategory() {
		return category;
	}

	public void setCategory(List<String> category) {
		this.category = category;
	}

	public UserDetails getUserDetails() {
		return userDetails;
	}

	public void setUserDetails(UserDetails userDetails) {
		this.userDetails = userDetails;
	}

	
}
