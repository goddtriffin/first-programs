public class ArrayBuilder {

    private char[][] letterArray;
    private char baseLetter;

    public ArrayBuilder (char baseLetter, int n, int m) {
        //handle user input error
        if (baseLetter < 65 || (baseLetter > 90 && baseLetter < 97) || baseLetter > 122) {
            System.out.println("The input 'baseLetter' ("+ baseLetter +") is not an uppercase, nor lowercase english alphabet letter.");
            return;
        }

        this.baseLetter = baseLetter;

        //handle user input error
        if (n < 0) {
            System.out.println("The input 'n' ("+ n +") is a negative number, which is not allowed due to array initialization.");
            return;
        }

        if (m < 0) {
            System.out.println("The input 'm' ("+ m +") is a negative number, which is not allowed due to array initialization.");
            return;
        }

        this.letterArray = new char[n][m];
    }

    public void build () {
        if (baseLetter == 0) {
            System.out.println("The char 'baseLetter' was not initialized properly, therefore cannot proceed to 'build'.");
            return;
        }

        if (letterArray == null) {
            System.out.println("The array 'letterArray' was not initialized properly, therefore cannot proceed to 'build'.");
            return;
        }

        for (int n=0; n<letterArray.length; n++) {
            for (int m=0; m<letterArray[n].length; m++) {
                int nextChar = baseLetter + n + m;

                if (baseLetter < 91) { //uppercase letter
                    nextChar = ((nextChar - 65) % 26) + 65;
                } else { //lowercase letter
                    nextChar = ((nextChar - 97) % 26) + 97;
                }

                letterArray[n][m] = (char)nextChar;
            }
        }
    }

    public char[][] getLetterArray() {
        return this.letterArray.clone();
    }

    public void printLetterArray () {
        if (letterArray == null) {
            System.out.println("The array 'letterArray' was not initialized properly, therefore cannot proceed to 'printLetterArray'.");
            return;
        }

        for (int n=0; n<letterArray.length; n++) {
            for (int m=0; m<letterArray[n].length; m++) {
                System.out.print("|"+ letterArray[n][m]);
            }
            System.out.println("|");
        }
    }

    public static void main (String[] args) {
        ArrayBuilder ab;

        ab = new ArrayBuilder('w', 5,5);
        ab.build();
        ab.printLetterArray();
    }
}
