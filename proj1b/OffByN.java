public class OffByN implements CharacterComparator {
    public int N;

    public OffByN(int input) {
        N = input;
    }

    @Override
    public boolean equalChars(char x, char y) {
        return x - y == N || x - y == -N;
    }
}
