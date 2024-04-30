package pt.ipp.isep.dei.esoft.project.mdisc.files.US14;

import pt.ipp.isep.dei.esoft.project.mdisc.Main;
import pt.ipp.isep.dei.esoft.project.mdisc.util.Edge;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainTest{
    public static void main(String[] args) {
        String folderPath = "src/main/java/pt/ipp/isep/dei/esoft/project/mdisc/files/US14";


        ArrayList<ExecutionRecord> executionRecords = new ArrayList<>();

        File folder = new File(folderPath);
        File[] files = folder.listFiles();

        if (files != null) {
            for (File file : files) {
                if (file.isFile() && file.getName().endsWith(".csv")) {

                    try {
                        List<Edge> edges;
                        List<Edge> mstEdges = new ArrayList<>();
                        edges = Main.readGraphFromCSV(file.getAbsolutePath());
                        long startTime = System.nanoTime();
                        Main.calculateMinimumSpanningTreeCost(edges, mstEdges);
                        long endTime = System.nanoTime();
                        long executionTime = (endTime - startTime) / 10000;
                        executionRecords.add(new ExecutionRecord(edges.size(), executionTime, file.getName()));
                        System.out.println("b");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        System.out.println("a");

        SwingUtilities.invokeLater(() -> {
            ExecutionTimePlotter plot = new ExecutionTimePlotter("Execution Records Plot", executionRecords);
            plot.setSize(800, 600);
            plot.setLocationRelativeTo(null);
            plot.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            plot.setVisible(true);
        });
    }
}


class ExecutionRecord {
    private final int inputSize;
    private final long executionTime;

    private final String fileName;

    public ExecutionRecord(int inputSize, long executionTime, String fileName) {
        this.inputSize = inputSize;
        this.executionTime = executionTime;
        this.fileName = fileName;
    }

    public int getInputSize() {
        return inputSize;
    }

    public long getExecutionTime() {
        return executionTime;
    }

    public String getFileName() {
        return fileName;
    }
}
