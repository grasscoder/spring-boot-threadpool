package org.example;

import java.util.List;

class ListNode {
    int val;
    ListNode next = null;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }
}

public class Solution2 {
    public static ListNode reverseBetween(ListNode head, int m, int n) {
        // write code here
        if (head == null || head.next == null || m == n || m <= 0 || n < m) {
            return head;
        }
        ListNode node = head, temp = head;
        for (int i = 1; i < m; i++) {
            if (node != null) {
                temp = node;
                node = node.next;
            }
        }
        //System.out.println("val = " + node.val);
        if (m == 1)
            return reverseBetween1(node, n - m + 1);
        ListNode nn = reverseBetween1(node, n - m + 1);
        temp.next = nn;
        return head;
    }

    public static ListNode reverseBetween1(ListNode head, int nums) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode cur = head;
        ListNode pre = null;
        int i = 0;
        while (cur != null) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
            i++;
            if (i == nums) {
                break;
            }
        }
        head.next = cur;
        return pre;
    }

    public static void main1(String[] args) {
        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(2);
        ListNode n3 = new ListNode(3);
        ListNode n4 = new ListNode(4);
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        ListNode head = reverseBetween(n1, 2, 4);
        while (head != null) {
            System.out.println(head.val);
            head = head.next;
        }
    }

    /*
     * 纸币的最少张数
     * */
    public static int least(int target) {
        if (target <= 0) {
            return 0;
        }
        System.out.println("商品价值: " + target + " 元");
        int res = 0;
        while (target != 0) {
            int curM = getCurrentM(target);
            int m = target / curM;
            System.out.println("需要面值 " + curM + " 元的人民币: " + m + " 张");
            res = res + m;
            target = target % curM;
        }
        System.out.println("至少需要人民币 " + res + " 张");
        return res;
    }

    public static int getCurrentM(int m) {
        int curM = 0;
        if (m >= 100)
            curM = 100;
        else if (m >= 50)
            curM = 50;
        else if (m >= 20)
            curM = 20;
        else if (m >= 10)
            curM = 10;
        else if (m >= 5)
            curM = 5;
        else if (m >= 1)
            curM = 1;
        return curM;
    }

    public static void main(String[] args) {
        System.out.println(least(141));
    }
}
