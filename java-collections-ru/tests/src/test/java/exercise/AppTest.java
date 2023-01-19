package exercise;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AppTest {
    List<Integer> numbers;

    @BeforeEach
    void initIntegerList() {
        this.numbers = new ArrayList<>();
        this.numbers.add(1);
        this.numbers.add(2);
        this.numbers.add(3);
        this.numbers.add(4);
        this.numbers.add(5);
    }

    @Test
    void testTake1() {
        // BEGIN
        int count = 2;
        List<Integer> expected = List.of(1, 2);
        List<Integer> actual = App.take(numbers, count);
        assertThat(actual).isEqualTo(expected);
        // END
    }

    @Test
    void testTake2() {
        // BEGIN
        int count = 8;
        List<Integer> expected = List.of(1, 2, 3, 4, 5);
        List<Integer> actual = App.take(numbers, count);
        assertThat(actual).isEqualTo(expected);
        // END
    }

    @Test
    void testTake3() {
        int count = 2;
        List<Integer> emptyArray = new ArrayList<>();
        List<Integer> expected = List.of();
        List<Integer> actual = App.take(emptyArray, count);
        assertThat(actual).isEqualTo(expected);

    }
}
