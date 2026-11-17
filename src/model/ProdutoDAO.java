package model;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO {
    private final String arquivo = "produtos.json";
    private List<Produto> objetos = new ArrayList<>();

    public ProdutoDAO() {
        abrir();
    }

    public void inserir(Produto obj) {
        objetos.add(obj);
        salvar();
    }

    public List<Produto> listar() {
        return objetos;
    }

    public Produto listar_id(int id) {
        for (Produto p : objetos) {
            if (p.getId() == id) return p;
        }
        return null;
    }

    public void atualizar(Produto obj) {
        for (int i = 0; i < objetos.size(); i++) {
            if (objetos.get(i).getId() == obj.getId()) {
                objetos.set(i, obj);
                salvar();
                return;
            }
        }
    }

    public void excluir(int id) {
        objetos.removeIf(p -> p.getId() == id);
        salvar();
    }

    public void salvar() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(arquivo))) {
            bw.write("[\n");
            for (int i = 0; i < objetos.size(); i++) {
                Produto p = objetos.get(i);
                bw.write(String.format("  {\"id\": %d, \"descricao\": \"%s\", \"preco\": %.2f, \"estoque\": %d, \"idCategoria\": %d}%s\n",
                        p.getId(), p.getDescricao(), p.getPreco(), p.getEstoque(), p.getIdCategoria(),
                        (i < objetos.size() - 1 ? "," : "")));
            }
            bw.write("]\n");
        } catch (IOException e) {
            System.out.println("[ERRO ProdutoDAO]: Erro ao salvar " + arquivo);
        }
    }

    private void abrir() {
        File f = new File(arquivo);
        if (!f.exists()) return;
        objetos.clear();
        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String line;
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.startsWith("{")) {
                    int id = Integer.parseInt(extrairValor(line, "id"));
                    String desc = extrairValor(line, "descricao");
                    double preco = Double.parseDouble(extrairValor(line, "preco").replace(",", "."));
                    int estoque = Integer.parseInt(extrairValor(line, "estoque"));
                    int idCat = Integer.parseInt(extrairValor(line, "idCategoria"));
                    objetos.add(new Produto(id, desc, preco, estoque, idCat));
                }
            }
        } catch (Exception e) {
            System.out.println("[AVISO ProdutoDAO]: Erro ao ler " + arquivo);
        }
    }

    private String extrairValor(String json, String chave) {
        String busca = "\"" + chave + "\":";
        int pos = json.indexOf(busca);
        if (pos == -1) return "";
        String sub = json.substring(pos + busca.length()).trim();
        if (sub.startsWith("\"")) {
            return sub.substring(1, sub.indexOf("\"", 1));
        } else {
            int end = sub.indexOf(",");
            if (end == -1) end = sub.indexOf("}");
            return sub.substring(0, end).trim();
        }
    }
}
