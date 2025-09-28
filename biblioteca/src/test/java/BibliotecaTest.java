import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class BibliotecaTest {

    @Test
    void deveAdicionarEBuscarLivro() {
        ServicoNotificacao mockServico = Mockito.mock(ServicoNotificacao.class);
        Biblioteca biblioteca = new Biblioteca(mockServico);

        Livro livro = new Livro("Java", "Autor");
        biblioteca.adicionarLivro(livro);

        assertNotNull(biblioteca.buscarLivro("Java"));
    }

    @Test
    void deveEmprestarLivroComNotificacao() {
        ServicoNotificacao mockServico = Mockito.mock(ServicoNotificacao.class);
        Biblioteca biblioteca = new Biblioteca(mockServico);
        Usuario usuario = new Usuario("Caio");
        Livro livro = new Livro("Java", "Autor");

        biblioteca.adicionarLivro(livro);
        biblioteca.emprestarLivro(usuario, "Java");

        Mockito.verify(mockServico).notificar("Livro emprestado: Java");
    }

    @Test
    void deveDevolverLivroComNotificacao() {
        ServicoNotificacao mockServico = Mockito.mock(ServicoNotificacao.class);
        Biblioteca biblioteca = new Biblioteca(mockServico);
        Usuario usuario = new Usuario("Caio");
        Livro livro = new Livro("Java", "Autor");

        biblioteca.adicionarLivro(livro);
        biblioteca.emprestarLivro(usuario, "Java");
        biblioteca.devolverLivro(usuario, "Java");

        Mockito.verify(mockServico).notificar("Livro devolvido: Java");
    }

    @Test
    void deveLancarErroSeLivroNaoExistir() {
        ServicoNotificacao mockServico = Mockito.mock(ServicoNotificacao.class);
        Biblioteca biblioteca = new Biblioteca(mockServico);
        Usuario usuario = new Usuario("Caio");

        assertThrows(IllegalArgumentException.class,
                () -> biblioteca.emprestarLivro(usuario, "Inexistente"));
    }

    @Test
    void deveRetornarNullSeLivroNaoExistirNaBusca() {
        ServicoNotificacao mockServico = Mockito.mock(ServicoNotificacao.class);
        Biblioteca biblioteca = new Biblioteca(mockServico);

        assertNull(biblioteca.buscarLivro("Inexistente"));
    }

    @Test
    void deveEmprestarEDepoisDevolverMesmoLivro() {
        ServicoNotificacao mockServico = Mockito.mock(ServicoNotificacao.class);
        Biblioteca biblioteca = new Biblioteca(mockServico);
        Usuario usuario = new Usuario("Caio");
        Livro livro = new Livro("Clean Code", "Autor");

        biblioteca.adicionarLivro(livro);
        biblioteca.emprestarLivro(usuario, "Clean Code");
        biblioteca.devolverLivro(usuario, "Clean Code");

        assertFalse(livro.isEmprestado());
    }

    @Test
    void deveAdicionarMultiplosLivrosENaoPerderNenhum() {
        ServicoNotificacao mockServico = Mockito.mock(ServicoNotificacao.class);
        Biblioteca biblioteca = new Biblioteca(mockServico);

        biblioteca.adicionarLivro(new Livro("L1", "A"));
        biblioteca.adicionarLivro(new Livro("L2", "B"));

        assertEquals(2, biblioteca.getLivros().size());
    }


}
