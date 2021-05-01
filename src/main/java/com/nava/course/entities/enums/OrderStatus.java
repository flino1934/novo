package com.nava.course.entities.enums;

public enum OrderStatus {

	//Como default do java ele vai retornar um dado numerico quando retornar na ordem que esta escrito começando em 0
	WAITING_PAYMENT(1),//esta deixando explicitado em manutenções futuras o tipo de valor enumerado que vai retornar começara em 1 por que eu defini 
	PAID(2),
	SHIPPED(3),
	DELIVERED(4),
	CANCELED(5);
	
	private int code;

	private OrderStatus(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}

	//vai converter de um valor numerico para um tipo enumerado
	public static OrderStatus valueOf(int code) {
		
		for(OrderStatus value : OrderStatus.values()) {//vai percorrer todos os valores de OrderStatus
			
			if (value.getCode() == code) {
				return value;
			}

		}
		throw new IllegalArgumentException("Invalid OrderStatus Code!");

	}

}
