package CS355.LWJGL;

//You might notice a lot of imports here.
//You are probably wondering why I didn't just import org.lwjgl.opengl.GL11.*
//Well, I did it as a hint to you.
//OpenGL has a lot of commands, and it can be kind of intimidating.
//This is a list of all the commands I used when I implemented my project.
//Therefore, if a command appears in this list, you probably need it.
//If it doesn't appear in this list, you probably don't.
//Of course, your milage may vary. Don't feel restricted by this list of imports.
import java.util.Iterator;

import org.lwjgl.input.Keyboard;

import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_LINES;
import static org.lwjgl.opengl.GL11.GL_MODELVIEW;
import static org.lwjgl.opengl.GL11.GL_PROJECTION;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glColor3f;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glLoadIdentity;
import static org.lwjgl.opengl.GL11.glMatrixMode;
import static org.lwjgl.opengl.GL11.glPushMatrix;
import static org.lwjgl.opengl.GL11.glRotatef;
import static org.lwjgl.opengl.GL11.glTranslatef;
import static org.lwjgl.opengl.GL11.glVertex3d;
import static org.lwjgl.opengl.GL11.glViewport;
import static org.lwjgl.opengl.GL11.glOrtho;
import static org.lwjgl.util.glu.GLU.gluPerspective;

/**
 * 
 * @author Brennan Smith
 */
public class StudentLWJGLController implements CS355LWJGLController {

	// This is a model of a house.
	// It has a single method that returns an iterator full of Line3Ds.
	// A "Line3D" is a wrapper class around two Point2Ds.
	// It should all be fairly intuitive if you look at those classes.
	// If not, I apologize.
	private WireFrame model = new HouseModel();

	private Camera camera = new Camera();

	// This method is called to "resize" the viewport to match the screen.
	// When you first start, have it be in perspective mode.
	@Override
	public void resizeGL() {

		glViewport(0, 0, 640, 480);

		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		gluPerspective(70, 640 / 480, 0, 500);
	}

	@Override
	public void update() {

	}

	// This is called every frame, and should be responsible for keyboard
	// updates.
	// An example keyboard event is captured below.
	// The "Keyboard" static class should contain everything you need to finish
	// this up.
	@Override
	public void updateKeyboard() {
		if (Keyboard.isKeyDown(Keyboard.KEY_A)) {
			camera.moveLeft();
		}

		if (Keyboard.isKeyDown(Keyboard.KEY_D)) {
			camera.moveRight();
		}

		if (Keyboard.isKeyDown(Keyboard.KEY_W)) {
			camera.moveForward();
		}

		if (Keyboard.isKeyDown(Keyboard.KEY_S)) {
			camera.moveBackward();
		}

		if (Keyboard.isKeyDown(Keyboard.KEY_Q)) {
			camera.turnLeft();
		}

		if (Keyboard.isKeyDown(Keyboard.KEY_E)) {
			camera.turnRight();
		}

		if (Keyboard.isKeyDown(Keyboard.KEY_R)) {
			camera.moveUp();
		}

		if (Keyboard.isKeyDown(Keyboard.KEY_F)) {
			camera.moveDown();
		}

		if (Keyboard.isKeyDown(Keyboard.KEY_O)) {
			glMatrixMode(GL_PROJECTION);
			glLoadIdentity();
			glOrtho(-400 / 15, 400 / 15, -300 / 15, 300 / 15, 0, 500);
		}

		if (Keyboard.isKeyDown(Keyboard.KEY_P)) {
			glMatrixMode(GL_PROJECTION);
			glLoadIdentity();
			gluPerspective(70, 800 / 600, 0, 500);
		}
	}

	// This method is the one that actually draws to the screen.
	@Override
	public void render() {
		// This clears the screen.
		glClear(GL_COLOR_BUFFER_BIT);

		// Do your drawing here.

		glMatrixMode(GL_MODELVIEW);
		glLoadIdentity();

		glRotatef(-camera.getRotation(), 0, 1, 0);
		glTranslatef(-camera.getX(), -camera.getY(), -camera.getZ());

		glBegin(GL_LINES);
		glColor3f(1, 1, 1);
		Iterator<Line3D> iterator = model.getLines();
		while (iterator.hasNext()) {
			Line3D line = iterator.next();
			glVertex3d(line.start.x, line.start.y, line.start.z);
			glVertex3d(line.end.x, line.end.y, line.end.z);
		}
		glEnd();
	}
}
