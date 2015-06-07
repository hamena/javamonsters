public class Main{

    public static void main(String[] args){

	Mounstro m1 = new Mounstro("Ortega", 100, 20, 15, 0.1f, 0.9f, 0.15f);
	Mounstro m2 = new Mounstro("Pacheco", 100, 20, 20, 0.2f, 0.9f, 0.25f);

	Arena arena = new Arena();
	arena.luchar(m1,m2);
    }
}
