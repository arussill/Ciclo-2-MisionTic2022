public class App {
    public static void main(String[] args) throws Exception {
        
        SchoolGradingSystem estudiante = new SchoolGradingSystem();;
        estudiante.readData();
        System.out.println((Math.round(estudiante.question1()*100)/100.0));
        System.out.println(estudiante.question2());
        System.out.println(estudiante.question3());
        System.out.println(estudiante.question4());
    }
}

