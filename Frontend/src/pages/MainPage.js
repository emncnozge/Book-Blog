import React, { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import Navbar from "../components/Navbar";
import Card from "../components/Card";
export default function MainPage() {
  const navigate = useNavigate();
  const [books, setBooks] = useState([]);
  const [loggedIn, setLoggedIn] = useState(false);

  useEffect(() => {
    setLoggedIn(window.localStorage.getItem("loggedIn"));
    if (!window.localStorage.getItem("loggedIn"))
      navigate("login", { replace: true });
    const axios = require("axios");
    axios
      .get("/api/book/last20")
      .then((response) => response.data)
      .then((data) => setBooks(data));
    setLoggedIn(window.localStorage.getItem("loggedIn"));
  }, [navigate]);
  if (loggedIn)
    return (
      <>
        <Navbar />
        <div className="container-fluid">
          <div className="row">
            <p className="altbaslik">Son eklenenler</p>
            {books?.map((book) => {
              return <Card key={book.book_id} type="book" data={book} />;
            })}
          </div>
        </div>
      </>
    );
}
