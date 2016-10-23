package edu

class DRCA {
	
	String nome

	static hasMany = [departamentos:Departamento]
	
    static constraints = {
		nome(nullable:false, blank:false)
    }
	
	@Override
	public String toString() {
		return nome;
	}
	
	static def list() {
		DRCA.findAll()
	}
}
