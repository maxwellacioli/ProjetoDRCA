package edu

class Departamento {

	String nome
	DRCA drca
	
	static belongsTo = [DRCA]
	static hasMany = [secretarias:Secretaria]
		
    static constraints = {
		nome(nullable:false, blank:false)
    }
	
	@Override
	public String toString() {
		return nome;
	}
	
	
}
