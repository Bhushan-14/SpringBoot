<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.entity.Book" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/6.0.0/mdb.min.css" rel="stylesheet" />
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <style>
        body {
            font-family: 'Open Sans', sans-serif;
            background: #6a11cb;
            background: -webkit-linear-gradient(to right, rgba(106, 17, 203, 1), rgba(37, 117, 252, 1));
            background: linear-gradient(to right, rgba(106, 17, 203, 1), rgba(37, 117, 252, 1));
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }
        .card {
            border-radius: 1rem;
            background-color: #343a40;
            color: #fff;
            padding: 2rem;
            width: 100%;
            max-width: 500px;
        }
        .card-body {
            text-align: center;
        }
        .form-outline {
            margin-bottom: 1.5rem;
            position: relative;
        }
        .form-control-lg {
            padding: 1rem;
            font-size: 1rem;
        }
        .form-label {
            position: absolute;
            top: 0;
            left: 0;
            padding: 0.75rem 1rem;
            transition: 0.2s;
        }
        .form-control-lg:focus + .form-label,
        .form-control-lg:not(:placeholder-shown) + .form-label {
            top: -1rem;
            left: 0;
            font-size: 0.75rem;
            color: #aaa;
        }
        .btn-custom {
            background-color: #007bff;
            border: none;
            color: #fff;
            padding: 0.75rem 1.5rem;
            font-size: 1rem;
            border-radius: 0.5rem;
            cursor: pointer;
            transition: background-color 0.3s;
        }
        .btn-custom:hover {
            background-color: #0056b3;
        }
        .text-white {
            color: #fff !important;
        }
    </style>
</head>
<body>
    <div class="card">
        <div class="card-body">
            <h2 class="fw-bold mb-2 text-uppercase">Update Book Details</h2>
            <form action="UpdateBookServlet" method="post">
                <% 
                    Book book = (Book) request.getAttribute("book");
                    if (book != null) {
                %>
                <input type="hidden" name="bookId" value="<%= book.getBookId() %>" />
                <div class="form-outline form-white mb-4">
                    <input type="text" id="bookTitle" name="title" class="form-control form-control-lg" value="<%= book.getBookTitle() %>" required />
                    <label class="form-label" for="bookTitle">Book Title</label>
                </div>
                <div class="form-outline form-white mb-4">
                    <input type="text" id="bookAuthor" name="author" class="form-control form-control-lg" value="<%= book.getAuthor() %>" required />
                    <label class="form-label" for="bookAuthor">Author</label>
                </div>
                <div class="form-outline form-white mb-4">
                    <input type="number" id="bookPrice" name="price" class="form-control form-control-lg" value="<%= book.getPrice() %>" step="0.01" required />
                    <label class="form-label" for="bookPrice">Price</label>
                </div>
                <button type="submit" class="btn btn-custom btn-lg">Update Book</button>
                <% 
                    } else {
                        out.println("Book not found.");
                    }
                %>
            </form>
        </div>
    </div>
</body>
</html>
