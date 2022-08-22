import React from "react";
import ReactDOM from "react-dom/client";
import "./index.css";
import Login from "./pages/Login";
import Authors from "./pages/Authors";
import Books from "./pages/Books";
import Profile from "./pages/Profile";
import Book from "./pages/Book/[id]";
import Author from "./pages/Author/[id]";
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
      <Route path="/book/:id" element={<Book />} />
      <Route path="/author/:id" element={<Author />} />
      <Route path="/user/:id" element={<User />} />
      <Route path="*" element={<MainPage />} />
    </Routes>
  </Router>
);
