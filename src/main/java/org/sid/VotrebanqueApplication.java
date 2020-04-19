package org.sid;

import java.util.Date;

import org.sid.dao.ClientRepository;
import org.sid.dao.CompteRepository;
import org.sid.dao.OperationRepository;
import org.sid.entities.Client;
import org.sid.entities.Compte;
import org.sid.entities.CompteCourant;
import org.sid.entities.CompteEpargne;
import org.sid.entities.Versement;
import org.sid.metier.IBanqueMetier;
import org.sid.entities.Operation;
import org.sid.entities.Retrait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class VotrebanqueApplication {
     

	 
	public static void main(String[] args) {
		SpringApplication.run(VotrebanqueApplication.class, args);
		
		
		
	}



}
