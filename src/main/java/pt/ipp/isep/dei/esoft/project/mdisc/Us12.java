package pt.ipp.isep.dei.esoft.project.mdisc;

import pt.ipp.isep.dei.esoft.project.mdisc.util.Edge;
import pt.ipp.isep.dei.esoft.project.mdisc.util.Vertice;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Us12 {

    public static void readFile(String[] args){
        String file="";
        System.out.print("Insert file: ");
        Scanner scan= new Scanner(System.in);
        file= scan.next();
        ArrayList<Edge> edge= new ArrayList<>();
        try{
            edge=readToEdges(file);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static ArrayList<Edge> readToEdges (String file) throws FileNotFoundException {
        Scanner scanFile = new Scanner(new File(file));
        String[] line;
        ArrayList<Edge> edge = new ArrayList<>();
        while (scanFile.hasNextLine()) {
            line = scanFile.nextLine().split(";");
            edge.add(new Edge(new Vertice(line[0]), new Vertice(line[1]), Integer.parseInt(line[2])));
        }
        return edge;

    }

}
