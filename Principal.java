import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int opcao;

        do {
            System.out.println("\n--- Menu Cadastro de Alunos ---");
            System.out.println("1- Inserir aluno");
            System.out.println("2- Listar alunos");
            System.out.println("3- Atualizar aluno");
            System.out.println("4- Excluir aluno");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opçao: ");
            
            opcao = sc.nextInt();
            sc.nextLine(); 

            switch (opcao) {
                case 1:
                    System.out.print("Nome do Aluno: ");
                    String nome = sc.nextLine();
                    System.out.print("Curso: ");
                    String curso = sc.nextLine();
                    System.out.print("Idade: ");
                    int idade = sc.nextInt();
                    sc.nextLine();
                    
                    AlunoManager.inserirAluno(nome, curso, idade);
                    break;

                case 2:
                    AlunoManager.listarAlunos();
                    break;

                case 3:
                    System.out.print("Qual ID do aluno deseja atualizar? ");
                    int idAtualizar = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Novo nome do Aluno: ");
                    String novoNome = sc.nextLine();
                    System.out.print("Novo Curso: ");
                    String novoCurso = sc.nextLine();
                    System.out.print("Nova Idade: ");
                    int novaIdade = sc.nextInt();
                    sc.nextLine();

                    AlunoManager.atualizarAluno(idAtualizar, novoNome, novoCurso, novaIdade);
                    break;

                case 4:
                    System.out.print("Qual ID do aluno deseja excluir? ");
                    int idExcluir = sc.nextInt();
                    sc.nextLine();
                    
                    AlunoManager.excluirAluno(idExcluir);
                    break;

                case 0:
                    System.out.println("Saindo do sistema...");
                    break;

                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
        } while (opcao != 0);

        sc.close();
    }
}