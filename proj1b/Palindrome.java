public class Palindrome {

    public Deque<Character> wordToDeque(String word){
        Deque<Character> q = new LinkedListDeque<>();
        for (int i = 0; i < word.length(); i++) {
            q.addLast(word.charAt(i));
        }
        return q;
    }


    private boolean helper(Deque<Character> q) {
        if (q.isEmpty() || q.size() == 1) {
            return true;
        }
        if (! q.removeFirst().equals(q.removeLast())) {
            return false;
        }
        return helper(q);
    }

    public boolean isPalindrome(String word){
        if (word == null){
            return true;
        }
        Deque<Character> q = wordToDeque(word);
        if (q.size() == 1) {
            return true;
        }
        return helper(q);
    }

    private boolean helper(Deque<Character> q, CharacterComparator cc) {
        if (q.isEmpty() || q.size() == 1) {
            return true;
        }
        if (! cc.equalChars(q.removeFirst(), q.removeLast())) {
            return false;
        }
        return helper(q, cc);
    }

    public boolean isPalindrome(String word, CharacterComparator cc){
        if (word == null) {
            return true;
        }
        Deque<Character> q = wordToDeque(word);
        if (q.size() == 1) {
            return true;
        }
        return helper(q, cc);
    }
}
