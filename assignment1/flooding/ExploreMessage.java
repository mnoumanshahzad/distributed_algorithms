import java.awt.Color;
import teachnet.view.renderer.Shape;

/**
 * Created by nouman on 11/15/16.
 */
public class ExploreMessage {
    private String message;
    private Color color;
    private Shape shape;

    public ExploreMessage(int messageNumber) {
        this.message = "Exploring (" + messageNumber + ")";
        this.color = new Color(255,0,0);
        this.shape = Shape.CIRCLE;
    }

    public String getMessage() { return message; }

    public Color getColor() { return color; }

    public String toString() { return message; }
}
