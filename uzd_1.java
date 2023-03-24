// Antanas Vasiliauskas 3 grupe 1 uzduotis 3 variantas
// Faile saugomi knygu aprasai. Vartotojui ivedus autoriaus varda, programa atspausdina to autoriaus knygas.
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class uzd_1{
    public static void main(String[] args){
        System.out.println("This program prints books of specified author.");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter library data file name:");
        String fileName = scanner.nextLine().trim();
        System.out.println("Enter author title:");
        String author = scanner.nextLine().trim();

        ArrayList<Book> books = new ArrayList<Book>();
        try{
            FileReader fr = new FileReader(fileName);
            BufferedReader br = new BufferedReader(fr);
            String line;
            while ((line = br.readLine()) != null) {
                String[] arr = line.split("\\|");
                Book book = new Book();
                book.setAuthor(arr[0].trim());
                book.setTitle(arr[1].trim());
                book.setYear(Integer.parseInt(arr[2].trim()));
                books.add(book);
            }
            br.close();

            boolean found = false;
            System.out.println("\nAuthor " + author + " books:");
    
            for(Book book: books){
                if(book.getAuthor().equals(author)){
                    found = true;
                    System.out.println("    " + book.getTitle());
                }
            }
            if(!found){
                System.out.println("    Books not found.");
            }
        }
        catch(FileNotFoundException e){
            //System.out.println("File with specified name not found.");
            System.out.println(e.getMessage());
        }
        catch(IOException e){
            //System.out.println("An error occured while reading from file.");
            System.out.println(e.getMessage());
        }

    }
}