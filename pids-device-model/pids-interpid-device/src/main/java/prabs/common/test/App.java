package prabs.common.test;

import java.util.StringTokenizer;

public class App {
    public static void main( String[] args ) {
        System.out.println( "Hello World!" );
        String property = System.getProperty("java.library.path");
        StringTokenizer parser = new StringTokenizer(property, ";");
        while (parser.hasMoreTokens()) {
            System.out.println(parser.nextToken());
        }
    }
}