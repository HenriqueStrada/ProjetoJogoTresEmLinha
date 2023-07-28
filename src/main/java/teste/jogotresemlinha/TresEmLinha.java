package teste.jogotresemlinha;
import java.util.InputMismatchException;
import java.util.Scanner;

public class TresEmLinha {
    private static char[][] tabela = new char[6][6];
    private static String turno = "A";

    public static void main(String[] args) {
        String winner = null;
        preencherTabelaVazia();

        System.out.println("Bem-vindo ao Tres Em Linha para dois jogadores!");
        System.out.println("--------------------------------");
        printTabela();
        System.out.println("A é o primeiro jogador, escolha entre as 6 colunas para por um numero!");

        try (Scanner in = new Scanner(System.in)) {
            while (winner == null) {
                int colInput;
                try {
                    colInput = in.nextInt();
                    if (!(colInput > 0 && colInput <= 6)) {
                        System.out.println("Input Invalido; recoloque um numero de coluna:");
                        continue;
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Input Invalido; recoloque um numero de coluna:");
                    continue;
                }
                int col = colInput - 1;
                int row = -1;
                for (int r = tabela.length - 1; r >= 0; r--) {
                    if (tabela[r][col] == ' ') {
                        row = r;
                        break;
                    }
                }
                if (row != -1) {
                    tabela[row][col] = turno.charAt(0);
                    printTabela();
                    winner = checkWinner(row, col);
                    if (winner == null) {
                        if (turno.equals("A")) {
                            turno = "B";
                        } else {
                            turno = "A";
                        }
                    }
                } else {
                    System.out.println("Coluna está cheia; recoloque um numero em outra coluna:");
                    continue;
                }
            }
            if (winner.equalsIgnoreCase("empate")) {
                System.out.println("É um empate, obrigado por jogadr.");
            } else {
                System.out.println("Parabéns! " + winner + " Ganhou! Obrigado por jogar.");
            }
        }
    }

    static String checkWinner(int row, int col) {
        char player = tabela[row][col];
        // Verifica a linha
        int count = 0;
        for (int c = 0; c < tabela[row].length; c++) {
            if (tabela[row][c] == player) {
                count++;
                if (count == 3) return turno;
            } else {
                count = 0;
            }
        }
        // Verifica a coluna
        count = 0;
        for (int r = 0; r < tabela.length; r++) {
            if (tabela[r][col] == player) {
                count++;
                if (count == 3) return turno;
            } else {
                count = 0;
            }
        }

        // Verifica a diagonal principal
        count = 0;
        int startRow = Math.max(0, row - col);
        int startCol = Math.max(0, col - row);
        for (int i = 0; i < Math.min(tabela.length, tabela[0].length); i++) {
            int r = startRow + i;
            int c = startCol + i;
            if (r >= tabela.length || c >= tabela[0].length) break;
            if (tabela[r][c] == player) {
                count++;
                if (count == 3) return turno;
            } else {
                count = 0;
            }
        }
        // Verifica a diagonal secundária
        count = 0;
        startRow = Math.min(tabela.length - 1, row + col);
        startCol = Math.max(0, col - (tabela.length - 1 - row));
        for (int i = 0; i < Math.min(tabela.length, tabela[0].length); i++) {
            int r = startRow - i;
            int c = startCol + i;
            if (r < 0 || c >=tabela[0].length) break;
            if (tabela[r][c] == player) {
                count++;
                if (count == 3) return turno;
            } else {
                count = 0;
            }
        }
        // Verifica se há espaços vazios
        for (int r = 0; r < tabela.length; r++) {
            for (int c = 0; c < tabela[r].length; c++) {
                if (tabela[r][c] == ' ') {
                    return null;
                }
            }
        }
        // Empate
        return "draw";
    }

    private static void printTabela() {
        StringBuilder createBoard = new StringBuilder("/---|---|-------|---|---\\ \n");
        for (int row = 0; row < tabela.length; row++) {
            createBoard.append("| ");
            for (int col = 0; col < tabela[row].length; col++) {
                createBoard.append(tabela[row][col]).append(" | ");
            }
            createBoard.append(" \n|-----------------------| \n");
        }
        createBoard.append("/---|---|-------|---|---\\ \n");
        System.out.println(createBoard);
    }

    private static void preencherTabelaVazia() {
        for (int row = 0; row < tabela.length; row++) {
            for (int col = 0; col < tabela[row].length; col++) {
                tabela[row][col] = ' ';
            }
        }
    }
}
