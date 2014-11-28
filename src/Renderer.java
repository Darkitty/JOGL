import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.glu.GLU;

public class Renderer implements GLEventListener {

        // Utilitaire donnant accès aux commandes bas niveau d'OpenGL
        private GLU glu;
        // Angle courant de rotation sur l'axe X
        public float alphaX=0f;        
        // Angle courant de rotation sur l'axe Y
        public float alphaY=0f;        
        // Angle courant de rotation sur l'axe Z
        public float alphaZ=0f;   
        // Angle courant de rotation sur l'axe X
        public float transX=0f;        
        // Angle courant de rotation sur l'axe Y
        public float transY=0f;        
        // Angle courant de rotation sur l'axe Z
        public float transZ=0f;       

        
        /**
        * Appelée immédiatemment lors de l'initialisation du context graphique OpenGl (canvas).
        * N'est appelée qu'une seule fois.
        */
        @Override
        public void init(GLAutoDrawable drawable) {
                // Récupération du contexte en GL2
                GL2 gl = drawable.getGL().getGL2();                                                  
                // Initialisation de l'utilitaire
                glu = new GLU();                                                                      
                // Remplissage du contexte avec du NOIR
                gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);                                              
                // Configuration la profondeur au maximum
                gl.glClearDepth(1.0f);                                                                                        
                // Autorisation de faire un rendu avec une perspective
                gl.glEnable(GL2.GL_DEPTH_TEST);                                                              
                // Restriction de l'affichage aux éléments(Z<=Max)
                gl.glDepthFunc(GL2.GL_LEQUAL);                                                                
                // Correction pour la meilleure perspective
                gl.glHint(GL2.GL_PERSPECTIVE_CORRECTION_HINT, GL2.GL_NICEST);        
                // Joli mélange de couleur, et lissage des textures
                gl.glShadeModel(GL2.GL_SMOOTH);                                                              
        }
                
                
        /**
        * Appelée par l'animateur pour executer le rendu graphique.
        */
        @Override
        public void display(GLAutoDrawable drawable) {
                // Récupération du contexte en GL2
                GL2 gl = drawable.getGL().getGL2();                                                  
                // Réinitialisation de la scène (effacement des tampons)
                gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);        
                // Reinitialisation de la matrice courante
                gl.glLoadIdentity();                                                                                  

                /* (Alt aux translations) Placement de la caméra au point (4,0,12)
                Direction vers l'origine de la scène (0,0,0)
                Inclinaison nulle car la vue suit l'axe vertical (y) */
                glu.gluLookAt(        4f, 0f, 12f,
                                                0f, 0f, 0f,  
                                                0f, 1f, 0f  
                );

                // Rotation de la matrice courante de l'angle alpha autour de l'axe x (1,0,0)
                gl.glRotatef(alphaX,        
                                        1f, 0f, 0f
                );

                // Rotation de la matrice courante de l'angle alpha autour de l'axe x (0,1,0)
                gl.glRotatef(alphaY,
                                        0f, 1f, 0f
                );

                // Rotation de la matrice courante de l'angle alpha autour de l'axe x (0,0,1)
                gl.glRotatef(alphaZ,
                                        0f, 0f, 1f 
                );
                
                // Translation sur les axes x, y, z
                gl.glTranslatef(transX, transY, transZ);

                // Tous les dessins utltérieurs subiront la transformation : Dessin d'un cube
                new Cube(2.0f, 0, 0, 0).draw(gl);                                                            
        }

        
        /**
        * Appelée avant la destruction du context OpenGL. Permet de liberer les ressources telles que les buffers.
        */
        @Override
        public void dispose(GLAutoDrawable arg0) {
                // Non utilisée ici mais sa déclaration est obligatoire de par l'interface
        }

        /**
        * Appelée lorsque la fenetre d'affichage est redimensionnée,
        * Mais aussi lorsque la fenetre est affichée pour la première fois
        */
        @Override
        public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
                // Récupération du contexte en GL2
                GL2 gl = drawable.getGL().getGL2();

                // Alternative à la division par zéro
                if (height == 0) height = 1;                                                                          

                // Configuration de la zone d'affichage OpenGL
                gl.glViewport(0, 0, width, height);                                                          

                // Spécification d'une matrice de projection en perspective
                // Passage en mode définition de la PROJECTION
                gl.glMatrixMode(GL2.GL_PROJECTION);  
                // Reinitialisation de la matrice courante
                gl.glLoadIdentity();
                // Angle d'observation en degrés, echelle entre largeur et hauteur, intervalle de projection
                glu.gluPerspective(45.0, (float)width/height, 0.1, 100.0);

                // Selection de la transformation point de vue
                gl.glMatrixMode(GL2.GL_MODELVIEW);
                // Reinitialisation de la matrice courante
                gl.glLoadIdentity();
        }

}