package badocha.hubert;

import java.io.FileNotFoundException;

public class Main {

    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Program powinien zostać wywołany z jednym argumentem - ścieżką do pliku z danymi wejściowymi");
            System.exit(1);
        }
        try (Parser parser = new Parser(args[0])) {
            int[] generalData = parser.parseGeneralData();

            int n = generalData[0];
            int p = generalData[1];
            int d = generalData[2];
            int c = generalData[3];

            int[][] mergedConstituencies = parser.parseMergedConstituencies();
            String[] partiesNames = parser.parsePartiesNames(p);
            int[] partiesBudgets = parser.parsePartiesBudgets(p);
            StrategyEnum[] partiesStrategies = parser.parsePartiesStrategies(p);
            int[] votersInConstituency = parser.parseNumberOfVoters(n);
            SingleConstituency[] constituencies = parser.parseConstituencies(
                    n,
                    c,
                    votersInConstituency,
                    p
            );

            Action[] actions = parser.parseActions(d, c);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
}
