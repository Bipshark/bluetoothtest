import com.sperkins.mindwave.event.*;
import com.sperkins.mindwave.socket.BluetoothSocket;

import java.io.IOException;

/**
 * Created by nikteg on 13/03/16.
 */
public class Main {
    public static void main(String[] args) {
        BluetoothSocket socket = new BluetoothSocket(event -> {

            // Put your code here
            // You can check which headset generated this event with event.getDeviceAddress(), which returns the headset's Bluetooth MAC address
            // You can determine event type with event.getEventType()
            // You can cast to a specific event class to access more information
            if(EventType.ATTENTION.equals(event.getEventType())) {
                AttentionEvent attentionEvent = (AttentionEvent)event;
                System.out.println(attentionEvent.getValue()); // Prints the headset's Attention percentage from 0 to 100
            } else if(EventType.MEDITATION.equals(event.getEventType())) {
                MeditationEvent meditationEvent = (MeditationEvent)event;
                System.out.println(meditationEvent.getValue()); // Prints the headset's Meditation percentage from 0 to 100
            } else if(EventType.HEADSET_CONNECTED.equals(event.getEventType())) {
                System.out.println("Headset connected");
            } else if(EventType.EIGHT_BIT_RAW_WAVE.equals(event.getEventType())) {
                RawEvent rawEvent = (RawEvent)event;
                System.out.println(rawEvent.getValues()); // rawEvent.getValues() returns an array containing an int value for each brain wave
            } else if(EventType.POOR_SIGNAL_QUALITY.equals(event.getEventType())) {
                System.out.println("Headset signal is too degraded to read"); // This event is fired when the headset connection to the user's forehead/ear is too nondeterministic to adequately calculate EEG and Attention/Meditation values
            }

        });

        try {
            socket.start();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
