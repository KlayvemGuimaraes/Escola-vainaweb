package br.com.EscolaVNW.ServiceStudent;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.EscolaVNW.Student.Aluno;
import br.com.EscolaVNW.StudentData.Dados;
import br.com.EscolaVNW.ModelStudent.AlunoModel;

@Service
public class AlunoService {

	@Autowired
	private Aluno repository;
	
	public String cadastrarAluno(Dados dados) {

		var aluno = repository.findByCpf(dados.cpf());
		
		if (aluno.isPresent()) {
			return "Aluno já existente em nossa base de dados, por favor verifique se você já possui conta em nossa plataforma!.";
		} else {
			repository.save(new AlunoModel(dados));
			return "Opaaaa, cadastro concluído, bem vindo á nossa plataforma!";
		}
	}
	
	public List<AlunoModel> encontraAluno() {
		return repository.findAll();
	}
	
	public Optional<AlunoModel> encontraAlunoID(Long id) {
		return repository.findById(id);
	}
	
	public void atualizaAluno(AlunoModel aluno) {
		repository.save(aluno);
	}
	
	public void deletaAlunoID(Long id) {
        repository.deleteById(id);
    }

}
