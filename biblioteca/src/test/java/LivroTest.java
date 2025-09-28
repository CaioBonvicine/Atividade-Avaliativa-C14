import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LivroTest {

    @Test
    void deveEmprestarLivroDisponivel() {
        Livro livro = new Livro("Java", "Autor");
        livro.emprestar();
        assertTrue(livro.isEmprestado());
    }

    @Test
    void naoDeveEmprestarLivroJaEmprestado() {
        Livro livro = new Livro("Java", "Autor");
        livro.emprestar();
        assertThrows(IllegalStateException.class, livro::emprestar);
    }

    @Test
    void deveDevolverLivroEmprestado() {
        Livro livro = new Livro("Java", "Autor");
        livro.emprestar();
        livro.devolver();
        assertFalse(livro.isEmprestado());
    }

    @Test
    void naoDeveDevolverLivroNaoEmprestado() {
        Livro livro = new Livro("Java", "Autor");
        assertThrows(IllegalStateException.class, livro::devolver);
    }


    @Test
    void tituloEAutorDevemSerMantidos() {
        Livro livro = new Livro("Clean Code", "Robert Martin");
        assertEquals("Clean Code", livro.getTitulo());
        assertEquals("Robert Martin", livro.getAutor());
    }

    @Test
    void estadoInicialDoLivroDeveSerDisponivel() {
        Livro livro = new Livro("Effective Java", "Joshua Bloch");
        assertFalse(livro.isEmprestado());
    }



}
