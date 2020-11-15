
package ohtu;

public class Player implements Comparable<Player>{
    private String name;
    private String team;
    private String nationality;
    private String birthdate;
    private int goals;
    private int assists;
    private int penalties;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public String getTeam() {
        return team;
    }

    public void setNationality(String nationality)  {
        this.nationality = nationality;
    }

    public String getNationality() {
        return nationality;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setGoals(int goals) {
        this.goals = goals;
    }

    public int getGoals() {
        return goals;
    }

    public void setAssists(int assists) {
        this.assists = assists;
    }

    public int getAssists() {
        return assists;
    }
    
    public void setPenalties(int penalties) {
        this.penalties = penalties;
    }

    public int getPenalties() {
        return penalties;
    }

    @Override
    public String toString() {
        StringBuilder palaute = new StringBuilder();
        palaute.append(String.format("%-20s", name));
        palaute.append(String.format("%-5s", team));
        palaute.append(String.format("%-3s", goals));
        palaute.append("+");
        palaute.append(String.format("%3s", assists));
        palaute.append(" =");
        palaute.append(String.format("%3s", assists+goals));
        return palaute.toString();
    }

    @Override
    public int compareTo(Player p) {
        int tama = this.getGoals() + this.getAssists();
        int toinen = p.getGoals() + p.getAssists();
        int cmp = Integer.compare(toinen, tama);
        return cmp;
    }
      
}
