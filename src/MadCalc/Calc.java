package MadCalc;

public class Calc {

    public static int execute(String string) throws NotCorrectCurrentOperands {

        String [] str = string.trim().split(" ");
        if (str.length > 3) {
            throw new NotCorrectCurrentOperands("Количество аргументов должно быть не более 2-х");
        }

        int a;
        int b;

        for (int i = 0; i < 3; i++) {
            if (i == 0 || i == 2){
                try {
                    Integer.parseInt(str[i]);
                }
                catch (Exception e){
                    throw new NotCorrectCurrentOperands( i + 1 + "-й аргумент не является числом!");
                }
                finally {
                    System.out.println("Тьфу на тебя!");
                }
            }

            if (i == 1 && !(str[i].equals("+") || str[i].equals("-")))
                throw new NotCorrectCurrentOperands("Не корректный операнд! Операнд может быть или + или -");
        }

        int result;
        if (str[1].equals("+"))
            result = Integer.parseInt(str[0]) + Integer.parseInt(str[2]);
        else result = Integer.parseInt(str[0]) - Integer.parseInt(str[2]);

        return result;
    }
}
