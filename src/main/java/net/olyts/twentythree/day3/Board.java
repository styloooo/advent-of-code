package net.olyts.twentythree.day3;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Character.isDigit;

@Getter
@Slf4j
public class Board {
    public static final Character PERIOD_SYMBOL = '.';
    public static final char ASTERISK_SYMBOL = '*';
    private final int rowLength;  // rows.get(0).size()
    private final int numRows;     // rows.size()
    private final List<Row> rows;

    public Board(List<List<Character>> inputRows) {
        numRows = inputRows.size();
        rowLength = numRows > 0 ? inputRows.get(0).size() : 0;
        rows = new ArrayList<>();

        for (int i = 0; i < inputRows.size(); i++) {
            List<Character> inputRow = inputRows.get(i);
            Row row = new Row(i, inputRow);
            rows.add(row);
        }
    }

    public List<Integer> getNumbersAdjacentToSymbols() {
        List<Integer> numbersAdjacentToSymbols = new ArrayList<>();
        for (int rowNum = 0; rowNum < numRows; rowNum++) {
            for (int rowIndex = 0; rowIndex < rowLength; rowIndex++) {
                if(cellContainsDigit(rowNum, rowIndex)) {
                    String fullNumText = getFullNumberText(rowNum, rowIndex);
                    int numLength = fullNumText.length();
                    if (isSymbolAdjacentToBlock(rowNum, rowIndex, numLength)) numbersAdjacentToSymbols.add(Integer.valueOf(fullNumText));
                    rowIndex += numLength;
                }
            }
        }
        return numbersAdjacentToSymbols;
    }

    public List<Integer> getGearRatios() {
        List<Integer> gearRatios = new ArrayList<>();
        List<IndexPair> gearRatioIndexPairs = getIndexPairForEachGear();

        for (IndexPair ij : gearRatioIndexPairs) {
            log.info("i: {}, j: {}", ij.i, ij.j);
        }

        return gearRatios;
    }

    private Character getRowValue(int rowNum, int rowIndex) {
        return rows.get(rowNum).getRowValue(rowIndex);
    }

    private boolean cellContainsDigit(int rowNum, int rowIndex) {
        if (rowNum < 0 || rowIndex < 0 || rowNum >= this.numRows || rowIndex >= this.rowLength) return false;
        return isDigit(getRowValue(rowNum, rowIndex));
    }

    private boolean cellContainsSymbol(int rowNum, int rowIndex) {
        if (rowNum < 0 || rowIndex < 0 || rowNum >= this.numRows || rowIndex >= this.rowLength) return false;
        else return !cellContainsDigit(rowNum, rowIndex) && !getRowValue(rowNum, rowIndex).equals(PERIOD_SYMBOL);
    }

    private String getFullNumberText(int rowNum, int rowIndex) {
        Row row = rows.get(rowNum);
        StringBuilder fullNum = new StringBuilder().append(row.getRowValue(rowIndex));  // default to first character

//        while (cellContainsDigit(rowNum, ++rowIndex)) {
//            fullNum.append(row.getRowValue(rowIndex));
//        }

        int l = rowIndex - 1;
        int r = rowIndex + 1;
        while(cellContainsDigit(rowNum, l) || cellContainsDigit(rowNum, r)) {

            if (cellContainsDigit(rowNum, l)) {
                fullNum.insert(0, row.getRowValue(l));
                l--;
            }

            if (cellContainsDigit(rowNum, r)) {
                fullNum.append(row.getRowValue(r));
                r++;
            }

        }

        return fullNum.toString();
    }

    private List<IndexPair> getIndexPairForEachGear() {
        List<IndexPair> gearIndexPairs = new ArrayList<>();
        for (Row row : this.getRows()) {
            for (int rowIndex : row.findAll(ASTERISK_SYMBOL)) {
                gearIndexPairs.add(new IndexPair(row.getIndex(), rowIndex));
            }
        }
        return gearIndexPairs;
    }

    private boolean isSymbolAdjacentToCell(int rowNum, int rowIndex) {
        // Is rowNum or rowIndex out of bounds?
        if (rowNum >= this.numRows || rowNum < 0 || rowIndex >= this.rowLength || rowIndex < 0) return false;

        // Check upper left diagonal (rowNum-1, rowIndex-1)
        return cellContainsSymbol(rowNum - 1, rowIndex - 1)
        // Check above cell (rowNum - 1, rowIndex)?
                || cellContainsSymbol(rowNum - 1, rowIndex)
        // Check upper right diagonal (rowNum - 1, rowIndex + 1)
                || cellContainsSymbol(rowNum - 1, rowIndex + 1)
        // Check left of cell (rowNum, rowIndex - 1)
                || cellContainsSymbol(rowNum, rowIndex - 1)
        // Check right of cell (rowNum, rowIndex + 1)
                || cellContainsSymbol(rowNum, rowIndex + 1)
        // Check lower left diagonal (rowNum + 1, rowIndex - 1)
                || cellContainsSymbol(rowNum + 1, rowIndex - 1)
        // Check below cell (rowNum + 1, rowIndex)
                || cellContainsSymbol(rowNum + 1, rowIndex)
        // Check lower right diagonal (rowNum + 1, rowIndex + 1)
                || cellContainsSymbol(rowNum + 1, rowIndex + 1);

    }


    private boolean isSymbolAdjacentToBlock(int rowNum, int rowIndex, int blockLength) {
        for (int i = 0; i < blockLength; i++) {
            if (isSymbolAdjacentToCell(rowNum, rowIndex + i)) {
                return true;
            }
        }
        return false;
    }
}
