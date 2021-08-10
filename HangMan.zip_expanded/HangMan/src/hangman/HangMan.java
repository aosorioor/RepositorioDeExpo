
package hangman;
import java.util.Locale;
import java.util.Random;
import java.util.Scanner;
import java.util.Arrays;

public class HangMan {

    public static void main(String[] args){
        System.out.println("              HangMan game ");
        System.out.println("  Word        Errors      Used letters");
                    
        startGame();
    }

    // Toma una palabra al alzar de la lista.
    public static String randomWord(String[] wordlist){
        Random randomIndex = new Random();
        int index = randomIndex.nextInt(wordlist.length);
      return wordlist[index];
    }

    // Vuelve la palabra en un array.
    public static String[] wordToArray(String word){
        int lengthWord = word.length();
        String[] chars = new String[lengthWord];
        for(int i = 0; i < lengthWord; i++){
            chars[i] = String.valueOf(word.charAt(i));
        }
        return chars;
    }

    // Imprime el contenido de un array en una línea (sin brackets ni comas).
    public static void printArray(String[] arrayValue){
        System.out.println();
        for (String s : arrayValue) {
            System.out.print(s +" ");
        }
    }

    // Esconde la palabra.
    public static String[] hiddenWord(String word){
        int lengthWord = word.length();
        String[] hiddenChars = new String[lengthWord];
        for(int i = 0; i < lengthWord; i++){
            hiddenChars[i] = "_";
        }
        printArray(hiddenChars);
        return hiddenChars;
    }

    // Si alguna de las letras ingresadas hace parte de la palabra, la muestra.
    public static String[] showWord(String[] hiddenWord, String[] word, String letter) {
        for (int i = 0; i < word.length; i++) {
            if (letter.equals(word[i])) {
                hiddenWord[i] = letter;
            }
        }
            printArray(hiddenWord);
            return hiddenWord;
        }

    // Hace el dibujo del ahorcado cada vez que un error ocurre.
    public static void hangManDrawing(int numOfFails){
        System.out.print("     ");
        String[] hangMan = {"q", "(", "X", "_","X", ")", "p"};
        for (int i = 0; i < numOfFails; i++){
            System.out.print(hangMan[i]);
        }
    }

    // Determina si el juego debe continuar o parar.
    public static boolean continueGame(String[] hiddenWord, String[] stringWord, int numOfFails, String register, String word){
        hangManDrawing(numOfFails);
        System.out.print("      " + register);
        if(Arrays.equals(hiddenWord, stringWord)){
            System.out.println("\n\nGanaste !!");
            return false;
        } else if (numOfFails == 7) {
            System.out.print("\n\nPerdiste !! La palabra secreta es: ");
            System.out.println(word);
            return false;
        } else {
            return true;
        }
    }

    // Identifica si hubo o no error.
    public static boolean mistake(String[] stringWord, String letter){
        for (String s : stringWord) {
            if (s.equals(letter)) {
                return false;
            }
        }
        return true;
    }

    public static void startGame(){
        String[] words = {"humanidad", "humano", "persona", "gente", "hombre", "mujer", "adolescente", "caballero",
        "dama", "cuerpo", "pierna", "cabeza", "cara", "abdomen", "salud", "familia", "viajes", "dinero"};
        String word = randomWord(words);
        String[] stringWord = wordToArray(word);
        String[] hiddenWord = hiddenWord(word);
        boolean stateGame = true;
        int numOfFails = 0;
        String register = "";

        while (stateGame) {
            Scanner input = new Scanner(System.in);
            System.out.println();
            String letter = input.nextLine().toLowerCase(Locale.ROOT);
            System.out.println("User try the letter " + letter);
            showWord(hiddenWord, stringWord, letter);
            if (mistake(stringWord, letter)){
                numOfFails += 1;
            }
            register += letter;
            stateGame = continueGame(hiddenWord, stringWord, numOfFails, register, word);
        }
    }

   
    
}
