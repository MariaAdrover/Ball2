/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ball2;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.JPanel;

/**
 *
 * @author miaad
 */
public class Tablero extends JPanel implements Runnable {

    private int ancho;
    private int alto;
    private Pelota pelota;
    private Image buffImage;
    private Graphics buffGraph;

    public Tablero(int ancho, int alto) {
        this.setAncho(ancho);
        this.setAlto(alto);
        this.setPreferredSize(new Dimension(this.ancho, this.alto));
        this.setBackground(Color.black);
        this.setFocusable(true);

        this.pelota = new Pelota(this, 0, (this.alto/2)-50, 100, 100, 0, 0);
        
        this.buffImage = null;
    }
    
    /*private void render() {
        if (this.buffImage == null) {
            this.buffImage = this.createImage(this.ancho, this.alto);
            if (this.buffImage == null) {
                System.out.println("dbImage is null");
                return;
            } else {
                this.buffGraph = this.buffImage.getGraphics();
                System.out.println("ok");
            }
        }
        
        this.buffGraph.setColor(Color.black);
        this.buffGraph.fillRect(0, 0, this.ancho, this.alto);
    }*/

    public void paintComponent(Graphics g) {
        g.setColor(Color.black);
        g.fillRect(0, 0, this.ancho, this.alto);
        g.setColor(Color.white);
        g.fillOval(this.pelota.getX(), this.pelota.getY(), this.pelota.getAncho(), this.pelota.getAlto());

    }

    public int getAncho() {
        return ancho;
    }

    public void setAncho(int ancho) {
        this.ancho = ancho;
    }

    public int getAlto() {
        return alto;
    }

    public void setAlto(int alto) {
        this.alto = alto;
    }

    public Pelota getPelota() {
        return pelota;
    }

    @Override
    public void run() {
        while (this.pelota.isRunningAnimation()) {
            //this.render();
            this.repaint();

            try {
                Thread.sleep(10);
            } catch (InterruptedException ex) {
            }
        }
    }
}
