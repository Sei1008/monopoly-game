package Transitions;

import javafx.animation.PauseTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class DominoTransitions implements ITransitionStrategy {
    private final int DURATION_MS = 200;
    private final ImageView image;
    private int currentPosition;
    private int stepsRemaining;
    private final double[] boardX;
    private final double[] boardY;
    private final double offsetX;
    private final double offsetY;
    private Runnable onFinished;

    // Constructor không có offset (backward compatible)
    public DominoTransitions(ImageView image, int currentPosition, int steps,
                             double[] boardX, double[] boardY) {
        this(image, currentPosition, steps, boardX, boardY, 0, 0);
    }

    // Constructor có offset cho nhiều player cùng ô
    public DominoTransitions(ImageView image, int currentPosition, int steps,
                             double[] boardX, double[] boardY,
                             double offsetX, double offsetY) {
        this.image = image;
        this.currentPosition = currentPosition;
        this.stepsRemaining = steps;
        this.boardX = boardX;
        this.boardY = boardY;
        this.offsetX = offsetX;
        this.offsetY = offsetY;
    }

    // Set callback để gọi khi animation hoàn toàn kết thúc
    public void setOnFinished(Runnable onFinished) {
        this.onFinished = onFinished;
    }

    @Override
    public void run() {
        if (stepsRemaining <= 0) {
            // Animation xong hoàn toàn — gọi callback
            if (onFinished != null) {
                onFinished.run();
            }
            return;
        }

        int nextPosition = currentPosition + 1;
        if (nextPosition > 40) nextPosition = 1;

        System.out.println("Di chuyển: " + currentPosition + " → " + nextPosition
                + " | còn " + (stepsRemaining - 1) + " bước");

        image.setLayoutX(boardX[nextPosition] + offsetX);
        image.setLayoutY(boardY[nextPosition] + offsetY);

        TranslateTransition trans = new TranslateTransition(Duration.millis(DURATION_MS), image);
        trans.setToX(0);
        trans.setToY(0);

        currentPosition = nextPosition;
        stepsRemaining--;

        trans.setOnFinished(e -> {
            if (stepsRemaining > 0) {
                PauseTransition pause = new PauseTransition(Duration.millis(200));
                pause.setOnFinished(ev -> this.run());
                pause.play();
            } else {
                // Bước cuối xong — gọi callback
                if (onFinished != null) {
                    onFinished.run();
                }
            }
        });
        trans.play();
    }
}