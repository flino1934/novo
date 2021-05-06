package com.nava.course.entities;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.nava.course.entities.enums.OrderStatus;

@Entity
@Table(name = "tb_order")
public class Order implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yy/MM/dd'T'HH:mm:ss'Z'", timezone = "GMT")
	private Instant moment;

	private Integer status;

	@ManyToOne // Um cliente tem varios Pedidos relação N*1
	@JoinColumn(name = "Client_id")
	private User client;

	@OneToMany(mappedBy = "id.order") // um pedido tem varios items de pedido// explicando mapppedby pois a chave primaria que relaciona os dois items esta em OrderItemPK e conseguimos pegar ele falando para o jpa pegar a chave id.product
	private Set<OrderItem> itens = new HashSet<>();

	@OneToOne(mappedBy = "order",cascade = CascadeType.ALL)//estamos colocando o atributo de mapeamento CascadeType.ALL para falar que tanto pedido quanto pagamento deverão ter o mesmo ID
	//a classe order é a classe principal pois ela é independente, pode se ter um pedido sem pagamento mas não pode ter uma pagamento sem pedido
	private Payment payment;
	
	public Order() {
		// TODO Auto-generated constructor stub
	}

	public Order(Long id, Instant moment, OrderStatus status, User client) {

		this.id = id;
		this.moment = moment;
		setStatus(status);
		this.client = client;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Instant getMoment() {
		return moment;
	}

	public void setMoment(Instant moment) {
		this.moment = moment;
	}

	public User getClient() {
		return client;
	}

	public void setClient(User client) {
		this.client = client;
	}

	public OrderStatus getStatus() {
		return OrderStatus.valueOf(status);
	}

	public Set<OrderItem> getItems() {
		return itens;
	}

	public void setStatus(OrderStatus status) {
		if (status != null) {
			this.status = status.getCode();
		}

	}

	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
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
		Order other = (Order) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
