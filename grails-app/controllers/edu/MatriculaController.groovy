package edu

import grails.transaction.Transactional

class MatriculaController {

	def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Aluno.list(params), model:[alunoCount: Aluno.count()]
    }
	
	def lsdisc(Aluno aluno) {
	
		if (aluno == null) {
            redirect view: 'index'
			return
        }
		
		def departamento = aluno.curso.secretaria.departamento
		def listDisciplinas = findDisciplinasByDepartamento(departamento)
		
		//params.max = Math.min(listDisciplinas.size() ?: 10, 100)
        respond listDisciplinas, model:[aluno: aluno]
    }
	
	@Transactional
	def trymatr(Disciplina disciplina) {
		
		def aluno
		def alunoId = params?.a
		
		if(alunoId != null) {
			aluno = Aluno.findById(params?.a)
		}
		
		if (disciplina == null || aluno == null) {
            transactionStatus.setRollbackOnly()
            redirect(view: 'index')
			return
        }

		def secretariaDoAluno = aluno.curso?.secretaria
		def secretariaDaDisciplina = disciplina.curso?.secretaria
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
		
		// Os alunos só podem se matricular em disciplinas do departamento ao qual seu curso pertence
		if(idDepartamentoDoAluno != idDepartamentoDaDisciplina) {
			aluno.errors.reject('error.not.avaliable')
			transactionStatus.setRollbackOnly()
            render(view: 'lsdisc', model: [aluno: aluno, disciplinaList: disciplinaList])
            return
		}
		
		//Os alunos não podem se matricular em disciplinas que já tenham se matriculado ou cursado
		if(disciplinasMatriculadas.contains(disciplina)) {
			aluno.errors.reject('error.already.registred')
			transactionStatus.setRollbackOnly()
			render(view: 'lsdisc', model: [aluno: aluno, disciplinaList: disciplinaList])
			return
		}
		
		// Alunos de pós-graduação não podem cursar disciplinas da graduação
		if(alunoEhPos && !disciplinaEhPos) {
			aluno.errors.reject('error.not.pos.graduation')
			transactionStatus.setRollbackOnly()
			render(view: 'lsdisc', model: [aluno: aluno, disciplinaList: disciplinaList])
			return
		}
		
		//Os alunos de graduação podem cursar disciplinas de pós-graduação caso já tenham cumprido pelo menos 170 créditos.
		if(!alunoEhPos && disciplinaEhPos && creditosCumpridos < 170) {
			faltam = 170 - creditosCumpridos
			aluno.errors.reject('error.not.enough.credits', [faltam] as Object[],
								'Matrícula não realizada. O aluno não cumpriu créditos suficientes para cursar esta disciplina. Faltam [{0}] créditos.')
			transactionStatus.setRollbackOnly()
			render(view: 'lsdisc', model: [aluno: aluno, disciplinaList: disciplinaList])
			return
		}
		
		// O aluno precisa cumprir os pré-requisitos (número de créditos mínimo e disciplinas pré-requisitos)
		if(creditosCumpridos < creditosRequisitos) {
			faltam = creditosRequisitos - creditosCumpridos
			aluno.errors.reject('error.not.enough.credits', [faltam] as Object[],
								'Matrícula não realizada. O aluno não cumpriu créditos suficientes para cursar esta disciplina. Faltam [{0}] créditos.')
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
		
		flash.message = "A matrícula foi efetuada com sucesso!"
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
		
		if(listDisciplinas.isEmpty()) return null
		
		return listDisciplinas
		
	}

	
}
