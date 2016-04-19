package lib;

import java.util.List;
import java.util.Random;

public class Library {

    public static int randomIntBetween(int min, int max) {
        return min + (int)(Math.random() * (max - min) + 1);
    }

    public static int randomIntFromList(List<Integer> nums) {
        return nums.get(new Random().nextInt(nums.size()));
    }

    public static int randomPick(int... args) {
        Random random = new Random();
        int index = random.nextInt(args.length);
        return args[index];
    }

    public static String randomString(String... args) {
        Random random = new Random();
        int index = random.nextInt(args.length);
        return args[index];
    }
}
