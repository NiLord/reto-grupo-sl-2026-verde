package com.appbg.appbg.domain.model;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * Modelo sencillo para representar una cantidad monetaria con código de moneda.
 */
public class Moneda {
	private BigDecimal cantidad;
	private String codigo; // ISO 4217, por ejemplo "COP", "USD"

	public Moneda() {
		this.cantidad = BigDecimal.ZERO;
		this.codigo = "COP";
	}

	public Moneda(BigDecimal cantidad, String codigo) {
		this.cantidad = cantidad == null ? BigDecimal.ZERO : cantidad;
		this.codigo = codigo == null ? "COP" : codigo;
	}

	public static Moneda of(BigDecimal cantidad, String codigo) {
		return new Moneda(cantidad, codigo);
	}

	public BigDecimal getCantidad() {
		return cantidad;
	}

	public void setCantidad(BigDecimal cantidad) {
		this.cantidad = cantidad == null ? BigDecimal.ZERO : cantidad;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo == null ? this.codigo : codigo;
	}

	public Moneda sumar(Moneda otra) {
		checkCurrencyMatch(otra);
		return new Moneda(this.cantidad.add(otra.cantidad), this.codigo);
	}

	public Moneda restar(Moneda otra) {
		checkCurrencyMatch(otra);
		return new Moneda(this.cantidad.subtract(otra.cantidad), this.codigo);
	}

	public Moneda multiplicar(long multiplicador) {
		return new Moneda(this.cantidad.multiply(BigDecimal.valueOf(multiplicador)), this.codigo);
	}

	private void checkCurrencyMatch(Moneda otra) {
		if (otra == null) throw new IllegalArgumentException("Moneda nula");
		if (!Objects.equals(this.codigo, otra.codigo)) {
			throw new IllegalArgumentException("Códigos de moneda diferentes: " + this.codigo + " vs " + otra.codigo);
		}
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Moneda moneda = (Moneda) o;
		return Objects.equals(cantidad, moneda.cantidad) && Objects.equals(codigo, moneda.codigo);
	}

	@Override
	public int hashCode() {
		return Objects.hash(cantidad, codigo);
	}

	@Override
	public String toString() {
		return "Moneda{" + "cantidad=" + cantidad + ", codigo='" + codigo + '\'' + '}';
	}
}
