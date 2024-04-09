package br.com.EscolaVNW.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.EscolaVNW.ServiceStudent.AlunoService;
import br.com.EscolaVNW.StudentData.Dados;
import br.com.EscolaVNW.ModelStudent.AlunoModel;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/aluno")
public class AlunoControlador {

	@Autowired
	private AlunoService service;

	@GetMapping
	public List<AlunoModel> buscarAluno() {

		List<AlunoModel> alunos = service.encontraAluno();
        List<AlunoModel> alunosFiltrados = new ArrayList<>();

        for (AlunoModel aluno : alunos) {

            AlunoModel alunoFiltrado = new AlunoModel();

            alunoFiltrado.setNome(aluno.getNome());
            alunoFiltrado.setEmail(aluno.getEmail());
            alunoFiltrado.setCurso(aluno.getCurso());

            alunosFiltrados.add(alunoFiltrado);
        }

        return alunosFiltrados;
	}

	@GetMapping("/{id}")
	public Dados buscarAlunoID(@PathVariable Long id) {

		Optional<AlunoModel> alunoOptional = service.encontraAlunoID(id);

		if (alunoOptional.isPresent()) {
			AlunoModel aluno = alunoOptional.get();
			return new Dados(aluno.getNome(), aluno.getEmail(), null, aluno.getCurso(), null, null);
			}

		return null;
	}

	@PostMapping
	public ResponseEntity<String> cadastraAluno(@RequestBody Dados dados) {
		String result = service.cadastrarAluno(dados);

		if (result != null) {
			return ResponseEntity.status(HttpStatus.CREATED).body(result);
		} else {
			ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
		return null;
	}

	@PutMapping("/{id}")
	public ResponseEntity<Object> atualizaAluno(@PathVariable Long id, @RequestBody @Valid Dados dadosAtualizados) {
		Optional<AlunoModel> alunoOptional = service.encontraAlunoID(id);

		if (alunoOptional.isPresent()) {
			AlunoModel aluno = alunoOptional.get();

			aluno.setNome(dadosAtualizados.nome());
			aluno.setCurso(dadosAtualizados.curso());
			aluno.setTelefone(dadosAtualizados.telefone());
			aluno.setEndereco(dadosAtualizados.endereco());

			service.atualizaAluno(aluno);
		}

		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity deletaAlunoID(@PathVariable Long id) {
		service.deletaAlunoID(id);
		return ResponseEntity.noContent().build();
	}
}
