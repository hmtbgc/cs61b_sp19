import org.junit.Test;
import static org.junit.Assert.*;

public class TestPalindrome {
    // You must use this palindrome, and not instantiate
    // new Palindromes, or the autograder might be upset.
    static Palindrome palindrome = new Palindrome();

    @Test
    public void testWordToDeque() {
        Deque d = palindrome.wordToDeque("persiflage");
        String actual = "";
        for (int i = 0; i < "persiflage".length(); i++) {
            actual += d.removeFirst();
        }
        assertEquals("persiflage", actual);
    }

    @Test
    public void testIsPalindrome() {
        assertTrue(palindrome.isPalindrome(null));
        assertTrue(palindrome.isPalindrome("a"));
        assertFalse(palindrome.isPalindrome("cat"));
        assertFalse(palindrome.isPalindrome("caAc"));
        assertFalse(palindrome.isPalindrome("Aa"));
        assertTrue(palindrome.isPalindrome("cchiihcc"));
        assertFalse(palindrome.isPalindrome("Pip"));
        assertFalse(palindrome.isPalindrome("rus&suf"));
        assertTrue(palindrome.isPalindrome("(("));
    }

    @Test
    public void testIsPalindromeOffByOne() {
        CharacterComparator cc = new OffByOne();
        assertTrue(palindrome.isPalindrome(null, cc));
        assertTrue(palindrome.isPalindrome("a", cc));
        assertFalse(palindrome.isPalindrome("cat", cc));
        assertTrue(palindrome.isPalindrome("flake", cc));
        assertFalse(palindrome.isPalindrome("aaa", cc));
        assertTrue(palindrome.isPalindrome("aab", cc));
    }

    @Test
    public void testIsPalindromeOffByN() {
        CharacterComparator ccN = new OffByN(5);
        assertTrue(palindrome.isPalindrome(null, ccN));
        assertTrue(palindrome.isPalindrome("a", ccN));
        assertFalse(palindrome.isPalindrome("cat", ccN));
        assertTrue(palindrome.isPalindrome("a&f", ccN));
        assertFalse(palindrome.isPalindrome("pzo", ccN));
    }
}