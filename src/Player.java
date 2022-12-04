import java.util.Random;

public class Player {
    public int[] prizes = new int[6];
    public int lastSixth;

    public Player() {
        for (int i = 0; i < 6; i++)
            prizes[i] = 0;
    }

    public static Player last6P;
    public static Random ran = new Random();

    public boolean round() {
        int[] dices = dice();
        int res = judge(dices);
        getPrize(res);
        display(dices, res);
        return (res == 9);
    }

    public void display(int[] d, int n) {
        for (int i : d)
            System.out.print(i + ",");
        switch (n) {
            case 0:
                System.out.println("无奖，继续努力");
                break;
            case 1:
                System.out.println("一秀，恭喜");
                break;
            case 2:
                System.out.println("二举，恭喜");
                break;
            case 3:
                System.out.println("三红，恭喜");
                break;
            case 4:
                System.out.println("四进，恭喜");
                break;
            case 5:
                System.out.println("对堂，恭喜");
                break;
            case 6:
                System.out.println("状元，恭喜");
                break;
            case 7:
                System.out.println("状元，恭喜");
                break;
            case 8:
                System.out.println("状元，恭喜");
                break;
            case 9:
                System.out.println("六杯红，恭喜");
                break;
        }
    }

    public int[] dice() {
        int[] dices = new int[6];
        for (int i = 0; i < 6; i++)
            dices[i] = ran.nextInt(6) + 1;
        return dices;
    }

    public int judge(int[] d) {
        int[] nums = new int[6];
        for (int i = 0; i < 6; i++)
            nums[d[i] - 1]++;
        if (nums[3] == 6)
            return 9;
        else if (nums[3] == 4 && nums[0] == 2)
            return 8;
        else if ((nums[0] == 5) || (nums[1] == 5) || (nums[2] == 5) || (nums[3] == 5) || (nums[4] == 5) || (nums[5] == 5))
            return 7;
        else if (nums[3] == 4)
            return 6;
        else if ((nums[0] == 1) && (nums[1] == 1) && (nums[2] == 1) && (nums[3] == 1) && (nums[4] == 1) && (nums[5] == 1))
            return 5;
        else if ((nums[0] == 4) || (nums[1] == 4) || (nums[2] == 4) || (nums[4] == 4) || (nums[5] == 4))
            return 4;
        else
            return nums[3];
    }

    public void getPrize(int n) {
        if (n == 0) ;
        else if (n > 0 && n < 6) {
            if (Prize.pool[n - 1] > 0) {
                Prize.pool[n - 1]--;
                prizes[n - 1]++;
            }
        } else if (n > 5 && n < 9) {
            if (Prize.pool[5] > 0) {
                Prize.pool[5]--;
                prizes[5]++;
                lastSixth = n;
                last6P = this;
            } else if (last6P.lastSixth < n) {
                prizes[5]++;
                lastSixth = n;
                last6P = this;
                Prize.initPool[5]++;
            }
        } else if (n == 9) {
            prizes = (int[]) Prize.initPool.clone();
        }
    }
}
