import java.util.ArrayList;
import java.util.List;

public class Biblioteca {
    private List<Livro> livros = new ArrayList<>();
    private ServicoNotificacao notificacao;

    public Biblioteca(ServicoNotificacao notificacao) {
        this.notificacao = notificacao;
    }

    public void adicionarLivro(Livro livro) {
        livros.add(livro);
    }

    public Livro buscarLivro(String titulo) {
        for (Livro livro : livros) {
            if (livro.getTitulo().equalsIgnoreCase(titulo)) {
                return livro;
            }
        }
        return null;
    }

    public void emprestarLivro(Usuario usuario, String titulo) {
        Livro livro = buscarLivro(titulo);
        if (livro == null) throw new IllegalArgumentException("Livro não encontrado");
        usuario.emprestarLivro(livro);
        notificacao.notificar("Livro emprestado: " + titulo);
    }

    public void devolverLivro(Usuario usuario, String titulo) {
        Livro livro = buscarLivro(titulo);
        if (livro == null) throw new IllegalArgumentException("Livro não encontrado");
        usuario.devolverLivro(livro);
        notificacao.notificar("Livro devolvido: " + titulo);
    }

    public List<Livro> getLivros() {
        return livros;
    }
}