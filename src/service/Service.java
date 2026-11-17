package service;

import model.*;
import java.util.List;

public class Service {

    private static final ClienteDAO clienteDAO = new ClienteDAO();
    private static final CategoriaDAO categoriaDAO = new CategoriaDAO();
    private static final ProdutoDAO produtoDAO = new ProdutoDAO();

    // ----------------- OPERAÇÕES CLIENTE -----------------
    public static void cliente_inserir(String nome, String email, String fone) {
        int maxId = 0;
        for (Cliente c : clienteDAO.listar()) {
            if (c.getId() > maxId) maxId = c.getId();
        }
        Cliente obj = new Cliente(maxId + 1, nome, email, fone);
        clienteDAO.inserir(obj);
    }

    public static List<Cliente> cliente_listar() {
        return clienteDAO.listar();
    }

    public static Cliente cliente_listar_id(int id) {
        return clienteDAO.listar_id(id);
    }

    public static void cliente_atualizar(int id, String nome, String email, String fone) {
        if (cliente_listar_id(id) == null) {
            throw new IllegalArgumentException("Cliente ID " + id + " não encontrado.");
        }
        Cliente obj = new Cliente(id, nome, email, fone);
        clienteDAO.atualizar(obj);
    }

    public static void cliente_excluir(int id) {
        if (cliente_listar_id(id) == null) {
            throw new IllegalArgumentException("Cliente ID " + id + " não encontrado.");
        }
        clienteDAO.excluir(id);
    }

    // ----------------- OPERAÇÕES CATEGORIA -----------------
    public static void categoria_inserir(String descricao) {
        int maxId = 0;
        for (Categoria c : categoriaDAO.listar()) {
            if (c.getId() > maxId) maxId = c.getId();
        }
        Categoria obj = new Categoria(maxId + 1, descricao);
        categoriaDAO.inserir(obj);
    }

    public static List<Categoria> categoria_listar() {
        return categoriaDAO.listar();
    }

    public static Categoria categoria_listar_id(int id) {
        return categoriaDAO.listar_id(id);
    }

    public static void categoria_atualizar(int id, String descricao) {
        if (categoria_listar_id(id) == null) {
            throw new IllegalArgumentException("Categoria ID " + id + " não encontrada.");
        }
        Categoria obj = new Categoria(id, descricao);
        categoriaDAO.atualizar(obj);
    }

    public static void categoria_excluir(int id) {
        if (categoria_listar_id(id) == null) {
            throw new IllegalArgumentException("Categoria ID " + id + " não encontrada.");
        }
        categoriaDAO.excluir(id);
    }

    // ----------------- OPERAÇÕES PRODUTO -----------------
    public static void produto_inserir(String descricao, double preco, int estoque, int idCategoria) {
        if (categoria_listar_id(idCategoria) == null) {
            throw new IllegalArgumentException("Categoria informada (ID " + idCategoria + ") não existe.");
        }
        int maxId = 0;
        for (Produto p : produtoDAO.listar()) {
            if (p.getId() > maxId) maxId = p.getId();
        }
        Produto obj = new Produto(maxId + 1, descricao, preco, estoque, idCategoria);
        produtoDAO.inserir(obj);
    }

    public static List<Produto> produto_listar() {
        return produtoDAO.listar();
    }

    public static Produto produto_listar_id(int id) {
        return produtoDAO.listar_id(id);
    }

    public static void produto_atualizar(int id, String descricao, double preco, int estoque, int idCategoria) {
        if (produto_listar_id(id) == null) {
            throw new IllegalArgumentException("Produto ID " + id + " não encontrado.");
        }
        if (categoria_listar_id(idCategoria) == null) {
            throw new IllegalArgumentException("Categoria informada (ID " + idCategoria + ") não existe.");
        }
        Produto obj = new Produto(id, descricao, preco, estoque, idCategoria);
        produtoDAO.atualizar(obj);
    }

    public static void produto_excluir(int id) {
        if (produto_listar_id(id) == null) {
            throw new IllegalArgumentException("Produto ID " + id + " não encontrado.");
        }
        produtoDAO.excluir(id);
    }

    // Reajuste percentual no preço de todos os produtos
    public static void produto_reajustar(double percentual) {
        List<Produto> lista = produtoDAO.listar();
        if (lista.isEmpty()) {
            throw new IllegalArgumentException("Nenhum produto cadastrado para reajuste.");
        }
        double fator = 1.0 + (percentual / 100.0);
        for (Produto p : lista) {
            p.setPreco(p.getPreco() * fator);
        }
        produtoDAO.salvar();
    }
}
