public class App {
    public static void main(String[] args) throws Exception {
        SchoolGradingSystem estudiante = new SchoolGradingSystem();;
        estudiante.loadData();
        System.out.println((Math.round(estudiante.stat1()*100)/100.0));
        System.out.println(estudiante.stat2());
        System.out.println(estudiante.stat3());
        System.out.println(estudiante.stat4());
    }
}

