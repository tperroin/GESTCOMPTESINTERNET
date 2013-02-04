/**************************************************************************
* Source File	:  Enregistrement.java
* Author                   :  tibo  
* Project name         :  Espace de travail* Created                 :  16/01/2013
* Modified   	:  16/01/2013
* Description	:  Definition of the class Enregistrement
**************************************************************************/



import java.util.*;



public  class Enregistrement  
{ 
	//Inners Classifiers
	

	//Attributes
		
		
	
		private 
	 java.lang.Long id;
		private 
	 java.lang.String prelevementEncaissement;
		private 
	 java.lang.String recetteDepense;
		private 
	 java.util.Date date;
		private 
	 java.lang.String montant;
		private 
	 java.lang.Number ancienSolde;
		private 
	 java.lang.Number nouveauSolde;
		private 
	 char numCHQ;
		private 
	 char dateFacture;
		private 
	 boolean anticipation;
	
	//Attributes Association
	
		Compte contenir;
	 
		Etat possèder;
	 
		ModeReglement être_règlé;
	 
		Motif avoir_un;
	 
		Libelle définir;
	 
	
	
	
	

	
	//Operations
		
		
	
	

} //End Class Enregistrement


