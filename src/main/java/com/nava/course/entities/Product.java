package com.nava.course.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "tb_product")
public class Product implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String description;
	private Double price;
	private String imgUrl;

	@ManyToMany
	@JoinTable(name = "tb_product_category", joinColumns = @JoinColumn(name = "product_id"), inverseJoinColumns = @JoinColumn(name = "categorie_id"))
	private Set<Category> categories = new HashSet<>();// o set não permite repetir a mesma categoria mais de uma vez
	
	@OneToMany(mappedBy = "id.product")//um produto tem varios pedidos messmo não sendo o mesmo produto em si desculpa felipe do futuro isso vai ficar // confuso explicando mappedby pois a chave primaria que relaciona os dois items esta em OrderItemPK e conseguimos pegar ele falando para o jpa pegar a chave id.product
	private Set<OrderItem> itens = new HashSet<>();// o set não permite repetir a mesma categoria mais de uma vez

	public Product() {
		// TODO Auto-generated constructor stub
	}

	public Product(Long id, String name, String description, Double price, String imgUrl) {

		this.id = id;
		this.name = name;
		this.description = description;
		this.price = price;
		this.imgUrl = imgUrl;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public Set<Category> getCategories() {
		return categories;
	}
	
	@JsonIgnore//para não dar o problema do looping infinito estamos fazendo esta anotação pois o pedido vai chamar o produto e aqui é um metodo que tras o pedido assim entrando em um looping
	public Set<Order> getOrders(){
		
		Set<Order> set = new HashSet<>();
		for (OrderItem x : itens) {//vai percorrer a coleção itens da linha 36 que é uma coleção que é associada ao produto pois ela acessa a classe OrderItem
			set.add(x.getOrder());//depois de varrer a lista ordemItem ele vai guardar dentro da coleção set do tipo <Order> para que não corra o risco de se repetir o mesmo produto lembrando que pode ter em quantidade mais de um mas não repetir na lista 
		}
		return set;
		
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Product other = (Product) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
