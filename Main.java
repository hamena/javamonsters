public class Main{

    public static void main(String[] args){

	Mounstro m1 = new Mounstro("Ortega", 100, 10, 20, 4, 0.1f, 0.9f, 0.15f, 0.5f);
	Mounstro m2 = new Mounstro("Pacheco", 100, 10, 20, 4, 0.1f, 0.9f, 0.15f, 0.5f);

	Arena arena = new Arena();
	arena.luchar(m1,m2);
    }
}
