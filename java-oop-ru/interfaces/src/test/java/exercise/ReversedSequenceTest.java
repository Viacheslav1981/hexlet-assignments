package exercise;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ReversedSequenceTest {

    @Test
    void testToString() {

        CharSequence actual = new ReversedSequence("abcde");
        actual = actual.toString();
        String expected = "edcba";
        assertThat(actual).isEqualTo(expected);

    }

    @Test
    void testCharAt() {

        CharSequence text = new ReversedSequence("abcdef");
        text = text.toString();
        char actual = text.charAt(2);
        char expected = 'd';

        assertThat(actual).isEqualTo(expected);


    }

    @Test
    void testLength() {

        CharSequence text = new ReversedSequence("abcde");

        int actual = text.length();
        int expected = 5;

        assertThat(actual).isEqualTo(expected);


    }
}
