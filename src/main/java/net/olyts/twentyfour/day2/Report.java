package net.olyts.twentyfour.day2;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Report {
    private List<Integer> levels;

    public Report(List<Integer> levels) {
        this.levels = levels;
    }

    public boolean isReportSafe() {
        Integer prev = this.getLevels().get(0);
        boolean isMonotonic = prev < this.getLevels().get(1);
        for (int i = 1; i < this.getLevels().size(); i++) {
            Integer cur = this.getLevels().get(i);

            if (isMonotonic && prev >= cur) {
                return false;
            }

            if (!isMonotonic && prev <= cur) {
                return false;
            }

            int difference = Math.abs(cur - prev);
            if (difference == 0 || difference > 3) {
                return false;
            }

            prev = cur;

        }
        return true;
    }

    public boolean isReportSafeWithProblemDampener() {
        if (isReportSafe()) return true;

        Report dampenedReport;
        List<Integer> subList;
        for (int i = 0; i < levels.size(); i++) {
            subList = new ArrayList<>();
            subList.addAll(this.levels.subList(0, i));
            subList.addAll(this.levels.subList(i + 1, levels.size()));
            dampenedReport = new Report(subList);
            if (dampenedReport.isReportSafe()) return true;
        }

        return false;

    }



}