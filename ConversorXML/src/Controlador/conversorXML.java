package Controlador;



import java.io.BufferedReader;

import java.io.FileReader;

import java.io.FileWriter;

import java.io.IOException;

import java.util.ArrayList;

import java.util.List;



import javax.xml.stream.XMLOutputFactory;

import javax.xml.stream.XMLStreamException;

import javax.xml.stream.XMLStreamWriter;



public class conversorXML {



    public static void main(String[] args) {

        convertCsvToXml("Formulario_BitCoin.csv", "Formulario_BitCoin.xml");

    }



    public static void convertCsvToXml(String csvFileName, String xmlFileName) {

        try (BufferedReader br = new BufferedReader(new FileReader(csvFileName));

             FileWriter fw = new FileWriter(xmlFileName)) {



            // Crear el escritor XML

            XMLOutputFactory xmlOutputFactory = XMLOutputFactory.newInstance();

            XMLStreamWriter xmlStreamWriter = xmlOutputFactory.createXMLStreamWriter(fw);



            // Escribir la estructura XML

            xmlStreamWriter.writeStartDocument();

            xmlStreamWriter.writeStartElement("data");



            // Leer el archivo CSV

            String line;

            List<String> headers = null;

            while ((line = br.readLine()) != null) {

                String[] fields = line.split(",");

                if (headers == null) {

                    // Si es la primera línea, guarda los encabezados

                    headers = new ArrayList<>();

                    for (String field : fields) {

                        headers.add(field.trim());

                    }

                } else {

                    // Escribir una entrada para cada línea de datos

                    xmlStreamWriter.writeStartElement("entry");

                    for (int i = 0; i < headers.size(); i++) {
                        xmlStreamWriter.writeStartElement(headers.get(i));

                        // Verificar si el índice i es válido
                        if (i < fields.length) {
                            xmlStreamWriter.writeCharacters(fields[i].trim());
                        } else {
                            // Tratar el caso donde el índice es mayor o igual a la longitud de fields
                            System.err.println("Advertencia: El índice está fuera de los límites del array.");
                        }

                    xmlStreamWriter.writeEndElement(); // Cerrar la etiqueta de entrada

                }

            }



            // Cerrar la estructura XML

            xmlStreamWriter.writeEndElement();

            xmlStreamWriter.writeEndDocument();



            // Cerrar el escritor XML

            xmlStreamWriter.close();



            System.out.println("Conversión exitosa de CSV a XML.");


            }
        } catch (IOException | XMLStreamException e) {

            e.printStackTrace();

        }
    }
}