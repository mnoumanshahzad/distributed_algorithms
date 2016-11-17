import teachnet.algorithm.BasicAlgorithm;

import java.awt.Color;
import java.util.Map;

public class Flooding extends BasicAlgorithm {

    //Initial color of the node is set to Blue
    Color color = new Color(0,0,255);

    String caption = "";

    static int messageNumber = 0;

    //interface number of the node who sends the first explore message
    int informer = -1;

    //flag used for the initiator nodes
    boolean initiator = false;

    //flag used to mark the node as informed
    boolean informed = false;

    //# of neighbours
    int neighbours = 0;

    //# of acknowledgements received so far
    int ackCounter = 0;

    public void setup(Map<String, Object> config) {
        int id = (Integer) config.get("node.id");
        caption += id;
        neighbours = checkInterfaces();
    }

    /**
     * Marks a node as informed
     * Changes its color to Yellow
     */
    private void informed() {
        this.informed = true;
        setColor(new Color(255,255,0));
    }

    private void setColor(Color color) {
        this.color = color;
    }

    /**
     * Code that runs only on the initiating nodes
     * Sets the initiator flog as true
     * Sends an explore message to each neighbour
     */
    public void initiate() {

        for (int i = 0; i < neighbours; i++) {
            send(i, new ExploreMessage(++messageNumber));
        }
        initiator = true;
        informed();
    }

    /**
     * If an explorer message is received
     *   - Already informed node sends back an acknowledgment to the receiver
     *   - Leaf nodes send back an acknowledgment to the immediate parent
     *   - Sub nodes send an explorer message to all neighbours except the one who sent it the explorer message first
     *
     * If an acknowledgement message is received
     *   - The ackCounter is incremented
     *   - Collects acks from all neighbours
     *   - Sends an ack back to the informer node when all acks are received
     *   - Program ends when all acks are received at the initiator node
     */

    public void receive(int interf, Object message) {
        if (message instanceof ExploreMessage) {
            if (informed) {
                send(interf, new AcknowledgeMessage(++messageNumber));
            }
            else {
                informer = interf;
                informed();

                if (neighbours == 1) {
                    setColor(new Color(0,255,0));
                    send(interf, new AcknowledgeMessage(++messageNumber));
                }
                else {
                    for (int i = 0; i < neighbours; i++) {
                        if (i != informer) {
                            send(i, new ExploreMessage(++messageNumber));
                        }
                    }
                }
            }
        }

        if (message instanceof AcknowledgeMessage) {
            ackCounter++;
            if (initiator && ackCounter == neighbours) {
                System.exit(0);
            }
            if (!initiator && ackCounter == neighbours - 1) {
                setColor(new Color(0,255,0));
                send(informer, new AcknowledgeMessage(++messageNumber));
            }
        }
    }
}
