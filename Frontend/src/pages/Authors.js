import React, { useState, useEffect } from "react";
import { Link, useNavigate } from "react-router-dom";
import Navbar from "../components/Navbar";
export default function Authors() {
  const [authors, setAuthors] = useState([]);
  const [loggedIn, setLoggedIn] = useState(false);
  const [search, setSearch] = useState("");
  const navigate = useNavigate();
  useEffect(() => {
    setLoggedIn(window.localStorage.getItem("loggedIn"));
    if (!window.localStorage.getItem("loggedIn"))
      navigate("login", { replace: true });
    const axios = require("axios");
    axios
      .get("/api/author/")
      .then((response) => response.data)
      .then((data) => setAuthors(data));
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
            placeholder="Yazar ara"
            onChange={(e) => setSearch(e.target.value)}
          />
          <div className="row">
            {authors
              ?.filter((author) =>
                turkishToLower(author.name)
                  .trim()
                  .includes(turkishToLower(search).trim())
              )
              .map((author) => {
                return (
                  <div
                    key={author.author_id}
                    className="col-12 col-sm-6 col-md-4 col-lg-3 col-xl-2"
                  >
                    <Link to={"/author/" + author.author_id} className="author">
                      <div className="card">
                        <div className="card-img-top mt-3">
                          <img
                            alt="author_photo"
                            src={"/api/author/photo/" + author.author_id}
                            className="image"
                          />
                        </div>
                        <div className="card-body">
                          <h5 className="card-title">{author.name}</h5>
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
