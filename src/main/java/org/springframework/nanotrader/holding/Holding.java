/*
 * Copyright 2002-2012 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.nanotrader.holding;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "Holding")
public class Holding implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@NotNull
	private Long id = new Long(-1);

	public Long getId() {
		return this.id;
	}

	public void setId(Long l) {
		if (l != null) {
			this.id = l;
		}
	}

	@NotNull
	private Float purchasePrice;

	@NotNull
	private Integer quantity;

	private Date purchaseDate = new Date();

	@NotNull
	private Integer accountId;

	@NotNull
	private String quoteSymbol;

	public Float getPurchasePrice() {
		return purchasePrice;
	}

	public void setPurchasePrice(Float f) {
		this.purchasePrice = f;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer i) {
		this.quantity = i;
	}

	public Date getPurchaseDate() {
		return purchaseDate;
	}

	public void setPurchaseDate(Date d) {
		if (d != null) {
			this.purchaseDate = d;
		}
	}

	public Integer getAccountId() {
		return this.accountId;
	}

	public void setAccountId(Integer i) {
		this.accountId = i;
	}

	public String getQuoteSymbol() {
		return quoteSymbol;
	}

	public void setQuoteSymbol(String s) {
		this.quoteSymbol = s;
	}

	@Override
	public String toString() {
		return "Holding [holdingid=" + id + ", purchasePrice=" + purchasePrice
				+ ", quantity=" + quantity + ", purchaseDate=" + purchaseDate
				+ ", quoteSymbol=" + quoteSymbol + "]";
	}

	public int hashCode() {
		if (getId() == null) {
			return -1;
		}
		return getId().hashCode();
	}

	public boolean equals(Object o) {
		if (o == null) {
			return false;
		}

		if (!(o instanceof Holding)) {
			return false;
		}
		return o.hashCode() == hashCode();
	}

}
