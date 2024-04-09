package br.com.EscolaVNW.Student;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.EscolaVNW.ModelStudent.AlunoModel;

@Repository
public interface Aluno extends JpaRepository<AlunoModel, Long> {

    Optional<AlunoModel> findByCpf(String cpf);
}
