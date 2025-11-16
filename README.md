Student Name: Sneha Gupta

Roll Number: 2401010020

Department: SOET
Session: 2025–2026
Program: B.Tech (Computer Science Engineering)
Semester: III
Course Name: Java Programming
Faculty: Lucky Kumar
Assignment – 3 (Unit 3)
Total Marks: 10
Submission Date: 25 Nov, 2025


# 📚 City Library Digital Management System

### 🎯 Project Overview
The City Central Library aims to digitize its book and member management process using Java.  
This project allows efficient handling of books, members, and transactions with **file handling** and **collections**.

---

### 🧱 Features
- Add new books and members
- Issue and return books
- Store and retrieve data from text files (`books.txt` and `members.txt`)
- Search and sort books using `Comparable` and `Comparator`
- Use of Java Collections: `List`, `Set`, `Map`
- Data persistence using File I/O streams

---

### 🏗️ Class Design

#### **Book.java**
- Handles individual book details and status  
- Attributes: `bookId`, `title`, `author`, `category`, `isIssued`

#### **Member.java**
- Manages library members and their issued books  
- Attributes: `memberId`, `name`, `email`, `issuedBooks (List<Integer>)`

#### **LibraryManager.java**
- Controls all operations: add/search/sort/issue/return  
- Uses Maps to store books and members  
- Handles file I/O for persistent storage

---

### ⚙️ How to Run

1. Open Command Prompt / Terminal.
2. Navigate to the folder:
   ```bash
   cd CityLibraryDigitalManagementSystem
