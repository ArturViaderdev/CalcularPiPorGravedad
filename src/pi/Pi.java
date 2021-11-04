/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author arturv
 */
public class Pi {

    final static int max = 100;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        System.out.println("Valor de pi en java " + Math.PI);
        System.out.println("Calculando pi:");
        nilakantha();
        bailey();
        System.out.println("Calulando por método propio gravitatorio. Espera por favor.");
        creaimagen();
        System.out.println("Se ha creado un png del círculo.");
    }

    private static void nilakantha() {
        int cont = 0;
        double numero = 2;

        double pi = 3;
        do {
            pi = pi + (4 / (numero * (numero + 1) * (numero + 2)) - 4 / ((numero + 2) * (numero + 3) * (numero + 4)));
            numero = numero + 4;
            cont++;

        } while (cont < max);
        System.out.println("Calculado por nilakantha " + pi);
        System.out.println("Se han hecho 100 ejecuciones. Cuantas más más se aproxima.");
    }

    private static void bailey() {
        BigDecimal pi = BigDecimal.ZERO;
        for (int i = 0; i <= max; i++) {
            BigDecimal a = BigDecimal.valueOf(1).divide(BigDecimal.valueOf(16).pow(i), 30, RoundingMode.HALF_UP);
            BigDecimal b1 = BigDecimal.valueOf(4).divide(BigDecimal.valueOf(8).multiply(BigDecimal.valueOf(i)).add(BigDecimal.valueOf(1)), 30, RoundingMode.HALF_UP);
            BigDecimal b2 = BigDecimal.valueOf(2).divide(BigDecimal.valueOf(8).multiply(BigDecimal.valueOf(i)).add(BigDecimal.valueOf(4)), 30, RoundingMode.HALF_UP);
            BigDecimal b3 = BigDecimal.valueOf(1).divide(BigDecimal.valueOf(8).multiply(BigDecimal.valueOf(i)).add(BigDecimal.valueOf(5)), 30, RoundingMode.HALF_UP);
            BigDecimal b4 = BigDecimal.valueOf(1).divide(BigDecimal.valueOf(8).multiply(BigDecimal.valueOf(i)).add(BigDecimal.valueOf(6)), 30, RoundingMode.HALF_UP);
            BigDecimal b = b1.subtract(b2).subtract(b3).subtract(b4);
            pi = pi.add(a.multiply(b));
        }
        System.out.println("Calculado por bailey: " + pi);
        System.out.println("Este pi es el correcto con todos los decimales que aparecen correctos. No caben más en la variable.");
    }

    private static void colores(BufferedImage imagen, int ancho, int alto) {
        int color = (255 << 24) | (255 << 16) | (255 << 8) | 255;
        int colorx;
        int r = 0;
        int g = 0;
        int b = 0;
        b = 128;
        boolean sentido = false;
        for (int y = 0; y < ancho; y++) {
            for (int x = 0; x < alto; x++) {

                try {
                    colorx = (r << 24) | (g << 16) | (b << 8) | 255;
                    imagen.setRGB(x, y, colorx);
                } catch (Exception ex) {
                    System.out.println(x + " " + y);
                }
                if (!sentido) {
                    if (r < 255) {
                        r++;
                    } else {
                        sentido = true;
                    }
                } else {
                    if (r > 0) {
                        r--;
                    } else {
                        sentido = false;
                    }
                }
            }
            if (g < 255) {
                g++;
            } else {
                g = 0;
            }
        }
    }

    private static void pintablanco(BufferedImage imagen, int ancho, int alto) {
        int color = (255 << 24) | (255 << 16) | (255 << 8) | 255;
        for (int y = 0; y < ancho; y++) {
            for (int x = 0; x < alto; x++) {

                try {
                    imagen.setRGB(x, y, color);
                } catch (Exception ex) {
                    System.out.println(x + " " + y);
                }

            }
        }
    }

    private static void pintacirculo(BufferedImage imagen, int ancho, int alto, int radiox, int radioy, int color) {
        ArrayList<Double> listax;
        ArrayList<Double> listay;
        listax = new ArrayList<Double>();
        listay = new ArrayList<Double>();
        double puntox, puntoy, velocidadx, velocidady, gravedadx, centrox, centroy, gravedady, velocidadmax;
        // int color = (255 << 24) | (0 << 16) | (0 << 8) | 0;
        int medio;

        gravedadx = 1;
        gravedady = 1;

        ancho = ancho * 1000;
        alto = alto * 1000;
        radiox = radiox * 1000;
        radioy = radioy * 1000;

        centrox = alto / 2;
        centroy = ancho / 2;

        puntox = centrox - radiox;
        puntoy = centroy - radioy;

        velocidadx = 0;
        velocidady = 0;
        boolean haciendo = false;
        boolean puestohaciendo = false;
        boolean sal = false;
        boolean primera = true;
        boolean iniciay = false;
        boolean mediacircunferencia = false;
        boolean circunferenciacompleta = false;
        int cuentavueltas = 0;
        int cont = 0;
        double pi;
        double piantiguo;
        medio = 0;
        double recorrido = 0;
        double freno = 0;
        double frenodos = 0;
        boolean aplicafrenodos = false;
        int vueltas = 0;
        piantiguo = 0;
        do {

            if (!iniciay) {

                if (puntox >= centrox) {
                    if (puntox == centrox) {
                        System.out.println("Clavado");
                        System.out.println("Centrox " + centrox + "Puntox " + puntox + "Puntoy " + puntoy);
                    }
                    iniciay = true;
                    System.out.println("Puntox " + puntox + " Centrox " + centrox);
                    listax.add(puntox);
                    listay.add(puntoy);
                }
            } else {
                if (!mediacircunferencia) {
                    if (puntox <= centrox) {
                        mediacircunferencia = true;
                        circunferenciacompleta = false;
                        /*   System.out.println("El recorrido es" + recorrido);
                        System.out.println("Pi es " + ((recorrido / (2 * radiox))) * 2);*/

                    }
                } else {
                    if (!circunferenciacompleta) {
                        if (puntox >= centrox) {
                            if (puntox == centrox) {
                                System.out.println("Clavado pi");

                            }
                            vueltas++;
                            circunferenciacompleta = true;
                            mediacircunferencia = false;
                            //          System.out.println("El recorrido es" + recorrido + "Circunferencia completa");

                            pi = (((recorrido / vueltas) / (2 * radiox)));
                            System.out.println(pi);
                            if (cuentavueltas < 0) {
                                cuentavueltas++;
                            } else {
                                cuentavueltas = 0;
                                System.out.println("Calculado con sistema gravitatorio. Invento Artur Viader. Pi es " + pi);
                                System.out.println("El valor se aproxima mucho. Cuantas más vueltas al círculo se dan más se aproxima. También cuanto menor es la fuerza gravitatoria.");
                                sal = true;
                            }

                            /*if(pi==piantiguo)
                            {
                                sal=true;
                                 System.out.println("Pi es " + pi );
                            }
                            else
                            {
                                piantiguo = pi;
                               
                            }*/
                        }

                    }

                }

            }

            if (puntox > centrox) {
                if (freno > 0) {
                    velocidadx -= ((puntox - centrox) / 10000000000000.0) * freno;
                } else {
                    velocidadx -= ((puntox - centrox) / 10000000000000.0);
                }

                if (!puestohaciendo) {
                    haciendo = true;
                    puestohaciendo = true;
                }

            } else if (puntox < centrox) {
                if (freno > 0) {
                    velocidadx += ((centrox - puntox) / 10000000000000.0) * freno;
                } else {
                    velocidadx += ((centrox - puntox) / 10000000000000.0);
                }

            } else {
                haciendo = true;
                puestohaciendo = true;
            }

            if (!iniciay) {
                if (puntox + velocidadx > centrox) {
                    freno = puntox + velocidadx - centrox;
                    freno = freno / velocidadx;
                    freno = 1 - freno;
                    System.out.println("Puesto freno");
                }
            }

            if (mediacircunferencia && !circunferenciacompleta) {
                if (puntox + velocidadx >= centrox) {
                    freno = puntox + velocidadx - centrox;
                    freno = freno / velocidadx;
                    freno = 1 - freno;
                    System.out.println("Puesto freno");
                }
            }
            if (iniciay) {
                if (puntoy > centroy) {
                   
                        if (freno > 0) {
                            velocidady -= ((puntoy - centroy) / 10000000000000.0) * freno;
                        } else {
                            velocidady -= ((puntoy - centroy) / 10000000000000.0);
                        }
                    

                    if (!puestohaciendo) {
                        haciendo = true;
                        puestohaciendo = true;
                    }
                } else if (puntoy < centroy) {
               
                        if (freno > 0) {
                            velocidady += ((centroy - puntoy) / 10000000000000.0) * freno;
                        } else {
                            velocidady += ((centroy - puntoy) / 10000000000000.0);
                        }
                } else {

                }
              
                    if (freno > 0) {
                        recorrido += Math.sqrt(Math.pow(velocidadx * freno, 2) + Math.pow(velocidady * freno, 2));
                    } else {
                        recorrido += Math.sqrt(Math.pow(velocidadx, 2) + Math.pow(velocidady, 2));
                    }
                

            }
            
           
                if (freno > 0) {
                    puntox += velocidadx * freno;
                    puntoy += velocidady * freno;
                    freno=0;
                } else {
                    puntox += velocidadx;
                    puntoy += velocidady;
                }
         

            if (iniciay) {

                listax.add(puntox);
                listay.add(puntoy);
            }

            cont++;
        } while (!sal);

        for (cont = 0; cont < listax.size(); cont++) {
            imagen.setRGB(listax.get(cont).intValue() / 1000, listay.get(cont).intValue() / 1000, color);
        }
    }

    private static void creaimagen() {
        try {
            int alto = 2160;
            int ancho = 3840;
            int r, g, b;
            r = 0;
            g = 0;
            b = 0;
            BufferedImage imagen = new BufferedImage(ancho, alto, BufferedImage.TYPE_INT_RGB);
            pintablanco(imagen, alto, ancho);
            //pintalineas(imagen,alto,ancho);
            int color = (255 << 24) | (0 << 16) | (0 << 8) | 0;
            for (int cont = 1000; cont >= 1000; cont -= 1) {
                color = (255 << 24) | (r << 16) | (g << 8) | b;
                if (r < 255) {
                    r += 1;
                } else {
                    g++;
                }

                pintacirculo(imagen, alto, ancho, cont, cont, color);
            }
            System.out.println("Paro");
            //colores(imagen, alto, ancho);
            File f = null;
            f = new File("imagen.png");
            ImageIO.write(imagen, "png", f);
        } catch (IOException ex) {
            Logger.getLogger(Pi.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
