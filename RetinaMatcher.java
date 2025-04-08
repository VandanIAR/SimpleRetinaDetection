import org.opencv.core.*;
import org.opencv.features2d.*;
import org.opencv.imgcodecs.Imgcodecs;

import java.util.List;

public class RetinaMatcher {
    public static double compareImages(String path1, String path2) {
        Mat img1 = Imgcodecs.imread(path1, Imgcodecs.IMREAD_GRAYSCALE);
        Mat img2 = Imgcodecs.imread(path2, Imgcodecs.IMREAD_GRAYSCALE);

        ORB orb = ORB.create();
        MatOfKeyPoint kp1 = new MatOfKeyPoint();
        MatOfKeyPoint kp2 = new MatOfKeyPoint();
        Mat desc1 = new Mat();
        Mat desc2 = new Mat();

        orb.detectAndCompute(img1, new Mat(), kp1, desc1);
        orb.detectAndCompute(img2, new Mat(), kp2, desc2);

        if (desc1.empty() || desc2.empty()) {
            System.out.println("❌ Feature extraction failed.");
            return 0.0;
        }

        BFMatcher matcher = BFMatcher.create(Core.NORM_HAMMING, true);
        MatOfDMatch matches = new MatOfDMatch();
        matcher.match(desc1, desc2, matches);

        List<DMatch> matchList = matches.toList();
        double totalDist = 0;
        for (DMatch match : matchList) {
            totalDist += match.distance;
        }

        double avgDist = totalDist / matchList.size();
        return 100 - Math.min(avgDist, 100); // Similarity %
    }
}