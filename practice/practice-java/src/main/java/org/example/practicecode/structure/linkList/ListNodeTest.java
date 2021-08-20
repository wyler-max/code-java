package org.example.practicecode.structure.linkList;

import org.example.practicecode.structure.utils.LinkListUtil;
import org.junit.Test;

public class ListNodeTest {

    @Test
    public void testTraverse() {
        ListNode head = LinkListUtil.buildLinkList(3);
        System.out.println("普通迭代");
        LinkListUtil.traverse(head);

        System.out.println("递归迭代");
        LinkListUtil.traverseRecursion(head);
    }

}
