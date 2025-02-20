package app;

import java.util.List;
import java.util.Scanner;

import dao.XDAO;
import model.X;

public class Aplicacao {

    public static void main(String[] args) throws Exception {
        XDAO usuarioDAO = new XDAO();
        Scanner scanner = new Scanner(System.in);
        int opcao = -1;

        while (opcao != 0) {
            System.out.println("\n===== MENU =====");
            System.out.println("1. Inserir usuário");
            System.out.println("2. Atualizar usuário");
            System.out.println("3. Excluir usuário");
            System.out.println("4. Listar usuários");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine(); // Limpar o buffer

            switch (opcao) {
                case 1:
                    System.out.print("Digite o código: ");
                    int codigo = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Digite o login: ");
                    String login = scanner.nextLine();
                    System.out.print("Digite a senha: ");
                    String senha = scanner.nextLine();
                    System.out.print("Digite o sexo (M/F): ");
                    char sexo = scanner.nextLine().charAt(0);
                    X novoUsuario = new X(codigo, login, senha, sexo);
                    if (usuarioDAO.insert(novoUsuario)) {
                        System.out.println("Usuário inserido com sucesso!");
                    } else {
                        System.out.println("Erro ao inserir usuário.");
                    }
                    break;

                case 2:
                    System.out.print("Digite o código do usuário a ser atualizado: ");
                    int codigoAtualizar = scanner.nextInt();
                    scanner.nextLine();
                    X usuarioAtualizar = usuarioDAO.get(codigoAtualizar);
                    if (usuarioAtualizar != null) {
                        System.out.print("Novo login: ");
                        usuarioAtualizar.setLogin(scanner.nextLine());
                        System.out.print("Nova senha: ");
                        usuarioAtualizar.setSenha(scanner.nextLine());
                        System.out.print("Novo sexo (M/F): ");
                        usuarioAtualizar.setSexo(scanner.nextLine().charAt(0));
                        if (usuarioDAO.update(usuarioAtualizar)) {
                            System.out.println("Usuário atualizado com sucesso!");
                        } else {
                            System.out.println("Erro ao atualizar usuário.");
                        }
                    } else {
                        System.out.println("Usuário não encontrado.");
                    }
                    break;

                case 3:
                    System.out.print("Digite o código do usuário a ser excluído: ");
                    int codigoExcluir = scanner.nextInt();
                    if (usuarioDAO.delete(codigoExcluir)) {
                        System.out.println("Usuário excluído com sucesso!");
                    } else {
                        System.out.println("Erro ao excluir usuário.");
                    }
                    break;

                case 4:
                    System.out.println("\n==== Lista de Usuários ====");
                    List<X> usuarios = usuarioDAO.getOrderByCodigo();
                    for (X usuario : usuarios) {
                        System.out.println(usuario);
                    }
                    break;

                case 0:
                    System.out.println("Encerrando o programa.");
                    break;

                default:
                    System.out.println("Opção inválida, tente novamente.");
            }
        }
        scanner.close();
    }
}