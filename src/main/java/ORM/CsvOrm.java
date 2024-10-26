package ORM;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Field;

public class CsvOrm {

    public <T> List<T> connect(Class<T> entityClass, String filepath) {
        List<T> entities = new ArrayList<>();
        try (Scanner reader = openFile(filepath)) {
            // Read header line to identify field names
            if (reader.hasNextLine()) {
                String headerLine = reader.nextLine();
                String[] headers = headerLine.split(",");
                
                // Read each line of the CSV
                while (reader.hasNextLine()) {
                    String line = reader.nextLine();
                    String[] values = line.split(",");
                    
                    // Create an instance of the entity class
                    T entity = entityClass.getDeclaredConstructor().newInstance();
                    
                    // Map values to entity fields
                    for (int i = 0; i < headers.length; i++) {
                        Field field = entityClass.getDeclaredField(headers[i].trim());
                        field.setAccessible(true); // Allow access to private fields
                        field.set(entity, convertValue(field.getType(), values[i].trim()));
                    }
                    
                    entities.add(entity);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return entities;
    }

    private Scanner openFile(String filepath) throws FileNotFoundException {
        File myObj = new File(filepath);
        Scanner reader = new Scanner(myObj);
        return reader;
    }

    private Object convertValue(Class<?> fieldType, String value) {
        if (fieldType == int.class || fieldType == Integer.class) {
            return Integer.parseInt(value);
        } else if (fieldType == double.class || fieldType == Double.class) {
            return Double.parseDouble(value);
        } else if (fieldType == boolean.class || fieldType == Boolean.class) {
            return Boolean.parseBoolean(value);
        } else {
            return value; // For String and other types
        }
    }
}
