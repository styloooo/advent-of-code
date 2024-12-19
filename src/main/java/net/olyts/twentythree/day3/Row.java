package net.olyts.twentythree.day3;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
public class Row {
    int index;
    private List<Character> rowValues;

    public String toString() {
        return rowValues.toString();
    }

    public Character getRowValue(int rowIndex) {
        return rowValues.get(rowIndex);
    }

    public List<Integer> findAll(Character chr) {
        List<Integer> chrFoundAtIndices = new ArrayList<>();
        for (int i = 0; i < this.rowValues.size(); i++) {
            Character value = this.getRowValue(i);
            if (value.equals(chr)) chrFoundAtIndices.add(i);
        }
        return chrFoundAtIndices;
    }

}
