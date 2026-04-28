package Week1.Day2.HomeAssignmentDay2;

public class Library {

    public String addBook(String bookTitle){
        System.out.println("Book added successfully");
        return bookTitle;
    }

    public void issueBook(){
        System.out.println("Book issued successfully");
    }

    public static void main(String[] args) {

        Library library = new Library();
        String addedbook =library.addBook("Java Programming");
        System.out.println(addedbook);
        
        library.issueBook();
    }

}
