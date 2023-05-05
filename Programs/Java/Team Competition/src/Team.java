import javax.swing.*;

public class Team {

    private String name;
    private String location;
    private int nWins;
    private int nLosses;
    private double offense;
    private double defense;

    public Team (String name, String location) {
        this.name = name;
        this.location = location;
        this.nWins = 0;
        this.nLosses = 0;
        this.offense = Team.luck();
        this.defense = Team.luck();
    }

    public int calcTotalGames () {
        return nWins + nLosses;
    }

    public double calcWinRate () {
        return (double)this.getN_Wins() / (double)this.calcTotalGames() * 100;
    }

    public double calcLossRate () {
        return (double)this.getN_Losses() / (double)this.calcTotalGames() * 100;
    }

    public int calcDifference () {
        return Math.abs(this.getN_Wins() - this.getN_Losses());
    }

    public String generateStats () {
        String stats = "";

        stats += this.getName() +" ("+ this.getLocation() +")\n";
        stats += "Total games: "+ this.calcTotalGames() +"\n";
        stats += "No. wins: "+ this.getN_Wins() +" ("+ String.format("%.2f", this.calcWinRate()) +"%)\n";
        stats += "No. losses: "+ this.getN_Losses() +" ("+ String.format("%.2f", this.calcLossRate()) +"%)\n";
        stats += "Difference: "+ this.calcDifference() +"\n";

        return stats;
    }

    public static double luck () {
        return Math.random();
    }

    public static Team play (Team team1, boolean isHome, Team team2) {
        Team homeTeam;
        Team awayTeam;

        if (isHome) {
            homeTeam = team1;
            awayTeam = team2;
        } else {
            homeTeam = team2;
            awayTeam = team1;
        }

        double homeScore = (homeTeam.getOffense() + homeTeam.getDefense() + 0.2) * Team.luck();
        double awayScore = (awayTeam.getOffense() + awayTeam.getDefense()) * Team.luck();

        if (homeScore > awayScore) { //homeTeam wins
            homeTeam.incrementN_Wins();
            awayTeam.incrementN_Losses();

            return homeTeam;
        } else { //awayTeam wins
            homeTeam.incrementN_Losses();
            awayTeam.incrementN_Wins();

            return awayTeam;
        }
    }

    public String getName () {
        return this.name;
    }

    public String getLocation () {
         return this.location;
    }

    public int getN_Wins () {
        return this.nWins;
    }

    public int getN_Losses () {
        return this.nLosses;
    }

    public double getOffense () {
        return this.offense;
    }

    public double getDefense () {
        return this.defense;
    }

    public void incrementN_Wins () {
        this.nWins++;
    }

    public void incrementN_Losses () {
        this.nLosses++;
    }

    public static String[] refine (String s) {
        String[] refinedInput = s.split(","); //split input on comma(,)

        for (int i=0; i<refinedInput.length; i++) { //loop through each word from input
            refinedInput[i] = refinedInput[i].trim().toLowerCase(); //get rid of leading/trailing spaces - send to lowercase

            String[] pieces = refinedInput[i].split(" "); //split refinedInput on space( ) [so as to get each individual piece within]
            refinedInput[i] = ""; //clear refinedInput of all pieces

            for (int j=0; j<pieces.length; j++) { //loop through each 'piece'

                if (pieces[j].length() > 0) { //make sure piece exists
                    pieces[j] = pieces[j].trim(); //get ride of all leading/trailing spaces
                    pieces[j] = Character.toUpperCase(pieces[j].charAt(0)) + pieces[j].substring(1); //capitalize first letter

                    refinedInput[i] += pieces[j]; //add piece back into refinedInput

                    if (j<pieces.length-1) { //if another word comes next
                        refinedInput[i] += " "; //separate the words with a space
                    }
                }

            }
        }

        return refinedInput;
    }

    public static void main (String[] args) {
        String[] homeInfo = Team.refine(JOptionPane.showInputDialog(null, "Enter name and location for home team separated by a comma(,): ")); //get homeTeam
        Team homeTeam = new Team(homeInfo[0], homeInfo[1]);

        String[] awayInfo = Team.refine(JOptionPane.showInputDialog(null, "Enter name and location for away team separated by a comma(,): ")); //get awayTeam
        Team awayTeam = new Team(awayInfo[0], awayInfo[1]);

        JOptionPane.showMessageDialog(null, "Home team is: "+ homeTeam.getName() +" from "+ homeTeam.getLocation() +" rated "+ String.format("%.2f", homeTeam.getOffense()) +" (offense) "+ String.format("%.2f", homeTeam.getDefense()) +" (defense)."); //show homeTeam stats
        JOptionPane.showMessageDialog(null, "Away team is: "+ awayTeam.getName() +" from "+ awayTeam.getLocation() +" rated "+ String.format("%.2f", awayTeam.getOffense()) +" (offense) "+ String.format("%.2f", awayTeam.getDefense()) +" (defense)."); //show awayTeam stats

        for (int i=0; i<3; i++) { //play 3 games
            Team winner = Team.play(homeTeam, true, awayTeam);

            if (winner == homeTeam) {
                JOptionPane.showMessageDialog(null, "Round "+ (i+1) +"\nWinner is: "+ homeTeam.getName() +" from "+ homeTeam.getLocation() +" rated "+ String.format("%.2f", homeTeam.getOffense()) +" (offense) "+ String.format("%.2f", homeTeam.getDefense()) +" (defense).");
            } else if (winner == awayTeam){
                JOptionPane.showMessageDialog(null, "Round "+ (i+1) +"\nWinner is: "+ awayTeam.getName() +" from "+ awayTeam.getLocation() +" rated "+ String.format("%.2f", awayTeam.getOffense()) +" (offense) "+ String.format("%.2f", awayTeam.getDefense()) +" (defense).");
            }
        }

        JOptionPane.showMessageDialog(null, homeTeam.generateStats()); //homeTeam season outcome
        JOptionPane.showMessageDialog(null, awayTeam.generateStats()); //awayTeam season outcome
    }
}
