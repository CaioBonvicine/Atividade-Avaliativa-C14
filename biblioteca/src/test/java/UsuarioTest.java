import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UsuarioTest {

    @Test
    void deveEmprestarLivro() {
        Usuario usuario = new Usuario("Caio");
        Livro livro = new Livro("Java", "Autor");
        usuario.emprestarLivro(livro);
        assertTrue(usuario.getLivrosEmprestados().contains(livro));
    }

    @Test
    void naoDeveEmprestarMaisQueTresLivros() {
        Usuario usuario = new Usuario("Caio");
        usuario.emprestarLivro(new Livro("L1", "A"));
        usuario.emprestarLivro(new Livro("L2", "A"));
        usuario.emprestarLivro(new Livro("L3", "A"));

        assertThrows(IllegalStateException.class,
                () -> usuario.emprestarLivro(new Livro("L4", "A")));
    }

    @Test
    void deveDevolverLivro() {
        Usuario usuario = new Usuario("Caio");
        Livro livro = new Livro("Java", "Autor");
        usuario.emprestarLivro(livro);
        usuario.devolverLivro(livro);
        assertFalse(usuario.getLivrosEmprestados().contains(livro));
    }

    @Test
    void naoDeveDevolverLivroNaoEmprestado() {
        Usuario usuario = new Usuario("Caio");
        Livro livro = new Livro("Java", "Autor");
        assertThrows(IllegalStateException.class,
                () -> usuario.devolverLivro(livro));
    }


    @Test
    void devePermitirMultiplosLivrosEmprestadosAteOLimite() {
        Usuario usuario = new Usuario("Caio");
        Livro l1 = new Livro("Livro1", "A");
        Livro l2 = new Livro("Livro2", "B");
        Livro l3 = new Livro("Livro3", "C");

        usuario.emprestarLivro(l1);
        usuario.emprestarLivro(l2);
        usuario.emprestarLivro(l3);

        assertEquals(3, usuario.getLivrosEmprestados().size());
    }

    @Test
    void devolverLivroReduzListaDeEmprestados() {
        Usuario usuario = new Usuario("Caio");
        Livro livro = new Livro("Livro Teste", "Autor");
        usuario.emprestarLivro(livro);
        usuario.devolverLivro(livro);

        assertEquals(0, usuario.getLivrosEmprestados().size());
    }

    @Test
    void deveLancarErroAoEmprestarLivroJaEmprestadoPorOutroUsuario() {
        Usuario u1 = new Usuario("Caio");
        Usuario u2 = new Usuario("João");
        Livro livro = new Livro("Java Avançado", "Autor");

        u1.emprestarLivro(livro);

        assertThrows(IllegalStateException.class,
                () -> u2.emprestarLivro(livro));
    }

}