import org.opencv.core.Core;

public class Main {
    public static void main(String[] args) {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

        String img1 = "images/retina1.jpg";
        String img2 = "images/retina2.jpg";

        double score = RetinaMatcher.compareImages(img1, img2);
        System.out.printf("🧠 Match Score: %.2f%%\n", score);

        if (score > 60) {
            System.out.println("✅ Retina MATCHED");
        } else {
            System.out.println("❌ Retina NOT matched");
        }
    }
}