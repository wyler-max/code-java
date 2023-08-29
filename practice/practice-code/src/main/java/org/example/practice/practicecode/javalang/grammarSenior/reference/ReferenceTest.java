package org.example.practice.practicecode.javalang.grammarSenior.reference;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

public class ReferenceTest {
    public static void main(String[] args) throws InterruptedException {
        ReferenceQueue<HugeBlock> queue = new ReferenceQueue<>();
        HugeBlock block = HugeBlock.sizeOf(4 * 1024 * 1024);
        WeakReference<HugeBlock> hugeBlockRef1 = new WeakReference<>(block, queue);
        block = null;

        System.out.println(
            "Before trigger gc, hugeBlockRef1 = " + hugeBlockRef1 + ", hugeBlockRef.get() = " + hugeBlockRef1.get());

        System.gc(); // 触发GC

        System.out.println(
            "After trigger gc, hugeBlockRef1 = " + hugeBlockRef1 + ", hugeBlockRef.get() = " + hugeBlockRef1.get());

        Reference<? extends HugeBlock> ref = queue.remove();
        System.out.println("Get ref from queue: " + ref);
    }

    private static class HugeBlock {
        private byte[] block;

        private HugeBlock(int size) {
            this.block = new byte[size];
        }

        public static HugeBlock sizeOf(int size) {
            return new HugeBlock(size);
        }

        @Override
        public String toString() {
            return String.format("Byte Block [%d bytes]", block.length);
        }
    }
}
