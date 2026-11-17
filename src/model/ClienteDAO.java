package model;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {
    private final String arquivo = "clientes.json";
    private List<Cliente> objetos = new ArrayList<>();

    public ClienteDAO() {
        abrir();
    }

    public void inserir(Cliente obj) {
        objetos.add(obj);
        salvar();
    }

    public List<Cliente> listar() {
        return objetos;
    }

    public Cliente listar_id(int id) {
        for (Cliente c : objetos) {
            if (c.getId() == id) return c;
        }
        return null;
    }

    public void atualizar(Cliente obj) {
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
                    String nome = extrairValor(line, "nome");
                    String email = extrairValor(line, "email");
                    String fone = extrairValor(line, "fone");
                    objetos.add(new Cliente(id, nome, email, fone));
                }
            }
        } catch (Exception e) {
            System.out.println("[AVISO ClienteDAO]: Erro ao ler " + arquivo);
        }
    }

    private void salvar() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(arquivo))) {
            bw.write("[\n");
            for (int i = 0; i < objetos.size(); i++) {
                Cliente c = objetos.get(i);
                bw.write(String.format("  {\"id\": %d, \"nome\": \"%s\", \"email\": \"%s\", \"fone\": \"%s\"}%s\n",
                        c.getId(), c.getNome(), c.getEmail(), c.getFone(), (i < objetos.size() - 1 ? "," : "")));
            }
            bw.write("]\n");
        } catch (IOException e) {
            System.out.println("[ERRO ClienteDAO]: Erro ao salvar " + arquivo);
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
