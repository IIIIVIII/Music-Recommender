/**
 * Exception to be thrown when files provided for MusicRecommender is malformed.
 *
 * @version 2023-07-13
 * @author Purdue CS
 */
public class NoRecommendationException extends Exception {
    public NoRecommendationException() {
    }

    public NoRecommendationException(String message) {
        super(message);
    }


}
