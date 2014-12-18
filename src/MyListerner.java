import com.leapmotion.leap.Controller;
import com.leapmotion.leap.Frame;
import com.leapmotion.leap.Listener;

public class MyListerner extends Listener {

   public void onInit(Controller controller)  {//...
	   }

   public void onConnect(Controller controller) {
       //... initialize gestures controls
   }

   public void onDisconnect(Controller controller){
	   //...
   }

   public void onExit(Controller controller) {
	   //...
   }

   public void onFrame(Controller controller) {
       // Get the most recent frame and report some basic information
       Frame frame = controller.frame();
       //...
   }

   public void onFocusGained(Controller controller){
	   //...
   }

   public void onFocusLost(Controller controller) {
	   //... 
   }

}
