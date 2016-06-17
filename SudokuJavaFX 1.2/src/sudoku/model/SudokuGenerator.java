
package sudoku.model;

public class SudokuGenerator {

	public static void main(String[] args) {
		generate(40);
	}
	
	
	
	public static Sudoku generate(int numberOfClues) {

		// IntArray mit leeren Feldern erzeugen
		int[][] arraySudoku = new int[9][9];

		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {

				arraySudoku[i][j] = 0;

			}

		}
		
		int[][] copy = copySudokuArray(arraySudoku);
		Sudoku objectSudoku = new Sudoku(copy);
		int enteredFields = 0;

		while (enteredFields < numberOfClues ) {

			int xKoord = (int) Math.floor((Math.random() * 9));
			int yKoord = (int) Math.floor((Math.random() * 9));
			int digit = (int) (Math.floor((Math.random() * 9)) + 1);

			// Wenn Feld frei ist -> muss eine L�sung haben, da es im Schritt davor l�sbar war -> alle Zahlen durchprobieren (f�r Performance)
			if (arraySudoku[xKoord][yKoord] == 0) {

				boolean filledPos = false;

				while (!filledPos) {

					filledPos = true;
					arraySudoku[xKoord][yKoord] = digit;
					
					copy = copySudokuArray(arraySudoku);
			

					// entspricht nicht den Sudoku Regeln -> n�chste Zahl probieren
					if (!objectSudoku.setSudoku(copy)) {
						digit = moduloHochzaehlen(digit);
						filledPos = false;
					} else {
						//Pr�ft L�sbarkeit
						objectSudoku.solve();

						// Ist so nicht l�sbar -> n�chste Zahl probieren
						if (!objectSudoku.filled()) {
							digit = moduloHochzaehlen(digit);
							filledPos = false;
							
						}

					}
					
					if(filledPos)
						enteredFields++;
				}
				
				
			}

		}

		return new Sudoku(arraySudoku);

	}

	public static int moduloHochzaehlen(int zahl) {
		zahl = (zahl+1) % 10;
		if(zahl==0) zahl++;
		return zahl;
	}

	private static int[][] copySudokuArray(int[][] array) {
		int[][] arrayret = new int[9][9];

		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				arrayret[i][j] = array[i][j];

			}

		}

		return arrayret;
	}

}
