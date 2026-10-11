package com.example.codingtest.linkedlist;

public class TwoThirdsOfLinkedList {
    public <T> Node<T> getTwoThirdsNode(Node<T> head) {
        Node<T> current = head;
        Node<T> answer = null;
        long length = 0;
        long answerIndex = -1;

        while (current != null) {
            length++;

            if (length == 2) {
                answer = head;
                answerIndex = 0;
            } else if (length > 2) {
                long requiredIndex = (2 * length) / 3 - 1;

                if (requiredIndex > answerIndex) {
                    answer = answer.next;
                    answerIndex++;
                }
            }

            current = current.next;
        }

        return length > 1 ? answer : null;
    }
}
