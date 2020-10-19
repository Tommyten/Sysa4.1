// Cellular automaton
public class CAFirstAutomaton {
	// L�nge einer Zeile
	static int LENGTH;
	
	// Anzahl von Schritten, Zeitachse
	static int MAXTIME;
	
	// erster Index: Zeitachse, zweiter Index: eine komplette Zeile
	static int[][] S;

	static int[] Regel18 = new int[] {0, 1, 0, 0, 1, 0, 0, 0};
	static int[] Regel30 = new int[] {0, 1, 1, 1, 1, 0, 0, 0};
	static int[] Regel57 = new int[] {1, 0, 0, 1, 1, 1, 0, 0};
	// Regel für Ü4.1
	static int[] Regel62 = new int[] {0, 1, 1, 1, 1, 1, 0, 0};
	
	// Konstruktor: Felder f�r Zellen anlegen und mit Inhalt f�llen
	CAFirstAutomaton () {
		LENGTH = 10;
		MAXTIME = 300;
		S = new int[MAXTIME][LENGTH];
		// Initialisierung aller Zellen S
		for (int i = 0; i < LENGTH; i++) 
			S[0][i] = 0;

		// Initialisierung für Ü4.1
		S[0][1] = 1;
		S[0][3] = 1;
		S[0][5] = 1;
		S[0][6] = 1;

		printPopulation(0);
	}
	
	// Berechne f�r den Zeitschritt t den Inhalt der Zellen aus der Vorg�ngergeneration t-1
	static void nextGeneration(int t) {
		for (int i = 1; i < LENGTH-1; i++) {
			// Berechne Index f�r Regel aus Vorg�ngergeneration
			int index = 4*S[t-1][i-1] + 2*S[t-1][i] + S[t-1][i+1];
			// Wende die Regel an
			S[t][i]= Regel62[index];
		}
	}
	
	// Ausgabe auf Konsole
	static void printPopulation(int t) {
		for (int i = 0; i < LENGTH; i++) {
			if (S[t][i] == 1) System.out.print(" 1 "); 
			else System.out.print(" . ");
		}
		System.out.println();
	}

	// Ü4.1
	static void printValuesOfRows() {
		System.out.println();
		for (int t = 0; t < MAXTIME; t++) {
			int value = 0;
			for (int i = 0; i < LENGTH; i++) {
				value += (int) (S[t][i] * Math.pow(2, i));
			}
			System.out.println("Value for t = " + t + " = " + value);
		}
	}
	
	public static void main(String[] args) {
		new CAFirstAutomaton();
		// Erzeuge mehrere Generationen
		for (int t = 1; t < MAXTIME; t++) {
			nextGeneration(t);
			printPopulation(t);
		}
		// Ausgabe des Ergebnisses als gef�llte/nicht gef�llte Rechtecke auf dem Bildschirm
		RenderCA r = new RenderCA(S, LENGTH, MAXTIME);
		r.repaint();

		// Ü4.1
		printValuesOfRows();
	}

}
