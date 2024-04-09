package br.com.EscolaVNW.StudentData;

import br.com.EscolaVNW.Courses.Curso;
import br.com.EscolaVNW.ModelStudent.Endereco;

public record Dados(String nome, String email, String cpf, Curso curso, String telefone, Endereco endereco) {

}
