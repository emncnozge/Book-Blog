import React, { useState, useEffect } from "react";
import { Link, useNavigate } from "react-router-dom";
import Navbar from "../components/Navbar";
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
              return (
                <div
                  key={book.book_id}
                  className="col-12 col-sm-6 col-md-4 col-lg-3 col-xl-2"
                >
                  <Link to={"/book/" + book.book_id} className="book">
                    <div className="card">
                      <div className="img-top">
                        <img
                          alt="book_photo"
                          src={"/api/book/photo/" + book.book_id}
                          className="image"
                        />
                      </div>

                      <div className="card-body">
                        <h5 className="card-title">{book.name}</h5>
                      </div>
                    </div>
                  </Link>
                </div>
              );
            })}
          </div>
        </div>
      </>
    );
}
