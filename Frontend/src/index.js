import React from "react";
import ReactDOM from "react-dom/client";
import "./index.css";
import Login from "./pages/Login";
import "bootstrap/dist/css/bootstrap.min.css";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import MainPage from "./pages/MainPage";

const root = ReactDOM.createRoot(document.getElementById("root"));
root.render(
  <Router>
    <Routes>
      <Route path="/login" element={<Login />} />
      <Route path="/" element={<MainPage />} />
    </Routes>
  </Router>
);
