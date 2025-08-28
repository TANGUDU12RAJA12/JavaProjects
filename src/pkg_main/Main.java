package pkg_main;

import java.util.Scanner;

import pkg_book.Book;
import pkg_book.BookManager;
import pkg_exception.BookNotFoundException;
import pkg_exception.StudentNotFoundException;
import pkg_person.Student;
import pkg_person.StudentManager;
import pkg_transaction.BookTransactionManager;


public class Main {

	public static void main(String[] args)  {
		int choice = 0 ;
		
		Scanner sc = new Scanner(System.in);
		
		BookManager bm =new BookManager();
		StudentManager sm = new StudentManager();
		BookTransactionManager btm = new BookTransactionManager(); 
		
		do {
			System.out.println(" Enter 1 if Student\n Enter 2 if Librarian\n Enter 3 if want to Exit:");	
			choice  = sc.nextInt();
			if(choice == 1) // if user is Student..
			{
				System.out.println("Enter your Roll Number");
				int rollNo = sc.nextInt();
				try {
					Student s = sm.get(rollNo);
					if(s == null) 
						throw new StudentNotFoundException();
					int stud_choice;
					do {
						System.out.println(" Enter 1 to View All Books \n Enter 2 To Search Book By ISBN  \n Enter 3 to List Books by Subject\n Enter 4 to Issue a Book \n Enter 5 return a Book\n Enter 99 to exit ");
						stud_choice = sc.nextInt();
						switch(stud_choice) {
						case 1:
							System.out.println("All the Book Records are");
							bm.viewAllBooks();
							break;
						case 2:
							System.out.println("Plase Enter ISBN to search");
							int search_isnb;
							System.out.println("*** Enter ISBN of the Book to Search ***");
							search_isnb = sc.nextInt();
							Book book = bm.searchBookByIsbn(search_isnb);
							if(book == null)
								System.out.println("No Book with this ISBN Exists in our Libary");
							else
								System.out.println(book);
							break;
						case 3:
							System.out.println("Enter The Subject");
							sc.nextLine();
							String search_subject = sc.nextLine();
							bm.listBooksBySubject(search_subject);
							break;
						case 4:
							System.out.println("Enter the ISBN to Issue a  Book");
							int issue_isbn = sc.nextInt();
							book  = bm.searchBookByIsbn(issue_isbn);
							try {
								if(book == null) {
								    throw new BookNotFoundException();
							    }
								if(book.getAvailable_quentity() > 0) {
									if(btm.issuBook(rollNo, issue_isbn)) {
										book.setAvailable_quentity(book.getAvailable_quentity()-1);
										System.out.println("Book has been Issued");
									}
								}
								else {
									System.out.println("The Book has been Isuued...");
								}
							}
							catch(BookNotFoundException bnfe) {
								System.out.println(bnfe);
							}
							
							
							break;
						case 5:
							System.out.println("Please Enter the ISBN to Return a Book");
							int return_isbn =sc.nextInt();
							book = bm.searchBookByIsbn(return_isbn);
							if(book != null) {
								if(btm.returnBook(rollNo, return_isbn)) {
									book.setAvailable_quentity(book.getAvailable_quentity()+1);
									System.out.println("Thank you for Returning the Book");
								}
								else
									System.out.println("Cloud Not Return The Book");
							}
							else {
								System.out.println("Book with this ISBN Does Not Exists");
							}
							
							break;
						case 99:
							System.out.println("Thank you for using library");
							break;
						default:
							System.out.println("Invalid choice");
							
						}
					}while(stud_choice != 99);	
				}
				catch(StudentNotFoundException se) {
					System.out.println(se);
				}
			}
			//for Librarian
			else if(choice == 2) {
				int lib_choice;
				do {
				
				System.out.println("Enter 11 to view all Student\nEnter 12 to Print a Student by Roll Number\nEnter 13 Register a Student\nEnter 14 to Update a Studen\nEnter 15 Delete a Student");
				System.out.println("Enter 21 to view all Books\nEnter 22 to Print a Book by ISBN\nEnter 23 to Add a New Book\nEnter 24 to Update a Book\nEnter 25 to Delete a Book ");
				System.out.println("Enter 31 to view all Transection");
				System.out.println("Enter 99 to exit");
				lib_choice = sc.nextInt();
				switch(lib_choice) {
				case 11://view all student 
					System.out.println("All the Students Records");
					sm.viewAllStudents();
					break;
				case 12://search a student base upon rollNumber..
					System.out.println("Enter Roll Number to Fetch Student's Record");
					int get_rollNo = sc.nextInt();
					Student student = sm.get(get_rollNo);
					if(student == null) {
						System.out.println("No record Matches this Roll Number");
					}
					else
						System.out.println(student);
					    break; 
				case 13://register a new student 
					System.out.println("Enter Student Details to Add");
					String name; 
					String emailaId; 
					String phoneNumber; 
					String address; 
					String dob; 
					int rollNo; 
					int std;
					String division;
					sc.nextLine();
					
					System.out.println("Name");
					name =sc.nextLine();
					
					System.out.println("EmailId");
					emailaId =sc.nextLine();
					
					System.out.println("PhoneNumber");
					phoneNumber =sc.nextLine();
					
					System.out.println("Address");
					address =sc.nextLine();
					
					System.out.println("DateOfBirth");
					dob =sc.nextLine();
					
					System.out.println("RollNumber as Integer");
					rollNo =sc.nextInt();
					
					System.out.println("Standed as Integer");
					std =sc.nextInt();
					
					sc.nextLine();
					
					System.out.println("Division");
					division =sc.nextLine();
					
					 student  =new Student( name,  emailaId, phoneNumber,  address,  dob, rollNo,  std,
								 division);
					sm.addStudent(student);
                    System.out.println("Student is Added");
                    break;
				case 14://updating a Student
					System.out.println("Enter The RollNumber to Update the Record");
					int modify_rollNo;
					modify_rollNo = sc.nextInt();
					student = sm.get(modify_rollNo);
					try {
					if(student == null)
						throw new StudentNotFoundException();
                   
					sc.nextLine();
					System.out.println("Name");
					name =sc.nextLine();
					
					System.out.println("EmailId");
					emailaId =sc.nextLine();
					
					System.out.println("PhoneNumber");
					phoneNumber =sc.nextLine();
					
					System.out.println("Address");
					address =sc.nextLine();
					
					System.out.println("DateOfBirth");
					dob =sc.nextLine();
					
					System.out.println("Standed as Integer");
					std =sc.nextInt();
					
					sc.nextLine();
					
					System.out.println("Division");
					division =sc.nextLine();
					
					sm.updateStudent(modify_rollNo, name, emailaId, phoneNumber, address, dob, std, division);
					System.out.println("Student record is Updated");
					
					}
					catch(StudentNotFoundException se) {
						System.out.println(se);
					}
					break;	
				case 15://Delete a Student...
					System.out.println("Enter The RollNumber to Delete the Record");
					int delete_rollNo;
					delete_rollNo = sc.nextInt();
					if(sm.deleteStudent(delete_rollNo))
						System.out.println("Student Record is Removed");
					else
						System.out.println("No Record with Given Roll Number Exists");
					break;
				case 21://view all books
					bm.viewAllBooks();
					break;
				case 22://Searching by ISBN
					int search_isnb;
					System.out.println("*** Enter ISBN of the Book to Search ***");
					search_isnb = sc.nextInt();
					Book book = bm.searchBookByIsbn(search_isnb);
					if(book == null)
						System.out.println("No Book with this ISBN Exists in our Libary");
					else
						System.out.println(book);
				case 23://add a book in to library
					System.out.println("Plase Enter all the Book details to Add");
					int isbn;
				    String title;
				    String publisher;
				    int edition;
				    String subject;
				    int available_quentity;
				    System.out.println("ISBN");
				    isbn  = sc.nextInt();
				    sc.nextLine();
				    System.out.println("Title");
				    title = sc.nextLine();
				    System.out.println("Publisher");
				    publisher = sc.nextLine();
				    System.out.println("Edition ");
				    edition = sc.nextInt();
				    sc.nextLine();
				    System.out.println("Subject");
				    subject = sc.nextLine();
				    
				    System.out.println("Quentity");
				    available_quentity =sc.nextInt();
				    
				    book = new Book(isbn, title,publisher, edition,subject,available_quentity);
				    bm.addBook(book);
				    System.out.println("A book Record is Added");
				    break;
				case 24://update a record of book..
					System.out.println("Plase Enter the ISBN to update the record");
					int update_isbn;
					update_isbn = sc.nextInt();
					try {
						book = bm.searchBookByIsbn(update_isbn);
						if(book == null)
							throw new BookNotFoundException();
						System.out.println("Enter Book Details to Modify");
					    sc.nextLine();
					    System.out.println("Title");
					    title = sc.nextLine();
					    System.out.println("Publisher");
					    publisher = sc.nextLine();
					    System.out.println("Edition ");
					    edition = sc.nextInt();
					    sc.nextLine();
					    System.out.println("Subject");
					    subject = sc.nextLine();
					    
					    System.out.println("Quentity");
					    available_quentity =sc.nextInt();
					    
					    bm.updateBook(update_isbn, update_isbn, title, publisher, edition, subject, available_quentity);
					}
					catch(BookNotFoundException bnfe){
						System.out.println(bnfe);
					}
					
					break;
				case 25://delete a record of book 
					
					System.out.println("Plase Enter the ISBN to Delete the record");
					int delete_isbn;
					delete_isbn = sc.nextInt();
					try {
						book = bm.searchBookByIsbn(delete_isbn);
						if(book == null)
							throw new BookNotFoundException();
						bm.deleteBook(delete_isbn);
						System.out.println("Record Deleted");
						}
					catch(BookNotFoundException bnfe){
						System.out.println(bnfe);
					}
					
					break;
				case 31://To view All transaction
					System.out.println("All the Transection are");
					btm.showAll();
					
				case 99:
					System.out.println("Thank you for using library");
					break;
				default:
					System.out.println("Invalid choice");
					
				}
			}while(lib_choice != 99);
			
			}
			
		}while(choice !=3);
         sm.writeToFile();
         bm.writeToFile();
         btm.writeToFile();
         sc.close();
         }
      
}
