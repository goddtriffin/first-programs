public class Movie {

    private String name;
    private double criticRating;
    private double usersRating;
    private int numUsersRatings;

    public Movie (String name, double criticRating, double usersRating, int numUsersRatings) {
        this.name = name;

        if (criticRating<1 || criticRating>5){
            this.criticRating = 5.0;
        } else {
            this.criticRating = criticRating;
        }

        if (usersRating<1 || usersRating>5){
            this.usersRating = 5.0;
        } else {
            this.usersRating = usersRating;
        }

        this.numUsersRatings = numUsersRatings;
    }

    public String getName () {
        return this.name;
    }

    public double getCriticRating () {
        return this.criticRating;
    }

    public double getUsersRating () {
        return this.usersRating;
    }

    public int getNumUsersRatings () {
        return this.numUsersRatings;
    }

    public boolean addUserRating (int newRating) {
        if (newRating >= 1 && newRating <= 5) {

            double totalRating = usersRating * numUsersRatings;
            totalRating += newRating;

            numUsersRatings++;

            usersRating = totalRating / numUsersRatings;

            return true;
        }

        return false;
    }

    public static int compareMovies (Movie movie1, Movie movie2) {
        //if m1 > m2, return 1
        //if m1 < m2, return 2
        //return 0

        //condition 1
        if (movie1.getCriticRating() > movie2.getCriticRating()) { // 1: >critic; >=user = better

            if (movie1.getUsersRating() >= movie2.getUsersRating()) {
                return 1;
            }

        } else if (movie2.getCriticRating() > movie1.getCriticRating()) { // 2: >critic; >=user = better

            if (movie2.getUsersRating() >= movie1.getUsersRating()) {
                return 2;
            }

        }

        //condition 2
        if ( (movie1.getCriticRating() > movie2.getCriticRating()) && (movie1.getUsersRating() < movie2.getUsersRating()) || ((movie2.getCriticRating() > movie1.getCriticRating()) && (movie2.getUsersRating() < movie1.getUsersRating())) ) { // >critic && <user; >smartScore = better

                double smartScore1 = 0.5 * movie1.getCriticRating() + 0.3 * movie1.getUsersRating() + 0.1 * getReviewRange(movie1.getNumUsersRatings());
                double smartScore2 = 0.5 * movie2.getCriticRating() + 0.3 * movie2.getUsersRating() + 0.1 * getReviewRange(movie2.getNumUsersRatings());

                if (smartScore1 > smartScore2) {
                    return 1;
                }

                if (smartScore2 > smartScore1) {
                    return 2;
                }

        }

        //condition 3
        if (movie1.getCriticRating() == movie2.getCriticRating()) { // =critic; >user = better

            if (movie1.getUsersRating() > movie2.getUsersRating()) {
                return 1;
            }

            if (movie2.getUsersRating() > movie1.getUsersRating()) {
                return 2;
            }
        }

        return 0;
    }

    private static int getReviewRange (int numUsersRating) {
        if (numUsersRating >= 0 && numUsersRating <=1000) {
            return 1;
        }

        if (numUsersRating >= 1001 && numUsersRating <=5000) {
            return 2;
        }

        if (numUsersRating >= 5001 && numUsersRating <=10000) {
            return 3;
        }

        if (numUsersRating >= 10001 && numUsersRating <=15000) {
            return 4;
        }

        if (numUsersRating >= 15001 && numUsersRating <=20000) {
            return 5;
        }

        if (numUsersRating >= 20001 && numUsersRating <=25000) {
            return 6;
        }

        if (numUsersRating >= 25001 && numUsersRating <=30000) {
            return 7;
        }

        if (numUsersRating >= 30001 && numUsersRating <=50000) {
            return 8;
        }

        if (numUsersRating >= 50001 && numUsersRating <=100000) {
            return 9;
        }

        if (numUsersRating > 100000) {
            return 10;
        }

        return 0;
    }
}
