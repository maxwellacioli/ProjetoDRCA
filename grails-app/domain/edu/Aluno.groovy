package edu

class Aluno {
	Integer matricula
	String nome
	Integer creditosObrigatorios
	Integer creditosEletivos
	
	Curso curso
	static belongsTo = [Curso, Disciplina]
	static hasMany = [disciplinas:Disciplina]
	
    static constraints = {
		matricula(nullable:false, unique:true, blank:false)
		nome(nullable:false, blank:false)
		creditosObrigatorios(min:0)
		creditosEletivos(min:0)
    }
	
	@Override
	public String toString() {
		return nome;
	}
}