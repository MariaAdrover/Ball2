package ball2;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;

/**
 *
 * @author miaad
 */
public class Ball2 extends JFrame implements Runnable, ActionListener {

    GridBagConstraints grid;
    Tablero tablero;
    Thread threadBall;
    Thread threadPelota;
    Thread threadTablero;
    JButton b1;
    JButton b2;
    JButton b3;
    JButton b4;
    JButton b5;
    JLabel labelX;
    JLabel labelY;
    JLabel labelVx;
    JLabel labelVy;
    JLabel labelA;

    public Ball2() {
        super("Ball2 - MOVIMIENTO RECTILINEO");
        this.setBackground(Color.white);

        Container contenedor = this.getContentPane();
        contenedor.setLayout(new GridBagLayout());
        this.grid = new GridBagConstraints();

        this.tablero = new Tablero(1200, 1000);
        this.setGridConstraints(0, 0, 1, 10, 1, 1, 1, 0);
        contenedor.add(tablero, grid);

        this.b1 = new JButton("Movimiento Rectilíneo (Horizontal) Uniforme");
        this.b1.setFont(new Font("Courier New", Font.BOLD, 20));
        this.b1.addActionListener(this);
        this.setGridConstraints(1, 0, 1, 1, 0, 0.1, 1, 0);
        contenedor.add(b1, grid);

        this.b2 = new JButton("Movimiento Rectilíneo Uniformemente Acelerado -- ERROR");
        this.b2.setFont(new Font("Courier New", Font.BOLD, 20));
        this.b2.addActionListener(this);
        this.setGridConstraints(1, 1, 1, 1, 0, 0.1, 1, 0);
        contenedor.add(b2, grid);

        this.b3 = new JButton("Rebote");
        this.b3.setFont(new Font("Courier New", Font.BOLD, 20));
        this.b3.addActionListener(this);
        this.b3.setEnabled(true);
        this.setGridConstraints(1, 2, 1, 1, 0, 0.1, 1, 0);
        contenedor.add(b3, grid);

        this.labelX = new JLabel("x --> " + this.tablero.getPelota().getX());
        this.labelX.setFont(new Font("Courier New", Font.BOLD, 22));
        this.setGridConstraints(1, 3, 1, 1, 0, 0.1, 1, 0);
        contenedor.add(labelX, grid);

        this.labelY = new JLabel("y --> " + this.tablero.getPelota().getY());
        this.labelY.setFont(new Font("Courier New", Font.BOLD, 22));
        this.setGridConstraints(1, 4, 1, 1, 0, 0.1, 1, 0);
        contenedor.add(labelY, grid);

        this.labelVx = new JLabel("vX --> " + this.tablero.getPelota().getvX());// REPASAR >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
        this.labelVx.setFont(new Font("Courier New", Font.BOLD, 22));
        this.setGridConstraints(1, 5, 1, 1, 0, 0.1, 1, 0);
        contenedor.add(labelVx, grid);

        this.labelVy = new JLabel("vY --> " + this.tablero.getPelota().getvY());// REPASAR >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
        this.labelVy.setFont(new Font("Courier New", Font.BOLD, 22));
        this.setGridConstraints(1, 6, 1, 1, 0, 0.1, 1, 0);
        contenedor.add(labelVy, grid);

        this.labelA = new JLabel("a --> " + this.tablero.getPelota().getA());
        this.labelA.setFont(new Font("Courier New", Font.BOLD, 22));
        this.setGridConstraints(1, 7, 1, 1, 0, 0.1, 1, 0);
        contenedor.add(labelA, grid);

        this.b4 = new JButton("RESET");
        this.b4.setFont(new Font("Courier New", Font.BOLD, 20));
        this.b4.addActionListener(this);
        this.setGridConstraints(1, 8, 1, 1, 0, 0.1, 1, 0);
        contenedor.add(b4, grid);

        this.b5 = new JButton("PAUSE");
        this.b5.setFont(new Font("Courier New", Font.BOLD, 20));
        this.b5.addActionListener(this);
        this.b5.setEnabled(false);
        this.setGridConstraints(1, 9, 1, 1, 0, 0.1, 1, 0);
        contenedor.add(b5, grid);

        this.pack();
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);

    }

    // NONE --> 0
    // BOTH --> 1
    // HORIZONTAL --> 2
    // VERTICAL --> 3    
    private void setGridConstraints(int x, int y, int width,
            int height, double weightX, double weightY, int fill, int inset) {
        this.grid.gridx = x;
        this.grid.gridy = y;
        this.grid.gridwidth = width;
        this.grid.gridheight = height;
        this.grid.weightx = weightX;
        this.grid.weighty = weightY;
        this.grid.fill = fill;
        // diseny.insets = this.setInset(inset);
        //this.grid.anchor = GridBagConstraints.WEST;

    }

    private void leerValorCoordenadas() {
        this.labelX.setText("x --> " + this.tablero.getPelota().getX());
        this.labelY.setText("y --> " + this.tablero.getPelota().getY());

    }

    private void leerVelocidad() {// REPASAR >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
        this.labelVx.setText("vX --> " + this.tablero.getPelota().getvX());
        this.labelVy.setText("vY --> " + this.tablero.getPelota().getvY());
    }

    private void leerAceleracion() {
        this.labelA.setText("a --> " + this.tablero.getPelota().getA());
    }

    private void leerValoresJLabel() {
        this.leerValorCoordenadas();
        this.leerVelocidad();
        this.leerAceleracion();
    }

    private void crearThreads() {
        this.threadBall = new Thread(this);
        this.threadTablero = new Thread(this.tablero);
        this.threadPelota = new Thread(this.tablero.getPelota());
    }

    private void startThreads() {
        System.out.println("inicio THREADS");
        this.threadPelota.start();
        this.threadTablero.start();
        this.threadBall.start();
    }

    private void reset() {
        this.tablero.getPelota().stopAnimation();
        this.tablero.getPelota().reiniciarVariablesACero();
        this.tablero.getPelota().restablecerPosicion();
        this.tablero.getPelota().setPaused(false);
        this.disablePauseButton();
        this.leerValoresJLabel();
        this.repaint();
        this.tablero.repaint();
    }

    private void switchPauseButton() {
        if (this.tablero.getPelota().isPaused()) {
            this.b5.setText("RESUME");
        } else {
            this.b5.setText("PAUSE");
        }
    }

    private void disablePauseButton() {
        this.b5.setText("PAUSE");
        this.b5.setEnabled(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == this.b1) {
            System.out.println("boton1");
            if (this.tablero.getPelota().isRunningAnimation()) { //????????????
                this.tablero.getPelota().stopAnimation();
            }
            this.tablero.getPelota().inicializarVariablesAnimacion1();
            this.crearThreads();
            this.tablero.getPelota().setRunningAnimation(true);
            this.b5.setEnabled(true);
            this.startThreads();
        } else if (e.getSource() == this.b2) {
            System.out.println("boton2");
            if (this.tablero.getPelota().isRunningAnimation()) {
                this.tablero.getPelota().stopAnimation();
            }
            this.tablero.getPelota().inicializarVariablesAnimacion2();
            this.crearThreads();
            this.tablero.getPelota().setRunningAnimation(true);
            this.b5.setEnabled(true);
            this.startThreads();
        } else if (e.getSource() == this.b3) {
            System.out.println("boton3");
            if (this.tablero.getPelota().isRunningAnimation()) {
                this.tablero.getPelota().stopAnimation();
            }
            this.crearThreads();
            this.tablero.getPelota().inicializarVariablesAnimacion3();
            this.tablero.getPelota().setRunningAnimation(true);
            this.b5.setEnabled(true);
            this.startThreads();
        } else if (e.getSource() == this.b4) { // boton 
            this.reset();
        } else if (e.getSource() == this.b5) {
            if (this.tablero.getPelota().isPaused()) {
                this.tablero.getPelota().setPaused(false);
            } else {
                this.tablero.getPelota().setPaused(true);
            }

            this.switchPauseButton();
        }
    }

    @Override
    public void run() {
        while (this.tablero.getPelota().isRunningAnimation()) {
            this.leerValoresJLabel();
            this.repaint();

            try {
                Thread.sleep(10);
            } catch (InterruptedException ex) {
            }
        }

        this.leerValoresJLabel();
        this.repaint(); // CUTRE. REVISAR >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>><
    }

    public static void main(String[] args) {
        Ball2 ball2 = new Ball2();
    }

}
