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
        System.out.println("#-1-2-3-");
        for (int i = 0; i < sizeX; i++)
        {
            System.out.print(i+1);
            for (int j = 0; j < sizeY; j++)
            {
                System.out.print("|" + field[i][j]);
            }
            System.out.print("|\n");
        }
        System.out.println("--------");
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
            x=scanner.nextInt();
            y=scanner.nextInt();
        }while (!isCellValid(x,y) || !isCellEmpty(x,y));
    }
    public static void stepCpu()
    {
        int x, y;
        do{
            x=random.nextInt(sizeX);
            y=random.nextInt(sizeY);
        }while (!isCellEmpty(x,y));
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
    public static void main(String[] args) {
        System.out.println("Hello world!");
        begin();
        plotXY();
    }
}