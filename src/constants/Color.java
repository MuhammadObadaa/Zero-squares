package constants;

public enum Color {
    BLACK('K',0),
    RED('R',1),
    GREEN('G',2),
    YELLOW('Y',3),
    BLUE('B',4),
    PURPLE('P',5),
    CYAN('C',6),
    WHITE('W',7),
    NOCOLOR('O',8);

    //BG_BLACK('K',"\u001B[40m"),

    private final int index;
    private final char symbol;

    static final String base = "\u001B[";
    static final String suffex = "m";

    Color(char symbol,int index){
        this.symbol = symbol;
        this.index = index;
    }

    // TODO: rename method name
    public static String resetColorCode(){
        return Color.base + "0" + Color.suffex;
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
        }else if(symbol == GREEN.symbol) {
            return Color.GREEN;
        }else if(symbol == CYAN.symbol) {
            return Color.CYAN;
        }else if(symbol == NOCOLOR.symbol) {
            return Color.NOCOLOR;
        }else{
            return null;
        }
    }

    public static String formColor(Color fgColor, Color bgColor){
        String result = Color.base;

        if(fgColor != null){
            result += "3" + fgColor.index;

            if(bgColor != null){
                result += ";4" + bgColor.index;
            }

            result += Color.suffex;

            return result;
        }

        if(bgColor != null){
            result += "4" + bgColor.index + Color.suffex;

            return result;
        }

        return "";
    }

//
//    public String toString(){
//        return this.toString(false);
//    }
//    public String toString(boolean background) {
//        return  this.index + (background ? '3':'4');
//    }
}
