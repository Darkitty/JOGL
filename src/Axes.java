import javax.media.opengl.GL2;

public class Axes {

	public Axes(){
	
	}

	public void draw(GL2 gl){

		// Dessin de 6 quadrilateres : {4 vertex = 1 quadrilatère}
		gl.glBegin(GL2.GL_LINES);                                    

		// le quadrilatere de devant et de derriere, blanc
		gl.glColor3f(1f, 1f, 1f);                            

		gl.glVertex3f(-300, 0, 0);
		gl.glVertex3f(300, 0, 0);
		
		gl.glVertex3f(0, -300, 0);
		gl.glVertex3f(0, 300, 0);
		
		gl.glEnd();

	}

}