import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

class UsuarioTest {
    private Usuario usuario;
    private Livro livro;

    @BeforeEach
    void setUp() {
        usuario = new Usuario("Caio");
        livro = new Livro("Java", "Autor");
    }

    @Test
    void emprestarLivro() {
        usuario.emprestarLivro(livro);
        assertTrue(usuario.getLivrosEmprestados().contains(livro));
    }

    @Test
    void emprestarMaisQueTresLivros() {
        usuario.emprestarLivro(new Livro("L1", "A"));
        usuario.emprestarLivro(new Livro("L2", "A"));
        usuario.emprestarLivro(new Livro("L3", "A"));

        assertThrows(IllegalStateException.class, () ->
                usuario.emprestarLivro(new Livro("L4", "A")));
    }

    @Test
    void devolverLivro() {
        usuario.emprestarLivro(livro);
        usuario.devolverLivro(livro);
        assertFalse(usuario.getLivrosEmprestados().contains(livro));
    }

    @Test
    void devolverLivroNaoEmprestado() {
        assertThrows(IllegalStateException.class, () -> usuario.devolverLivro(livro));
    }

    @Test
    void tresLivrosEmprestados() {
        usuario.emprestarLivro(new Livro("L1", "A"));
        usuario.emprestarLivro(new Livro("L2", "B"));
        usuario.emprestarLivro(new Livro("L3", "C"));

        assertEquals(3, usuario.getLivrosEmprestados().size());
    }

    @Test
    void devolverReduzLista() {
        usuario.emprestarLivro(livro);
        usuario.devolverLivro(livro);
        assertEquals(0, usuario.getLivrosEmprestados().size());
    }

    @Test
    void livroJaEmprestadoPorOutroUsuario() {
        Usuario u2 = new Usuario("João");

        usuario.emprestarLivro(livro);

        assertThrows(IllegalStateException.class, () ->
                u2.emprestarLivro(livro));
    }
}