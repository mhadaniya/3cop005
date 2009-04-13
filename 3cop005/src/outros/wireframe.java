package outros;

// wireframe.java
// Draws a wireframe sphere in OpenGL
//
// Copyright (C) 2004-2006 Frank. C. Langbein <frank@langbein.org>
//
// Conversion to Java by Simon Manasseh <cookie@woaf.net>
//
// This program is free software; you can redistribute it and/or modify
// it under the terms of the GNU General Public License as published by
// the Free Software Foundation; either version 2, or (at your option)
// any later version.
//
// This program is distributed in the hope that it will be useful,
// But WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
// GNU General Public License for more details.
//
// You should have received a copy of the GNU General Public License
// along with this program; if not, write to the Free Software
// Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA
// 02111-1307, USA.
//
//
// Differences from C source:
// - GLUT not included in jogl, so replaced with AWT toolkit functions
//
// Requires jogl libraries available from http://jogl.dev.java.net/
// Compile as any standard java program, make sure your class and
// library paths include the jogl libraries! 
//
// See the jogl documentation for more information. Note that you have
// to install jogl.jar and jogl-natives-linux.jar (or the corresponding
// natives file for your platform).
//
// JOGL version JSR231b05 is installed in /opt/jogl-JSR231b05 in the
// COMSC Linux labs.
//
// Under Linux add the class and libraries using the following:
//
//   if you are using the bourne shell / bash:
//
//     export CLASSPATH=$CLASSPATH:/PATH/TO/JOGL/CLASSES
//     export LD_LIBRARY_PATH=$LD_LIBRARY_PATH:/PATH/TO/JOGL/CLASSES
//
//   if you are using the c-shell / tcsh:
//
//     setenv CLASSPATH {$CLASSPATH}:/PATH/TO/JOGL/CLASSES
//     setenv LD_LIBRARY_PATH {$LD_LIBRARY_PATH}:/PATH/TO/JOGL/CLASSES
//
//     (add the CLASSPATH / LD_LIBRARY_PATH variable in the second argument
//      only if they are defined, otherwise you'll get an error message)
//
// Also make sure . (the current directory) is in your CLASSPATH if you want
// to make it simple for java to find the executable.
//
// Compile with
//
//   javac wireframe.java
//
// Run with
//
//   java wireframe
//


// Import AWT libraries
import java.awt.*;
import java.awt.event.*;

// Import jogl library
import com.sun.opengl.util.FPSAnimator;
import javax.media.opengl.GL;
import javax.media.opengl.GLCapabilities;
import javax.media.opengl.GLCanvas;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.glu.GLU;


// Wireframe class
public class wireframe extends Frame {

  private GLCanvas glCanvas;
  private FPSAnimator animator;
  private Dimension dimension;

  // Setup window
  public wireframe(Dimension dim) {

    super("CM0304 - Graphics - wireframe sphere");

    dimension = dim;
    super.setSize(dim);

    glCanvas = new GLCanvas (new GLCapabilities ());
    glCanvas.addGLEventListener(new Renderer());

    add(glCanvas, java.awt.BorderLayout.CENTER);
    animator = new FPSAnimator(glCanvas, 60);
  }

  // Method to return glCanvas
  public GLCanvas getGLCanvas() {
    return glCanvas;
  }

  // Method to return animator
  public FPSAnimator getAnimator() {
    return animator;
  }

  // Rendering methods, etc. for the scene
  static class Renderer implements GLEventListener, KeyListener,
                                    MouseListener, MouseMotionListener {

    ///////////////////////////////////////////////////////////////////////
    // Constants, global variables, etc.

    // Shortcuts for math constants and functions
    final double PI = Math.PI;
    final double PI_2 = PI / 2;
    final double sin(double i) {
      return Math.sin(i); 
    }
    final double cos(double i) { 
      return Math.cos(i); 
    }

    // Conversion factors for degrees to radians
    final double deg_to_rad = PI / 180;
    final double rad_to_deg = 180 / PI;

    // Id of display list containing the scene describtion
    private int scene_dl;

    // Track mouse position
    int mouse_x = 0;
    int mouse_y = 0;

    // Camera position
    double camera_x = 3.0;
    double camera_y = 0.0;
    double camera_z = 0.0;
    double camera_yaw = 0.0;
    double camera_pitch = 0.0;

    // Camera sensitivity
    final double angle_sensitivity = 0.005;
    final double dist_sensitivity = 0.01;

    // Scene rotation
    double scene_yaw = 0.0;
    double scene_pitch = 0.0;

    // GLU
    private GLU glu = new GLU ();

    //////////////////////////////////////////////////////////////////////
    // Methods to draw the models in the scene

    // Draw a sphere
    private void sphere(GL gl) {

      // Quadrilateral strips
      for (double phi = -80.0; phi <= 80.0; phi += 10.0) {
        gl.glBegin (GL.GL_QUAD_STRIP);
        for (double thet = -180.0; thet <= 180.0; thet += 10.0) {
          gl.glVertex3d (sin (deg_to_rad * thet) * cos (deg_to_rad * phi),
                         cos (deg_to_rad * thet) * cos (deg_to_rad * phi),
                         sin (deg_to_rad * phi));
          gl.glVertex3d (sin (deg_to_rad * thet) * cos (deg_to_rad * (phi + 10.0)),
                         cos (deg_to_rad * thet) * cos (deg_to_rad * (phi + 10.0)),
                         sin (deg_to_rad * (phi + 10.0)));
        }
        gl.glEnd();
      }

      // North pole
      gl.glBegin (GL.GL_TRIANGLE_FAN);
      gl.glVertex3d (0.0, 0.0, 1.0);
      for (double thet = -180.0; thet <= 180.0; thet += 10.0) {
        gl.glVertex3d (sin (deg_to_rad * thet) * cos (deg_to_rad * 80.0),
                       cos (deg_to_rad * thet) * cos (deg_to_rad * 80.0),
                       sin (deg_to_rad * 80.0));
      }
      gl.glEnd();

      // South pole
      gl.glBegin (GL.GL_TRIANGLE_FAN);
      gl.glVertex3d (0.0, 0.0, -1.0);
      for (double thet = -180.0; thet <= 180.0; thet += 10.0) {
        gl.glVertex3d (sin (deg_to_rad * thet) * cos (deg_to_rad * -80.0),
                       cos (deg_to_rad * thet) * cos (deg_to_rad * -80.0),
                       sin (deg_to_rad * -80.0));
      }
      gl.glEnd();
    }

    // Main method called to setup the scene contents
    private void scene(GL gl) {
      // Draw sphere
      sphere(gl);
    }

    //////////////////////////////////////////////////////////////////////
    // OpenGL functions

    // Initialise OpenGL
    public void init(GLAutoDrawable glDrawable) {
      final GL gl = glDrawable.getGL();

      glDrawable.addKeyListener(this);
      glDrawable.addMouseListener(this);
      glDrawable.addMouseMotionListener(this);

      // Set clear color to black
      gl.glClearColor (0.0f, 0.0f, 0.0f, 1.0f);

      // Set wireframe mode for drawing the sphere
      gl.glPolygonMode (GL.GL_FRONT_AND_BACK, GL.GL_LINE);

      // Store scene in a display list: 
      //   first create one display list
      scene_dl = gl.glGenLists (1);
      //   and then call the rendering functions to store the
      //   primitives in the list
      gl.glNewList (scene_dl, GL.GL_COMPILE);
      scene (gl);
      gl.glEndList ();
    }

    // Display event: render the scene
    public void display(GLAutoDrawable glDrawable) {
      final GL gl = glDrawable.getGL();
      // Clear frame buffer
      gl.glClear(GL.GL_COLOR_BUFFER_BIT);

      // Setup modelview matrix (model and viewing transformation)
      gl.glMatrixMode (GL.GL_MODELVIEW);
      gl.glLoadIdentity ();

      // Camera
      glu.gluLookAt (camera_x + cos (camera_yaw) * cos (camera_pitch),
                     camera_y + sin (camera_yaw) * cos (camera_pitch),
                     camera_z + sin (camera_pitch),
                     camera_x, camera_y, camera_z,
                     0.0, 0.0, 1.0);

      // Scene
      gl.glRotated (scene_yaw * rad_to_deg, 0.0, 0.0, 1.0);
      gl.glRotated (scene_pitch * rad_to_deg, 0.0, 1.0, 0.0);

      // Render scene by calling display list
      // (without display lists call scene () here)
      gl.glCallList(scene_dl);

      // Flush OpenGL pipeline
      gl.glFlush();
    }

    // the displayChanged method isn't actually implemented in jogl....
    // yet...
    public void displayChanged(GLAutoDrawable glDrawable, boolean modeChanged,
                                boolean deviceChanged) {
    }

    // Window reshape event
    public void reshape(GLAutoDrawable glDrawable, int x, int y, int w,
                         int h) {
      final GL gl = glDrawable.getGL();

      // Setup viewport size
      gl.glViewport(0, 0, w, h);

      // Setup projection matrix
      gl.glMatrixMode(GL.GL_PROJECTION);
      gl.glLoadIdentity();

      // Perspective projection
      glu.gluPerspective(60.0, 
                         (double)w / (double)h, 
                         0.1, 1000.0);
    }

    //////////////////////////////////////////////////////////////////////
    // Keyboard events

    public void keyPressed(KeyEvent e) {
      // ESC for exit
      if (e.getKeyCode() == KeyEvent.VK_ESCAPE)
        System.exit(0);
    }

    public void keyReleased(KeyEvent e) {
    }

    public void keyTyped(KeyEvent e) {
    }

    //////////////////////////////////////////////////////////////////////
    // Mouse events

    // Do-nothing methods, but required nonetheless
    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    public void mousePressed(MouseEvent e) {
    }

    public void mouseReleased(MouseEvent e) {
    }

    public void mouseClicked(MouseEvent e) {
    }

    // Mouse event called when mouse button is pressed and moved
    public void mouseDragged(MouseEvent e) {

      int x = e.getX();
      int y = e.getY();

      // Button 1 pressed: change viewing direction
      if ((e.getModifiers() & e.BUTTON1_MASK) != 0) {
        camera_yaw += angle_sensitivity * (double) (mouse_x - x);
        if (camera_yaw > PI) {
          camera_yaw -= 2.0 * PI;
        } else if (camera_yaw < -PI) {
          camera_yaw += 2.0 * PI;
        }
        camera_pitch += angle_sensitivity * (double) (y - mouse_y);
        if (camera_pitch > PI_2) {
          camera_pitch = PI_2;
        } else if (camera_pitch < -PI_2) {
          camera_pitch = -PI_2;
        }
      }

      // Button 2 pressed: change position
      if ((e.getModifiers() & e.BUTTON2_MASK) != 0) {
        camera_x += dist_sensitivity * (double) (y - mouse_y)
                    * cos (camera_yaw) * cos (camera_pitch);
        camera_y += dist_sensitivity * (double) (y - mouse_y)
                    * sin (camera_yaw) * cos (camera_pitch);
        camera_z += dist_sensitivity * (double) (y - mouse_y)
                    * sin (camera_pitch);
      }

      // Button 3 pressed: rotate scene
      if ((e.getModifiers() & e.BUTTON3_MASK) != 0) {
        scene_yaw += angle_sensitivity * (double) (x - mouse_x);
        if (scene_yaw > PI) {
          scene_yaw -= 2.0 * PI;
        } else if (scene_yaw < -PI) {
          scene_yaw += 2.0 * PI;
        }
        scene_pitch += angle_sensitivity * (double) (y - mouse_y);
        if (scene_pitch > PI) {
          scene_pitch -= 2.0 * PI;
        } else if (scene_pitch < -PI) {
          scene_pitch += 2.0 * PI;
        }
      }

      mouse_x = x;
      mouse_y = y;
    }

    // Passive motion callback to capture mouse movements while buttons
    // are not pressed
    public void mouseMoved(MouseEvent e) {
      // Update mouse position
      mouse_x = e.getX();
      mouse_y = e.getY();
    }

  }
  // end of renderer class

  //////////////////////////////////////////////////////////////////////////
  // MAIN

  public static void main(String[] args) {

    final wireframe scene = new wireframe(new Dimension(500,500));

    scene.addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent e) {
        scene.getAnimator().stop();
        System.exit(0);
      }
    });

    scene.setVisible(true);
    scene.getAnimator().start();
    scene.getGLCanvas().requestFocus();

  }

}
