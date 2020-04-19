package org.sid.web;

import org.sid.entities.Compte;
import org.sid.entities.Operation;
import org.sid.metier.IBanqueMetier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class BanqueController {
	@Autowired
	private IBanqueMetier banqueMetier;
	
	@RequestMapping(value ="/operations")
	public String index() {
		return "template1";
	}
	
	@RequestMapping(value ="/consulterCompte")
	public String consulter(Model model,String codeCompte,@RequestParam(name="page",defaultValue="0")int page,@RequestParam(name="size",defaultValue="4")int size) {
		
		try{
	    Compte cp=banqueMetier.consulterCompte(codeCompte);
		Page<Operation> pageOperations=banqueMetier.listOperation(codeCompte, page, size);
	    model.addAttribute("listOperations",pageOperations.getContent());
		int[] pages=new int[pageOperations.getTotalPages()];
		model.addAttribute("pages", pages);
	    model.addAttribute("compte",cp);
	    model.addAttribute("codeCompte",codeCompte);
		
		}
		catch(Exception e) {
			model.addAttribute("exception", e);
		}
		return "template1";
		
	}
	@RequestMapping(value="/saveOperation",method=RequestMethod.POST)
	public String saveOperation(Model model,String typeOperation,String codeCompte,Double montant,String codeCompte2) {
		try{
			if(typeOperation.equals("RETRAIT")) {
				banqueMetier.retirer(codeCompte, montant);
			}
			if(typeOperation.equals("VERS")) {
			banqueMetier.verser(codeCompte, montant);
		                                      }
		  
		  if(typeOperation.equals("VIR")) {
			banqueMetier.virement(codeCompte, codeCompte2, montant);
		}
		}
		catch(Exception e) {
			model.addAttribute("error",e);
		}
		return "redirect:/consulterCompte?codeCompte="+codeCompte;
	}

}
