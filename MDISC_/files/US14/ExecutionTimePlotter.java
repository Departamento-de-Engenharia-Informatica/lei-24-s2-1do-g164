package pt.ipp.isep.dei.esoft.project.mdisc.files.US14;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.DefaultXYDataset;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ExecutionTimePlotter extends JFrame {

    public ExecutionTimePlotter(String title, ArrayList<ExecutionRecord> records) {
        super(title);
        initUI(records);
    }

    private void initUI(ArrayList<ExecutionRecord> records) {
        // Create a dataset
        DefaultXYDataset dataset = createDataset(records);

        // Create a chart based on the dataset
        JFreeChart chart = ChartFactory.createScatterPlot(
                getTitle(), // Chart title
                "Input Size", // X-Axis label
                "Running Time", // Y-Axis label
                dataset, // Dataset
                PlotOrientation.VERTICAL,
                true, // Include legend
                true, // Include tooltips
                false // Include URLs
        );

        // Customize the appearance of points
        XYPlot plot = (XYPlot) chart.getPlot();
        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        renderer.setSeriesLinesVisible(0, false); // Hide lines connecting points
        renderer.setSeriesShapesVisible(0, true); // Show shapes (points)
        renderer.setSeriesShape(0, new java.awt.geom.Ellipse2D.Double(-3, -3, 6, 6)); // Set shape of points
        plot.setRenderer(renderer);

        // Create a panel to display the chart
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(800, 600));
        setContentPane(chartPanel);
    }

    private DefaultXYDataset createDataset(ArrayList<ExecutionRecord> records) {
        DefaultXYDataset dataset = new DefaultXYDataset();

        // Convert ExecutionRecords to double arrays
        double[][] data = new double[2][records.size()];
        for (int i = 0; i < records.size(); i++) {
            ExecutionRecord record = records.get(i);
            data[0][i] = record.getInputSize(); // X-axis: Input Size
            data[1][i] = record.getExecutionTime(); // Y-axis: Running Time
        }

        dataset.addSeries("Execution Records", data);

        return dataset;
    }
}
