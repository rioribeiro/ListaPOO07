package template;

import service.Service;
import model.*;
import java.util.List;
import java.util.Scanner;

public class UI {

    private static final Scanner sc = new Scanner(System.in);

    public static int menu() {
        System.out.println("\n================ PAINEL ADMINISTRAÇÃO ================");
        System.out.println(" 1 - Cliente: Inserir");
        System.out.println(" 2 - Cliente: Listar");
        System.out.println(" 3 - Cliente: Atualizar");
        System.out.println(" 4 - Cliente: Excluir");
        System.out.println("------------------------------------------------------");
        System.out.println(" 5 - Categoria: Inserir");
        System.out.println(" 6 - Categoria: Listar");
        System.out.println(" 7 - Categoria: Atualizar");
        System.out.println(" 8 - Categoria: Excluir");
        System.out.println("------------------------------------------------------");
        System.out.println(" 9 - Produto: Inserir");
        System.out.println("10 - Produto: Listar");
        System.out.println("11 - Produto: Atualizar");
        System.out.println("12 - Produto: Excluir");
        System.out.println("13 - Produto: Reajustar Preços");
        System.out.println("------------------------------------------------------");
        System.out.println(" 0 - Sair");
        System.out.print("Escolha uma opção: ");
        return sc.nextInt();
    }

    // CLIENTES
    public static void cliente_inserir() {
        sc.nextLine();
        System.out.print("Nome: ");
        String nome = sc.nextLine();
        System.out.print("E-mail: ");
        String email = sc.nextLine();
        System.out.print("Telefone: ");
        String fone = sc.nextLine();

        Service.cliente_inserir(nome, email, fone);
        System.out.println("[SUCESSO] Cliente cadastrado com ID automático!");
    }

    public static void cliente_listar() {
        List<Cliente> lista = Service.cliente_listar();
        if (lista.isEmpty()) {
            System.out.println("\nNenhum cliente cadastrado.");
        } else {
            System.out.println("\n--- RELAÇÃO DE CLIENTES ---");
            for (Cliente c : lista) System.out.println(c);
        }
    }

    public static void cliente_atualizar() {
        System.out.print("ID do cliente a atualizar: ");
        int id = sc.nextInt();
        sc.nextLine();

        System.out.print("Novo Nome: ");
        String nome = sc.nextLine();
        System.out.print("Novo E-mail: ");
        String email = sc.nextLine();
        System.out.print("Novo Telefone: ");
        String fone = sc.nextLine();

        Service.cliente_atualizar(id, nome, email, fone);
        System.out.println("[SUCESSO] Cliente atualizado!");
    }

    public static void cliente_excluir() {
        System.out.print("ID do cliente a excluir: ");
        int id = sc.nextInt();
        Service.cliente_excluir(id);
        System.out.println("[SUCESSO] Cliente removido!");
    }

    // CATEGORIAS
    public static void categoria_inserir() {
        sc.nextLine();
        System.out.print("Descrição da Categoria: ");
        String desc = sc.nextLine();

        Service.categoria_inserir(desc);
        System.out.println("[SUCESSO] Categoria cadastrada com ID automático!");
    }

    public static void categoria_listar() {
        List<Categoria> lista = Service.categoria_listar();
        if (lista.isEmpty()) {
            System.out.println("\nNenhuma categoria cadastrada.");
        } else {
            System.out.println("\n--- RELAÇÃO DE CATEGORIAS ---");
            for (Categoria c : lista) System.out.println(c);
        }
    }

    public static void categoria_atualizar() {
        System.out.print("ID da categoria a atualizar: ");
        int id = sc.nextInt();
        sc.nextLine();

        System.out.print("Nova Descrição: ");
        String desc = sc.nextLine();

        Service.categoria_atualizar(id, desc);
        System.out.println("[SUCESSO] Categoria atualizada!");
    }

    public static void categoria_excluir() {
        System.out.print("ID da categoria a excluir: ");
        int id = sc.nextInt();
        Service.categoria_excluir(id);
        System.out.println("[SUCESSO] Categoria removida!");
    }

    // PRODUTOS
    public static void produto_inserir() {
        sc.nextLine();
        System.out.print("Descrição do Produto: ");
        String desc = sc.nextLine();
        System.out.print("Preço (R$): ");
        double preco = sc.nextDouble();
        System.out.print("Estoque: ");
        int est = sc.nextInt();
        System.out.print("ID da Categoria: ");
        int idCat = sc.nextInt();

        Service.produto_inserir(desc, preco, est, idCat);
        System.out.println("[SUCESSO] Produto cadastrado com ID automático!");
    }

    public static void produto_listar() {
        List<Produto> lista = Service.produto_listar();
        if (lista.isEmpty()) {
            System.out.println("\nNenhum produto cadastrado.");
        } else {
            System.out.println("\n--- RELAÇÃO DE PRODUTOS ---");
            for (Produto p : lista) System.out.println(p);
        }
    }

    public static void produto_atualizar() {
        System.out.print("ID do produto a atualizar: ");
        int id = sc.nextInt();
        sc.nextLine();

        System.out.print("Nova Descrição: ");
        String desc = sc.nextLine();
        System.out.print("Novo Preço (R$): ");
        double preco = sc.nextDouble();
        System.out.print("Novo Estoque: ");
        int est = sc.nextInt();
        System.out.print("Novo ID da Categoria: ");
        int idCat = sc.nextInt();

        Service.produto_atualizar(id, desc, preco, est, idCat);
        System.out.println("[SUCESSO] Produto atualizado!");
    }

    public static void produto_excluir() {
        System.out.print("ID do produto a excluir: ");
        int id = sc.nextInt();
        Service.produto_excluir(id);
        System.out.println("[SUCESSO] Produto removido!");
    }

    public static void produto_reajustar() {
        System.out.print("Informe o percentual de reajuste (ex: 10 para +10% ou -5 para -5%): ");
        double perc = sc.nextDouble();

        Service.produto_reajustar(perc);
        System.out.println("[SUCESSO] Reajuste de " + perc + "% aplicado a todos os produtos!");
    }

    public static void main(String[] args) {
        int op = -1;
        while (op != 0) {
            try {
                op = menu();
                switch (op) {
                    case 1 -> cliente_inserir();
                    case 2 -> cliente_listar();
                    case 3 -> cliente_atualizar();
                    case 4 -> cliente_excluir();

                    case 5 -> categoria_inserir();
                    case 6 -> categoria_listar();
                    case 7 -> categoria_atualizar();
                    case 8 -> categoria_excluir();

                    case 9 -> produto_inserir();
                    case 10 -> produto_listar();
                    case 11 -> produto_atualizar();
                    case 12 -> produto_excluir();
                    case 13 -> produto_reajustar();

                    case 0 -> System.out.println("\nEncerrando aplicação em camadas...");
                    default -> System.out.println("\nOpção inválida!");
                }
            } catch (Exception e) {
                System.out.println("\n[ERRO]: " + e.getMessage());
                sc.nextLine();
            }
        }
    }
}
