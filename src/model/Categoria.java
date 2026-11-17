package model;

public class Categoria {
    private int id;
    private String descricao;

    public Categoria(int id, String descricao) {
        setId(id);
        setDescricao(descricao);
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) {
        if (descricao != null && !descricao.trim().isEmpty()) this.descricao = descricao;
        else throw new IllegalArgumentException("A descrição da categoria não pode ser vazia.");
    }

    @Override
    public String toString() {
        return String.format("Categoria #%d | Descrição: %s", id, descricao);
    }
}
