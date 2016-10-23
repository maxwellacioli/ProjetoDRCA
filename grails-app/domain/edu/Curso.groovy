package edu

class Curso {

	String nome

	Secretaria secretaria
	
	static belongsTo = [Secretaria]
	static hasMany = [disciplinas:Disciplina]

	static constraints = {
		nome(nullable:false, blank:false)
	}
	
	@Override
	public String toString() {
		return nome;
	}
}
