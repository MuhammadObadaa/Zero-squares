package models;

public enum Color {
    BLACK('K',"\u001B[30m"),
    RED('R',"\u001B[31m"),
    BLUE('B',"\u001B[34m"),
    CYAN('C',"\u001B[36m"),
    GREEN('G',"\u001B[32m"),
    YELLOW('Y',"\u001B[33m"),
    WHITE('W',"\u001B[37m");

    private final String code;
    private final char symbol;

    Color(char symbol,String code){
        this.symbol = symbol;
        this.code = code;
    }

    public static Color getColor(char symbol){
        // Main three square colors in game

        symbol = Character.toUpperCase(symbol);

        if(symbol == RED.symbol){
            return Color.RED;
        }else if(symbol == BLUE.symbol){
            return Color.BLUE;
        }else if(symbol == YELLOW.symbol) {
            return Color.YELLOW;
        }else{
            return null;
        }
    }

    @Override
    public String toString() {
        return this.code;
    }
}
