package io.dwllr;
import io.dwllr.cassandra.Connect;
import io.dwllr.cassandra.Query;
import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

public class DataProcessorDriver {
    public static void main(String[] args) {
        int numberOfFiles = 6;
        Connect connection = new Connect();
        String iter = "", parser = "", file = "", type = "";
        for (int i = 1; i < numberOfFiles; i++) {
            try {
                FileInputStream fileInput = new FileInputStream("./resources/config.xml");
                Properties properties = new Properties();
                properties.loadFromXML(fileInput);
                fileInput.close();
                file = (String) properties.get("file" + i);
                iter = (String) properties.get("DataRowIterator" + i);
                parser = (String) properties.get("DataRowParser" + i);
                type = (String) properties.get("DataType" + i);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                Class<?> classIterator = Class.forName("io.dwllr.processor." + iter);
                Constructor<?> constructor = classIterator.getConstructor(String.class);
                Object obj = constructor.newInstance(file);
                Method methodCall = classIterator.getDeclaredMethod("getNextRow");
                Object value = methodCall.invoke(obj);
                Optional<List<String>> row = (Optional<List<String>>) value;
                Class<?> parserClass = Class.forName("io.dwllr.processor." + parser);
                Constructor<?> constructorParser = parserClass.getConstructor();
                Object parserObj = constructorParser.newInstance();
                Query query;
                while (row.isPresent()) {
                    Method methodCallQuery = parserClass.getDeclaredMethod("getQueryFromData", List.class);
                    Object queryValue = methodCallQuery.invoke(parserObj, row.get());
                    query = (Query) queryValue;

                    try {
                        String finalQuery;
                        if (type.equals("zip")) {
                            finalQuery = query.getQuery();
                        } else {
                            finalQuery = query.getFinalQuery();
                        }
                        System.out.println(finalQuery);
                        connection.query(finalQuery);
                    } catch (Exception e) {
                        System.out.println("QUERY FAILED");
                    }
                    value = methodCall.invoke(obj);
                    row = (Optional<List<String>>) value;
                }
                if (!row.isPresent()) {
                    Method methodCallClose = classIterator.getDeclaredMethod("closeDataset");
                    methodCallClose.invoke(obj);
                    System.out.println("Closed and done");
                }

            } catch (Exception e) {
                System.out.println("Error creating iterator with filename");
            }
        }
    }
}