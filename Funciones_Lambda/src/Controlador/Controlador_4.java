package Controlador;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.stream.Stream;

public class Controlador_4 {

    private static final String ARCHIVO_CSV = "productos.csv";

    public void calcularPrecioMedio() {
        try (BufferedReader br = new BufferedReader(new FileReader(ARCHIVO_CSV))) {
            Stream<String> lineas = br.lines();

            double precioMedio = lineas.mapToDouble(linea -> {
                String[] valores = linea.split(",");
                if (valores.length >= 2) {
                    String precioString = valores[1].replaceAll("\"", "").trim();
                    try {
                        return Double.parseDouble(precioString);
                    } catch (NumberFormatException e) {
                        System.err.println("Valor no numérico: " + precioString);
                    }
                }
                return 0;
            }).filter(precio -> precio > 0).average().orElse(0);

            if (precioMedio > 0) {
                System.out.println("El precio medio de los productos es: " + precioMedio);
            } else {
                System.out.println("No se encontraron datos válidos en el archivo.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}