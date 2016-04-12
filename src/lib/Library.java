package lib;

import java.util.List;
import java.util.Random;

public class Library {

    public static int randomInt(int min, int max) {
        return min + (int)(Math.random() * (max - min) + 1);
    }

    public static int randomIntFromList(List<Integer> nums) {
        return nums.get(new Random().nextInt(nums.size()));
    }

    public static int randomInts(int... args) {
        Random random = new Random();
        return random.nextInt(args.length);
    }

    public static String randomString(String[] strings) {
        Random random = new Random();
        int index = random.nextInt(strings.length);
        return strings[index];
    }
}
