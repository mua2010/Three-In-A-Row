package model;

public interface RowGameRulesStrategy {
    public void reset();

    public void move(int row, int col);

    public boolean isWin(int row, int col, String playerSymbol);

    public boolean isTie();
}
