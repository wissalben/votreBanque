package org.sid.metier;

import org.sid.entities.Compte;
import org.sid.entities.Operation;
import org.springframework.data.domain.Page;

public interface IBanqueMetier {
	 public Compte consulterCompte(String pin);
	 public void verser(String codeCpte,Double montant);
	 public void retirer(String codeCpte,Double montant);
	 public void virement (String codeCpte1,String codecpte2,Double montant);
     public Page<Operation> listOperation(String codeCpte,Integer page,Integer size);
}
