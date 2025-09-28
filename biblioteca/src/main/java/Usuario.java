import java.util.ArrayList;
import java.util.List;

public class Usuario {
    private String nome;
    private List<Livro> livrosEmprestados = new ArrayList<>();

    public Usuario(String nome) {
        this.nome = nome;
    }

    public String getNome() { return nome; }

    public void emprestarLivro(Livro livro) {
        if (livrosEmprestados.size() >= 3) {
            throw new IllegalStateException("Usuário já possui 3 livros emprestados");
        }
        livro.emprestar();
        livrosEmprestados.add(livro);
    }

    public void devolverLivro(Livro livro) {
        if (!livrosEmprestados.contains(livro)) {
            throw new IllegalStateException("Livro não foi emprestado por este usuário");
        }
        livro.devolver();
        livrosEmprestados.remove(livro);
    }

    public List<Livro> getLivrosEmprestados() {
        return livrosEmprestados;
    }
}