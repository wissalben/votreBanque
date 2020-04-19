package org.sid.metier;

import java.util.Date;
import java.util.Optional;

import org.sid.dao.CompteRepository;
import org.sid.dao.OperationRepository;
import org.sid.entities.Compte;
import org.sid.entities.CompteCourant;
import org.sid.entities.Operation;
import org.sid.entities.Retrait;
import org.sid.entities.Versement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class BanqueMetierImpl implements IBanqueMetier {
 
	@Autowired
	private CompteRepository compteRepository;
	@Autowired
	private OperationRepository operationRepository;
	@Override
	public Compte consulterCompte(String codeCpte) {
		 
		
		Compte cp=compteRepository.findById(codeCpte).orElse(null);
		if(cp==null) throw new RuntimeException("Compte Introuvable");
		
		return cp;
	}

	@Override
	public void verser(String codeCpte, Double montant) {
		Compte cp=consulterCompte(codeCpte);
		Versement v=new Versement(new Date(),montant,cp);
		operationRepository.save(v);
		cp.setSolde(cp.getSolde()+montant);
		compteRepository.save(cp);
		
	}

	@Override
	public void retirer(String codeCpte, Double montant) {
		Compte cp=consulterCompte(codeCpte);
		Retrait r=new Retrait(new Date(),montant,cp);
		Double facilitesCaisse=0.0;
		if(cp instanceof CompteCourant) 
			facilitesCaisse=((CompteCourant) cp).getDecouvert();
			
		if(cp.getSolde()+facilitesCaisse<montant)
			 throw new RuntimeException("Solde insuffisant");
		
		operationRepository.save(r);
		cp.setSolde(cp.getSolde()-montant);
		compteRepository.save(cp);
	}

	@Override
	public void virement(String codeCpte1, String codeCpte2, Double montant) {
		if(codeCpte1.equals(codeCpte2))
			throw new RuntimeException("impossible de virer sur le meme compte !");
		
		retirer(codeCpte1,montant);
		verser(codeCpte2,montant);
		
	}

	@Override
	public Page<Operation> listOperation(String codeCpte, Integer page, Integer size) {
		
		return operationRepository.listOperation(codeCpte, new PageRequest(page,size));
	}

}
