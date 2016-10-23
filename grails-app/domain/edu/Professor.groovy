package edu

class Professor {

	String nome
	
	Departamento departamento
	
	static belongsTo = [Departamento]
	static hasMany = [disciplinas:Disciplina]
	
    static constraints = {
		nome(nullable:false, blank:false)
    }
	
	@Override
	public String toString() {
		return nome;
	}
}
