package school;

import java.util.*;

class Aluno {
    String nome;
    String cpf;
    Map<String, Double> notas;

    Aluno(String nome, String cpf) {
        this.nome = nome;
        this.cpf = cpf;
        this.notas = new HashMap<>();
    }
}

public class SchoolApp {
    // Cores ANSI
    public static final String RESET = "\u001B[0m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";
    public static final String CYAN = "\u001B[36m";
    
    static Scanner scanner = new Scanner(System.in);
    static List<Aluno> alunos = new ArrayList<>();

    public static void main(String[] args) {
        while (true) {
            System.out.println(CYAN + "\n--- Sistema Escolar ---" + RESET);
            System.out.println(BLUE + "1. Cadastrar aluno" + RESET);
            System.out.println(BLUE + "2. Listar alunos" + RESET);
            System.out.println(BLUE + "3. Adicionar nota" + RESET);
            System.out.println(BLUE + "4. Ver notas de um aluno" + RESET);
            System.out.println(BLUE + "5. Listar todas as notas de todos os alunos em uma matéria" + RESET);
            System.out.println(RED + "0. Sair" + RESET);
            System.out.print(YELLOW + "Escolha uma opção: " + RESET);
            int opcao = Integer.parseInt(scanner.nextLine());

            switch (opcao) {
                case 1:
                    cadastrarAluno();
                    break;
                case 2:
                    listarAlunos();
                    break;
                case 3:
                    adicionarNota();
                    break;
                case 4:
                    verNotasAluno();
                    break;
                case 5:
                    listarNotasPorMateria();
                    break;
                case 0:
                    System.out.println(RED + "Encerrando..." + RESET);
                    return;
                default:
                    System.out.println(RED + "Opção inválida." + RESET);
            }
        }
    }

    static void cadastrarAluno() {
        System.out.print(CYAN + "Nome do aluno: " + RESET);
        String nome = scanner.nextLine();
        System.out.print(CYAN + "CPF do aluno: " + RESET);
        String cpf = scanner.nextLine();
        alunos.add(new Aluno(nome, cpf));
        System.out.println(GREEN + "Aluno cadastrado com sucesso!" + RESET);
    }

    static void listarAlunos() {
        if (alunos.isEmpty()) {
            System.out.println(RED + "Nenhum aluno cadastrado." + RESET);
            return;
        }

        System.out.println(GREEN + "\n--- Lista de Alunos ---" + RESET);
        for (Aluno a : alunos) {
            System.out.println(CYAN + "Nome: " + RESET + a.nome + " | " + CYAN + "CPF: " + RESET + a.cpf);
        }
    }

    static void adicionarNota() {
        System.out.print(CYAN + "CPF do aluno: " + RESET);
        String cpf = scanner.nextLine();
        Aluno aluno = buscarAlunoPorCpf(cpf);
        if (aluno == null) {
            System.out.println(RED + "Aluno não encontrado." + RESET);
            return;
        }

        System.out.print(CYAN + "Nome da matéria: " + RESET);
        String materia = scanner.nextLine();
        System.out.print(CYAN + "Nota: " + RESET);
        double nota = Double.parseDouble(scanner.nextLine());
        aluno.notas.put(materia, nota);
        System.out.println(GREEN + "Nota registrada com sucesso!" + RESET);
    }

    static void verNotasAluno() {
        System.out.print(CYAN + "CPF do aluno: " + RESET);
        String cpf = scanner.nextLine();
        Aluno aluno = buscarAlunoPorCpf(cpf);
        if (aluno == null) {
            System.out.println(RED + "Aluno não encontrado." + RESET);
            return;
        }

        System.out.println(GREEN + "\n--- Notas de " + aluno.nome + " ---" + RESET);
        if (aluno.notas.isEmpty()) {
            System.out.println(RED + "Nenhuma nota registrada." + RESET);
        } else {
            for (Map.Entry<String, Double> entry : aluno.notas.entrySet()) {
                System.out.println(CYAN + entry.getKey() + ": " + RESET + entry.getValue());
            }
        }
    }

    static void listarNotasPorMateria() {
        System.out.print(CYAN + "Nome da matéria: " + RESET);
        String materia = scanner.nextLine();

        System.out.println(GREEN + "\nNotas para a matéria: " + materia + RESET);
        System.out.println("-------------------------------");

        boolean encontrouNota = false;
        for (Aluno a : alunos) {
            if (a.notas.containsKey(materia)) {
                System.out.println(CYAN + a.nome + ": " + RESET + a.notas.get(materia));
                encontrouNota = true;
            }
        }

        if (!encontrouNota) {
            System.out.println(RED + "Nenhum aluno possui nota para essa matéria." + RESET);
        }
    }

    static Aluno buscarAlunoPorCpf(String cpf) {
        for (Aluno a : alunos) {
            if (a.cpf.equals(cpf)) {
                return a;
            }
        }
        return null;
    }
}
