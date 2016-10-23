package edu

class Secretaria {

	boolean grad

	Departamento departamento

	static belongsTo = [Departamento]
	static hasMany = [cursos:Curso]


	@Override
	public String toString() {
		String toString = "Secretaria de "
		if(grad == true) {
			toString += "Graduacao "
		} else {
			toString += "Pos-graduacao "
		}
		toString += departamento.toString()
		return toString;
	}
}
