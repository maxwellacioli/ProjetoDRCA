package edu

class Disciplina {
	String nome
	String codigo
	Integer creditos
	Boolean obrigatoria
	Boolean oferecida
	Integer preRequisitos
	
	Professor professor
	Curso curso
	
	static belongsTo = [Professor, Curso]
	static hasMany = [disciplinasRequisito:Disciplina]

    static constraints = {
		nome(nullable:false, blank:false)
		codigo(nullable:false, blank:false, unique:true)
		creditos(min:0)
		preRequisitos(min:0)
    }
	
	@Override
	public String toString() {
		return nome;
	}
}
