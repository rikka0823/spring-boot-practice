package com.rikka.springBootPractice.controller;

import com.rikka.springBootPractice.entity.Book;
import com.rikka.springBootPractice.model.BookDto;
import com.rikka.springBootPractice.service.BookService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebServlet("/servletTest")
@RestController
public class BookController extends HttpServlet {

    Logger logger = LoggerFactory.getLogger(BookController.class);

    @Autowired
    private BookService bookService;

    @PostMapping("/books")
    public ResponseEntity<Book> createBook(@RequestBody @Valid BookDto bookDto) {
        Integer bookId = bookService.createBook(bookDto);
        Book book = bookService.getBookById(bookId);

        return ResponseEntity.status(HttpStatus.CREATED).body(book);
    }

    @GetMapping("/books/{bookId}")
    public ResponseEntity<Book> getBook(@PathVariable Integer bookId) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(bookService.getBookById(bookId));
    }

    @PutMapping("/books/{bookId}")
    public ResponseEntity<Book> updateBook(@PathVariable Integer bookId,
                                           @RequestBody @Valid BookDto bookDto) {
        bookService.updateBook(bookId, bookDto);
        return ResponseEntity.status(HttpStatus.OK).body(bookService.getBookById(bookId));
    }

    @DeleteMapping("/books/{bookId}")
    public ResponseEntity<?> deleteBook(@PathVariable Integer bookId) {
        try {
            bookService.deleteBookById(bookId);
        } catch (EmptyResultDataAccessException e) {
            logger.info(e.getMessage());
        }

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


    @GetMapping("/databaseConnAndSearch")
    public ResponseEntity<?> databaseConnAndSearch() {
        String conUrl = "jdbc:mysql://localhost:3306/spring_boot_practice?serverTimezone=Asia/Taipei&characterEncoding=utf-8";
        String user = "root";
        String password = "root";

        Scanner sc = new Scanner(System.in);
        System.out.print("Input:");
        try {
            String keyWord = sc.nextLine();

            Connection conn = DriverManager.getConnection(conUrl, user, password);

            String sql = "SELECT * FROM product WHERE product_name LIKE ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, "%" + keyWord + "%");

            ResultSet rs = pstmt.executeQuery();
            System.out.println("product_name\tprice");
            System.out.println("------");

            boolean hasRes = false;
            while (rs.next()) {
                hasRes = true;
                String productName = rs.getString("product_name");
                int price = rs.getInt("price");
                System.out.println(productName + "\t" + price);
            }

            if (!hasRes) {
                System.out.println("Not found");
            }

            rs.close();
            pstmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ResponseEntity.ok().build();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        res.setContentType("text/plain;charset=UTF-8");

        String productId = escape(req.getParameter("productId"));
        String productName = escape(req.getParameter("productName"));

        String conUrl = "jdbc:mysql://localhost:3306/spring_boot_practice?serverTimezone=Asia/Taipei&characterEncoding=utf-8";
        try {
            Connection conn = DriverManager.getConnection(conUrl, "root", "root");

            /*
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM product WHERE product_id = ? AND product_name = ?");
            pstmt.setString(1, productId);
            pstmt.setString(2, productName);

            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                res.getWriter().write(rs.getString("product_name") + ":\t" + rs.getInt(5));
            } else {
                res.getWriter().write("Not found");
            }
             */

            PreparedStatement pstmt = conn.prepareStatement("SELECT COUNT(*) AS count FROM product WHERE product_id = ? AND product_name = ?");
            pstmt.setString(1, productId);
            pstmt.setString(2, productName);

            ResultSet rs = pstmt.executeQuery();
            if (rs.next() && rs.getInt(1) > 0) {
                res.getWriter().write(String.valueOf(rs.getInt("count")));
            } else {
                res.getWriter().write("Not found");
            }

            rs.close();
            pstmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @GetMapping("/searchAndOutputTxt")
    public ResponseEntity<?> searchAndOutputTxt() {
        String conUrl = "jdbc:mysql://localhost:3306/spring_boot_practice?serverTimezone=Asia/Taipei&characterEncoding=utf-8";
        String user = "root";
        String password = "root";

        Scanner sc = new Scanner(System.in);
        System.out.print("Input:");
        try {
            String keyWord = sc.nextLine();

            Connection conn = DriverManager.getConnection(conUrl, user, password);

            String sql = "SELECT c.customer_name, cs.stock_id, s.stock_name, cs.shares_owned " +
                    "FROM customers AS c " +
                    "JOIN customer_stocks AS cs " +
                    "ON c.customer_id = cs.customer_id " +
                    "JOIN stocks AS s " +
                    "ON cs.stock_id = s.stock_id " +
                    "WHERE s.stock_name = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, keyWord);

            ResultSet rs = pstmt.executeQuery();
            BufferedWriter bw = new BufferedWriter(new FileWriter("C:\\output\\股票.txt"));;
            int line = 1;
            while (rs.next()) {
                bw.write(
                        line + "\t" +
                        rs.getString("customer_name") + "\t" +
                        rs.getString("stock_id") + "\t" +
                        rs.getString("stock_name") + "\t" +
                        rs.getInt("shares_owned") + "\n"
                );
                line++;
            }

            bw.close();
            rs.close();
            pstmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ResponseEntity.ok().build();
    }

    @PostMapping("/readFileAndInsert")
    public ResponseEntity<?> readFileAndInsert() throws SQLException {
        String conUrl = "jdbc:mysql://localhost:3306/spring_boot_practice?serverTimezone=Asia/Taipei&characterEncoding=utf-8";
        String user = "root";
        String password = "root";

        Connection conn = null;
        try {
            conn = DriverManager.getConnection(conUrl, user, password);
            conn.setAutoCommit(false);

            List<String[]> values = new ArrayList<>();

            BufferedReader br = new BufferedReader(new FileReader("C:\\source\\data.txt"));
            String line;
            while ((line = br.readLine()) != null) {
                String[] value = line.split(" - ");
                if (value.length != 5) {
                    continue;
                }
                values.add(value);
            }

            String sql = "INSERT INTO computer_data (processor_type, processor_speed, screen_size, weight, internal_memory) " +
                    "VALUES (?, ?, ?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            for (String[] value : values) {
                for (int i = 0; i < value.length; i++) {
                    pstmt.setString(i + 1, value[i].substring(value[i].indexOf(":") + 1));
                }
                pstmt.addBatch();
            }

            pstmt.executeBatch();
            conn.commit();

            pstmt.close();
            conn.close();
        } catch (Exception e) {
            if (conn != null) {
                conn.rollback();
            }
            e.printStackTrace();
        }

        return ResponseEntity.ok().build();
    }

    @PutMapping("/searchAndUpdate")
    public ResponseEntity<?> searchAndUpdate() throws SQLException {
        String conUrl = "jdbc:mysql://localhost:3306/spring_boot_practice?serverTimezone=Asia/Taipei&characterEncoding=utf-8";
        String user = "root";
        String password = "root";

        Connection conn = null;
        try {
            conn = DriverManager.getConnection(conUrl, user, password);
            conn.setAutoCommit(false);

            String selectSql = "SELECT * FROM inventory WHERE quantity < safety_stock";
            PreparedStatement selectPstmt = conn.prepareStatement(selectSql);

            System.out.println("product_id\tproduct_name\t");
            List<String> updateIds = new ArrayList<>();
            ResultSet rs = selectPstmt.executeQuery();
            while (rs.next()) {
                String productId = rs.getString("product_id");
                String productName = rs.getString("product_name");
                System.out.println(productId + "\t" + productName);
                updateIds.add(productId);
            }

            String updateSql = "UPDATE inventory " +
                    "SET quantity = quantity + reorder_amount " +
                    "WHERE product_id = ?";
            PreparedStatement updatePstmt = conn.prepareStatement(updateSql);
            for (String updateId : updateIds) {
                updatePstmt.setString(1, updateId);
                updatePstmt.addBatch();
            }

            updatePstmt.executeBatch();
            conn.commit();

            updatePstmt.close();
            rs.close();
            selectPstmt.close();
            conn.close();
        } catch (Exception e) {
            if (conn != null) {
                conn.rollback();
            }
            e.printStackTrace();
        }

        return ResponseEntity.ok().build();
    }

    @PutMapping("/filterAndUpdate")
    public ResponseEntity<?> filterAndUpdate() {
        String conUrl = "jdbc:mysql://localhost:3306/spring_boot_practice?serverTimezone=Asia/Taipei&characterEncoding=utf-8";
        String user = "root";
        String password = "root";

        try {
            Connection conn = DriverManager.getConnection(conUrl, user, password);

            String updateSql = "UPDATE employee " +
                    "SET salary = salary + " +
                    "  CASE " +
                    "    WHEN job_title = 'Manager' THEN 2000 "+
                    "    WHEN job_title = 'Deputy_Manager' THEN 1500 " +
                    "    ELSE 1000 " +
                    "  END";
            PreparedStatement pstmt = conn.prepareStatement(updateSql);
            pstmt.executeUpdate();

            String selectSql = "SELECT * FROM employee";
            Statement stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery(selectSql);
            while (rs.next()) {
                String eNo = rs.getString("e_no");
                String eName = rs.getString("e_name");
                String jobTitle = rs.getString("job_title");
                int salary = rs.getInt("salary");
                System.out.println(eNo + "\t" + eName + "\t" + jobTitle + "\t" + salary);
            }

            rs.close();
            stmt.close();
            pstmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ResponseEntity.ok().build();
    }

    private String escape(String str) {
        if (str == null) {
            return "";
        }
        return str.replace("&", "&amp")
                .replace("<", "&lt;")
                .replace(">", "&gt;");
    }
}
