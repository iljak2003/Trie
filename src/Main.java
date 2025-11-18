import java.util.Arrays;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        String str = "qwerty";
        for (int i = 0; i < str.length(); i++){
            System.out.println(str.substring(i, i+1));
        }
    }
}