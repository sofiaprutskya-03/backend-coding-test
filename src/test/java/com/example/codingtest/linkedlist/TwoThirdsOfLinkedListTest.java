package com.example.codingtest.linkedlist;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class TwoThirdsOfLinkedListTest {
    private final TwoThirdsOfLinkedList solution = new TwoThirdsOfLinkedList();

    @Test
    void returnsNullForEmptyList() {
        assertThat(solution.getTwoThirdsNode(null)).isNull();
    }

    @Test
    void returnsNullForSingleElementList() {
        assertThat(solution.getTwoThirdsNode(list(42))).isNull();
    }

    @Test
    void returnsCorrectNodeForIntegerExamples() {
        assertThat(solution.getTwoThirdsNode(list(0, 1)).data).isEqualTo(0);
        assertThat(solution.getTwoThirdsNode(list(0, 1, 2)).data).isEqualTo(1);
        assertThat(solution.getTwoThirdsNode(list(0, 1, 2, 3)).data).isEqualTo(1);
        assertThat(solution.getTwoThirdsNode(list(0, 1, 2, 3, 4)).data).isEqualTo(2);
        assertThat(solution.getTwoThirdsNode(list(0, 1, 2, 3, 4, 5)).data).isEqualTo(3);
    }

    @Test
    void worksWithGenericValues() {
        assertThat(solution.getTwoThirdsNode(list("a", "b", "c", "d", "e")).data)
                .isEqualTo("c");
    }

    @SafeVarargs
    private static <T> Node<T> list(T... values) {
        Node<T> head = null;
        Node<T> tail = null;

        for (T value : values) {
            Node<T> node = new Node<>(value);

            if (head == null) {
                head = node;
                tail = node;
            } else {
                tail.next = node;
                tail = node;
            }
        }

        return head;
    }
}
