import java.util.Scanner;

public class CanWin {

    static boolean canWin(int leap, int[] game) {
        return solve(game, new boolean[game.length], leap, 0);
    }

    static boolean solve(int[] game, boolean[] visited, int leap, int i) {
        
        if (i >= game.length)
            return true;
        
        if (i < 0 || game[i] == 1 || visited[i])
            return false;

        visited[i] = true;

        return solve(game, visited, leap, i + 1)
                || solve(game, visited, leap, i + leap)
                || solve(game, visited, leap, i - 1);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int q = sc.nextInt();

        while (q-- > 0) {
            int n = sc.nextInt();
            int leap = sc.nextInt();
            int[] game = new int[n];
            for (int i = 0; i < n; i++) {
                game[i] = sc.nextInt();
            }
            System.out.println(canWin(leap, game) ? "YES" : "NO");
        }
        sc.close();
    }
}
