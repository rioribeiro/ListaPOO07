package model;

public class Produto {
    private int id;
    private String descricao;
    private double preco;
    private int estoque;
    private int idCategoria;

    public Produto(int id, String descricao, double preco, int estoque, int idCategoria) {
        setId(id);
        setDescricao(descricao);
        setPreco(preco);
        setEstoque(estoque);
        setIdCategoria(idCategoria);
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) {
        if (descricao != null && !descricao.trim().isEmpty()) this.descricao = descricao;
        else throw new IllegalArgumentException("A descrição do produto não pode ser vazia.");
    }

    public double getPreco() { return preco; }
    public void setPreco(double preco) {
        if (preco >= 0) this.preco = preco;
        else throw new IllegalArgumentException("O preço do produto não pode ser negativo.");
    }

    public int getEstoque() { return estoque; }
    public void setEstoque(int estoque) {
        if (estoque >= 0) this.estoque = estoque;
        else throw new IllegalArgumentException("O estoque do produto não pode ser negativo.");
    }

    public int getIdCategoria() { return idCategoria; }
    public void setIdCategoria(int idCategoria) {
        if (idCategoria > 0) this.idCategoria = idCategoria;
        else throw new IllegalArgumentException("ID da categoria deve ser maior que zero.");
    }

    @Override
    public String toString() {
        return String.format("Produto #%d | %s | Preço: R$ %.2f | Estoque: %d un. | Categoria ID: %d",
                id, descricao, preco, estoque, idCategoria);
    }
}
