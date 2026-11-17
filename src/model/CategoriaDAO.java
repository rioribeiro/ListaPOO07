package model;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CategoriaDAO {
    private final String arquivo = "categorias.json";
    private List<Categoria> objetos = new ArrayList<>();

    public CategoriaDAO() {
        abrir();
    }

    public void inserir(Categoria obj) {
        objetos.add(obj);
        salvar();
    }

    public List<Categoria> listar() {
        return objetos;
    }

    public Categoria listar_id(int id) {
        for (Categoria c : objetos) {
            if (c.getId() == id) return c;
        }
        return null;
    }

    public void atualizar(Categoria obj) {
        for (int i = 0; i < objetos.size(); i++) {
            if (objetos.get(i).getId() == obj.getId()) {
                objetos.set(i, obj);
                salvar();
                return;
            }
        }
    }

    public void excluir(int id) {
        objetos.removeIf(c -> c.getId() == id);
        salvar();
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
                    objetos.add(new Categoria(id, desc));
                }
            }
        } catch (Exception e) {
            System.out.println("[AVISO CategoriaDAO]: Erro ao ler " + arquivo);
        }
    }

    private void salvar() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(arquivo))) {
            bw.write("[\n");
            for (int i = 0; i < objetos.size(); i++) {
                Categoria c = objetos.get(i);
                bw.write(String.format("  {\"id\": %d, \"descricao\": \"%s\"}%s\n",
                        c.getId(), c.getDescricao(), (i < objetos.size() - 1 ? "," : "")));
            }
            bw.write("]\n");
        } catch (IOException e) {
            System.out.println("[ERRO CategoriaDAO]: Erro ao salvar " + arquivo);
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
