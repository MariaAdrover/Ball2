package ball2;

public class Pelota implements Runnable {

    private Tablero tablero;
    private volatile int x;
    private volatile int y;
    private volatile int lastX;
    private volatile int lastY;
    private volatile double vX;
    private volatile double vY;
    private volatile double a;
    private int ancho;
    private int alto;
    private volatile boolean runningAnimation;
    private volatile boolean paused;

    public Pelota(Tablero t, int x, int y, int ancho, int alto, double v, double a) {
        this.tablero = t;
        this.setX(x);
        this.setY(y);
        this.setAncho(ancho);
        this.setAlto(alto);
        this.runningAnimation = false;
        this.paused = false;
    }

    public void inicializarVariablesAnimacion1() {
        System.out.println("inicio variables1");
        this.x = 0;
        this.y = 450;
        this.lastX = 2;
        this.lastY = 450;
        this.vX = -2;
        this.vY = 0;
        this.a = 0;
    }

    public void inicializarVariablesAnimacion2() {
        System.out.println("inicio variables2");
        this.x = 0;
        this.y = 450;
        this.lastX = 2;
        this.lastY = 450;
        this.vX = 1;
        this.vY = 0;
        this.a = -1;
    }

    public void inicializarVariablesAnimacion3() {
        System.out.println("inicio variables3");
        this.x = 0;
        this.y = 450;
        this.lastX = 6;
        this.lastY = 443;
        this.vX = -1;
        this.vY = 1;
        this.a = 0;
    }
    // >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    // MAAAAAAAAAAL la a. >>>>>>>>>>>>>>> poner a para cada eje
    public void mover() {
        if (this.x + this.vX + this.a < 0 || this.x + this.vX + this.a > 1100) {
            this.vX = this.lastX - this.x;
            this.a = -(this.a);
        } else {
            this.vX = this.x - this.lastX;
        }

        if (this.y + this.vY + this.a < 0 || this.y + this.vY + this.a > 900) {
            this.vY = this.lastY - this.y;
            this.a = -(this.a);
        } else {
            this.vY = this.y - this.lastY;
        }

        this.lastX = this.x;
        this.lastY = this.y;

        /*if (this.vX != 0) {
            this.vX = this.vX + this.a;
        }

        if (this.vY != 0) {
            this.vY = this.vY + this.a;
        }*/
        
        this.x += this.vX;
        this.y += this.vY;

        /*if (this.vX >= 0) {
            if ((this.x + this.vX) < this.tablero.getAncho() - 100) {
                this.x += this.vX;
            } else {
                this.vX = -(this.vX);
                this.a = -(this.a);
                this.x += this.vX;
            }
        } else {
            if ((this.x + this.vX) > 0) {
                this.x += this.vX;
            } else {
                this.vX = -(this.vX);
                this.a = -(this.a);
                this.x += this.vX;
            }
        }*/
    }

    public void stopAnimation() {
        this.runningAnimation = false;
        System.out.println("FIN animacion");
    }

    public void reiniciarVariablesACero() {
        this.vX = 0;
        this.a = 0;
    }

    public void restablecerPosicion() {
        this.x = 0;
        this.y = this.tablero.getAlto() / 2 - 50;
    }

    @Override
    public void run() {

        while (this.runningAnimation) {
            if (!this.paused) {
                this.mover();
            }

            try {
                Thread.sleep(10);
            } catch (InterruptedException ex) {
            }
        }
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public int getAncho() {
        return this.ancho;
    }

    public int getAlto() {
        return this.alto;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setAncho(int ancho) {
        this.ancho = ancho;
    }

    public void setAlto(int alto) {
        this.alto = alto;
    }

    public boolean isRunningAnimation() {
        return runningAnimation;
    }

    public double getA() {
        return a;
    }

    public void setA(double a) {
        this.a = a;
    }

    public void setRunningAnimation(boolean runningAnimation) {
        this.runningAnimation = runningAnimation;
    }

    public boolean isPaused() {
        return paused;
    }

    public void setPaused(boolean paused) {
        this.paused = paused;
    }

    public int getLastX() {
        return lastX;
    }

    public void setLastX(int lastX) {
        this.lastX = lastX;
    }

    public int getLastY() {
        return lastY;
    }

    public void setLastY(int lastY) {
        this.lastY = lastY;
    }

    public double getvX() {
        return vX;
    }

    public void setvX(double vX) {
        this.vX = vX;
    }

    public double getvY() {
        return vY;
    }

    public void setvY(double vY) {
        this.vY = vY;
    }
}
