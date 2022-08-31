import React from "react";
import ReactDOM from "react-dom/client";
import "./index.css";
import Login from "./pages/Login";

import Authors from "./pages/Authors";
import Books from "./pages/Books";
import Profile from "./pages/Profile";
import Book from "./pages/Book/[id]";
import Author from "./pages/Author/[id]";
import FavoriteAuthors from "./pages/FavoriteAuthors";
import FavoriteBooks from "./pages/FavoriteBooks";
import FavoriteUsers from "./pages/FavoriteUsers";
import AdminAuthors from "./pages/Admin/Authors/Authors";
import AdminAddAuthor from "./pages/Admin/Authors/AddAuthor";
import AdminEditAuthor from "./pages/Admin/Authors/EditAuthor/[id]";
import AdminBooks from "./pages/Admin/Books/Books";
import AdminAddBook from "./pages/Admin/Books/AddBook";
import AdminEditBook from "./pages/Admin/Books/EditBook/[id]";
import AdminUsers from "./pages/Admin/Users/Users";
import AdminEditUser from "./pages/Admin/Users/EditUser/[id]";
import AddEntry from "./pages/Entry/AddEntry";
import Entry from "./pages/Entry/[id]";
import EditEntry from "./pages/Entry/EditEntry";
import User from "./pages/User/[id]";
import "bootstrap/dist/css/bootstrap.min.css";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import MainPage from "./pages/MainPage";

const root = ReactDOM.createRoot(document.getElementById("root"));
root.render(
  <Router>
    <Routes>
      <Route path="/" element={<MainPage />} />
      <Route path="/authors" element={<Authors />} />
      <Route path="/books" element={<Books />} />
      <Route path="/login" element={<Login />} />
      <Route path="/profile" element={<Profile />} />
      <Route path="/favoriteauthors" element={<FavoriteAuthors />} />
      <Route path="/favoritebooks" element={<FavoriteBooks />} />
      <Route path="/favoriteusers" element={<FavoriteUsers />} />
      <Route path="/book/:id" element={<Book />} />
      <Route path="/author/:id" element={<Author />} />
      <Route path="/user/:id" element={<User />} />
      <Route path="/admin/authors" element={<AdminAuthors />} />
      <Route path="/admin/addauthor/" element={<AdminAddAuthor />} />
      <Route path="/admin/editauthor/:id" element={<AdminEditAuthor />} />
      <Route path="/admin/books" element={<AdminBooks />} />
      <Route path="/admin/addbook/" element={<AdminAddBook />} />
      <Route path="/admin/editbook/:id" element={<AdminEditBook />} />
      <Route path="/admin/users" element={<AdminUsers />} />
      <Route path="/addentry/:id" element={<AddEntry />} />
      <Route path="/book/:id/entry/:id" element={<Entry />} />
      <Route path="/book/:id/editentry/:id" element={<EditEntry />} />
      <Route path="*" element={<MainPage />} />
    </Routes>
  </Router>
);
