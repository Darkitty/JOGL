import java.awt.EventQueue;
import java.awt.Frame;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.media.opengl.awt.GLCanvas;

import com.jogamp.opengl.util.FPSAnimator;
import com.leapmotion.leap.*;

@SuppressWarnings("serial")
public class Main extends Frame{
		// Composant awt, pour le rendu OpenGL (JPanel)
	private GLCanvas canvas;                      

		// Gestionnaire du FPS, permet de ménager le CPU
	private final FPSAnimator animator;

		// Classe interne chargée des évenements claviers
	private static final class MyKeyListener extends KeyAdapter {

		private final Main $this;
		private final Renderer renderer;
		private boolean isSpacePressed;
		private boolean isUpPressed;
		private boolean isDownPressed;
		private boolean isLeftPressed;
		private boolean isRightPressed;
		private boolean isZPressed;
		private boolean isQPressed;
		private boolean isSPressed;
		private boolean isDPressed;
		private boolean isAPressed;
		private boolean isEPressed;
		private boolean isOPressed;
		private boolean isPPressed;

		private MyKeyListener(Main m, Renderer r) {
			$this = m;
			renderer = r;
		}

		private void doThat(){
	    	//Creation de la connexion avec le LeapMotion
	    	Controller controller = new Controller();
	    	com.leapmotion.leap.Frame frame = controller.frame();
	    	HandList hands = frame.hands();
	    	Hand firstHand = hands.get(0);
			if(isSpacePressed) {
		    	renderer.alphaX = firstHand.palmPosition().getX();
				renderer.alphaY = firstHand.palmPosition().getY();
				renderer.transZ = firstHand.palmPosition().getZ();
			}
			if(isDownPressed)
				renderer.alphaX += 0.8;
			if(isUpPressed)
				renderer.alphaX -= 0.8;
			if(isLeftPressed)
				renderer.alphaY -= 0.8;
			if(isRightPressed)
				renderer.alphaY += 0.8;
			if(isPPressed)
				renderer.alphaZ += 0.8;
			if(isOPressed)
				renderer.alphaZ -= 0.8;
			if(isZPressed)
				renderer.transX += 0.2;
			if(isQPressed) 	
				renderer.transY -= 0.2; 	
			if(isSPressed) 	
				renderer.transX -= 0.2; 	
			if(isDPressed) 	
				renderer.transY += 0.2; 	
			if(isAPressed) 	
				renderer.transZ -= 0.2; 	
			if(isEPressed) 	
				renderer.transZ += 0.2;
		}

		public void keyPressed(KeyEvent e) {
			switch (e.getKeyCode()) {
				case KeyEvent.VK_ESCAPE:
					$this.animator.stop();
					System.exit(0);
					break;
				case KeyEvent.VK_SPACE:
					isSpacePressed = true;
					break;
				case KeyEvent.VK_UP:
					isUpPressed = true;
					break;
				case KeyEvent.VK_DOWN:
					isDownPressed = true;
					break;
				case KeyEvent.VK_RIGHT:
					isRightPressed = true;
					break;
				case KeyEvent.VK_LEFT:
					isLeftPressed = true;
					break;
				case KeyEvent.VK_O:
					isOPressed = true;
					break;
				case KeyEvent.VK_P:
					isPPressed = true;
					break;
				case KeyEvent.VK_Z:
					isZPressed = true;
					break;
				case KeyEvent.VK_Q:
					isQPressed = true;
					break;
				case KeyEvent.VK_S:
					isSPressed = true;
					break;
				case KeyEvent.VK_D:
					isDPressed = true;
					break;
				case KeyEvent.VK_A:
					isAPressed = true;
					break;
				case KeyEvent.VK_E:
					isEPressed = true;
					break;
			}
			doThat();
		}

		public void keyReleased(KeyEvent e) {
			switch (e.getKeyCode()) {
				case KeyEvent.VK_UP:
					isUpPressed = false;
					break;
				case KeyEvent.VK_SPACE:
					isSpacePressed = false;
					break;
				case KeyEvent.VK_DOWN:
					isDownPressed = false;
					break;
				case KeyEvent.VK_RIGHT:
					isRightPressed = false;
					break;
				case KeyEvent.VK_LEFT:
					isLeftPressed = false;
					break;
				case KeyEvent.VK_O:
					isOPressed = false;
					break;
				case KeyEvent.VK_P:
					isPPressed = false;
					break;
				case KeyEvent.VK_Z:
					isZPressed = false;
					break;
				case KeyEvent.VK_Q:
					isQPressed = false;
					break;
				case KeyEvent.VK_S:
					isSPressed = false;
					break;
				case KeyEvent.VK_D:
					isDPressed = false;
					break;
				case KeyEvent.VK_A:
					isAPressed = false;
					break;
				case KeyEvent.VK_E:
					isEPressed = false;
					break;
			}
		}
	}


	public Main(){
		// Appel du constructeur parent
		super();
		// Spécifie la taille de la fenêtre
		setSize(1000,1000);
		// Renseigne le titre de la fenetre
		setTitle("Manipulation d'une boite en 3D");
		// Permet de centrer la fenetre à l'ouverture
		setLocationRelativeTo(null);                          

		// Initialisation du manager d'événements OpenGL
		Renderer renderer = new Renderer();                  

		// Initialisation du canvas
		canvas = new GLCanvas();      
		// Spécification d'un manager d'événements OpenGL
		canvas.addGLEventListener(renderer);          

		// Ajout du canvas au frame
		add(canvas);                                                          

		// Initialisation de l'animateur avec ici 50 FPS
		animator = new FPSAnimator(canvas, 50);      

		// Adaptater permettant libérer les ressources lors de la fermeture de la fenêtre
		addWindowListener(new WindowAdapter() {              
			@Override
			public void windowClosing(WindowEvent e) {
				// Arret de l'animateur
				animator.stop();
				// Fermeture de l'application
				System.exit(0);                            
			}
		});

		// Liaison de la fenêtre et du canvas avec le manager d'evenement clavier
		addKeyListener(new MyKeyListener(this, renderer));    
		canvas.addKeyListener(new MyKeyListener(this, renderer));


		// Masque les menus et bordures de la fenetre
		setUndecorated(true);
		// Permet d'être en mode plein écran*/
		//setExtendedState(Frame.MAXIMIZED_BOTH);        

		// Démarrage de l'animateur
		animator.start();
		// Affichage de la fenêtre
		setVisible(true);                                                    
	}

	public static void main(String[] args) {
		// Les méthodes AWT doivent être invoquées depuis l'EDT d'AWT
		EventQueue.invokeLater(new Runnable() {              
			@Override
			public void run() {
				new Main();
			}
		});  
	}

}