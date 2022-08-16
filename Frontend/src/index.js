import React from "react";
import ReactDOM from "react-dom/client";
import "./index.css";
import Login from "./pages/Login";
import Authors from "./pages/Authors";
import Books from "./pages/Books";
import Profile from "./pages/Profile";
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
      <Route path="*" element={<MainPage />} />
    </Routes>
  </Router>
);
