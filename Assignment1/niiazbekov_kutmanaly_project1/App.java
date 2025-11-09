public class App {
    public static void main(String[] args) throws Exception {
        Soil soil = new Soil("sample1.txt");

        if (soil.doesDrain()) 
        {
            System.out.println("Allows water to drain");
        } 
        else 
        {
            System.out.println("Doesn't allow water to drain");
        }
    }
}