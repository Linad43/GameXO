import java.util.Random;
import java.util.Scanner;
public class Main
{
    private static final char DOT_HUMAN = 'X';
    private static final char DOT_AI = '0';
    private static final char DOT_EMPTY = '*';
    private static final Scanner scanner = new Scanner(System.in);
    private static final Random random = new Random();
    private static char[][] field;
    static int sizeX = 3;
    static int sizeY = 3;
    static int vin_count = 3;
    public static void begin()
    {
        field = new char[sizeX][sizeY];
        for (int i = 0; i < sizeX; i++)
        {
            for (int j = 0; j < sizeY; j++)
            {
                field[i][j] = DOT_EMPTY;
            }
        }
    }
    public static void plotXY ()
    {
        System.out.print("#");
        for (int i = 0; i < sizeX; i++) {
            System.out.print("-" + (i+1));
        }
        System.out.println("-");

        for (int i = 0; i < sizeX; i++)
        {
            System.out.print(i+1);
            for (int j = 0; j < sizeY; j++)
            {
                System.out.print("|" + field[i][j]);
            }
            System.out.print("|\n");
        }
        for (int i = 0; i < sizeX*2+2; i++) {
            System.out.print("-");
        }
        System.out.print("\n");
    }
    public static void stepHum()
    {
        int x, y, f=0;
        do{
            if (f==0)
            {
                System.out.println("Ваш ход, введите координаты (через пробел): ");
                f=1;
            }
            else
                System.out.println("Введены не корректные данные повторите ввод: ");
            x=scanner.nextInt()-1;
            y=scanner.nextInt()-1;
        }while (!isCellValid(x,y) || !isCellEmpty(x,y));
        field[x][y] = DOT_HUMAN;
    }
    public static void stepCpu()
    {
        int x, y;
        do{
            x=random.nextInt(sizeX);
            y=random.nextInt(sizeY);
        }while (!isCellEmpty(x,y));
        field[x][y] = DOT_AI;
    }
    /**
     * Проверка, является ли ячейка игрового поля пустой
     * @param x координата
     * @param y координата
     * @return результат проверки
     */
    static boolean isCellEmpty(int x, int y){
        return field[x][y] == DOT_EMPTY;
    }

    /**
     * Проверка валидности координат хода
     * @param x координата
     * @param y координата
     * @return результат проверки
     */
    static boolean isCellValid(int x, int y){
        return x >= 0 && x < sizeX && y >= 0 && y < sizeY;
    }
    /*
    Добавить метод проверки на победу, ничью.
    /**
     * Проверка состояния игры
     * @param dot фишка игрока
     * @param s победный слоган
     * @return состояние игры
     */

    public static boolean checkWin(char dot)
    {
        for (int i = 0; i < sizeX; i++)
        {
            for (int j = 0; j < sizeY; j++)
            {
                if (field[i][j] == dot)
                {
                    return check1(i,j,dot) || check2(i,j,dot) || check3(i,j,dot) || check4(i,j,dot);
                }
            }
        }
        return false;
    }
    public static boolean check1(int i, int j, char dot)
    {
        if(j+vin_count <= sizeY) {
            for (int k = 0; k < vin_count; k++) {
                if (field[i][j + k] != dot) {
                    break;
                }
                if (k == vin_count - 1) {
                    return true;
                }
            }
        }
        return false;
    }
    public static boolean check2(int i, int j, char dot)
    {
        if(i+vin_count <= sizeX) {
            for (int k = 0; k < vin_count; k++) {
                if (field[i + k][j] != dot) {
                    break;
                }
                if (k == vin_count - 1) {
                    return true;
                }
            }
        }
        return false;
    }
    public static boolean check3(int i, int j, char dot)
    {
        if(i+vin_count <= sizeX && j+vin_count <= sizeY) {
            for (int k = 0; k < vin_count; k++) {
                if (field[i + k][j + k] != dot) {
                    break;
                }
                if (k == vin_count - 1) {
                    return true;
                }
            }
        }
        return false;
    }
    public static boolean check4(int i, int j, char dot)
    {
        if(i-vin_count <= sizeX && i-vin_count >= 0 && j+vin_count <= sizeY) {
            for (int k = 0; k < vin_count; k++) {
                if (field[i - k][j + k] != dot) {
                    break;
                }
                if (k == vin_count - 1) {
                    return true;
                }
            }
        }
        return false;
    }
    static boolean checkDraw(){
        for (int x = 0; x < sizeX; x++){
            for (int y = 0; y < sizeY; y++){
                if (isCellEmpty(x, y))
                {
                    return false;
                }
            }
        }
        return true;
    }
    static boolean checkState(char dot, String s){
        if (checkWin(dot)){
            System.out.println(s);
            return true;
        }
        else if (checkDraw()){
            System.out.println("Ничья!");
            return true;
        }
        // Игра продолжается
        return false;
    }

    public static void main(String[] args) {
        System.out.println("Hello world!");
        while (true) {
            begin();
            plotXY();
            while (true) {
                stepHum();
                plotXY();
                if (checkState(DOT_HUMAN, "Вы победили!"))
                    break;
                stepCpu();
                plotXY();
                if (checkState(DOT_AI, "Компьютер победил!"))
                    break;
            }
            System.out.print("Желаете сыграть еще раз? (Y - да): ");
            if(!scanner.next().equalsIgnoreCase("Y"))
                break;
        }
    }
}