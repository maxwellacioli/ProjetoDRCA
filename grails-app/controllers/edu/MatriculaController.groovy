package edu

import grails.transaction.Transactional

class MatriculaController {

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Aluno.list(params), model:[alunoCount: Aluno.count()]
    }
	
	def lsdisc(Aluno aluno) {
	
		if (aluno == null) {
            redirect(view: 'index')
			return
        }
		
		def departamento = aluno.curso.secretaria.departamento
		def listDisciplinas = findDisciplinasByDepartamento(departamento)
		
		//params.max = Math.min(listDisciplinas.size() ?: 10, 100)
        respond listDisciplinas, model:[aluno: aluno]
    }
	
	@Transactional
	def trymatr(Disciplina disciplina) {
		
		def aluno = Aluno.findById(params?.a)
		
		if (disciplina == null || aluno == null) {
            transactionStatus.setRollbackOnly()
            redirect(view: 'index')
			return
        }

		def secretariaDoAluno = aluno.curso.secretaria
		def secretariaDaDisciplina = disciplina.curso.secretaria
		def disciplinaList = findDisciplinasByDepartamento(secretariaDoAluno.departamento)
		
		def idDepartamentoDoAluno = secretariaDoAluno.departamento.id
		def idDepartamentoDaDisciplina = secretariaDaDisciplina.departamento.id
		
		def alunoEhPos = !secretariaDoAluno.grad
		def disciplinaEhPos = !secretariaDaDisciplina.grad
		
		def disciplinasMatriculadas = aluno.disciplinas
		def creditosCumpridos = aluno.creditosObrigatorios + aluno.creditosEletivos
		def faltam
		
		def creditosRequisitos = disciplina.preRequisitos
		def disciplinasResquisitos = disciplina.disciplinasRequisito
		
		// Os alunos s� podem se matricular em disciplinas do departamento ao qual seu curso pertence
		if(idDepartamentoDoAluno != idDepartamentoDaDisciplina) {
			aluno.errors.reject('error.not.avaliable')
			transactionStatus.setRollbackOnly()
            render(view: 'lsdisc', model: [aluno: aluno, disciplinaList: disciplinaList])
            return
		}
		
		//Os alunos n�o podem se matricular em disciplinas que j� tenham se matriculado ou cursado
		if(disciplinasMatriculadas.contains(disciplina)) {
			aluno.errors.reject('error.already.registred')
			transactionStatus.setRollbackOnly()
			render(view: 'lsdisc', model: [aluno: aluno, disciplinaList: disciplinaList])
			return
		}
		
		// Alunos de p�s-gradua��o n�o podem cursar disciplinas da gradua��o
		if(alunoEhPos && !disciplinaEhPos) {
			aluno.errors.reject('error.not.pos.graduation')
			transactionStatus.setRollbackOnly()
			render(view: 'lsdisc', model: [aluno: aluno, disciplinaList: disciplinaList])
			return
		}
		
		//Os alunos de gradua��o podem cursar disciplinas de p�s-gradua��o caso j� tenham cumprido pelo menos 170 cr�ditos.
		if(!alunoEhPos && disciplinaEhPos && creditosCumpridos < 170) {
			faltam = 170 - creditosCumpridos
			aluno.errors.reject('error.not.enough.credits', [faltam] as Object[],
								'Matr�cula n�o realizada. O aluno n�o cumpriu cr�ditos suficientes para cursar esta disciplina. Faltam [{0}] cr�ditos.')
			transactionStatus.setRollbackOnly()
			render(view: 'lsdisc', model: [aluno: aluno, disciplinaList: disciplinaList])
			return
		}
		
		// O aluno precisa cumprir os pr�-requisitos (n�mero de cr�ditos m�nimo e disciplinas pr�-requisitos)
		if(creditosCumpridos < creditosRequisitos) {
			faltam = creditosRequisitos - creditosCumpridos
			aluno.errors.reject('error.not.enough.credits', [faltam] as Object[],
								'Matr�cula n�o realizada. O aluno n�o cumpriu cr�ditos suficientes para cursar esta disciplina. Faltam [{0}] cr�ditos.')
			transactionStatus.setRollbackOnly()
			render(view: 'lsdisc', model: [aluno: aluno, disciplinaList: disciplinaList])
			return
		}
		
		for( d in disciplinasResquisitos ) {
		
			if(!disciplinasMatriculadas.contains(d)) {
				aluno.errors.reject('error.not.required.disciplines')
				transactionStatus.setRollbackOnly()
				render(view: 'lsdisc', model: [aluno: aluno, disciplinaList: disciplinaList])				
				return
			}
		}
		
		aluno.addToDisciplinas(disciplina)
		
		flash.message = message(code: 'registration.success')
		
		redirect action: "lsdisc", id: aluno.id
		return
	}
	
	private def findDisciplinasByDepartamento(Departamento departamento) {
	
		if(departamento == null) {
			return null
		}
		
		def listSecretarias = Secretaria.findAllByDepartamento(departamento)
		def listCursos
		def listDisciplinasAux
		def listDisciplinas = []
		
		for(secretaria in listSecretarias){
			listCursos = Curso.findAllBySecretaria(secretaria)
			for(curso in listCursos){
				listDisciplinasAux = Disciplina.findAllByCurso(curso)
				for(disciplina in listDisciplinasAux) {
					listDisciplinas.add(disciplina)
				}
			}
		}
		
		return listDisciplinas
		
	}

	
}
