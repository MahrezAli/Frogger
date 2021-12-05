package gameCommons;

public class Chrono {

    private long tempsDepart = 0;
    private long tempsFin = 0;
    private long duree = 0;

    public void start() {
        tempsDepart = System.currentTimeMillis();
        tempsFin = 0;
        duree = 0;
    }

    public void stop(){
        if(tempsDepart == 0) {
            return;
        }
        tempsFin = System.currentTimeMillis();
        duree = (tempsFin - tempsDepart);
        tempsDepart = 0;
        tempsFin = 0;
    }

    public long getDureeSec(){
        return duree / 1000;
    }


}