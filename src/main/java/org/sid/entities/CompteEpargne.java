package org.sid.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;
@Entity
@DiscriminatorValue("CE")

public class CompteEpargne extends Compte {
	
	private Double taux;

	public CompteEpargne() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CompteEpargne(String codeCompte, Date dateCreation, Double solde, Client client, Double taux) {
		super(codeCompte, dateCreation, solde, client);
		this.taux = taux;
	}

	public Double getTaux() {
		return taux;
	}

	public void setTaux(Double taux) {
		this.taux = taux;
	}
	

}
