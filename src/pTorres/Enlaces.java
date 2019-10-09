package pTorres;
/* * *
 * Tarea 3. Implementación en Consola del Proyecto Redes de Comunicación
 * Materia: Tecnologías de Programación
 * Tutor: María Lucía Barrón Estrada.
 *
 * Autor: * Víctor Manuel Bátiz Beltrán.
 * Última Revisión: 08/10/2019
 *
 * * */

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Enlaces {
    private static Grafo gT = new Grafo("Redes de Comunicaciones Las Torres");

    /*
     * Método para leer un archivo de texto y descomponerlo en líneas, cada línea es limpiada de espacios
     * y es comparada contra una expresión regular para ver si cumple con las reglas de una operación de agregar
     * o remover enlace o si es una pregunta.
     *
     * @Param archivo Recibe como parámetro la ruta al archivo.
     * @Param return regresa un ArrayList conteniendo las líneas válidas
     */
    static ArrayList<String> obtenerContenido(String archivo) throws FileNotFoundException, IOException {
        ArrayList<String> vLineas = new ArrayList<String>();
        String regexValidarRutas = "[A-z]([a-zA-Z0-9]){0,14}(\\s*)(->|-|<-)(\\s*)[A-z]([a-zA-Z0-9]){0,14}(\\s*)[.].*";
        String regexValidarPregunta = "[A-z]([a-zA-Z0-9]){0,14}(\\s*)(=>|<=)(\\s*)[A-z]([a-zA-Z0-9]){0,14}(\\s*)[?].*";
        String cadena;
        FileReader f = new FileReader(archivo);
        BufferedReader b = new BufferedReader(f);
        while((cadena = b.readLine())!=null) {
            // Se anexa al arreglo la cadena solo si Agrega o quita una ruta o hace una pregunta
            // de lo contrario la ignoro.
            cadena = cadena.strip().replace(" ",""); //Elimina espacios vacios
            if ((Pattern.matches(regexValidarRutas,cadena)) || (Pattern.matches(regexValidarPregunta,cadena))) {
                vLineas.add(cadena);
            }
        }
        b.close();
        return vLineas;
    }

    /*
     * Método clave para el programa, ya que es el que analiza cada línea del archivo para
     * determinar si es es una operación (agregar o remover enlace) o una pregunta de si existe
     * un enlace entre torres.
     *
     * @Param vLineas es un ArrayList de las líneas de texto válidas del archivo fuente.
     */
    public static void procesaLineas(ArrayList<String> vLineas) {
        int tipo = 0; //0=nada 1=enlace 2=pregunta
        String origen, destino ="";
        String separador ="";
        for (String a : vLineas) {
            ArrayList arrCadena = new ArrayList();
            Pattern regexParaSeparar = Pattern.compile("([A-z]([a-zA-Z0-9]){0,14})|(->|<-|-|=>|<=)|([.]|[?])");
            Matcher matchPatron = regexParaSeparar.matcher(a);
            //Revisa la cadena y extrae cada componente. Los agrega a un ArrayList
            while(matchPatron.find()) {
                arrCadena.add(matchPatron.group()); //Agrega cada elemento al ArrayList
            }
            //Obtiene cada elemento para procesarlos
            origen = arrCadena.get(0).toString();
            separador = arrCadena.get(1).toString();
            destino = arrCadena.get(2).toString();
            //En caso de que los operadores sean inversos, invierte origen y destino
            if (separador.equals("<-") || separador.equals("<=")) {
                origen = arrCadena.get(2).toString();
                destino = arrCadena.get(0).toString();
            }

           if (!origen.equalsIgnoreCase(destino)) {
               //Crea los nodos
               Grafo.Nodo nOrigen = new Grafo.Nodo(origen);
               Grafo.Nodo nDestino = new Grafo.Nodo(destino);
               switch (separador) {
                    case "<-":
                    case "->":
                        //Agrega los nodos en caso de que no existan
                        gT.addNodo(new Grafo.Nodo(origen));
                        gT.addNodo(new Grafo.Nodo(destino));
                        //Agrega Enlace
                        gT.addArista(new Grafo.Arista(nOrigen, nDestino));
                        break;
                    case "-":
                        boolean res = gT.removeArista(new Grafo.Arista(nOrigen, nDestino)); //Se remueve el enlace
                        break;
                    case "<=":
                    case "=>":
                        try {
                            Grafo.Comunicacion comunicacion = gT.obtenMejorCamino(nOrigen, nDestino);
                            System.out.println(comunicacion.toString());
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                        break;
                    default:
                        //
                }
            }
        }
    }

    /*
     * Método principal de ejecución del programa de Redes de Comunicación
     */
    public static void main(String[] args) {
        ArrayList<String> vectorLineas = new ArrayList<String>(); //Contendra las líneas del archivo de texto.

        System.out.println(gT.getNombre());
        try {
            vectorLineas = obtenerContenido("src/torres2.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("**********Inicio Líneas válidas**********");
        for (String a : vectorLineas) {
            System.out.println(a);
        }
        System.out.println("***********Fin  Líneas válidas***********");

        //Se llama al método donde se procesan las líneas y se muestran los resultados
        procesaLineas(vectorLineas);

    }
}
