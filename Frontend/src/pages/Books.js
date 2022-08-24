import React, { useState, useEffect } from "react";
import { Link, useNavigate } from "react-router-dom";
import Navbar from "../components/Navbar";
export default function Books() {
  const navigate = useNavigate();
  const [books, setBooks] = useState([]);
  const [loggedIn, setLoggedIn] = useState(false);
  const [search, setSearch] = useState("");

  useEffect(() => {
    setLoggedIn(window.localStorage.getItem("loggedIn"));
    if (!window.localStorage.getItem("loggedIn"))
      navigate("login", { replace: true });

    const axios = require("axios");
    axios
      .get("/api/book")
      .then((response) => response.data)
      .then((data) => setBooks(data));
  }, [navigate]);
  function turkishToLower(e) {
    let string = e;
    let letters = { İ: "i", I: "ı", Ş: "ş", Ğ: "ğ", Ü: "ü", Ö: "ö", Ç: "ç" };
    string = string.replace(/(([İIŞĞÜÇÖ]))/g, function (letter) {
      return letters[letter];
    });
    return string.toLowerCase();
  }
  if (loggedIn)
    return (
      <>
        <Navbar />
        <div className="container-fluid">
          <input
            type="text"
            className="form-control mb-3"
            style={{ width: "min(250px, 100vw)" }}
            placeholder="Kitap ara"
            onChange={(e) => setSearch(e.target.value)}
          />
          <div className="row">
            {books
              ?.filter((book) =>
                turkishToLower(book.name)
                  .trim()
                  .includes(turkishToLower(search).trim())
              )
              .map((book) => {
                return (
                  <div
                    key={book.book_id}
                    className="col-12 col-sm-6 col-md-4 col-lg-3 col-xl-2"
                  >
                    <Link to={"/book/" + book.book_id} className="book">
                      <div className="card">
                        <div className="card-img-top mt-3">
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
