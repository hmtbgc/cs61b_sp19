import edu.princeton.cs.algs4.StdAudio;
import es.datastructur.synthesizer.GuitarString;
public class GuitarHero {

    public static void main(String[] args) {
        String keyboard = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";
        GuitarString[] key = new GuitarString[keyboard.length()];
        for (int i = 0; i < keyboard.length(); ++i) {
            key[i] = new GuitarString(440 * Math.pow(2, (i - 24.0) / 12.0));
        }

        while (true) {
            if (StdDraw.hasNextKeyTyped()) {
                char c = StdDraw.nextKeyTyped();
                int index = keyboard.indexOf(c);
                if (index != -1) {
                    key[index].pluck();
                }
            }

            double sample = 0.0;
            for (int i = 0; i < 37; ++i) {
                sample += key[i].sample();
            }
            StdAudio.play(sample);
            for (int i = 0; i < 37; ++i) {
                key[i].tic();
            }
        }
    }
}
