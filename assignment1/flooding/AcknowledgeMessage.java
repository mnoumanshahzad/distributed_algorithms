import java.awt.Color;
import teachnet.view.renderer.Shape;

/**
 * Created by nouman on 11/15/16.
 */
public class AcknowledgeMessage {
    private String message;
    private Color color;
    private Shape shape;

    public AcknowledgeMessage(int messageNumber) {
        this.message = "Acknowledgement (" + messageNumber + ")";
        this.color = new Color(0,255,0);
        this.shape = Shape.CIRCLE;
    }

    public String getMessage() { return message; }

    public Color getColor() { return color; }

    public String toString() { return message; }
}
