package ui.tabs;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import ui.TrailLogGUI;
import model.Trail;

import javax.swing.*;
import java.awt.*;
import java.util.List;

// A tab for viewing a Pie Chart of trails completed
public class ChartTab extends JPanel {

    private TrailLogGUI controller;

    // EFFECTS: Constructs a chart tab that displays a pie chart with completion stats
    public ChartTab(TrailLogGUI controller) {
        this.controller = controller;
        setLayout(new BorderLayout());
        add(createCompletionChart(), BorderLayout.CENTER);
    }

    // EFFECTS: Creates a pie chart that shows completion stats
    private ChartPanel createCompletionChart() {
        DefaultPieDataset dataset = new DefaultPieDataset();
        int completed = 0;
        int notCompleted = 0;
        List<Trail> trails = controller.getTrailLog().getTrailList();

        for (int i = 0; i < trails.size(); i++) {
            Trail trail = trails.get(i);
            if (trail.getCompletionStatus()) {
                completed++;
            } else {
                notCompleted++;
            }
        }

        dataset.setValue("Completed", completed);
        dataset.setValue("Not Completed", notCompleted);

        JFreeChart pieChart = ChartFactory.createPieChart(
                "Trail Completion Status",
                dataset,
                true,
                true,
                false
        );
        
        return new ChartPanel(pieChart);
    }
}


