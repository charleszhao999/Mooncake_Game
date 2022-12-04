import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("请依次指定一秀、二举、三红、四进、对堂、状元奖品数。");
        for (int i = 0; i < 6; i++)
            Prize.pool[i] = input.nextInt();
        Prize.initPool = (int[]) Prize.pool.clone();
        System.out.print("请指定玩家人数：");
        int pNum = input.nextInt();
        ArrayList<Player> players = new ArrayList<Player>();
        for (int i = 0; i < pNum; i++)
            players.add(new Player());
        boolean gameOver = false;
        while (!gameOver) {
            for (int i = 0; i < pNum; i++) {
                System.out.println((i + 1) + "号玩家请掷筛子：");
                //input.next();
                gameOver = players.get(i).round();
                if (gameOver) {
                    for (int j = 0; j < pNum; j++) {
                        if (j == i)
                            continue;
                        else
                            for (int k = 0; k < 6; k++)
                                players.get(j).prizes[k] = 0;
                    }
                    break;
                }
                int sum = 0;
                for (int j : Prize.pool)
                    sum += j;
                if (sum == 0) {
                    gameOver = true;
                    break;
                }
            }
        }
        System.out.println("游戏结束，即将显示游戏结果。");
        //System.out.println("玩家序号" + "\t" + "一秀" + "\t" + "二举" + "\t" + "三红" + "\t" + "四进" + "\t" + "对堂" + "\t" + "状元");
        System.out.printf("%s%5s%5s%5s%5s%5s%5s\n", "玩家序号", "一秀", "二举", "三红", "四进", "对堂", "状元");
        for (int i = 0; i < pNum; i++) {
            System.out.printf("%6d", (i + 1));
            for (int j : players.get(i).prizes)
                System.out.printf("%6d", j);
            System.out.println();
        }
    }
}
