import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class uzd_1{
    public static void main(String[] args){
        System.out.println("This program prints books of specified author.");
        Scanner scanner = new Scanner(System.in);  // Create a Scanner object
        System.out.println("Enter library data file name:");
        String fileName = scanner.nextLine().trim();  // Read user input
        System.out.println("Enter author title:");
        String author = scanner.nextLine().trim();  // Read user input

        FileReader fr;
        BufferedReader br;
        ArrayList<Book> books = new ArrayList<Book>();
        try{
            fr = new FileReader(fileName);
            br = new BufferedReader(fr);
            String line;
            while ((line = br.readLine()) != null) {
                
                String[] arr = line.split("\\|");
                Book book = new Book();
                book.author = arr[0].trim();
                book.title = arr[1].trim();
                book.year = Integer.parseInt(arr[2].trim());
                books.add(book);
            }
            br.close();

            boolean found = false;
            System.out.println("\nAuthor " + author + " books:");
    
            for(Book book: books){
                if(book.author.equals(author)){
                    found = true;
                    System.out.println("    " + book.title);
                }
            }
            if(!found){
                System.out.println("    Books not found.");
            }
        }
        catch(FileNotFoundException e){
            System.out.println("File with specified name not found.");
            System.out.println(e.getMessage());
        }
        catch(IOException e){
            System.out.println("An error occured while reading from file.");
            System.out.println(e.getMessage());
        }

    }
}