package org.sid.dao;


import org.sid.entities.Compte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

public interface CompteRepository extends CrudRepository<Compte,String>, QueryByExampleExecutor <Compte> {


}
