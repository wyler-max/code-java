package org.example.practice.practicecode.javalang.utils.thread.threadLocal;

/**
 * 哈希魔数 </br>
 * 16进制：0x61c88647 </br>
 * 10进制：1640531527 </br>
 * 2进制：01100001110010001000011001000111 </br>
 * 补码：10011110001101110111100110111001 </br>
 */
public class HashMagicNumberTest {
    // 哈希魔数 来自：ThreadLocal.HASH_INCREMENT
    private static final int HASH_INCREMENT = 0x61c88647;

    public static void main(String[] args) {
        System.out.println("打印散列：");
        printHashCode(8);
        printHashCode(16);
        printHashCode(32);
    }

    /**
     * 哈希算法：keyIndex = ((i + 1) * HASH_INCREMENT) & (length - 1) </br>
     * 以上哈希计算可以得到 => 完美散列
     *
     * 证明 (x - y) * HASH_INCREMENT != 2^n * (n m) ，在 x != y，n != m ，HASH_INCREMENT为奇数的情况下恒成⽴
     */
    private static void printHashCode(int capacity) {
        int hashKey = 0;
        for (int i = 0; i < capacity; i++) {
            hashKey = ((i + 1) * HASH_INCREMENT) & (capacity - 1);
            System.out.print(hashKey + " ");
        }
        System.out.println();
    }

    /**
     * 哈希魔数 0x61c88647 = 2^32 * 黄金分割比
     */
    @Test
    public void printMagicNumber() {
        double perfectNum = (Math.sqrt(5) - 1) / 2;
        System.out.println("黄金分割值：" + perfectNum);

        long l1 = (long)((1L << 32) * perfectNum);
        // =2654435769, 16进制值为 0x61c88647的补码
        System.out.println("32位无符号值：" + l1);
        int l2 = (int)l1;
        // =-1640531527, 16进制值为 0x61c88647的反码
        System.out.println("32位有符号值：" + l2);
    }
}
